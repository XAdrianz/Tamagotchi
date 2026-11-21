package org.uabc2109072.tamagotchi;

public enum Juguete {
    PELOTA_GOMA("Pelota de goma", 10, 15),
    RATON_JUGUETE("Raton de juguete", 8, 12),
    RUEDA_EJERCICIO("Rueda de ejercicio", 12, 20),
    CUERDA_NUDO("Cuerda con nudo", 9, 14);

    private final String nombre;
    private final int bonusFelicidad;
    private final int precio;

    Juguete(String nombre, int bonusFelicidad, int precio) {
        this.nombre = nombre;
        this.bonusFelicidad = bonusFelicidad;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getBonusFelicidad() {
        return bonusFelicidad;
    }

    public int getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return nombre;
    }
}