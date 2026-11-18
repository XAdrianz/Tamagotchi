package org.uabc2109072.tamagotchi;

import java.util.Scanner;

public class TiendaTamagotchi {
    Scanner leerNum = new Scanner(System.in);
    public void abrirTienda(Tamagotchi tamagotchi) {
        int opc;
        do {
            System.out.println("=======Tienda Benavides======");
            System.out.println("Puntos: " + tamagotchi.puntos);
            System.out.println("[1] Comida: 10PTS");
            System.out.println("[2] Medicina: 20PTS");
            System.out.println("[3] Salir:");
            System.out.println("=============================");
            opc = leerNum.nextInt();
            if (opc == 1) {
                if (tamagotchi.puntos >= 10) {
                    System.out.println("Compra exitosa");
                    System.out.println("+1 Comida en tu inventario");
                    tamagotchi.alimentos++;
                    tamagotchi.puntos -= 10;
                } else {
                    System.out.println("No tienes puntos suficientes");
                }
            } else if (opc == 2) {
                if (tamagotchi.puntos >= 20) {
                    System.out.println("Compra exitosa");
                    System.out.println("+1 Medicina en tu inventario");
                    tamagotchi.medicinas++;
                    tamagotchi.puntos -= 20;
                } else {
                    System.out.println("No tienes puntos suficientes");
                }
            } else if (opc == 3) {
                System.out.println("Saliendo de la tienda...");
                ;
            }
        } while (opc != 3);
    }
}
