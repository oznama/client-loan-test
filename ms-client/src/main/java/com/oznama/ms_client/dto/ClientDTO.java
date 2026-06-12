package com.oznama.ms_client.dto;

import com.oznama.ms_client.constants.ClientType;

public record ClientDTO(
        String id,
        String name,
        String email,
        int edad,
        ClientType clientType
) {
}
