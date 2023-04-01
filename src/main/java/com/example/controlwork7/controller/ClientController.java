package com.example.controlwork7.controller;

import com.example.controlwork7.dto.ClientDto;
import com.example.controlwork7.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    @PostMapping
    public ResponseEntity<ClientDto> register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        if(!clientService.checkUserEmail(email)) {
            return new ResponseEntity<>(clientService.registerUser(name, email, password), HttpStatus.CREATED);
        }else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}