package org.uabc2109072.tamagotchi;

public class Cuyo extends Tamagotchi implements TamagotchiActions{

    private int nivelCariñoso;
    private int nivelSociable;

    public Cuyo(String nombre,int energia,int hambre,int felicidad,int salud, int Cariñoso,int Sociable){
        super(nombre,energia,hambre,felicidad,salud);
        nivelCariñoso=10;
        nivelSociable=10;
    }

    public Cuyo(){
        super("Añañin",100,100,100,100);
        nivelCariñoso=6;
        nivelSociable=7;
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

    public void cuyCuy(){}

    public void roerMadera(){}
}
