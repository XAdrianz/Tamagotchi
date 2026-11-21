package org.uabc2109072.tamagotchi;

public class Gato extends Tamagotchi implements TamagotchiActions{

    private int nivelFlojera;
    private int nivelCazador;
    private boolean ronroneando=false;

    public Gato(String nombre,int energia,int hambre,int felicidad,int salud, int flojera,int Caza){
        super(nombre,energia,hambre,felicidad,salud);
        nivelFlojera=flojera;
        nivelCazador=Caza;
    }

    public Gato(String nombre){
        super(nombre,100,0,100,100);
        nivelFlojera=6;
        nivelCazador=7;
    }

    public Gato(){
        this("Añañin");
    }

    @Override
    public String comer(Alimento alimento) {
        String bloqueo = noPuedeActuar();
        if (bloqueo != null) return bloqueo;
        int energiaGanada = 0;
        switch (alimento) {
            case CROQUETAS -> energiaGanada = 10;
            case FRUTA -> energiaGanada = 3;
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
        switch (juego) {
            case PELOTA -> { felicidadGanada = 15; energiaPerdida = 8; }
            case CORRER -> { felicidadGanada = 10; energiaPerdida = 15; }
            case ESCONDIDAS -> { felicidadGanada = 25; energiaPerdida = 10; }
        }
        felicidad += felicidadGanada;
        energia -= energiaPerdida;
        hambre += 5;
        corregirLimites();
        return "!" + nombre + " y tu van a jugar a " + juego + "!";
    }

    // forma particular de dormir del gato
    @Override
    public String dormir(int horasDeSuenio) {
        boolean estabaDurmiendo = durmiendo;
        String mensaje = super.dormir(horasDeSuenio);
        if (!estabaDurmiendo && durmiendo) {
            mensaje += " (se hace bolita y duerme sobre tus pies)";
        }
        return mensaje;
    }

    public String maullar(){
        return "MEOW MEOW";
    }

    public String ronronear(){
        ronroneando = !ronroneando;
        if (ronroneando) {
            felicidad += 3;
            corregirLimites();
            return nombre + " esta ronroneando";
        }
        return nombre + " dejo de ronronear";
    }

    @Override
    public String comportamientoEspecifico() {
        if (Math.random() < 0.5) return maullar();
        return ronronear();
    }

    @Override
    public String mostrarCaracteristicas() {
        return "Nivel de flojera:  " + nivelFlojera + "/10\n"
                + "Nivel de cazador:  " + nivelCazador + "/10\n"
                + "Ronronea: " + (ronroneando ? "Si" : "No");
    }
}