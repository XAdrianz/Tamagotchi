package org.uabc2109072.tamagotchi;

import java.util.Scanner;

public class TiendaTamagotchi {
    Scanner leerNum = new Scanner(System.in);
    public void abrirTienda(Tamagotchi tamagotchi) {
        int opc;
        do {
            System.out.print("=======Tienda Benavides======\n");
            System.out.print("Puntos: " + tamagotchi.puntos);
            System.out.print("[1] Comida: 10PTS\n");
            System.out.print("[2] Medicina: 20PTS\n");
            System.out.print("[3] Salir:\n");
            System.out.print("=============================\n");
            opc = leerNum.nextInt();
            if (opc == 1) {
                if (tamagotchi.puntos >= 10) {
                    System.out.print("Compra exitosa\n");
                    System.out.print("+1 Comida en tu inventario\n");
                    tamagotchi.alimentos++;
                    tamagotchi.puntos -= 10;
                } else {
                    System.out.print("No tienes puntos suficientes\n");
                }
            } else if (opc == 2) {
                if (tamagotchi.puntos >= 20) {
                    System.out.print("Compra exitosa\n");
                    System.out.print("+1 Medicina en tu inventario\n");
                    tamagotchi.medicinas++;
                    tamagotchi.puntos -= 20;
                } else {
                    System.out.print("No tienes puntos suficientes\n");
                }
            } else if (opc == 3) {
                System.out.print("Saliendo de la tienda...\n");
                ;
            }
        } while (opc != 3);
    }
}
