package com.oznama.ms_client.service.impl;

import com.oznama.ms_client.cache.ClientsCache;
import com.oznama.ms_client.constants.ClientType;
import com.oznama.ms_client.dto.ClientDTO;
import com.oznama.ms_client.dto.ClientNewDTO;
import com.oznama.ms_client.exception.ClientException;
import com.oznama.ms_client.mapper.ClientMapper;
import com.oznama.ms_client.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientMapper clientMapper;

    /**
     * Carga los clientes y crea una lista DTO
     * @return Lista DTO de clientes
     * @throws ClientException en caso de algun error al cargar los clientes
     */
    @Override
    public List<ClientDTO> findAll() throws ClientException {
        log.debug("Getting all clients");
        try {
            return ClientsCache.getClients().stream()
                    .map(clientMapper::toClientDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            String msgError = "Error to load clients";
            log.error(msgError, e);
            throw new ClientException(msgError);
        }
    }

    /**
     * Servicio para agregar un cliente a la lista
     * @param clientDTO con los datos agregar
     * @throws ClientException en caso de que el cliente no se agregue al cache
     */
    @Override
    public ClientDTO save(ClientNewDTO clientDTO) throws ClientException {
        log.debug("Creating client {}", clientDTO);
        ClientDTO newClientDTO = clientMapper.toClientDTO(clientDTO);
        if (!ClientsCache.add(clientMapper.toClientEntity(newClientDTO))) {
            String msgError = String.format("Client %s not created", newClientDTO.id());
            throw new ClientException(msgError);
        }
        if ( isClientVIP(clientDTO.clientType()) ) {
            log.info("Cliente VIP tiene descuento de prestamos");
        }
        return newClientDTO;
    }

    /**
     * Servicio para actualizar un cliente de la lista
     * @param clientDTO con los datos actualizar
     * @throws ClientException en caso de que el cliente no se actualice en el cache
     */
    @Override
    public void update(ClientDTO clientDTO) throws ClientException {
        log.debug("Updating client with id: {}", clientDTO.id());
        if (!ClientsCache.update(clientMapper.toClientEntity(clientDTO))) {
            String msgError = String.format("Client %s not updated", clientDTO.id());
            throw new ClientException(msgError);
        }
    }

    /**
     * Servicio para eliminar un cliente de la lista
     * @param id del cliente a eliminar
     * @throws ClientException en caso de que el cliente no se elimine en el cache
     */
    @Override
    public void delete(String id) throws ClientException {
        log.debug("Deleting client with id: {}", id);
        if (!ClientsCache.delete(id)) {
            String msgError = String.format("Client %s not deleted", id);
            throw new ClientException(msgError);
        }
    }

    /**
     * Metodo para validar que un valor sea Cliente VIP
     * @param obj valor a evaluar
     * @return si es VIP
     */
    private boolean isClientVIP(Object obj) {
        return obj instanceof ClientType clientType && clientType == ClientType.VIP;
    }
}
