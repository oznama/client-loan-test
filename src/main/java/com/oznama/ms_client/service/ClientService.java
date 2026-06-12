package com.oznama.ms_client.service;

import com.oznama.ms_client.dto.ClientDTO;
import com.oznama.ms_client.dto.ClientNewDTO;
import com.oznama.ms_client.exception.CustomException;

import java.util.List;

public interface ClientService {
    List<ClientDTO> findAll() throws CustomException;
    ClientDTO save(ClientNewDTO clientDTO) throws CustomException;
    void update(ClientDTO clientDTO) throws CustomException;
    void delete(String id) throws CustomException;
}
