package org.uabc2109072.tamagotchi;

public class Muñeca extends Tamagotchi implements TamagotchiActions{

    private int nivelAburrimiento;
    private int vecesQueLloro;

    public Muñeca(String nombre,int energia,int hambre, int felicidad,int salud) {
        super(nombre, energia, hambre, felicidad, salud);
    }

    public Muñeca(String nombre){
        super(nombre,100,0,100,100);
    }

    public Muñeca(){
        this("Barbie");
    }

    @Override
    public String comer(Alimento alimento) {
        String bloqueo = noPuedeActuar();
        if (bloqueo != null) return bloqueo;
        int energiaGanada = 0;
        switch (alimento) {
            case CROQUETAS -> energiaGanada = 2;
            case FRUTA -> energiaGanada = 8;
            case DULCES -> energiaGanada = 12;
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
        if (energia < 10) return nombre + " esta muy cansada para jugar";
        int felicidadGanada = 0;
        int energiaPerdida = 0;
        switch (juego) {
            case PELOTA -> { felicidadGanada = 8; energiaPerdida = 5; }
            case CORRER -> { felicidadGanada = 12; energiaPerdida = 10; }
            case ESCONDIDAS -> { felicidadGanada = 20; energiaPerdida = 6; }
        }
        felicidad += felicidadGanada;
        energia -= energiaPerdida;
        nivelAburrimiento = 0;
        corregirLimites();
        return "!" + nombre + " y tu van a jugar a " + juego + "!";
    }

    public String llorar(){
        vecesQueLloro++;
        return nombre + " esta llorando...";
    }

    public String felicidad(){
        return nombre + " dice: Estoy muy feliz!";
    }

    public String aburrimiento(){
        nivelAburrimiento++;
        return nombre + " dice: Estoy aburrida...";
    }

    // la muñeca reacciona segun su estado de animo
    @Override
    public String comportamientoEspecifico() {
        return switch (getEstado()) {
            case MAL, FALLECIDO -> llorar();
            case NECESITA_ATENCION -> aburrimiento();
            case BIEN -> felicidad();
        };
    }

    @Override
    public String mostrarCaracteristicas() {
        return "Nivel de aburrimiento: " + nivelAburrimiento + "\n"
                + "Veces que ha llorado:  " + vecesQueLloro;
    }

}