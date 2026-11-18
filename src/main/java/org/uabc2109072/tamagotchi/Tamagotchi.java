package org.uabc2109072.tamagotchi;


public class Tamagotchi {
    protected String nombre;
    protected int salud;
    protected int energia;
    protected int felicidad;
    protected int hambre;

    public Tamagotchi(String nombre,int energia,int hambre,int felicidad,int salud){
        this.energia=energia;
        this.hambre=hambre;
        this.felicidad=felicidad;
        this.nombre=nombre;
        this.salud=salud;
    }

    public Tamagotchi(){
        this.energia=100;
        this.hambre=100;
        this.felicidad=100;
        this.nombre="Miles";
        this.salud=100;
    }
}
