import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// TODO PARTIDA PERSONALIZADA CARGANDO ARCHIVO, CODE CLEAN
public class Laberinto {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner teclado = new Scanner(System.in);

        // Arraylist donde se guardaran los resultados
        ArrayList<Register> resultados = new ArrayList<>();
        boolean playing = true;

        // Boolean que indica cuando termina la partida + variables para los resultados finales
        boolean gameEnd = false;
        int numeroMovimientos = 0;
        int cantidadPartidas = 0;

        /* MENU */
        while (playing) {
            String menu;
            do {
                System.out.println("Elige que quieres hacer : \n" + "1. Jugar \n" + "2. Resultado de partidas \n" + "3. Salir ");
                menu = teclado.next();
                System.out.println();
            } while (!(menu.equalsIgnoreCase("1") || menu.equalsIgnoreCase("2") || menu.equalsIgnoreCase("3")));

            String dificultad;
            switch (menu) {
                case "1":
                    do {
                        System.out.println("Escoge la dificultad que quieres jugar : \n" + "1. Facil \n" + "2. Intermedio \n" + "3. Dificil \n" + "4. Personalizado");
                        dificultad = teclado.next();
                        System.out.println();
                    } while (!(dificultad.equalsIgnoreCase("1") || dificultad.equalsIgnoreCase("2") || dificultad.equalsIgnoreCase("3") || dificultad.equalsIgnoreCase("4")));

                    //posiciones del jugador
                    int movFila = 0;
                    int movColumna = 0;
                    Level nivel = null;
                    String[][] laberinto = null;
                    // diferentes niveles del laberinto
                    switch (dificultad) {
                        case "1":
                            cantidadPartidas++;
                            System.out.println("Partida numero " + cantidadPartidas);
                            enunciadoPartida(Level.EASY);
                            nivel = Level.EASY;
                            laberinto = Level.EASY.getLaberinto();
                            numeroMovimientos = Level.EASY.getMovements();

                            // llamamos a la funcion para que guarde el laberinto en el fichero
                            ficheroLaberinto(laberinto);
                            break;
                        case "2":
                            cantidadPartidas++;
                            System.out.println("Partida numero : " + cantidadPartidas);
                            enunciadoPartida(Level.MEDIUM);
                            nivel = Level.MEDIUM;
                            laberinto = Level.MEDIUM.getLaberinto();
                            numeroMovimientos = Level.MEDIUM.getMovements();

                            // llamamos a la funcion para que guarde el laberinto en el fichero
                            ficheroLaberinto(laberinto);
                            break;
                        case "3":
                            cantidadPartidas++;
                            System.out.println("Partida numero : " + cantidadPartidas);
                            enunciadoPartida(Level.DIFFICULT);
                            nivel = Level.DIFFICULT;
                            laberinto = Level.DIFFICULT.getLaberinto();
                            numeroMovimientos = Level.DIFFICULT.getMovements();

                            // llamamos a la funcion para que guarde el laberinto en el fichero
                            ficheroLaberinto(laberinto);
                            break;

                        case "4":
                            cantidadPartidas++;
                            System.out.println("Partida numero : " + cantidadPartidas);

                        // TODO



                            break;
                    }

                    laberinto[movFila][movColumna] = Propiedades.JUGADOR;

                    // mostramos el laberinto en cada movimiento
                    boolean laberintoSalida = true;
                    String mover;
                    while (laberintoSalida) {
                        for (int i = 0; i < laberinto.length; i++) {
                            for (int c = 0; c < laberinto[i].length; c++) {
                                System.out.print(laberinto[i][c] + " ");
                            }
                            System.out.println();
                        }

                        // se le preguntara al usuario su movimiento mientras le queden movimientos por hacer.
                        if (numeroMovimientos != 0) {
                            System.out.println("Quedan " + numeroMovimientos + " movimientos restantes");
                            System.out.print("Mueve : ");
                            mover = teclado.next();
                            laberinto[movFila][movColumna] = Propiedades.PASO;

                            // Condiciones del jugador dentro del laberinto. Controlamos si se encuentra un muro
                            if (mover.equalsIgnoreCase("q")) {
                                laberintoSalida = false;
                                gameEnd = false;
                                System.out.println("Has dejado de jugar la partida actual.");
                            } else if (mover.equalsIgnoreCase("s") && movFila + 1 != laberinto.length && laberinto[movFila + 1][movColumna] != Propiedades.WALL) {
                                movFila += 1;
                                numeroMovimientos--;
                            } else if (mover.equalsIgnoreCase("w") && movFila - 1 != -1 && laberinto[movFila - 1][movColumna] != Propiedades.WALL) {
                                movFila -= 1;
                                numeroMovimientos--;
                            } else if (mover.equalsIgnoreCase("d") && movColumna + 1 != laberinto.length && laberinto[movFila][movColumna + 1] != Propiedades.WALL) {
                                movColumna += 1;
                                numeroMovimientos--;
                            } else if (mover.equalsIgnoreCase("a") && movColumna - 1 != -1 && laberinto[movFila][movColumna - 1] != Propiedades.WALL) {
                                movColumna -= 1;
                                numeroMovimientos--;
                            } else {
                                numeroMovimientos--;
                            }


                            // Regalo
                            int findNumber;
                            if (laberinto[movFila][movColumna] == (Propiedades.GIFT1)) {
                                findNumber = 1;
                                textoEncontrar(findNumber);
                            }
                            if (laberinto[movFila][movColumna] == (Propiedades.GIFT2)) {
                                findNumber = 6;
                                textoEncontrar(findNumber);
                            }

                            // en caso de que llegue a la puerta secreta
                            if (laberinto[movFila][movColumna] == (Propiedades.SECRET)) {
                                int doorCode;
                                do {
                                    System.out.println("Has llegado a la puerta secreta \n" + "Introduce el codigo");
                                    doorCode = teclado.nextInt();
                                    System.out.println();
                                    if (doorCode == 61) {
                                        System.out.println("Codigo correcto, puedes pasar");
                                    } else {
                                        System.out.println("Codigo incorrecto");
                                    }
                                } while (doorCode != 61);
                            }

                            // encuentra la salida
                            if ((laberinto[movFila][movColumna] == (Propiedades.SALIDA))) {
                                System.out.println("Has encontrado la salida. \n" + "Te han sobrado : " + numeroMovimientos + " intentos.");
                                laberintoSalida = false;
                                gameEnd = true;
                            }

                            laberinto[movFila][movColumna] = Propiedades.JUGADOR;

                            // condicion que se cumple cuando se queda sin intentos y no ha terminado el laberinto
                        } else {
                            laberintoSalida = false;
                            gameEnd = false;
                            System.out.println("Te has quedados sin intentos \n" + "No has terminado el laberinto");
                        }
                    }

                    do {
                        System.out.print("Quieres volver a jugar?");
                        mover = teclado.next();
                    } while (!mover.equalsIgnoreCase("si") && (!mover.equalsIgnoreCase("no")));

                    System.out.println();

                    if (mover.equalsIgnoreCase("no")) {
                        playing = false;
                        System.out.println("Juego terminado.");
                        System.out.println("Se han jugado un total de " + cantidadPartidas + " partidas");
                    }

                    // Guardamos la informacion de las partidas un ArrayList
                    Register partidasFinalizadas = new Register(cantidadPartidas, numeroMovimientos, gameEnd, nivel);
                    resultados.add(partidasFinalizadas);
                    break;
                case "2":
                    if (cantidadPartidas == 0) {
                        System.out.println("Todavia no se ha jugado ninguna partida.");
                        System.out.println();
                    } else {
                        playing = false;
                        System.out.println("Se han jugado un total de " + cantidadPartidas + " partidas");
                    }
                    break;
                case "3":
                    System.out.println("Has salido del programa. ");
                    playing = false;
                    break;
            }
        }

