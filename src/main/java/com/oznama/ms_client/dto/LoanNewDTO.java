package com.oznama.ms_client.dto;

import com.oznama.ms_client.constants.LoanStatus;

import java.time.LocalDate;

public record LoanNewDTO(
        Double amount,
        String clientId,
        LocalDate date,
        LoanStatus status
) {
}
