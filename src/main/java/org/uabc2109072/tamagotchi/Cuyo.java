package org.uabc2109072.tamagotchi;

public class Cuyo extends Tamagotchi implements TamagotchiActions{

    private int nivelCariñoso;
    private int nivelSociable;

    public Cuyo(String nombre,int energia,int hambre,int felicidad,int salud, int Cariñoso,int Sociable){
        super(nombre,energia,hambre,felicidad,salud);
        nivelCariñoso=Cariñoso;
        nivelSociable=Sociable;
    }

    public Cuyo(String nombre){
        super(nombre,100,0,100,100);
        nivelCariñoso=6;
        nivelSociable=7;
    }

    public Cuyo(){
        this("Raul");
    }

    @Override
    public String comer(Alimento alimento) {
        String bloqueo = noPuedeActuar();
        if (bloqueo != null) return bloqueo;
        int energiaGanada = 0;
        switch (alimento) {
            case CROQUETAS -> energiaGanada = 8;
            case FRUTA -> energiaGanada = 15;
            case DULCES -> energiaGanada = 2;
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
            case PELOTA -> { felicidadGanada = 10; energiaPerdida = 5; }
            case CORRER -> { felicidadGanada = 15; energiaPerdida = 12; }
            case ESCONDIDAS -> { felicidadGanada = 22; energiaPerdida = 8; }
        }
        felicidad += felicidadGanada;
        energia -= energiaPerdida;
        hambre += 5;
        corregirLimites();
        return "!" + nombre + " y tu van a jugar a " + juego + "!";
    }

    public String cuyCuy(){
        felicidad += 2;
        corregirLimites();
        return "CUY CUY";
    }

    public String roerMadera(){
        hambre -= 5;
        felicidad += 3;
        corregirLimites();
        return nombre + " esta royendo la madera";
    }

    @Override
    public String comportamientoEspecifico() {
        if (Math.random() < 0.5) return cuyCuy();
        return roerMadera();
    }

    @Override
    public String mostrarCaracteristicas() {
        return "Nivel de cariño:    " + nivelCariñoso + "/10\n"
                + "Nivel de sociable:  " + nivelSociable + "/10";
    }
}
