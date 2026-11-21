package org.uabc2109072.tamagotchi;

import java.util.EnumMap;

public class Inventario {
    private int puntos;
    private int medicinas;
    private EnumMap<Alimento, Integer> alimentos;
    private EnumMap<Alimento, Integer> precios;
    private int precioMedicina = 20;

    public Inventario() {
        puntos = 50;
        medicinas = 1;
        alimentos = new EnumMap<>(Alimento.class);
        for (Alimento a : Alimento.values()) {
            alimentos.put(a, 2);
        }
        precios = new EnumMap<>(Alimento.class);
        precios.put(Alimento.CROQUETAS, 8);
        precios.put(Alimento.FRUTA, 10);
        precios.put(Alimento.DULCES, 6);
    }

    public int getPuntos() {
        return puntos;
    }

    public int getMedicinas() {
        return medicinas;
    }

    public int getAlimento(Alimento a) {
        return alimentos.get(a);
    }

    public int getPrecio(Alimento a) {
        return precios.get(a);
    }

    public int getPrecioMedicina() {
        return precioMedicina;
    }

    public void ganarPuntos(int cantidad) {
        puntos += cantidad;
    }

    private boolean gastar(int costo) {
        if (puntos < costo) return false;
        puntos -= costo;
        return true;
    }

    public boolean comprarAlimento(Alimento a) {
        if (!gastar(precios.get(a))) return false;
        alimentos.put(a, alimentos.get(a) + 1);
        return true;
    }

    public boolean comprarMedicina() {
        if (!gastar(precioMedicina)) return false;
        medicinas++;
        return true;
    }

    public boolean usarAlimento(Alimento a) {
        if (alimentos.get(a) <= 0) return false;
        alimentos.put(a, alimentos.get(a) - 1);
        return true;
    }

    public boolean usarMedicina() {
        if (medicinas <= 0) return false;
        medicinas--;
        return true;
    }

    public String mostrar(String moneda) {
        return moneda + ": " + puntos + "\n"
                + "Croquetas: " + alimentos.get(Alimento.CROQUETAS) + "\n"
                + "Fruta: " + alimentos.get(Alimento.FRUTA) + "\n"
                + "Dulces: " + alimentos.get(Alimento.DULCES) + "\n"
                + "Medicinas: " + medicinas;
    }

    public String mostrarTienda(String moneda) {
        return "TIENDA (tienes " + puntos + " " + moneda + ")\n"
                + "Croquetas: " + precios.get(Alimento.CROQUETAS) + " " + moneda + "\n"
                + "Fruta: " + precios.get(Alimento.FRUTA) + " " + moneda + "\n"
                + "Dulces: " + precios.get(Alimento.DULCES) + " " + moneda + "\n"
                + "Medicina: " + precioMedicina + " " + moneda;
    }
}