package com.rubio.marsroverapi.rover.services.components.commands;

import com.rubio.marsroverapi.rover.models.Rover;
import com.rubio.marsroverapi.rover.services.components.movement.RoverMover;

/**
 * Implementación de {@link RoverCommand} que representa el comando de avanzar.
 * <p>
 * Esta acción delega al {@link RoverMover} la lógica necesaria para mover
 * al rover hacia adelante en la dirección en la que se encuentra apuntando.
 * <p>
 * El metodo retorna <code>true</code> si durante el avance se detecta un obstáculo.
 */
public class MoveForwardCommand implements RoverCommand {
    private final RoverMover roverMover;

    public MoveForwardCommand(RoverMover roverMover) {
        this.roverMover = roverMover;
    }

    /**
     * Ejecuta el comando de avanzar sobre el rover recibido.
     * <p>
     * La operación es delegada al {@link RoverMover}, el cual determina si el
     * movimiento es válido y si existe un obstáculo en la trayectoria.
     *
     * @param rover instancia del rover sobre el cual se ejecutará el comando
     * @return <code>true</code> si se encuentra un obstáculo al intentar avanzar;
     *         <code>false</code> en caso contrario
     */
    @Override
    public boolean execute(Rover rover) {
        return roverMover.moveForward(rover);
    }

}
