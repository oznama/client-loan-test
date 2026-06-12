package com.oznama.ms_client.mapper;

import com.oznama.ms_client.ClientEntity;
import com.oznama.ms_client.dto.ClientDTO;
import com.oznama.ms_client.dto.ClientNewDTO;
import com.oznama.ms_client.exception.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientMapper {

    public ClientDTO toClientDTO(ClientNewDTO clientDTO) {
        log.debug("Mapping ClientNewDTO to ClientDTO {}", clientDTO);
        return new ClientDTO(
                null,
                clientDTO.name(),
                clientDTO.email(),
                clientDTO.edad(),
                clientDTO.clientType());
    }

    /**
     * Metodo para mapear objeto de ciente entity a DTO
     * @param clientEntity
     * @return clientTO
     */
    public ClientDTO toClientDTO(ClientEntity clientEntity) {
        log.debug("Mapping client {} of entity to ClientDTO", clientEntity.getId());
        return new ClientDTO(
                clientEntity.getId(),
                clientEntity.getName(),
                clientEntity.getEmail(),
                clientEntity.getEdad(),
                clientEntity.getType());
    }

    /**
     * Metodo para mapear objeto entity a DTO
     * @param clientDTO
     * @return clientEntity
     */
    public ClientEntity toClientEntity(ClientDTO clientDTO) {
        log.debug("Mapping client {} of DTO to Entity", clientDTO.id());
        return ClientEntity.builder()
                .id(clientDTO.id())
                .name(clientDTO.name())
                .email(clientDTO.email())
                .edad(clientDTO.edad())
                .type(clientDTO.clientType())
                .build();
    }
}
