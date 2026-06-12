package com.oznama.ms_client.dto;

import com.oznama.ms_client.constants.LoanStatus;

import java.time.LocalDate;
import java.util.UUID;

public record LoanDTO(
        String id,
        Double amount,
        String clientId,
        LocalDate date,
        LoanStatus status
) {
    public LoanDTO {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
