package com.oznama.ms_client.service;

import com.oznama.ms_client.dto.ClientDTO;
import com.oznama.ms_client.dto.ClientNewDTO;
import com.oznama.ms_client.exception.ClientException;

import java.util.List;

public interface ClientService {
    List<ClientDTO> findAll() throws ClientException;
    ClientDTO save(ClientNewDTO clientDTO) throws ClientException;
    void update(ClientDTO clientDTO) throws ClientException;
    void delete(String id) throws ClientException;
}
