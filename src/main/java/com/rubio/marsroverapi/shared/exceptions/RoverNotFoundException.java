package com.rubio.marsroverapi.shared.exceptions;

/**
 * Excepción personalizada que se lanza cuando no se encuentra un {@code Rover}.
 * <p>
 * Esta excepción es de tipo {@link RuntimeException} y puede ser utilizada
 * en cualquier parte del código donde se espere un rover que no exista.
 */
public class RoverNotFoundException extends RuntimeException {
    public RoverNotFoundException(String message) {
        super(message);
    }
}
