package viva_animais.adocoes_spring.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ===== TRATAMENTO DE VALIDAÇÃO EM @RequestBody =====
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String nomeDoAtributo = ((FieldError) error).getField();
            String mensagemDeErro = error.getDefaultMessage();
            erros.put(nomeDoAtributo, mensagemDeErro);
        });

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("timestamp", LocalDateTime.now());
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("mensagem", "Erro de validação nos dados enviados");
        resposta.put("erros", erros);

        return ResponseEntity.badRequest().body(resposta);
    }

    // ===== TRATAMENTO DE VALIDAÇÃO EM @PathVariable E @RequestParam =====
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(
            ConstraintViolationException ex) {

        Map<String, String> erros = new HashMap<>();
        
        ex.getConstraintViolations().forEach(violation -> {
            String nomeDoAtributo = violation.getPropertyPath().toString();
            String mensagemDeErro = violation.getMessage();
            erros.put(nomeDoAtributo, mensagemDeErro);
        });

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("timestamp", LocalDateTime.now());
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("mensagem", "Erro de validação nos parâmetros da requisição");
        resposta.put("erros", erros);

        return ResponseEntity.badRequest().body(resposta);
    }

    // ===== TRATAMENTO DE ERROS GERAIS =====
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("timestamp", LocalDateTime.now());
        resposta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        resposta.put("mensagem", "Erro interno no servidor");
        resposta.put("detalhes", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }
}