package com.oznama.ms_client.dto;

public record GenericResponse(
        int code,
        String message,
        Object data
) {
}
