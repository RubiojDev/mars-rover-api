package com.rubio.marsroverapi.rover.services.components.commands;

import com.rubio.marsroverapi.rover.models.Rover;
import com.rubio.marsroverapi.rover.services.components.rotation.RoverRotator;

/**
 * Implementación de {@link RoverCommand} que representa el comando para rotar a la derecha.
 * <p>
 * Esta acción delega al {@link RoverRotator} la lógica necesaria para actualizar
 * la orientación del rover.
 * <p>
 * El valor retornado proviene del propio rotador y normalmente será
 * <code>false</code>, dado que la rotación no implica movimiento ni detección
 * de obstáculos.
 */
public class TurnRightCommand implements RoverCommand {
    private final RoverRotator roverRotator;

    public TurnRightCommand(RoverRotator roverRotator) {
        this.roverRotator = roverRotator;
    }

    /**
     * Ejecuta el comando de rotar a la derecha sobre el rover recibido.
     * <p>
     * La operación es delegada al {@link RoverRotator}, encargado de actualizar
     * la orientación del rover.
     *
     * @param rover instancia del rover sobre la cual se ejecutará el comando
     * @return valor retornado por el rotador, normalmente <code>false</code> al no
     *         existir desplazamiento ni posibilidad de detectar obstáculos
     */
    @Override
    public boolean execute(Rover rover) {
        return roverRotator.rotateRight(rover);
    }

}
