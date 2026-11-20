package org.uabc2109072.tamagotchi;

public interface TamagotchiActions {

    public String comer(Alimento alimento);
    public String jugar(Juego juego);
    public String dormir(int horasDeSuenio);
    public boolean morir();
    public String sanar(int medicina);


}