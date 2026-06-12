package com.oznama.ms_client.controller;

import com.oznama.ms_client.dto.ClientDTO;
import com.oznama.ms_client.dto.ClientNewDTO;
import com.oznama.ms_client.service.ClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("client")
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAll() {
        log.info("Getting all clients");
        return  ResponseEntity.ok(clientService.findAll());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientNewDTO clientDTO) {
        log.info("Saving client {}", clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientDTO));
    }

    @PutMapping
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO clientDTO) {
        log.info("Updating client {}", clientDTO);
        clientService.update(clientDTO);
        return ResponseEntity.ok(clientDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        log.info("Deleting client {}", id);
        clientService.delete(id);
        return ResponseEntity.ok(true);
    }
}
