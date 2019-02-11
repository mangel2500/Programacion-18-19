
package hundir.la.flota;

import java.util.ArrayList;
import java.util.Scanner;

public class Tableros {

    public ArrayList<ArrayList<String>> tablero = new ArrayList();
    public ArrayList<ArrayList<String>> tableroPropio = new ArrayList();
    public ArrayList<ArrayList<String>> tableroContringante = new ArrayList();

    //CONSTRUCTOR POR DEFECTO
    public Tableros() {
    }

    //SETTERS
    public void setTableroContringante(ArrayList<ArrayList<String>> tableroContringante) {
        this.tableroContringante = tableroContringante;
    }

    public void setTablero(ArrayList<ArrayList<String>> tablero) {
        this.tablero = tablero;
    }

    public void setTableroPropio(ArrayList<ArrayList<String>> tableroPropio) {
        this.tableroPropio = tableroPropio;
    }

    //GETTERS
    public ArrayList<ArrayList<String>> getTablero() {
        return tablero;
    }

    public ArrayList<ArrayList<String>> getTableroPropio() {
        return tableroPropio;
    }

    public ArrayList<ArrayList<String>> getTableroContringante() {
        return tableroContringante;
    }

    //METODOS
    public int solicitarCrearTablero() {//METODO PARA SOLCITAR EL TAMAÑO DEL TABLERO
        Scanner tamañoTablero = new Scanner(System.in);
        System.out.print("¿Cual quieres que sea el tamaño del tablero(minimo 5)? ");
        int tamaño = tamañoTablero.nextInt();//PEDIMOS EL TAMAÑO DEL TABLERO
        while (tamaño < 5) {
            System.out.print("Error,el numero tiene que ser superior a 5: ");
            tamaño = tamañoTablero.nextInt();
        }
        return tamaño;
    }
    

    public void crearTablero(int tamaño) {//METODO PARA CREAR EL TABLERO
        int ancho = tamaño;
        int anchoNumeros = tamaño;
        System.out.println("");
        tablero.add(new ArrayList<>());//CREAMOS EL ARRAY VACIO DE LA SEGUNDA DIMENSION PARA LOS NUMEROS
        tableroPropio.add(new ArrayList<>());
        tableroContringante.add(new ArrayList<>());
        int numeroAncho = 1;
        tablero.get(0).add("   ");
        tableroPropio.get(0).add("   ");
        tableroContringante.get(0).add("   ");
        while (anchoNumeros != 0) {
            tablero.get(0).add(" " + numeroAncho + " ");
            tableroPropio.get(0).add(" " + numeroAncho + " ");
            tableroContringante.get(0).add(" " + numeroAncho + " ");
            numeroAncho++;
            anchoNumeros--;
        }
        int numeroAlto = 1;
        int contador = 1;
        while (tamaño != 0) {
            int ancho2 = ancho;
            tablero.add(new ArrayList());//VOLVEMOS A CREAR UN ARRAY VACIO
            tableroPropio.add(new ArrayList());
            tableroContringante.add(new ArrayList());
            tablero.get(contador).add(" " + numeroAlto + " ");
            tableroPropio.get(contador).add(" " + numeroAlto + " ");
            tableroContringante.get(contador).add(" " + numeroAlto + " ");
            while (ancho2 != 0) {
                tablero.get(contador).add("| |");
                tableroPropio.get(contador).add("| |");
                tableroContringante.get(contador).add("| |");
                ancho2--;
            }
            numeroAlto++;
            contador++;
            tamaño--;
        }
    }

