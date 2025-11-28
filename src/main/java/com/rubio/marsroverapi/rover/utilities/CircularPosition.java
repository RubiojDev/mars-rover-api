package com.rubio.marsroverapi.rover.utilities;

/**
 * Utilidad para calcular posiciones circulares dentro del mapa, permitiendo que
 * los valores se desplacen de forma continua dentro de un rango determinado.
 * <p>
 * Implementa un comportamiento tipo "wrap-around", donde avanzar más allá del
 * borde del mapa reinicia la posición al inicio, y retroceder desde el inicio
 * lo envía al final.
 */
public class CircularPosition {
    /**
     * Avanza una posición dentro del rango especificado, aplicando desplazamiento circular.
     *
     * @param current posición actual.
     * @param length límite máximo del rango (tamaño del eje).
     * @return la siguiente posición circular.
     */
    public static int forward(int current, int length) {
        return (current + 1) % length;
    }

    /**
     * Retrocede una posición dentro del rango especificado, aplicando desplazamiento circular.
     *
     * @param current posición actual.
     * @param length límite máximo del rango (tamaño del eje).
     * @return la posición circular anterior.
     */
    public static int backward(int current, int length) {
        return (current - 1 + length) % length;
    }

}
