package org.uabc2109072.tamagotchi;
import java.util.Scanner;

public class Launcher {

    static Scanner leer = new Scanner(System.in);
    static Coleccion coleccion = new Coleccion();
    static Inventario inventario = new Inventario();

    // pide un numero entre 1 y max
    static int pedirOpcion(int max) {
        int n = leer.nextInt();
        leer.nextLine();
        while (n < 1 || n > max) {
            System.out.println("Opcion invalida");
            n = leer.nextInt();
            leer.nextLine();
        }
        return n;
    }

    static void tienda(String moneda) {
        System.out.println(inventario.mostrarTienda(moneda));
        System.out.println("1.Croquetas 2.Fruta 3.Dulces 4.Medicina");
        int op = pedirOpcion(4);
        boolean exito;
        if (op == 4) {
            exito = inventario.comprarMedicina();
        } else {
            exito = inventario.comprarAlimento(Alimento.values()[op - 1]);
        }
        System.out.println(exito ? "Compra hecha" : "No te alcanza");
    }

    public static void main(String[] args) {
        System.out.println("Que mascota quieres? 1.Perro 2.Gato 3.Cuyo 4.Erizo");
        int tipo = pedirOpcion(4);
        System.out.println("Nombre de tu mascota:");
        String nombre = leer.nextLine();

        Tamagotchi mascota = switch (tipo) {
            case 1 -> new Perro(nombre);
            case 2 -> new Gato(nombre);
            case 3 -> new Cuyo(nombre);
            default -> new Erizo(nombre);
        };
        coleccion.agregar(mascota);

        int opcion = -1;
        while (opcion != 0 && mascota.estaVivo()) {
            System.out.println("\n" + mascota.mostrarEstado());
            System.out.println("\n" + inventario.mostrar(mascota.nombreMoneda()));
            System.out.println("\n1.Alimentar 2.Jugar 3.Dormir/Despertar 4.Sanar 5.Accion especial 6.Tienda 7.Mis juguetes 0.Salir");
            opcion = leer.nextInt();
            leer.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("1.Croquetas 2.Fruta 3.Dulces");
                    int a = pedirOpcion(3);
                    Alimento alimento = Alimento.values()[a - 1];
                    if (!mascota.estaVivo() || mascota.estaDurmiendo()) {
                        System.out.println(mascota.comer(alimento));
                    } else if (inventario.usarAlimento(alimento)) {
                        System.out.println(mascota.comer(alimento));
                    } else {
                        System.out.println("No te queda " + alimento);
                    }
                }
                case 2 -> {
                    System.out.println("1.Pelota 2.Correr 3.Escondidas");
                    int j = pedirOpcion(3);
                    System.out.println(mascota.jugar(Juego.values()[j - 1]));
                    int ganado = mascota.cobrarRecompensa();
                    if (ganado > 0) {
                        inventario.ganarPuntos(ganado);
                        System.out.println("+" + ganado + " " + mascota.nombreMoneda());
                    }
                }
                case 3 -> {
                    if (mascota.estaDurmiendo()) System.out.println(mascota.despertar());
                    else System.out.println(mascota.dormir(4));
                }
                case 4 -> {
                    if (!mascota.estaVivo()) {
                        System.out.println(mascota.getNombre() + " ha fallecido");
                    } else if (inventario.usarMedicina()) {
                        System.out.println(mascota.sanar(20));
                    } else {
                        System.out.println("No te quedan medicinas");
                    }
                }
                case 5 -> {
                    if (mascota.estaVivo() && !mascota.estaDurmiendo()) {
                        System.out.println(mascota.comportamientoEspecifico());
                    } else {
                        System.out.println(mascota.getNombre() + " no puede hacerlo ahora");
                    }
                }
                case 6 -> tienda(mascota.nombreMoneda());
                case 7 -> System.out.println(coleccion.listaJuguetes());
            }
            coleccion.pasarTiempo();
        }
        if (!mascota.estaVivo()) {
            System.out.println(mascota.getNombre() + " ha fallecido...");
        }
    }
}