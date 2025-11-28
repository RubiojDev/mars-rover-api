package com.rubio.marsroverapi.shared.exceptions;

/**
 * Excepción personalizada que se lanza cuando un comando recibido es inválido.
 * <p>
 * Esta excepción es de tipo {@link RuntimeException} y se utiliza durante
 * la validación de los comandos enviados al rover.
 */
public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
