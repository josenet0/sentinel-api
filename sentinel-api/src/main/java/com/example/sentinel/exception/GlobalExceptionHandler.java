package com.example.sentinel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Avisa o Spring: "Se der erro em QUALQUER Controller, venha para cá"
public class GlobalExceptionHandler {

    // Avisa o Spring: "Se o erro for de Validação (@Valid), execute este método"
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarErrosDeValidacao(MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();

        // Pega todos os campos que falharam e coloca num "Dicionário" (Campo -> Mensagem)
        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            erros.put(erro.getField(), erro.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }
}