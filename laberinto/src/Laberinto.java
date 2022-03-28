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

// TODO SOLUCIONAR BUG AL INTRODUCIR DATOS. (DO WHILE)
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
        boolean doors = false;
        int remainingMov = 0;

        // MENU
        while (playing) {
            int menu;
            do {
                System.out.println("Elige que desea hacer : \n"
                        + "1. Jugar \n"
                        + "2. Resultado de partidas \n"
                        + "3. Salir ");

                menu = teclado.nextInt();
            } while (!(menu >= 1 && menu <= 3) && partidas != 0);

            gameEnd = true;
            int dificultad = 0;
            switch (menu) {
                case 1:
                    do {
                        System.out.println("Escoge la dificultad que quieres jugar : \n"
                                + "1. Facil \n" + "2. Intermedio \n"
                                + "3. Dificil");
                        dificultad = teclado.nextInt();
                    } while (!(dificultad >= 1 && dificultad <= 3));

                    // Variables de movimiento en el laberinto
                    // FIXME CAMBIAR NOMBRE VARIABLES = ENG
                    boolean laberintoSalida = true;
                    //posicion jugador
                    int movColumna = 0;
                    int movFila = 0;
                    // salidas laberintos
                    int filEndgame = 0;
                    int colEndgame = 0;
                    /*especial*/
                    // numeros secretos
                    int filsecretNumber1 = 0;
                    int colsecretNumber1 = 0;

                    int filsecretNumber2;
                    int colsecretNumber2;

                    // puerta secreta
                    int secretDoor_1 = 0;
                    int secretDoor_2 = 0;
                    char[][] laberinto = null;

                    // diferentes niveles del laberinto
                    switch (dificultad) {
                        case 1:
                            cantidadPartidas++;
                            System.out.println("Partida numero " + cantidadPartidas);
                            enunciadoPartida(Level.EASY);
                            nivel = Level.EASY;
                            remainingMov = Level.EASY.getMovements();
                            // FIXME HACER LABERINTO MANUAL.
                            // definimos donde termina el laberinto.
                            filEndgame = 2;
                            colEndgame = 0;
                            laberinto = new char[][]{{JUGADOR, WALL, WALL}, {PASO, WALL, WALL}, {SALIDA, WALL, WALL}};
                            break;
                        case 2:
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
                        case 3:
                            // FIXME CAMBIAR POSICIONES LABERINTO MAS DIFICIL/LARGO
                            doors = true;
                            cantidadPartidas++;
                            System.out.println("Partida numero : " + cantidadPartidas);
                            enunciadoPartida(Level.DIFFICULT);
                            remainingMov = Level.DIFFICULT.getMovements();
                            nivel = Level.DIFFICULT;
                            // definimos salida del laberinto
                            filEndgame = 19;
                            colEndgame = 12;
                            // numeros secreto
                            filsecretNumber1 = 7;
                            colsecretNumber1 = 1;

                            filsecretNumber2 = 8;
                            colsecretNumber2 = 4;
                            /* Puerta secreta 1 */
                            secretDoor_1 = 9;
                            secretDoor_2 = 3;
                            /* Puerta secreta 1 */
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
                                    {WALL, WALL, PASO, WALL, WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, WALL, PASO, PASO, PASO, PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, WALL, PASO, WALL, WALL, WALL, WALL, PASO, PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, WALL, PASO, PASO, PASO, WALL, WALL, WALL, WALL, PASO, PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, WALL, WALL, PASO, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, PASO, PASO, PASO, WALL, WALL, WALL, WALL, WALL},
                                    {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, SALIDA, PASO, PASO, PASO, WALL, WALL, WALL, WALL}
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

                            // FIXME TERMINAR NUMERO SECRETO,DEFINIR PUERTA SECRETA, CAMBIAR LABERINTO.
                            // NUMEROS SECRETO LABERINTO SOLO SE ACTIVA CUANDO ESCOGE LA DIFICULTAD 3
                            if (doors) {

                                // POSICIONES OCULTAS.
                                if ((laberinto[movFila][movColumna] = JUGADOR) == laberinto[filsecretNumber1][colsecretNumber1]) {
                                    System.out.println("Has encontrado el numero secreto : 1 \n"
                                            + "almacenalo para luego combiarlo con otros \n"
                                            + "y conseguir salir del laberinto");
                                }




                                // PUERTA SECRETA
                                if ((laberinto[movFila][movColumna] = JUGADOR) == (laberinto[secretDoor_1][secretDoor_2])) {
                                    int secretNumber = 21;
                                    do {
                                        System.out.println("Has encontrado una puerta secreta... \n"
                                                + "Introduce un numero.");
                                        secretNumber = teclado.nextInt();
                                        if (secretNumber == 21) {
                                            System.out.println("Numero correcto, puedes pasar");
                                            doors = false;
                                        } else {
                                            System.out.println("Incorrecto.");
                                        }
                                    } while (secretNumber != 21);

                                }

                            }


                            // FIXME HACER QUE COINCIDAN EN POSICION,-FUNCIONA DEFINIENDO LA SALIDA ARRIBA.
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
                case 2:
                    if (cantidadPartidas == 0) {
                        System.out.println("Todavia no se ha jugado ninguna partida.");
                        System.out.println("");
                    } else {
                        playing = false;
                        System.out.println("Se han jugado un total de " + cantidadPartidas + " partidas");
                    }
                    break;
                case 3:
                    System.out.println("Has salido del programa. ");
                    playing = false;
                    break;
            }
        }

        // Llamada funcion
        escribirFichero(resultados);
    }

    public static void laberintoFichero(char[][] laberinto, char WALL, char JUGADOR, char SALIDA, char PASO, char SECRET) throws FileNotFoundException {
        PrintWriter ficheroLaberinto = new PrintWriter("Laberinto.txt");

    }

    public static void escribirFichero(ArrayList<Datos> resultados) throws FileNotFoundException {
        // Guarda los datos de la array en el fichero


        System.out.println("Guardando datos...");
        PrintWriter ficheroResultados = new PrintWriter("Resultados.txt");
        for (Datos resultado : resultados) {
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
