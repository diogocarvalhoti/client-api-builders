package io.platformbuilders.api.exception;

import io.platformbuilders.api.dto.ErrorDetailsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {

        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO(new Date(), "Recurso não encontrado", request.getDescription(true));
        return new ResponseEntity<>(errorDetailsDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNoContentException.class)
    public ResponseEntity<?> resourceNoContentExeption(ResourceNoContentException ex, WebRequest request) {

        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO(new Date(), "Não foram encontrados resultados para esta requisição.", request.getDescription(false));
        return new ResponseEntity<>(errorDetailsDTO, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetailsDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
