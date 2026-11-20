package org.uabc2109072.tamagotchi;

import java.util.Scanner;

// Prueba en terminal (la GUI se inicia con HelloApplication)
public class Launcher {

    static Scanner leerNum = new Scanner(System.in);

    // pide un numero entre 1 y max
    static int pedirOpcion(int max) {
        int numero = leerNum.nextInt();
        leerNum.nextLine();
        while (numero < 1 || numero > max) {
            System.out.println("Opcion invalida, intenta de nuevo");
            numero = leerNum.nextInt();
            leerNum.nextLine();
        }
        return numero;
    }

    public static void main(String[] args) {
        System.out.println("Que mascota quieres? 1.Perro 2.Gato 3.Cuyo 4.Muñeca");
        int tipo = pedirOpcion(4);
        System.out.println("Nombre de tu mascota:");
        String nombre = leerNum.nextLine();

        Tamagotchi mascota = switch (tipo) {
            case 1 -> new Perro(nombre);
            case 2 -> new Gato(nombre);
            case 3 -> new Cuyo(nombre);
            default -> new Muñeca(nombre);
        };

        int opcion = -1;
        while (opcion != 0 && mascota.estaVivo()) {
            System.out.println("\n" + mascota.mostrarEstado());
            System.out.println("\n1.Alimentar 2.Jugar 3.Dormir/Despertar 4.Sanar 5.Accion especial 0.Salir");
            opcion = leerNum.nextInt();
            leerNum.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("1.Croquetas 2.Fruta 3.Dulces");
                    int a = pedirOpcion(3);
                    System.out.println(mascota.comer(Alimento.values()[a - 1]));
                }
                case 2 -> {
                    System.out.println("1.Pelota 2.Correr 3.Escondidas");
                    int j = pedirOpcion(3);
                    System.out.println(mascota.jugar(Juego.values()[j - 1]));
                }
                case 3 -> {
                    if (mascota.estaDurmiendo()) System.out.println(mascota.despertar());
                    else System.out.println(mascota.dormir(4));
                }
                case 4 -> System.out.println(mascota.sanar(20));
                case 5 -> System.out.println(mascota.comportamientoEspecifico());
            }
            mascota.pasarTiempo();
        }
        if (!mascota.estaVivo()) {
            System.out.println(mascota.getNombre() + " ha fallecido😢");
        }
    }
}
