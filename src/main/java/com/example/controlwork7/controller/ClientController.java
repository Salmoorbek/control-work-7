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
        ClientDto registeredUser = clientService.registerUser(name, email, password);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

}