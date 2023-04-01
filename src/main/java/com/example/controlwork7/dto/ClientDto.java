package com.example.controlwork7.dto;

import com.example.controlwork7.entity.Client;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class ClientDto {
    private String name;
    private String email;
    public static ClientDto from(Client client) {
        return builder()
                .name(client.getName())
                .email(client.getEmail())
                .build();
    }
}