    public void introducirDatos() {//METODO INTRODUCIR LOS BARCOS EN EL TABLERO
        int ancho = this.tablero.size();
        System.out.println("");
        int numeroBarcosJug = ancho - 2;//NUMERO DE BARCOS NECESARIOS
        int tamañoTableroUtil = ancho - 1;
        this.mostrarTablero();
        while (numeroBarcosJug > 1) {
            System.out.print("Coloco al barco de tamaño " + numeroBarcosJug + ":");//PEDIMOS EL BARCO QUE VAMOS A COLOCAR Y LE DECIMOS SU TAMAÑO
            boolean correcto = false;
            while (correcto == false) {
                Scanner pedirPosicion = new Scanner(System.in);
                System.out.print("Numero de la posición Vertical(↕):");
                int posicionVertical = pedirPosicion.nextInt();
                while (posicionVertical < 1 || posicionVertical > ancho - 1) {//COMROBAMOS QUE LA POSICION VERTICAL NO SOBREPASA EL TAMAÑO DEL TABLERO
                    System.out.println("Error,vuelve a introducir la posición Vertical(↕):");
                    posicionVertical = pedirPosicion.nextInt();
                }
                System.out.print("Numero de la posición Horizontal(←→):");
                int posicionHorizontal = pedirPosicion.nextInt();
                while (posicionHorizontal < 1 || posicionHorizontal > ancho - 1) {//COMROBAMOS QUE LA POSICION HORIZONTAL NO SOBREPASA EL TAMAÑO DEL TABLERO
                    System.out.print("Error,vuelve a introducir la posición Horizontal(←→):");
                    posicionHorizontal = pedirPosicion.nextInt();
                }
                String primeraposion = Integer.toString(numeroBarcosJug);
                int barcorestante = numeroBarcosJug;
                Scanner pedirDireccion = new Scanner(System.in);
                System.out.print("En que direccion quieres colocar el barco(↑=AR,↓=AB,→=DE,←=IZ):");//PEDIMOS EN QUE DIRECCION VA A POSICIONAR EL BARCO
                String direccion = pedirDireccion.nextLine();
                Boolean dirrecionCorrecta = false;
                while (dirrecionCorrecta == false) {
                    dirrecionCorrecta = true;
                    if (null == direccion) {
                        System.out.print("Error.Vulve a introducir la direccion(↑=AR,↓=AB,→=DE,←=IZ):");
                        direccion = pedirDireccion.nextLine();
                        dirrecionCorrecta = false;
                    } else {
                        switch (direccion) {
                            case "AR"://EN CASO DE QUE HAYA SELECCIONADO HACIA LA ARRIBA
                                if (posicionVertical - (numeroBarcosJug - 1) > 0) {//COMPROBAMOS QUE EL BARCO CABE
                                    this.tablero.get(posicionVertical).set(posicionHorizontal, "|" + primeraposion + "|");//COLOCAMOS LA PRIMERA POSICION DEL BARCO
                                    this.tableroPropio.get(posicionVertical).set(posicionHorizontal, "|#|");
                                    while (barcorestante > 1) {
                                        posicionVertical--;
                                        this.tablero.get(posicionVertical).set(posicionHorizontal, "|" + primeraposion + "|");
                                        this.tableroPropio.get(posicionVertical).set(posicionHorizontal, "|#|");
                                        barcorestante--;
                                    }
                                    System.out.println("");
                                    this.mostrarTablero();
                                } else {
                                    System.out.println("Error,no se puede colocar en esta posicion por que el barco es demasiado grande.");
                                    numeroBarcosJug++;
                                }
                                break;
                            case "AB"://EN CASO DE QUE HAYA SELECCIONADO HACIA ABAJO
                                if ((posicionVertical + (numeroBarcosJug-1)) <= tamañoTableroUtil) {//COMPROBAMOS QUE EL BARCO CABE
                                    this.tablero.get(posicionVertical).set(posicionHorizontal, "|" + primeraposion + "|");//COLOCAMOS LA PRIMERA POSICION DEL BARCO
                                    this.tableroPropio.get(posicionVertical).set(posicionHorizontal, "|#|");
                                    while (barcorestante > 1) {
                                        posicionVertical++;
                                        this.tablero.get(posicionVertical).set(posicionHorizontal, "|" + primeraposion + "|");
                                        this.tableroPropio.get(posicionVertical).set(posicionHorizontal, "|#|");
                                        barcorestante--;
                                    }
                                    System.out.println("");
                                    this.mostrarTablero();
                                } else {
                                    System.out.println("Error,no se puede colocar en esta posicion por que el barco es demasiado grande.");
                                    numeroBarcosJug++;
                                }
                                break;
                            case "DE"://EN CASO DE QUE HAYA SELECCIONADO HACIA LA DERECHA
                                if ((posicionHorizontal + (numeroBarcosJug-1)) <= tamañoTableroUtil) {//COMPROBAMOS QUE EL BARCO CABE
                                    this.tablero.get(posicionVertical).set(posicionHorizontal, "|" + primeraposion + "|");//COLOCAMOS LA PRIMERA POSICION DEL BARCO
                                    this.tableroPropio.get(posicionVertical).set(posicionHorizontal, "|#|");
                                    while (barcorestante > 1) {
                                        posicionHorizontal++;
                                        this.tablero.get(posicionVertical).set(posicionHorizontal, "|" + primeraposion + "|");
                                        this.tableroPropio.get(posicionVertical).set(posicionHorizontal, "|#|");
                                        barcorestante--;
                                    }
                                    System.out.println("");
                                    this.mostrarTablero();
                                } else {
                                    System.out.println("Error,no se puede colocar en esta posicion por que el barco es demasiado grande.");
                                    numeroBarcosJug++;
                                }
                                break;
                            case "IZ"://EN CASO DE QUE HAYA SELECCIONADO HACIA LA IZQUIERDA
                                if (posicionHorizontal - (numeroBarcosJug - 1) > 0) {//COMPROBAMOS QUE EL BARCO CABE
                                    this.tablero.get(posicionVertical).set(posicionHorizontal, "|" + primeraposion + "|");//COLOCAMOS LA PRIMERA POSICION DEL BARCO
                                    this.tableroPropio.get(posicionVertical).set(posicionHorizontal, "|#|");
                                    while (barcorestante > 1) {
                                        posicionHorizontal--;
                                        this.tablero.get(posicionVertical).set(posicionHorizontal, "|" + primeraposion + "|");
                                        this.tableroPropio.get(posicionVertical).set(posicionHorizontal, "|#|");
                                        barcorestante--;
                                    }
                                    System.out.println("");
                                    this.mostrarTablero();
                                } else {
                                    System.out.println("Error,no se puede colocar en esta posicion por que el barco es demasiado grande.");
                                    numeroBarcosJug++;
                                }
                                break;
                            default:
                                System.out.println("Error.Vulve a introducir la direccion(↑=AR,↓=AB,→=DE,←=IZ):");
                                direccion = pedirDireccion.nextLine();
                                dirrecionCorrecta = false;
                                System.out.println("");
                                this.mostrarTablero();
                                break;
                        }
                    }
                }
                numeroBarcosJug--;
                correcto = true;
            }
        }
    }

    public void mostrarTablero() {//METODO MOSTRAR EL TABLERO
        for (int i = 0; i < tablero.size(); i++) {//DIBUJAMOS EL TABLERO
            System.out.println(tablero.get(i));
        }
        System.out.println("");
    }

    public void mostrarTableroPropio() {//METODO MOSTRAR EL TABLERO PROPIO
        for (int i = 0; i < tableroPropio.size(); i++) {//DIBUJAMOS EL TABLERO
            System.out.println(tableroPropio.get(i));
        }
        System.out.println("");
    }

    public void mostrarTableroContringante() {//METODO MOSTRAR EL TABLERO DEL CONTRINGANTE
        for (int i = 0; i < tableroContringante.size(); i++) {//DIBUJAMOS EL TABLERO
            System.out.println(tableroContringante.get(i));
        }
        System.out.println("");
    }

}
