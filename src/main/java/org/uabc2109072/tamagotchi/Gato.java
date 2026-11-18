package org.uabc2109072.tamagotchi;

public class Gato extends Tamagotchi implements TamagotchiActions{

    private int nivelFlojera;
    private int nivelCazador;

    public Gato(String nombre,int energia,int hambre,int felicidad,int salud, int flojera,int Caza){
        super(nombre,energia,hambre,felicidad,salud);
        nivelFlojera=10;
        nivelCazador=10;
    }

    public Gato(){
        super("Añañin",100,100,100,100);
        nivelFlojera=6;
        nivelCazador=7;
    }

    @Override
    public void comer(int alimento) {
        System.out.println("Estoy comiendo "+alimento);
        alimento--;
        hambre++;
        energia++;
    }

    @Override
    public int jugar() {
        System.out.println("!"+nombre+" y tu van a jugar!");
        energia--;
        return felicidad++;
    }

    @Override
    public int dormir(int horasDeSuenio) {
        System.out.println(nombre+" dormira una siesta...");
        hambre--;
        return energia++;
    }

    @Override
    public boolean morir() {
        System.out.print(nombre+" Ha fallecido😢");
        return false;
    }

    @Override
    public int sanar(int medicina) {
        System.out.println("Has sanado a "+nombre);
        return salud++;
    }

    public void maullar(){}

    public void ronronear(){}
}
