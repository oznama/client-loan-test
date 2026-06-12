package com.oznama.ms_client.service;

import com.oznama.ms_client.dto.LoanDTO;
import com.oznama.ms_client.dto.LoanNewDTO;
import com.oznama.ms_client.dto.LoanUpdateStatusDTO;
import com.oznama.ms_client.exception.CustomException;

import java.util.List;

public interface LoanService {
    List<LoanDTO> findAllActives() throws CustomException;
    LoanDTO save(LoanNewDTO loanNewDTO) throws CustomException;
    void updateStatus(LoanUpdateStatusDTO loanUpdateStatusDTO) throws CustomException;
    void delete(String id) throws CustomException;
    double getTotalAmount(Object obj, Double amount);
}
