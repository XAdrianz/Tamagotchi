package org.uabc2109072.tamagotchi;

import java.util.ArrayList;

public class Coleccion {
    private ArrayList<Tamagotchi> mascotas;
    private ArrayList<Juguete> misJuguetes;

    public Coleccion() {
        this.mascotas = new ArrayList<>();
        this.misJuguetes = new ArrayList<>();
    }

    public void agregar(Tamagotchi t) {
        if (t != null && !mascotas.contains(t)) {
            mascotas.add(t);
        }
    }

    public ArrayList<Tamagotchi> getMascotas() {
        return mascotas;
    }

    public void agregarJuguete(Juguete juguete) {
        if (juguete != null) {
            misJuguetes.add(juguete);
        }
    }

    public ArrayList<Juguete> getMisJuguetes() {
        return misJuguetes;
    }

    public void pasarTiempo() {
        for (Tamagotchi t : mascotas) {
            t.pasarTiempo();
        }
    }

    public String listaJuguetes() {
        if (misJuguetes.isEmpty()) {
            return "Aun no tienes juguetes en tu coleccion.";
        }
        StringBuilder sb = new StringBuilder("Mis juguetes:\n");
        for (int i = 0; i < misJuguetes.size(); i++) {
            Juguete j = misJuguetes.get(i);
            sb.append(i + 1).append(". ").append(j.getNombre())
                    .append(" (+").append(j.getBonusFelicidad()).append(" felicidad)\n");
        }
        return sb.toString().trim();
    }
}