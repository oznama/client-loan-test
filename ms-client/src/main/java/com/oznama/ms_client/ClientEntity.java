package com.oznama.ms_client;

import com.oznama.ms_client.constants.ClientType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClientEntity {
    private String id;
    private String name;
    private String email;
    private ClientType type;
}
