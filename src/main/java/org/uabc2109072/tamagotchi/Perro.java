package org.uabc2109072.tamagotchi;

public class Perro extends Tamagotchi implements TamagotchiActions{

    private int nivelImperactividad;
    private int nivelAgresividad;

    public Perro(String nombre,int energia,int hambre,int felicidad,int salud, int agresividad,int hiper){
        super(nombre,energia,hambre,felicidad,salud);
        nivelAgresividad=10;
        nivelImperactividad=10;
    }

    public Perro(){
        super("Miles",100,100,100,100);
        nivelImperactividad=3;
        nivelAgresividad=1;
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

    public void ladrar(){}

    public void perseguirCola(){}
}
