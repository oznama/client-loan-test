package com.oznama.ms_client.service;

import com.oznama.ms_client.constants.ClientType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoanServiceTest {
    @Autowired
    private LoanService loanService;

    @Test
    void calculcateRateTest() {
        double amount = 50000;
        ClientType clientType = ClientType.VIP;
        double totalAmount = loanService.getTotalAmount(clientType, amount);
        assertEquals(totalAmount, 52500);
    }
}
