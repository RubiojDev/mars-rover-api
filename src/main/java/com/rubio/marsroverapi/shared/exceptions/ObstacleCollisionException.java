package com.rubio.marsroverapi.shared.exceptions;

/**
 * Excepción personalizada que se lanza cuando no es posible crear un {@code Obstaculo}
 * debido a un conflicto de posición o colisión.
 * <p>
 * Esta excepción es de tipo {@link RuntimeException} y puede ser utilizada
 * en cualquier parte del código donde se espere un error al crear un obstaculo.
 */
public class ObstacleCollisionException extends RuntimeException {
    public ObstacleCollisionException(String message) {
        super(message);
    }
}
