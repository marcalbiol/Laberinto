// TODO OPTIMIZAR LABERINTOS. RESULTADOS, SALIDAS, GUARDAR EN FICHERO RESULTADO TODO OK.
// TODO MEJORA DE LABERINTOS, ENTRADAS SECRETAS Y SALIDA.
/*
 - Revisar:
    * https://www.w3schools.com/java/java_enums.asp
    * https://stackoverflow.com/a/44654387/12508080
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

// TODO SOLUCIONAR BUG AL INTRODUCIR DATOS. (DO WHILE)    SOLUCIONADO
public class Laberinto {
    public static final char WALL = '▒';
    public static final char JUGADOR = 'J';
    public static final char SALIDA = 'S';
    public static final char PASO = ' ';
    public static final char SECRET = 'E';

    public static void main(String[] args) throws FileNotFoundException {
        Scanner teclado = new Scanner(System.in);
        // elementos del laberinto
        // ALIEN
        int[] surrogates = {0xD83D, 0xDC7D};
        String alienEmojiString = new String(surrogates, 0, surrogates.length);
        System.out.println(alienEmojiString);
        //ALIEN
        int cantidadPartidas = 0;
        Level nivel = null;
        System.out.println("Cuantas partidas vas a jugar : ");
        int partidas = teclado.nextInt();
        ArrayList<Datos> resultados = new ArrayList<>();
        boolean playing = true;
        boolean gameEnd = false;
        int remainingMov = 0;

        // MENU
        while (playing) {
            String menu;
            // TODO pasar variables a char para que cuandro introduzca una letra no pete el programa
            do {
                System.out.println("Elige que desea hacer : \n"
                        + "1. Jugar \n"
                        + "2. Resultado de partidas \n"
                        + "3. Salir ");
                menu = teclado.next();
            } while ((menu.equalsIgnoreCase("1") && menu.equalsIgnoreCase("2")
                    && menu.equalsIgnoreCase("3")) && partidas != 0);

            gameEnd = true;
            String dificultad;
            switch (menu) {
                case "1":
                    do {
                        System.out.println("Escoge la dificultad que quieres jugar : \n"
                                + "1. Facil \n" + "2. Intermedio \n"
                                + "3. Dificil");
                        dificultad = teclado.next();
                    } while (!(dificultad.equalsIgnoreCase("1") || dificultad.equalsIgnoreCase("2") || dificultad.equalsIgnoreCase("3")));

                    // Variables de movimiento en el laberinto
                    // TODO CAMBIAR NOMBRE VARIABLES = ENG
                    boolean laberintoSalida = true;
                    boolean doors = false;
                    //posicion jugador
                    int movColumna = 0;
                    int movFila = 0;
                    // salidas laberintos
                    int filEndgame = 0;
                    int colEndgame = 0;
                    char[][] laberinto = null;

                    // diferentes niveles del laberinto
                    switch (dificultad) {
                        case "1":
                            cantidadPartidas++;
                            System.out.println("Partida numero " + cantidadPartidas);
                            enunciadoPartida(Level.EASY);
                            nivel = Level.EASY;
                            remainingMov = Level.EASY.getMovements();
                            // TODO HACER LABERINTO MANUAL.
                            // definimos donde termina el laberinto.
                            filEndgame = 2;
                            colEndgame = 0;
                            laberinto = new char[][]{{JUGADOR, WALL, WALL}, {PASO, WALL, WALL}, {SALIDA, WALL, WALL}};
                            break;
                        case "2":
                            cantidadPartidas++;
                            System.out.println("Partida numero : " + cantidadPartidas);
                            enunciadoPartida(Level.MEDIUM);
                            remainingMov = Level.MEDIUM.getMovements();
                            nivel = Level.MEDIUM;
                            filEndgame = 19;
                            colEndgame = 12;
                            laberinto = new char[][]{{JUGADOR, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {PASO, PASO, PASO, PASO, PASO, WALL, WALL, PASO, PASO, WALL, WALL, WALL, WALL, WALL, PASO, PASO, WALL, WALL, WALL, WALL},
                                    {WALL, PASO, PASO, WALL, PASO, WALL, WALL, PASO, WALL, WALL, WALL, PASO, WALL, WALL, WALL, PASO, PASO, PASO, WALL, WALL},
                                    {WALL, PASO, PASO, PASO, PASO, WALL, WALL, PASO, WALL, PASO, PASO, PASO, WALL, WALL, WALL, PASO, PASO, PASO, PASO, WALL},
                                    {WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL, WALL, PASO, PASO, PASO, WALL, WALL, WALL, PASO, PASO, PASO, PASO, WALL},
                                    {WALL, WALL, WALL, WALL, PASO, WALL, PASO, WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, PASO, PASO, PASO, PASO, WALL, PASO, WALL, WALL, PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, PASO, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL, WALL, PASO, WALL, PASO, PASO, PASO, PASO, PASO, WALL, WALL},
                                    {WALL, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, PASO, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, PASO, PASO, PASO, WALL, PASO, PASO, PASO, PASO, PASO, WALL},
                                    {WALL, PASO, WALL, PASO, PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL, PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, PASO, PASO, PASO, WALL, WALL, PASO, WALL, WALL, PASO, PASO, PASO, PASO, WALL, PASO, PASO, PASO, PASO, PASO, WALL},
                                    {WALL, WALL, WALL, WALL, PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, PASO, PASO, PASO, PASO, PASO, PASO, PASO, WALL, WALL, WALL, WALL, PASO, PASO, PASO, PASO, PASO, WALL, WALL, WALL},
                                    {WALL, WALL, PASO, WALL, WALL, WALL, WALL, PASO, PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL, PASO, PASO, WALL, WALL},
                                    {WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, PASO, PASO, WALL, WALL},
                                    {WALL, WALL, PASO, PASO, PASO, WALL, WALL, WALL, WALL, PASO, PASO, PASO, PASO, WALL, WALL, WALL, PASO, WALL, PASO, WALL},
                                    {WALL, WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, PASO, PASO, PASO, WALL, PASO, WALL, WALL, WALL},
                                    {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, SALIDA, PASO, PASO, PASO, WALL, WALL, WALL, WALL}
                            };
                            break;
                        case "3":
                            // TODO CAMBIAR POSICIONES LABERINTO MAS DIFICIL/LARGO
                            doors = true;
                            cantidadPartidas++;
                            System.out.println("Partida numero : " + cantidadPartidas);
                            enunciadoPartida(Level.DIFFICULT);
                            remainingMov = Level.DIFFICULT.getMovements();
                            nivel = Level.DIFFICULT;
                            // definimos salida del laberinto
                            filEndgame = 19;
                            colEndgame = 12;

                            laberinto = new char[][]{{JUGADOR, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, PASO, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, PASO, PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL, PASO, WALL, WALL},
                                    {WALL, WALL, WALL, WALL, PASO, WALL, PASO, WALL, WALL, WALL, PASO, WALL, WALL, PASO, WALL, WALL, PASO, PASO, WALL, WALL},
                                    {WALL, WALL, WALL, WALL, PASO, WALL, PASO, WALL, WALL, WALL, PASO, WALL, WALL, PASO, WALL, WALL, PASO, WALL, WALL, WALL},
                                    {WALL, PASO, PASO, PASO, PASO, WALL, PASO, WALL, WALL, WALL, PASO, WALL, WALL, PASO, WALL, WALL, PASO, WALL, WALL, WALL},
                                    {WALL, PASO, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL, PASO, WALL, WALL, PASO, WALL, WALL, PASO, PASO, WALL, WALL},
                                    {WALL, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, WALL},
                                    {WALL, WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL, PASO, WALL, WALL, WALL},
                                    {WALL, PASO, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL, PASO, WALL, WALL, WALL},
                                    {WALL, PASO, WALL, PASO, PASO, PASO, PASO, WALL, WALL, WALL, WALL, PASO, PASO, PASO, WALL, WALL, PASO, WALL, WALL, WALL},
                                    {WALL, PASO, PASO, PASO, WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL},
                                    {WALL, WALL, PASO, WALL, WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL},
                                    {WALL, WALL, PASO, PASO, PASO, PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL},
                                    {WALL, WALL, PASO, WALL, WALL, WALL, WALL, PASO, PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL},
                                    {WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL},
                                    {WALL, WALL, PASO, PASO, PASO, WALL, WALL, WALL, WALL, PASO, PASO, PASO, PASO, WALL, WALL, WALL, PASO, WALL, WALL, WALL},
                                    {WALL, WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, PASO, PASO, WALL, WALL, PASO, WALL, WALL, WALL},
                                    {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, SALIDA, PASO, PASO, PASO, WALL, WALL, WALL}
                            };
                            break;
                    }
                    laberinto[movFila][movColumna] = JUGADOR;
                    // mostramos el laberinto en cada movimiento
                    String mover;
                    while (laberintoSalida) {

                        for (int i = 0; i < laberinto.length; i++) {
                            for (int c = 0; c < laberinto[i].length; c++) {
                                System.out.print(laberinto[i][c] + " ");
                            }
                            System.out.println("");
                        }

                        // se le preguntara al usuario su movimiento mientras le queden movimientos por hacer.
                        if (remainingMov != 0) {
                            System.out.println("Mueve : \n" + "Te quedan " + remainingMov + " intentos.");
                            mover = teclado.next();
                            laberinto[movFila][movColumna] = PASO;

                            // Condiciones del jugador dentro del laberinto. Controlamos si se encuentra un muro
                            if (mover.equalsIgnoreCase("q")) {
                                partidas--;
                                laberintoSalida = false;
                                gameEnd = false;
                                System.out.println("Has dejado de jugar la partida actual.");
                            } else if (mover.equalsIgnoreCase("s") && movFila + 1 != laberinto.length && laberinto[movFila + 1][movColumna] != WALL) {
                                movFila += 1;
                                remainingMov--;
                            } else if (mover.equalsIgnoreCase("w") && movFila - 1 != -1 && laberinto[movFila - 1][movColumna] != WALL) {
                                movFila -= 1;
                                remainingMov--;
                            } else if (mover.equalsIgnoreCase("d") && movColumna + 1 != laberinto.length && laberinto[movFila][movColumna + 1] != WALL) {
                                movColumna += 1;
                                remainingMov--;
                            } else if (mover.equalsIgnoreCase("a") && movColumna - 1 != -1 && laberinto[movFila][movColumna - 1] != WALL) {
                                movColumna -= 1;
                                remainingMov--;
                            } else {
                                remainingMov--;
                            }

                            /* JUEGO OCULTO DE LA DIFICULTAD 3, EL USUARIO TENDRA QUE BUSCAR POR "HABITACIONES" LA COMBINACION
                               DE NUMEROS PARA LOGRAR ESCAPAR DEL LABERINTO. NO SIEMPRE ENCUENTRA LO QUE NECESITA */

                            if (doors) {
                                // POSICIONES OCULTAS.
                                // consigue numero "1"
                                if ((laberinto[movFila][movColumna] = JUGADOR) == laberinto[7][11]) {
                                    System.out.println("Has encontrado el numero secreto : 1 \n"
                                            + "combinalo con otro numero para desbloquear la puerta \n"
                                            + "y conseguir salir del laberinto");
                                }
                                // consigue numero 2
                                if ((laberinto[movFila][movColumna] = JUGADOR) == laberinto[11][12]) {
                                    System.out.println("Has encontrado el numero secreto : 6 \n"
                                            + "combinalo con otro numero para desbloquear la puerta\n"
                                            + "y conseguir salir del laberinto");
                                }
                                // resta movimientos
                                if ((laberinto[movFila][movColumna] = JUGADOR) == laberinto[4][7]) {
                                    remainingMov = remainingMov - 30;
                                    System.out.println("Aqui no hay nada bueno... se te restaran -30 movimientos; te quedan " + remainingMov + " movimientos");
                                }

                                // PUERTAS SECRETAS
                                // intento de salida = termina el juego.
                                if ((laberinto[movFila][movColumna] = JUGADOR) == laberinto[19][14]) {
                                    System.out.println("Te has equivocado de camino... Hasta luego");
                                    partidas--;
                                    laberintoSalida = false;
                                    gameEnd = false;
                                }
                                // puerta secreta donde se introduce la clave
                                if ((laberinto[movFila][movColumna] = JUGADOR) == (laberinto[9][3])) {
                                    String secretNumber;
                                    do {
                                        System.out.println("Has encontrado una puerta secreta... \n"
                                                + "Introduce un numero.");
                                        secretNumber = teclado.next();
                                        if (secretNumber.equalsIgnoreCase("61")) {
                                            System.out.println("Numero correcto, puedes pasar");
                                            doors = false;
                                        } else {
                                            System.out.println("Incorrecto.");
                                        }
                                    } while (!(secretNumber.equalsIgnoreCase("61")));

                                }

                            }


                            // CONDICIONES QUE FUNCIONAN EN LOS 3 NIVELES.
                            // TODO HACER QUE COINCIDAN EN POSICION,-FUNCIONA DEFINIENDO LA SALIDA ARRIBA.
                            if ((laberinto[movFila][movColumna] = JUGADOR) == (laberinto[filEndgame][colEndgame])) {
                                partidas--;
                                System.out.println("Has encontrado la salida. \n" + "Te han sobrado : " + remainingMov + " intentos.");
                                laberintoSalida = false;
                                gameEnd = true;
                            }
                            // condicion que se cumple cuando se queda sin intentos y no ha terminado el laberinto
                        } else {
                            partidas--;
                            laberintoSalida = false;
                            gameEnd = false;
                            System.out.println("Te has quedados sin intentos \n"
                                    + "No has terminado el laberinto");
                        }

                    }
                    laberinto[movFila][movColumna] = JUGADOR;

                    System.out.println("Partidas restantes por jugar : " + partidas + "\nQuieres volver a jugar? si/no");
                    mover = teclado.next();
                    if (mover.equalsIgnoreCase("no") || partidas == 0) {
                        playing = false;
                        System.out.println("Juego terminado.");
                        System.out.println("Se han jugado un total de " + cantidadPartidas + " partidas");
                    }
                    Datos partidasFinalizadas = new Datos(cantidadPartidas, remainingMov, gameEnd, nivel);
                    resultados.add(partidasFinalizadas);
                    break;
                case "2":
                    if (cantidadPartidas == 0) {
                        System.out.println("Todavia no se ha jugado ninguna partida.");
                        System.out.println("");
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

        // Llamada funcion
        escribirFichero(resultados);
    }


    public static void escribirFichero(ArrayList<Datos> resultados) throws FileNotFoundException {
        // Guarda los datos de la array en el fichero


        PrintWriter ficheroResultados = new PrintWriter("Resultados.txt");
        for (Datos resultado : resultados) {
            System.out.println("Guardando datos...");
            System.out.println(resultado);
            ficheroResultados.println(resultado);
        }
        ficheroResultados.close();
    }

    public static void enunciadoPartida(Level level) {
        System.out.println("Has elegido el nivel " + level.getName() +
                ".\nSe te creara el laberinto. \n" +
                "Utiliza W, A, S, D para moverte e intentar llegar al final;" +
                " o Q si te quieres rendir y abandonar partida\n" +
                "Tienes " + level.getMovements() + " movimientos para intentar salir del laberinto. \n" +
                "Pulsa q para salir de la partida.");
    }

}
