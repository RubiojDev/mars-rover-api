package com.rubio.marsroverapi.rover.services.components.commands;

import com.rubio.marsroverapi.rover.services.components.movement.RoverMover;
import com.rubio.marsroverapi.rover.services.components.rotation.RoverRotator;
import com.rubio.marsroverapi.shared.exceptions.InvalidCommandException;
import org.springframework.stereotype.Component;

/**
 * Factory encargada de obtener la instancia concreta de {@link RoverCommand}
 * correspondiente al código del comando recibido.
 * <p>
 * A partir de un código que representa una acción del rover, esta clase valida
 * el comando y retorna el objeto que ejecutará dicha acción.
 * <p>
 * Si el código proporcionado no corresponde a un comando válido,
 * se lanzará una excepción personalizada {@link InvalidCommandException}.
 */
@Component
public class CommandFactory {
    private final RoverMover roverMover;
    private final RoverRotator roverRotator;

    public CommandFactory(RoverMover roverMover, RoverRotator roverRotator) {
        this.roverMover = roverMover;
        this.roverRotator = roverRotator;
    }

    /**
     * Obtiene el comando correspondiente al código ingresado.
     * <p>
     * Si el código es válido, se devolverá la instancia concreta de {@link RoverCommand}
     * encargada de ejecutar la acción solicitada. En caso contrario,
     * se lanzará una excepción {@link InvalidCommandException}.
     *
     * @param code código del comando a ejecutar
     * @return una instancia de {@link RoverCommand} lista para ser ejecutada
     * @throws InvalidCommandException si el código proporcionado no es válido
     */
    public RoverCommand getCommand(String code) {
        return switch (code) {
            case "M" -> new MoveForwardCommand(roverMover);
            case "L" -> new TurnLeftCommand(roverRotator);
            case "R" -> new TurnRightCommand(roverRotator);
            default -> throw new InvalidCommandException("Invalid command: " + code);
        };
    }

}