        // Llamada funcion para guardar los resultados de la partida
        ficheroResultado(resultados);
    }

    public static void textoEncontrar(int findNumber) {
        System.out.println("Parece que aqui hay algo escondido... \n" + "Has encontrado el numero numero : " + findNumber + "\ncombinalo con otro numero para desbloquear la puerta\n" + "y conseguir salir del laberinto");
    }





    // TODO solo guarda el ultimo laberinto
    public static void ficheroLaberinto(String[][] laberinto) throws FileNotFoundException {
        // creamos el fichero donde se guardara el laberinto
        PrintWriter ficheroLaberintos = new PrintWriter("Laberintos.txt");
        ficheroLaberintos.print(Arrays.deepToString(laberinto));
        ficheroLaberintos.close();

    }

    public static void ficheroResultado(ArrayList<Register> resultados) throws FileNotFoundException {
        PrintWriter ficheroResultados = new PrintWriter("Resultados.txt");
        System.out.println("Guardando datos..." + "\n");
        for (Register resultado : resultados) {
            System.out.println(resultado);
            ficheroResultados.println(resultado);
        }
        ficheroResultados.close();
    }

    public static void enunciadoPartida(Level level) {
        System.out.println("Has elegido el nivel " + level.getName() + ".\nSe te creara el laberinto. \n" + "Utiliza W, A, S, D para moverte e intentar llegar al final;" + "o Q si te quieres rendir y abandonar partida\n" + "Tienes " + level.getMovements() + " movimientos para intentar salir del laberinto. \n" + "Pulsa q para salir de la partida.");
    }

}