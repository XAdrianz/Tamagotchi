package org.uabc2109072.tamagotchi;

public enum Alimento {
    CROQUETAS("Croquetas"),
    FRUTA("Fruta"),
    DULCES("Dulces");

    private final String nombre;

    Alimento(String nombre) {
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
