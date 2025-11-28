package com.rubio.marsroverapi.rover.initializer;

import com.rubio.marsroverapi.config.MapProperties;
import com.rubio.marsroverapi.rover.models.Rover;
import com.rubio.marsroverapi.rover.models.RoverDirectionEnum;
import com.rubio.marsroverapi.rover.repositories.RoverRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Inicializa la posición del rover dentro del mapa si aun no ha sido establecida.
 * <p>
 * La ubicación generada se persiste en la base de datos para luego ser
 * utilizada durante el funcionamiento de la aplicación.
 */
@Service
public class RoverInitializer {

    private final RoverRepository repository;
    private final MapProperties mapProperties;

    @Autowired
    public RoverInitializer(RoverRepository repository, MapProperties mapProperties) {
        this.repository = repository;
        this.mapProperties = mapProperties;
    }

    /**
     * Inicializa las coordenadas del rover al arrancar la aplicación.
     * <p>
     * Si no existe un rover registrado en la base de datos, se generan de forma
     * aleatoria sus coordenadas dentro de los límites del mapa, así como su
     * dirección inicial.
     * <p>
     * Posteriormente, la información generada es almacenada de manera persistente.
     */
    @PostConstruct
    public void initRover() {
        if (repository.count() == 0) {
            Random random = new Random();
            int posX = random.nextInt(mapProperties.getWidth());
            int posY = random.nextInt(mapProperties.getHeight());
            int directionIndex = random.nextInt(RoverDirectionEnum.values().length - 1);

            Rover rover = new Rover();
            rover.setPosX(posX);
            rover.setPosY(posY);
            rover.setDirection(RoverDirectionEnum.values()[directionIndex]);

            repository.save(rover);
        }
    }

}
