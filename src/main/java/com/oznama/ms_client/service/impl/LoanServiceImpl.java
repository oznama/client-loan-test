package com.oznama.ms_client.service.impl;

import com.oznama.ms_client.cache.EntityCache;
import com.oznama.ms_client.constants.ClientType;
import com.oznama.ms_client.dto.LoanDTO;
import com.oznama.ms_client.dto.LoanNewDTO;
import com.oznama.ms_client.dto.LoanUpdateStatusDTO;
import com.oznama.ms_client.exception.CustomException;
import com.oznama.ms_client.mapper.LoanMapper;
import com.oznama.ms_client.model.ClientEntity;
import com.oznama.ms_client.service.LoanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoanMapper  loanMapper;

    /**
     * Carga todos los prestamos activos
     * @return Lista de prestamos activos
     * @throws CustomException en caso de un error
     */
    @Override
    public List<LoanDTO> findAllActives() throws CustomException {
        log.debug("Finding all active loans.");
        return EntityCache.getLoans().stream()
                .map(loanMapper::toClientDTO)
                .collect(Collectors.toList());
    }

    /**
     * Creando prestamo
     * @param loanNewDTO con los datos
     * @return Prestamo con id aleatorio
     * @throws CustomException en caso de que no ser creado
     */
    @Override
    public LoanDTO save(LoanNewDTO loanNewDTO) throws CustomException {
        log.debug("Save loan request.");
        ClientEntity client = EntityCache.getClients().stream()
                .filter(c -> c.getId().equals(loanNewDTO.clientId()))
                .findFirst()
                .orElseThrow(() -> new CustomException("Client not valid"));
        LoanDTO newLoanDTO = loanMapper.toClientDTO(loanNewDTO, getTotalAmount(client.getType(), loanNewDTO.amount()));
        if (!EntityCache.add(loanMapper.toLoanEntity(newLoanDTO))) {
            throw new CustomException(("Error creating loan"));
        }
        log.debug("Save loan response.");
        return newLoanDTO;
    }


    /**
     * Servicio para actualizar estatus de prestamo
     * @param loanUpdateStatusDTO
     * @throws CustomException
     */
    @Override
    public void updateStatus(LoanUpdateStatusDTO loanUpdateStatusDTO) throws CustomException {
        log.debug("Update loan status request.");
        if (!EntityCache.updateLoanStatus(loanUpdateStatusDTO.id(), loanUpdateStatusDTO.status())) {
            throw new CustomException(("Error updating loan estatus"));
        }
    }

    /**
     * Servicio para eliminar un prestamo
     * @param id
     * @throws CustomException
     */
    @Override
    public void delete(String id) throws CustomException {
        log.debug("Deleting loan request.");
        if (!EntityCache.delete(id)) {
            throw new CustomException(("Error deleting loan"));
        }
        log.debug("Deleted loan response.");
    }

    /**
     * Servicio pawra calcular el interes del monto dependiendo el cliente
     * @param obj Tipo de cliente
     * @param amount del prestamo
     * @return monto del prestamo mas porcentaje de interes
     */
    @Override
    public double getTotalAmount(Object obj, Double amount) {
        log.debug("Calculating rate of {} for client {}", amount, obj);
        double rate = (obj instanceof ClientType clientType && clientType == ClientType.VIP) ? 5 : 10;
        double rateDivided = rate / 100;
        double interestRate = amount * rateDivided;
        double totalAmount = amount + interestRate;
        log.debug("Interest: {}, total amount: {}", interestRate, totalAmount);
        return totalAmount;
    }
}
