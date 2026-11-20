package org.uabc2109072.tamagotchi;

public class Perro extends Tamagotchi implements TamagotchiActions{

    private int nivelImperactividad;
    private int nivelAgresividad;
    private int nivelLadridos;

    public Perro(String nombre,int energia,int hambre,int felicidad,int salud, int agresividad,int hiper){
        super(nombre,energia,hambre,felicidad,salud);
        nivelAgresividad=agresividad;
        nivelImperactividad=hiper;
    }

    public Perro(String nombre){
        super(nombre,100,0,100,100);
        nivelImperactividad=3;
        nivelAgresividad=1;
    }

    public Perro(){
        this("Miles");
    }

    @Override
    public String comer(Alimento alimento) {
        String bloqueo = noPuedeActuar();
        if (bloqueo != null) return bloqueo;
        int energiaGanada = 0;
        switch (alimento) {
            case CROQUETAS -> energiaGanada = 15;
            case FRUTA -> energiaGanada = 5;
            case DULCES -> energiaGanada = 8;
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
        switch (juego) {
            case PELOTA -> { felicidadGanada = 20; energiaPerdida = 10; }
            case CORRER -> { felicidadGanada = 25; energiaPerdida = 20; }
            case ESCONDIDAS -> { felicidadGanada = 10; energiaPerdida = 5; }
        }
        felicidad += felicidadGanada;
        energia -= energiaPerdida;
        hambre += 5;
        corregirLimites();
        return "!" + nombre + " y tu van a jugar a " + juego + "!";
    }

    public String ladrar(){
        nivelLadridos++;
        return nombre + " dice: Guau guau!";
    }

    public String perseguirCola(){
        energia -= 5;
        felicidad += 5;
        corregirLimites();
        return nombre + " esta persiguiendo su cola";
    }

    @Override
    public String comportamientoEspecifico() {
        if (Math.random() < 0.5) return ladrar();
        return perseguirCola();
    }

    @Override
    public String mostrarCaracteristicas() {
        return "Nivel de hiperactividad: " + nivelImperactividad + "/10\n"
                + "Nivel de agresividad:    " + nivelAgresividad + "/10\n"
                + "Nivel de ladridos:       " + nivelLadridos;
    }
}