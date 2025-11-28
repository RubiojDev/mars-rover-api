package com.rubio.marsroverapi.rover.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa al rover dentro del mapa.
 * <p>
 * Contiene la información necesaria para identificar su posición
 * en los ejes X e Y del mapa, asi como la dirección hacia donde apunta.
 * <p>
 * Atributos:
 * <ul>
 *     <li><code>id</code>: identificador unico del rover.</li>
 *     <li><code>posX</code>: posición del eje X en el mapa.</li>
 *     <li><code>posY</code>: posición en el eje Y en el mapa.</li>
 *     <li><code>direction</code>: dirección hacia donde apunta el rover.</li>
 * </ul>
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rover")
public class Rover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer posX;
    private Integer posY;
    @Enumerated(EnumType.STRING)
    private RoverDirectionEnum direction;
}
