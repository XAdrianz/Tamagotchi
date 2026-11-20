package org.uabc2109072.tamagotchi;

public enum Juego {
    PELOTA("Pelota"),
    CORRER("Correr"),
    ESCONDIDAS("Escondidas");

    private final String nombre;

    Juego(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}