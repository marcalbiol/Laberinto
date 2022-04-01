import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// TODO FINALIZAR 1 - 2 LABERINTO, PARTIDA PERSONALIZADA CARGANDO ARCHIVO,
public class Laberinto {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner teclado = new Scanner(System.in);

        // Arraylist donde se guardaran los resultados
        ArrayList<Datos> resultados = new ArrayList<>();
        boolean playing = true;

        // Boolean que indica cuando termina la partida + variables para los resultados finales
        boolean gameEnd = false;
        int remainingMov = 0;
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
                        System.out.println("Escoge la dificultad que quieres jugar : \n" + "1. Facil \n" + "2. Intermedio \n" + "3. Dificil");
                        dificultad = teclado.next();
                        System.out.println();
                    } while (!(dificultad.equalsIgnoreCase("1") || dificultad.equalsIgnoreCase("2") || dificultad.equalsIgnoreCase("3")));

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
                            remainingMov = Level.EASY.getMovements();

                            // llamamos a la funcion para que guarde el laberinto en el fichero
                            ficheroLaberinto(laberinto);
                            break;
                        case "2":
                            cantidadPartidas++;
                            System.out.println("Partida numero : " + cantidadPartidas);
                            enunciadoPartida(Level.MEDIUM);
                            nivel = Level.MEDIUM;
                            laberinto = Level.MEDIUM.getLaberinto();
                            remainingMov = Level.MEDIUM.getMovements();

                            // llamamos a la funcion para que guarde el laberinto en el fichero
                            ficheroLaberinto(laberinto);
                            break;
                        case "3":
                            // TODO CAMBIAR POSICIONES LABERINTO MAS DIFICIL/LARGO

                            cantidadPartidas++;
                            System.out.println("Partida numero : " + cantidadPartidas);
                            enunciadoPartida(Level.DIFFICULT);
                            nivel = Level.DIFFICULT;
                            laberinto = Level.DIFFICULT.getLaberinto();
                            remainingMov = Level.DIFFICULT.getMovements();

                            // llamamos a la funcion para que guarde el laberinto en el fichero
                            ficheroLaberinto(laberinto);
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
                        if (remainingMov != 0) {
                            System.out.println("Mueve : \n" + "Te quedan " + remainingMov + " movimientos.");
                            mover = teclado.next();
                            System.out.println();
                            laberinto[movFila][movColumna] = Propiedades.PASO;

                            // Condiciones del jugador dentro del laberinto. Controlamos si se encuentra un muro
                            if (mover.equalsIgnoreCase("q")) {
                                laberintoSalida = false;
                                gameEnd = false;
                                System.out.println("Has dejado de jugar la partida actual.");
                            } else if (mover.equalsIgnoreCase("s") && movFila + 1 != laberinto.length && laberinto[movFila + 1][movColumna] != Propiedades.WALL) {
                                movFila += 1;
                                remainingMov--;
                            } else if (mover.equalsIgnoreCase("w") && movFila - 1 != -1 && laberinto[movFila - 1][movColumna] != Propiedades.WALL) {
                                movFila -= 1;
                                remainingMov--;
                            } else if (mover.equalsIgnoreCase("d") && movColumna + 1 != laberinto.length && laberinto[movFila][movColumna + 1] != Propiedades.WALL) {
                                movColumna += 1;
                                remainingMov--;
                            } else if (mover.equalsIgnoreCase("a") && movColumna - 1 != -1 && laberinto[movFila][movColumna - 1] != Propiedades.WALL) {
                                movColumna -= 1;
                                remainingMov--;
                            } else {
                                remainingMov--;
                            }


                            // Regalo
                            int findNumber;
                            if(laberinto[movFila][movColumna] == (Propiedades.GIFT1)){
                                findNumber = 1;
                                textoEncontrar(findNumber);
                            }
                            if(laberinto[movFila][movColumna] == (Propiedades.GIFT2)){
                                findNumber = 6;
                                textoEncontrar(findNumber);
                            }

                            // en caso de que llegue a la puerta secreta
                            if (laberinto[movFila][movColumna] == (Propiedades.SECRET)) {
                                int door_code;
                                do {
                                    System.out.println("Has llegado a la puerta secreta \n"
                                            + "Introduce el codigo");
                                    door_code = teclado.nextInt();
                                    System.out.println();
                                    if (door_code == 61) {
                                        System.out.println("Codigo correcto, puedes pasar");
                                    } else {
                                        System.out.println("Codigo incorrecto");
                                    }
                                } while (door_code != 61);
                            }

                            // encuentra la salida
                            if ((laberinto[movFila][movColumna] == (Propiedades.SALIDA))) {
                                System.out.println("Has encontrado la salida. \n" + "Te han sobrado : " + remainingMov + " intentos.");
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

                    System.out.println("Quieres volver a jugar? \n" + "Si / No");
                    mover = teclado.next();
                    System.out.println();
                    if (mover.equalsIgnoreCase("no")) {
                        playing = false;
                        System.out.println("Juego terminado.");
                        System.out.println("Se han jugado un total de " + cantidadPartidas + " partidas");
                    }

                    // Guardamos la informacion de las partidas un ArrayList
                    Datos partidasFinalizadas = new Datos(cantidadPartidas, remainingMov, gameEnd, nivel);
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

    // TODO solo guard el ultimo laberinto
    public static void ficheroLaberinto(String[][] laberinto) throws FileNotFoundException {
        // creamos el fichero donde se guardara el laberinto
        PrintWriter ficheroLaberintos = new PrintWriter("Laberintos.txt");
        ficheroLaberintos.print(Arrays.deepToString(laberinto));
        ficheroLaberintos.close();

    }

    public static void ficheroResultado(ArrayList<Datos> resultados) throws FileNotFoundException {
        PrintWriter ficheroResultados = new PrintWriter("Resultados.txt");
        System.out.println("Guardando datos...");
        for (Datos resultado : resultados) {
            System.out.println(resultado);
            ficheroResultados.println(resultado);
        }
        ficheroResultados.close();
    }

    public static void enunciadoPartida(Level level) {
        System.out.println("Has elegido el nivel " + level.getName() + ".\nSe te creara el laberinto. \n" + "Utiliza W, A, S, D para moverte e intentar llegar al final;" + " o Q si te quieres rendir y abandonar partida\n" + "Tienes " + level.getMovements() + " movimientos para intentar salir del laberinto. \n" + "Pulsa q para salir de la partida.");
    }

}