package com.oznama.ms_client.service;

import com.oznama.ms_client.constants.ClientType;
import com.oznama.ms_client.dto.ClientDTO;
import com.oznama.ms_client.dto.ClientNewDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    void createNewClient() {
        ClientNewDTO clientNewDTO = new ClientNewDTO("Oziel", "oznama27@gmail.com", 39, ClientType.VIP);
        ClientDTO clientDTO = clientService.save(clientNewDTO);
        assertNotNull(clientDTO.id());
    }
}
