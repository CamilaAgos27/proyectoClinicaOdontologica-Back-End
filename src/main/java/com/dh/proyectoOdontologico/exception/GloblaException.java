package com.dh.proyectoOdontologico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

    public class GloblaException {
        @ExceptionHandler({ResourceNotFoundException.class})
        public ResponseEntity<String> procesarResourceNotFoundException (ResourceNotFoundException rnfe){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
        @ExceptionHandler({BadRequestException.class})
        public ResponseEntity<String> procesarBadRequestException(BadRequestException bre){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bre.getMessage());
        }
    }
