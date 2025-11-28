package com.rubio.marsroverapi.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Propiedades del mapa cargadas desde el <code>application.properties</code>.
 * <p>
 * Se espera que el archivo de configuración defina:
 * <ul>
 *     <li><code>app.map.width</code>: ancho del mapa</li>
 *     <li><code>app.map.height</code>: alto del mapa</li>
 * </ul>
 */
@Getter
@Component
@ConfigurationProperties(prefix = "app.map")
public class MapProperties {

    private int width;
    private int height;

    public MapProperties(){}

    /**
     * Setter que recibe el ancho del mapa y
     * evita que se ingresen valores menores a 2
     * @param width Ancho del mapa
     */
    public void setWidth(int width) {
        if (width <= 1) {
            this.width = 2;
        } else {
            this.width = width;
        }
    }

    /**
     * Setter que recibe el alto del mapa y
     * evita que se ingresen valores menores a 2
     * @param height Alto del mapa
     */
    public void setHeight(int height) {
        if (height <= 1) {
            this.height = 2;
        } else {
            this.height = height;
        }
    }
}
