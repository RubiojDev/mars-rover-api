package com.rubio.marsroverapi.rover.services;

import com.rubio.marsroverapi.rover.dto.RoverDto;
import com.rubio.marsroverapi.rover.dto.request.CommandRequestDto;
import com.rubio.marsroverapi.rover.dto.response.CommandResponseDto;
import com.rubio.marsroverapi.rover.mappers.CommandResponseMapper;
import com.rubio.marsroverapi.rover.mappers.RoverMapper;
import com.rubio.marsroverapi.rover.models.Rover;
import com.rubio.marsroverapi.rover.repositories.RoverRepository;
import com.rubio.marsroverapi.rover.services.components.executor.RoverCommandExecutor;
import com.rubio.marsroverapi.shared.exceptions.RoverNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementación de {@link RoverService}.
 * <p>
 * Obtiene el estado actual del rover mediante consultas a la base de datos
 * y gestiona la ejecución de los comandos que se le envíen.
 */
@Service
public class RoverServiceImpl implements RoverService {
    private final RoverRepository repository;
    private final RoverMapper roverMapper;
    private final CommandResponseMapper commandResponseMapper;
    private final RoverCommandExecutor roverCommandExecutor;

    @Autowired
    public RoverServiceImpl(RoverRepository repository, RoverMapper roverMapper,
                            CommandResponseMapper commandResponseMapper,
                            RoverCommandExecutor roverCommandExecutor) {

        this.repository = repository;
        this.roverMapper = roverMapper;
        this.commandResponseMapper = commandResponseMapper;
        this.roverCommandExecutor = roverCommandExecutor;
    }

    /**
     * Obtiene el estado actual del rover mediante una consulta a la base de datos.
     * <p>
     * Si el rover no se encuentra registrado, se lanzará una excepción personalizada
     * {@link RoverNotFoundException}.
     *
     * @return un {@link RoverDto} que representa la posición y dirección actuales del rover
     * @throws RoverNotFoundException si no existe un rover persistido con el identificador esperado
     */
    @Override
    public RoverDto findRover() {
        Optional<Rover> rover = repository.findById(1);

        if (rover.isPresent()) {
            return roverMapper.toDto(rover.get());
        }

        throw new RoverNotFoundException(
                "Rover not found"
        );
    }

    /**
     * Procesa y ejecuta la lista de comandos proporcionada.
     * <p>
     * Primero verifica la existencia del rover. En caso de no existir,
     * se lanzará una excepción {@link RoverNotFoundException}. Si existe,
     * los comandos serán ejecutados mediante el {@link RoverCommandExecutor}.
     * <p>
     * Luego, se persiste el nuevo estado del rover y finalmente se construye
     * la respuesta mediante el {@link CommandResponseMapper}.
     *
     * @param commandList lista de comandos que el rover debe ejecutar
     * @return un {@link CommandResponseDto} con el estado final del rover y la información
     *         sobre si se encontró algún obstáculo durante el recorrido
     * @throws RoverNotFoundException si no existe un rover registrado
     */
    @Override
    public CommandResponseDto setCommand(CommandRequestDto commandList) {
        Rover rover = repository.findById(1)
                .orElseThrow(() -> new RoverNotFoundException("Rover not found")
        );

        boolean obstacleEncountered = roverCommandExecutor.execute(rover, commandList);
        repository.save(rover);

        CommandResponseDto responseDto;
        responseDto = commandResponseMapper.toDto(rover, obstacleEncountered);

        return responseDto;
    }

}
