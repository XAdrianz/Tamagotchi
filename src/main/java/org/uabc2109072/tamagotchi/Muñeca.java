package org.uabc2109072.tamagotchi;

public class Muñeca extends Tamagotchi implements TamagotchiActions{

    public Muñeca(String nombre,int energia,int felicidad,int salud, int flojera,int Caza) {
        super(nombre, energia, hambre, felicidad, salud);
    }

    public Muñeca(){
        super("Añañin",100,100,100,100);

    }


    @Override
    public void comer(int alimento) {

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

    public void llorar(){}

}
