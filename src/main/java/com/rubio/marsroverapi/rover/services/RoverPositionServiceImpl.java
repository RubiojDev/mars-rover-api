package com.rubio.marsroverapi.rover.services;

import com.rubio.marsroverapi.rover.repositories.RoverRepository;
import org.springframework.stereotype.Service;

/**
 * Implementación del {@link RoverPositionService}.
 * <p>
 * Verifica si el rover está en las coordenadas dadas mediante una consulta en la base de datos.
 */
@Service
public class RoverPositionServiceImpl implements RoverPositionService {
    private final RoverRepository repository;

    public RoverPositionServiceImpl(RoverRepository repository) {
        this.repository = repository;
    }

    /**
     * Determina si el rover se encuentra en las coordenadas dadas.
     * <p>
     * Utiliza el repositorio para consultar la existencia del rover
     * en las coordenadas dadas.
     * @param posX Coordenada del eje X en el mapa
     * @param posY Coordenada del eje Y en el mapa
     * @return <code>true</code> o <code>false</code> si el rover se encuentra en las coordenadas dadas.
     */
    @Override
    public boolean isRoverAt(int posX, int posY) {
        return repository.existsByPosXAndPosY(posX, posY);
    }

}
