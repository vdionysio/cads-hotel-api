package br.ifs.edu.cads.api.hotel.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    // ID Não encontrado (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> handleResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                e.getMessage(),
                request.getRequestURI(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    // Validação dos Objetos marcados com @Valid (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<StandardError.ValidationError> errors = e.getBindingResult().getFieldErrors().stream()
                .map(field -> new StandardError.ValidationError(field.getField(), field.getDefaultMessage()))
                .toList();

        StandardError err = new StandardError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Validação",
                "Dados inválidos",
                request.getRequestURI(),
                errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    // Violação de constraints do banco (409)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> handleDatabaseException(DataIntegrityViolationException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                "Erro de Banco de Dados",
                determinaMensagemPorConstraint(e.getMessage()),
                request.getRequestURI(),
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    private String determinaMensagemPorConstraint(String msgBruto) {
        if (msgBruto == null) return "Erro de integridade de dados.";

        String msg = msgBruto.toLowerCase();

        // define mensagem a partir da constraint violada
        if (msg.contains("uk_estado_nome")) {
            return "Já existe um estado cadastrado com este Nome.";
        } else if (msg.contains("uk_estado_uf")) {
            return "Já existe um estado cadastrado com esta UF.";
        }

        return "A operação viola uma regra de integridade do banco de dados.";
    }
}
