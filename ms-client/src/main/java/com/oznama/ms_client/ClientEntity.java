package com.oznama.ms_client;

import com.oznama.ms_client.constants.ClientType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ClientEntity {
    private String id;
    private String name;
    private String email;
    private int edad;
    private ClientType type;
}
