package com.oznama.ms_client.exception;

import com.oznama.ms_client.dto.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalControlAdvice {

    /**
     * Handler para excepcion personalizada ClientException
     * @param e Exception
     * @return Response Entity con mensaje personalizado de excepcion
     */
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<GenericResponse> handleMessageNotReadableException(ClientException e) {
        log.error("Catching message client exception");
        return ResponseEntity.badRequest().body(new GenericResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null));
    }

    /**
     * Handler para validaciones
     * @param e Exception
     * @return Response Entity con un mapa de los errores de validacion
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> handleValidationException(MethodArgumentNotValidException e) {
        log.error("Catching method argument not valid exception");
        Map<String, String> errors = e.getBindingResult().getAllErrors().stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResponseEntity.badRequest().body(new GenericResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", errors));
    }

    /**
     * Handler para request con formato invalido
     * @param e Exception
     * @return Response Entity con mensaje de excepcion
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GenericResponse> handleMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("Catching message not readable exception");
        return ResponseEntity.badRequest().body(new GenericResponse(HttpStatus.BAD_REQUEST.value(), "Peticion invalida", null));
    }

    /**
     * Handler para cachar cualquier excepcion
     * @param e Exception
     * @return Response Entity para errores internos
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> handleException(Exception e) {
        log.error("Catching exception");
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ha ocurrido un error inesperado", null));
    }
}
