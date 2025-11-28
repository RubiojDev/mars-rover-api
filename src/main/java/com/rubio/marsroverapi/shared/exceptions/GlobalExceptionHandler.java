package com.rubio.marsroverapi.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Maneja de forma centralizada todas las excepciones de la API REST.
 * <p>
 * Cada excepción lanzada dentro de los controladores del módulo Rover
 * será capturada y transformada en un {@link ResponseEntity} con un
 * mensaje descriptivo y un código HTTP apropiado.
 * <p>
 * La estructura del objeto enviado sera: {@code {message: "descripcion del error"}}.
 * <p>
 * Esto permite que la API devuelva respuestas consistentes y legibles
 * para los consumidores del servicio.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de tipo {@link ObstacleCollisionException}.
     *
     * @param ex la excepción lanzada.
     * @return un {@link ResponseEntity} con mensaje de error y HTTP 404.
     */
    @ExceptionHandler(ObstacleCollisionException.class)
    public ResponseEntity<Map<String, String>> handleObstacleCollision(ObstacleCollisionException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja excepciones de tipo {@link InvalidCommandException}.
     *
     * @param ex la excepción lanzada.
     * @return un {@link ResponseEntity} con mensaje de error y HTTP 400.
     */
    @ExceptionHandler(InvalidCommandException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCommand(InvalidCommandException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja excepciones de tipo {@link RoverNotFoundException}.
     *
     * @param ex la excepción lanzada.
     * @return un {@link ResponseEntity} con mensaje de error y HTTP 404.
     */
    @ExceptionHandler(RoverNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoverNotFound(RoverNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja cualquier excepción general no capturada específicamente.
     *
     * @param ex la excepción lanzada.
     * @return un {@link ResponseEntity} con mensaje de error genérico y HTTP 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", "Unexpected error: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Maneja errores de validación de argumentos de métodos.
     *
     * @param ex la excepción lanzada.
     * @return un {@link ResponseEntity} con los mensajes de error concatenados y HTTP 400.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> error = new HashMap<>();

        String fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        error.put("message", fieldErrors);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
