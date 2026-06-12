package com.oznama.ms_client.model;

import com.oznama.ms_client.constants.LoanStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class LoanEntity {
    private String id;
    private Double amount;
    private String clientId;
    private LocalDate date;
    private LoanStatus status;
}
