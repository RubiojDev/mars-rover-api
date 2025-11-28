package com.rubio.marsroverapi.obstacle.services;

import com.rubio.marsroverapi.obstacle.dto.ObstacleDto;
import com.rubio.marsroverapi.obstacle.mappers.ObstacleMapper;
import com.rubio.marsroverapi.obstacle.models.Obstacle;
import com.rubio.marsroverapi.obstacle.repositories.ObstacleRepository;
import com.rubio.marsroverapi.obstacle.validations.ObstacleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de {@link ObstacleService}.
 * <p>
 * Gestiona el manejo de los obstáculos dentro del mapa mediante consultas
 * a la base de datos, el uso de mapeadores y la aplicación de validaciones.
 */
@Service
public class ObstacleServiceImpl implements ObstacleService {
    private final ObstacleRepository repository;
    private final ObstacleMapper obstacleMapper;
    private final List<ObstacleValidation> validationList;

    @Autowired
    public ObstacleServiceImpl(ObstacleRepository repository, ObstacleMapper obstacleMapper,
                               List<ObstacleValidation> validationList) {

        this.repository = repository;
        this.obstacleMapper = obstacleMapper;
        this.validationList = validationList;
    }

    /**
     * Realiza una busqueda en la base de datos para obtener todos los obstaculos
     * y convertirlos a su representación DTO.
     * @return Lista de {@link ObstacleDto}
     */
    @Override
    public List<ObstacleDto> findAllObstacles() {
        List<Obstacle> obstacleList = repository.findAll();
        List<ObstacleDto> obstacleDtoList = new ArrayList<>();

        for (Obstacle obstacle : obstacleList) {
            obstacleDtoList.add(obstacleMapper.toDto(obstacle));
        }

        return obstacleDtoList;
    }

    /**
     * Crea un nuevo obstáculo con las coordenadas proporcionadas.
     * <p>
     * Antes de persistirlo, se aplican todas las validaciones registradas. Si las
     * coordenadas son válidas, el obstáculo es guardado en la base de datos y
     * convertido a su DTO correspondiente.
     *
     * @param posX coordenada del eje X del mapa
     * @param posY coordenada del eje Y del mapa
     * @return {@link ObstacleDto} del obstáculo creado
     */
    @Override
    public ObstacleDto createObstacle(int posX, int posY) {
        for (ObstacleValidation validation : validationList) {
            validation.isValid(posX, posY);
        }

        Obstacle obstacle = new Obstacle();
        obstacle.setPosX(posX);
        obstacle.setPosY(posY);
        repository.save(obstacle);

        return obstacleMapper.toDto(obstacle);
    }

    /**
     * Elimina todos los obstaculos existentes en la base de datos.
     * @return Mensaje confirmando la operacion: <code>"Deleted Successfully"</code>
     */
    @Override
    public String deleteAllObstacles() {
        repository.truncateTable();

        return "Deleted Successfully";
    }

}
