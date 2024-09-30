package com.apis.gestiontareas.apigestiontareas.exception;

import com.apis.gestiontareas.apigestiontareas.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFound(EntityNotFoundException ex) {

        var status = HttpStatus.NOT_FOUND;
        var errorResponse = new ErrorResponseDTO(status, ex.getMessage(), ex.toString());
        return new ResponseEntity<>(errorResponse, status);

    }

    @ExceptionHandler(CustomDuplicateKeyException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomDuplicateKeyException(CustomDuplicateKeyException ex) {

        var status = HttpStatus.BAD_REQUEST;
        var errorResponse = new ErrorResponseDTO(status, ex.getMessage(), ex.toString());
        return new ResponseEntity<>(errorResponse, status);

    }


}
