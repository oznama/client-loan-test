package com.oznama.ms_client.controller;

import com.oznama.ms_client.dto.*;
import com.oznama.ms_client.service.LoanService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("loan")
@AllArgsConstructor
public class LoanController {

    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAll() {
        log.info("Getting all loan actives");
        return  ResponseEntity.ok(loanService.findAllActives());
    }

    @PostMapping
    public ResponseEntity<LoanDTO> save(@Valid @RequestBody LoanNewDTO loanNewDTO) {
        log.info("Saving loan");
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.save(loanNewDTO));
    }

    @PutMapping
    public ResponseEntity<LoanUpdateStatusDTO> update(@Valid @RequestBody LoanUpdateStatusDTO loanUpdateStatusDTO) {
        log.info("Updating loan staus");
        loanService.updateStatus(loanUpdateStatusDTO);
        return ResponseEntity.ok(loanUpdateStatusDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        log.info("Deleting loan {}", id);
        loanService.delete(id);
        return ResponseEntity.ok(true);
    }
}
