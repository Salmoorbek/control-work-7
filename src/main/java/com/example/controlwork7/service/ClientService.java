package com.example.controlwork7.service;


import com.example.controlwork7.dao.ClientDao;
import com.example.controlwork7.dto.ClientDto;
import com.example.controlwork7.entity.Client;
import com.example.controlwork7.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientService {
    private final PasswordEncoder encoder;
    private final ClientDao clientDao;

    public ClientService(PasswordEncoder encoder, ClientDao clientDao) {
        this.encoder = encoder;
        this.clientDao = clientDao;
    }

    public ClientDto registerUser(String name, String email, String password) {
        var usr = Client.builder()
                .email(email)
                .name(name)
                .enabled(true)
                .password(encoder.encode(password))
                .role("USER")
                .build();
        clientDao.createNewUser(usr);

        return ClientDto.from(usr);
    }
    public boolean checkUserEmail(String email){
        return Objects.equals(clientDao.getUsersByEmail(email).getEmail(), email);
    }
}
