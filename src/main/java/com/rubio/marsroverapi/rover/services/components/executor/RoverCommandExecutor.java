package com.rubio.marsroverapi.rover.services.components.executor;

import com.rubio.marsroverapi.rover.dto.request.CommandRequestDto;
import com.rubio.marsroverapi.rover.models.Rover;
import com.rubio.marsroverapi.rover.services.components.commands.CommandFactory;
import com.rubio.marsroverapi.rover.services.components.commands.RoverCommand;
import org.springframework.stereotype.Service;

/**
 * Ejecuta la secuencia de comandos enviada para el {@link Rover}.
 * <p>
 * Cada comando es interpretado mediante el {@link CommandFactory} y ejecutado
 * inmediatamente. Si durante la ejecución alguno de los comandos provoca
 * que el rover detecte un obstáculo, el proceso se detiene y no se ejecutan
 * los comandos restantes.
 */
@Service
public class RoverCommandExecutor {
    private final CommandFactory factory;

    public RoverCommandExecutor(CommandFactory factory) {
        this.factory = factory;
    }

    /**
     * Procesa la lista de comandos y los ejecuta uno por uno sobre el rover.
     * <p>
     * La ejecución se interrumpe en cuanto un comando indica que se ha
     * encontrado un obstáculo.
     *
     * @param rover        instancia del rover sobre el cual se ejecutarán los comandos
     * @param commandList  lista de comandos enviados por el usuario
     * @return <code>true</code> si durante la ejecución de algún comando el rover
     *         encontró un obstáculo;<br>
     *         <code>false</code> si completó todos los comandos sin incidentes
     */
    public boolean execute(Rover rover, CommandRequestDto commandList) {
        boolean isObstacleEncountered = false;

        for (String code : commandList.getCommandList()) {
            RoverCommand command = factory.getCommand(code);
            isObstacleEncountered = command.execute(rover);

            if (isObstacleEncountered) {
                break;
            }
        }

        return isObstacleEncountered;
    }

}