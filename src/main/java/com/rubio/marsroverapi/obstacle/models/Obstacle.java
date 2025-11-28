package com.rubio.marsroverapi.obstacle.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un obstáculo dentro del mapa.
 * <p>
 * Contiene la información necesaria para identificar su posición
 * en el eje X y el eje Y del mapa.
 * <p>
 * Atributos:
 * <ul>
 *     <li><code>id</code>: identificador único del obstáculo.</li>
 *     <li><code>posX</code>: posición en el eje X.</li>
 *     <li><code>posY</code>: posición en el eje Y.</li>
 * </ul>
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "obstacle")
public class Obstacle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer posX;
    private Integer posY;
}
