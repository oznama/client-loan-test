package com.oznama.ms_client.mapper;

import com.oznama.ms_client.dto.ClientDTO;
import com.oznama.ms_client.dto.ClientNewDTO;
import com.oznama.ms_client.dto.LoanDTO;
import com.oznama.ms_client.dto.LoanNewDTO;
import com.oznama.ms_client.model.ClientEntity;
import com.oznama.ms_client.model.LoanEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoanMapper {

    /**
     * Metodo para mapear nuevo prestamo a prestamo DTO
     * @param loanNewDTO a mapear
     * @param amountWithRate monto con intereses
     * @return LoanDTO
     */
    public LoanDTO toClientDTO(LoanNewDTO loanNewDTO, Double amountWithRate) {
        log.debug("Mapping loanNewDTO to LoanDTO {}", loanNewDTO);
        return new LoanDTO(
                null,
                amountWithRate,
                loanNewDTO.clientId(),
                loanNewDTO.date(),
                loanNewDTO.status());
    }

    /**
     * Metodo para mapear objeto de prestamo entity a DTO
     * @param loanEntity
     * @return loanDTO
     */
    public LoanDTO toClientDTO(LoanEntity loanEntity) {
        log.debug("Mapping loan {} of entity to LoanDTO", loanEntity.getId());
        return new LoanDTO(
                loanEntity.getId(),
                loanEntity.getAmount(),
                loanEntity.getClientId(),
                loanEntity.getDate(),
                loanEntity.getStatus());
    }

    /**
     * Metodo para mapear objeto entity a DTO
     * @param loanDTO
     * @return loanEntity
     */
    public LoanEntity toLoanEntity(LoanDTO loanDTO) {
        log.debug("Mapping loan {} of DTO to Entity", loanDTO.id());
        return LoanEntity.builder()
                .id(loanDTO.id())
                .amount(loanDTO.amount())
                .clientId(loanDTO.clientId())
                .date(loanDTO.date())
                .status(loanDTO.status())
                .build();
    }
}
