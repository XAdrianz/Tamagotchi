package org.uabc2109072.tamagotchi;

public class Erizo extends Tamagotchi implements TamagotchiActions {

    private int nivelOlfato;
    private int nivelSentidoDelPeligro;
    private int anillosTotales;
    private boolean enBolita = false;

    public Erizo(String nombre, int energia, int hambre, int felicidad, int salud, int olfato, int peligro) {
        super(nombre, energia, hambre, felicidad, salud);
        nivelOlfato = olfato;
        nivelSentidoDelPeligro = peligro;
    }

    public Erizo(String nombre) {
        super(nombre, 100, 0, 100, 100);
        nivelOlfato = 6;
        nivelSentidoDelPeligro = 8;
    }

    public Erizo() {
        this("Sonic");
    }

    @Override
    public String nombreMoneda() {
        return "anillos";
    }

    @Override
    public String comer(Alimento alimento) {
        String bloqueo = noPuedeActuar();
        if (bloqueo != null) return bloqueo;
        int energiaGanada = 0;
        switch (alimento) {
            case CROQUETAS -> energiaGanada = 8;
            case FRUTA -> energiaGanada = 12;
            case DULCES -> energiaGanada = 5;
        }
        hambre -= 20;
        energia += energiaGanada;
        felicidad += 3;
        corregirLimites();
        return "Estoy comiendo " + alimento + " (+" + energiaGanada + " energia)";
    }

    @Override
    public String jugar(Juego juego) {
        String bloqueo = noPuedeActuar();
        if (bloqueo != null) return bloqueo;
        if (energia < 10) return nombre + " esta muy cansado para jugar";
        int felicidadGanada = 0;
        int energiaPerdida = 0;
        int anillos = 0;
        switch (juego) {
            case PELOTA -> { felicidadGanada = 12; energiaPerdida = 8; anillos = 3; }
            case CORRER -> { felicidadGanada = 25; energiaPerdida = 15; anillos = 6; }
            case ESCONDIDAS -> { felicidadGanada = 10; energiaPerdida = 5; anillos = 2; }
        }
        felicidad += felicidadGanada;
        energia -= energiaPerdida;
        hambre += 5;
        anillosTotales += anillos;
        ultimaRecompensa = anillos;
        corregirLimites();
        return "!" + nombre + " y tu van a jugar a " + juego + "!";
    }

    public String hacerseBolita() {
        enBolita = !enBolita;
        if (enBolita) {
            energia -= 3;
            corregirLimites();
            return nombre + " se hizo bolita";
        }
        felicidad += 2;
        corregirLimites();
        return nombre + " se desenrollo";
    }

    public String correrRapido() {
        if (energia < 10) return nombre + " esta muy cansado para correr";
        energia -= 12;
        hambre += 5;
        felicidad += 8;
        corregirLimites();
        return "*gruñidito*(debo ir rapido!)";
    }

    @Override
    public String comportamientoEspecifico() {
        if (Math.random() < 0.5) return hacerseBolita();
        return correrRapido();
    }

    @Override
    public String mostrarCaracteristicas() {
        return "Nivel de olfato:   " + nivelOlfato + "/10\n"
                + "Sentido peligro:   " + nivelSentidoDelPeligro + "/10\n"
                + "Anillos juntados:  " + anillosTotales + "\n"
                + "En bolita: " + (enBolita ? "Si" : "No");
    }
}