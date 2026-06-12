package com.oznama.ms_client.dto;

import com.oznama.ms_client.constants.LoanStatus;

public record LoanUpdateStatusDTO(
        String id,
        LoanStatus status
) {
}
