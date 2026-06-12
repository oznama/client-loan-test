package com.oznama.ms_client.exception;

public class ClientException extends RuntimeException {
    public ClientException(String msgError) {
        super(msgError);
    }
}
