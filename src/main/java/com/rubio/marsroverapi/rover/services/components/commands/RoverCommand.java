package com.rubio.marsroverapi.rover.services.components.commands;

import com.rubio.marsroverapi.rover.models.Rover;

/**
 * Representa un comando ejecutable sobre un {@link Rover}.
 * <p>
 * Cada implementación define una acción específica que el rover puede realizar,
 * como avanzar o rotar. El metodo retorna un valor booleano indicando si durante
 * la ejecución se encontró un obstáculo.
 */
public interface RoverCommand {
    boolean execute(Rover rover);
}
