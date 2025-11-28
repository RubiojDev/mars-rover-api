package com.rubio.marsroverapi.rover.services.components.rotation;

import com.rubio.marsroverapi.rover.models.Rover;
import com.rubio.marsroverapi.rover.models.RoverDirectionEnum;
import org.springframework.stereotype.Component;

import static com.rubio.marsroverapi.rover.utilities.CircularPosition.backward;
import static com.rubio.marsroverapi.rover.utilities.CircularPosition.forward;

/**
 * Componente encargado de actualizar la orientación del {@link Rover},
 * permitiéndole rotar hacia la izquierda o hacia la derecha.
 * <p>
 * Ambos métodos retornan <code>false</code> dado que la rotación no implica
 * desplazamiento y, por lo tanto, no existe posibilidad de colisión
 * con obstáculos.
 */
@Component
public class RoverRotator {
    /**
     * Rota el rover hacia la derecha.
     * <p>
     * Obtiene la dirección actual y utiliza el metodo {@code forward}
     * para calcular el siguiente valor dentro del conjunto de direcciones,
     * manteniendo un recorrido circular.
     *
     * @param rover instancia del rover cuya orientación será modificada
     * @return siempre <code>false</code>, ya que rotar no genera riesgo de colisiones
     */
    public boolean rotateRight(Rover rover) {
        int currentDirectionIndex = rover.getDirection().ordinal();
        int nextDirectionIndex = forward(currentDirectionIndex, RoverDirectionEnum.values().length);
        rover.setDirection(RoverDirectionEnum.values()[nextDirectionIndex]);
        return false;
    }

    /**
     * Rota el rover hacia la izquierda.
     * <p>
     * Obtiene la dirección actual y utiliza el metodo {@code backward}
     * para calcular la posición anterior dentro del conjunto de direcciones,
     * manteniendo un recorrido circular.
     *
     * @param rover instancia del rover cuya orientación será modificada
     * @return siempre <code>false</code>, ya que rotar no implica movimiento ni riesgo de colisiones
     */
    public boolean rotateLeft(Rover rover) {
        int currentDirectionIndex = rover.getDirection().ordinal();
        int nextDirectionIndex = backward(currentDirectionIndex, RoverDirectionEnum.values().length);
        rover.setDirection(RoverDirectionEnum.values()[nextDirectionIndex]);
        return false;
    }

}
