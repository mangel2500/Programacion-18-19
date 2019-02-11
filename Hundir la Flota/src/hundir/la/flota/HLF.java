package hundir.la.flota;

import java.util.Scanner;

public class HLF {

    public static void main(String[] args) {
        Tableros tableroJug1 = new Tableros();//CREAMOS EL TABLERO DEL JUGADOR 1
        Tableros tableroJug2 = new Tableros();//CREAMOS EL TABLERO DEL JUGADOR 2      
        int tamaño = tableroJug1.solicitarCrearTablero();
        tableroJug1.crearTablero(tamaño);
        tableroJug2.crearTablero(tamaño);
        int numeroBarcosJugador1 = tamaño - 2;
        int numeroBarcosJugador2 = tamaño - 2;
        Scanner pedirNombre = new Scanner(System.in);
        System.out.print("Dame el nombre del jugador 1: ");//PEDIMOS EL NICK AL JUGADOR 1
        String nickJugador1 = pedirNombre.nextLine();
        System.out.println("");
        System.out.println(nickJugador1 + " introduce los barcos");
        tableroJug1.introducirDatos();//INTRODUCIMOS LOS BARCOS DEL JUGADOR 1
        System.out.print("Dame el nombre del jugador 2: ");//PEDIMOS EL NICK AL JUGADOR 2
        String nickJugador2 = pedirNombre.nextLine();
        System.out.println("");
        System.out.println(nickJugador2 + " introduce los barcos");
        tableroJug2.introducirDatos();//INTRODUCIMOS LOS BARCOS DEL JUGADOR 2
        System.out.println("Ya tenemos los barcos asignados asi que vamos a empezar.");
        System.out.println("El jugador que consiga derribar antes los barcos del otro ganara.");
        System.out.println("");
        Boolean ganar = false;
        while (ganar != true) {//HASTA QUE UNO DE LOS JUGADORES NO HUNDA TODOS LOS BARCOS DEL CONTRARIO
            System.out.println("Turno de " + nickJugador1);
            Scanner principal = new Scanner(System.in);
            int opcion;
            do {
                System.out.println("");
                System.out.println("=======================================");
                System.out.println("=             TURNO JUGADOR 1         =");
                System.out.println("=======================================");
                System.out.println("=   1. Mostrar Tablero Contringantes  =");
                System.out.println("=   2. Mostrar Tablero                =");
                System.out.println("=   3. Introducir Tirada              =");
                System.out.println("=   4. Salir                          =");
                System.out.println("=======================================");
                System.out.print("Selecciona una opción: ");
                opcion = principal.nextInt();
                while (opcion > 4 || opcion < 1) {
                    System.out.print("Solo puedes escribir una de las 4 opciones:");
                    opcion = principal.nextInt();
                }
                System.out.println("");
                switch (opcion) {
                    case 1:
                        tableroJug1.mostrarTableroContringante();//MOSTRAMOS EL TABLERO DEL RIVAL
                        break;
                    case 2:
                        tableroJug1.mostrarTableroPropio();//MOSTRAMOS NUESTRO TABLERO
                        break;
                    case 3:
                        Scanner pedirTirada = new Scanner(System.in);
                        System.out.println("En que posicon vas a atacar:");
                        System.out.print("Numero Vertical(↕):");
                        int vertical = pedirTirada.nextInt();
                        System.out.print("Numero Horizontal(←→):");
                        int horizontal = pedirTirada.nextInt();
                        if (!"| |".equals(tableroJug2.tablero.get(vertical).get(horizontal))) {//COMPROBAMOS SI HA DADO A ALGUN BARCO
                            String barcoTocado = tableroJug2.tablero.get(vertical).get(horizontal);//UARDAMOS EL TAMAÑO DEL BARCO QUE HA SIDO ALCANZADO
                            tableroJug2.tableroContringante.get(vertical).set(horizontal, "|X|");
                            tableroJug2.tableroPropio.get(vertical).set(horizontal, "|X|");
                            tableroJug2.tablero.get(vertical).set(horizontal, "|X|");
                            int contadorBarco = 0;
                            for (int i = 0; i < tableroJug2.tablero.size(); i++) {
                                for (int a = 0; a < tableroJug2.tablero.get(i).size(); a++) {
                                    if (tableroJug2.tablero.get(i).get(a).equals(barcoTocado)) {
                                        contadorBarco++;
                                    }
                                }
                            }
                            if (contadorBarco == 0) {//COMPROBAMOS SI EL BARCO ESTA HUNDIDO
                                System.out.println("HAS HUNDIDO EL BARCO DE TAMAÑO" + barcoTocado);
                                numeroBarcosJugador2--;
                            } else {
                                System.out.println("TOCADO!!!");
                                System.out.println("El barco tocado tiene una longitud de " + barcoTocado);
                            }
                        } else {
                            System.out.println("AGUA!!!");
                            tableroJug2.tableroContringante.get(vertical).set(horizontal, "|0|");
                            tableroJug2.tableroPropio.get(vertical).set(horizontal, "|0|");
                        }
                        System.out.println("");
                        if (numeroBarcosJugador2 == 0) {//COMPROBAMOS QUE TODOS LOS BARCOS ESTAN HUNDIDOS
                            System.out.println("ENHORABUENA HAS GANADO");
                            System.out.println("");
                            System.out.println("Tablero Enemigo:");
                            tableroJug2.mostrarTableroContringante();
                            ganar = true;
                        }
                        break;
                }

            } while (opcion != 3 && opcion != 4);
            System.out.println("");
            System.out.println("Turno de " + nickJugador2);
            int opcion2;
            do {
                System.out.println("");
                System.out.println("=======================================");
                System.out.println("=             TURNO JUGADOR 2         =");
                System.out.println("=======================================");
                System.out.println("=   1. Mostrar Tablero Contringantes  =");
                System.out.println("=   2. Mostrar Tablero                =");
                System.out.println("=   3. Introducir Tirada              =");
                System.out.println("=   4. Salir                          =");
                System.out.println("=======================================");
                System.out.print("Selecciona una opción: ");
                opcion2 = principal.nextInt();
                while (opcion2 > 4 || opcion2 < 1) {
                    System.out.print("Solo puedes escribir una de las 4 opciones:");
                    opcion2 = principal.nextInt();
                }
                System.out.println("");
                switch (opcion2) {
                    case 1:
                        tableroJug2.mostrarTableroContringante();//MOSTRAMOS EL TABLERO DEL RIVAL
                        break;
                    case 2:
                        tableroJug2.mostrarTableroPropio();//MOSTRAMOS NUESTRO TABLERO
                        break;
                    case 3:
                        Scanner pedirTirada = new Scanner(System.in);
                        System.out.println("En que posicon vas a atacar:");
                        System.out.print("Numero Vertical(↕):");
                        int vertical = pedirTirada.nextInt();
                        System.out.print("Numero Horizontal(←→):");
                        int horizontal = pedirTirada.nextInt();
                        if (!"| |".equals(tableroJug1.tablero.get(vertical).get(horizontal))) {//COMPROBAMOS SI HA DADO A ALGUN BARCO
                            String barcoTocado = tableroJug1.tablero.get(vertical).get(horizontal);//GUARDAMOS EL TAMAÑO DEL BARCO QUE HA SIDO ALCANZADO
                            tableroJug1.tableroContringante.get(vertical).set(horizontal, "|X|");
                            tableroJug1.tableroPropio.get(vertical).set(horizontal, "|X|");
                            tableroJug1.tablero.get(vertical).set(horizontal, "|X|");
                            int contadorBarco = 0;
                            for (int i = 0; i < tableroJug1.tablero.size(); i++) {
                                for (int a = 0; a < tableroJug1.tablero.get(i).size(); a++) {
                                    if (tableroJug1.tablero.get(i).get(a).equals(barcoTocado)) {
                                        contadorBarco++;
                                    }
                                }
                            }
                            if (contadorBarco == 0) {//COMPROBAMOS SI EL BARCO ESTA HUNDIDO
                                System.out.println("HAS HUNDIDO EL BARCO DE TAMAÑO" + barcoTocado);
                                numeroBarcosJugador1--;
                            } else {
                                System.out.println("TOCADO!!!");
                                System.out.println("El barco tocado tiene una longitud de " + barcoTocado);
                            }
                        } else {
                            System.out.println("AGUA!!!");
                            tableroJug1.tableroContringante.get(vertical).set(horizontal, "|0|");
                            tableroJug1.tableroPropio.get(vertical).set(horizontal, "|0|");
                        }
                        System.out.println("");
                        if (numeroBarcosJugador1 == 0) {//COMPROBAMOS QUE TODOS LOS BARCOS ESTAN HUNDIDOS
                            System.out.println("ENHORABUENA HAS GANADO");
                            System.out.println("");
                            System.out.println("Tablero Enemigo:");
                            tableroJug1.mostrarTableroContringante();
                            ganar = true;
                        }
                        break;
                }
            } while (opcion2 != 3 && opcion2 != 4);
        }
    }
}
