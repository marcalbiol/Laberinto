// TODO OPTIMIZAR LABERINTOS. RESULTADO DE PARTIDA(FALTA MOSTAR EL NIVEL). FICHEROS.
/*
 - Revisar:
    * https://www.w3schools.com/java/java_enums.asp
    * https://stackoverflow.com/a/44654387/12508080
 */

import java.util.ArrayList;
import java.util.Scanner;

enum Level {
    EASY(1, "facil", 2), MEDIUM(2, "medio", 50), DIFFICULT(3, "dificl", 20);

    private final int level;
    private final String name;
    private final int movements;

    Level(int level, String name, int movements) {
        this.level = level;
        this.name = name;
        this.movements = movements;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public int getMovements() {
        return movements;
    }
}


public class laberinto {
    public static void main(String[] args) {
        // elementos del laberinto
        // ALIEN
        int[] surrogates = {0xD83D, 0xDC7D};
        String alienEmojiString = new String(surrogates, 0, surrogates.length);
        System.out.println(alienEmojiString);
        final char MURO = '▒';
        final char JUGADOR = 'J';
        final char SALIDA = 'S';
        final char PASO = ' ';
        int cantidadPartidas = 0;
        int gameNotEnded = 0;
        int gameEnded = 0;
        int numeroMovimientos = 0;
        juegoLaberinto(MURO, JUGADOR, SALIDA, PASO, cantidadPartidas, gameEnded, gameNotEnded);
    }

    // FIXME HACER CASE 2(RESULTADOS) CASE3 ( SALIR )
    public static void juegoLaberinto(char MURO, char JUGADOR, char SALIDA, char PASO, int cantidadPartidas, int gameEnded, int gameNotEnded) {
        Scanner teclado = new Scanner(System.in);
        // menu
        int menu;
        do {
            System.out.println("Elige que desea hacer : \n" + "1. Jugar \n" + "2. Resultado de partidas \n" + "3. Salir ");
            menu = teclado.nextInt();
        } while (!(menu >= 1 && menu <= 3));

        // dificultad del laberinto
        int numeroMovimientos = 0;
        boolean gameEnd = true;
        int dificultad = 0;
        switch (menu) {
            case 1:
                do {
                    System.out.println("Escoge la dificultad que quieres jugar : \n" + "1. Facil \n" + "2. Intermedio \n" + "3. Dificil");
                    dificultad = teclado.nextInt();
                } while (!(dificultad >= 1 && dificultad <= 3));

                // Variables de movimiento en el laberinto
                // FIXME CAMBIAR NOMBRE VARIABLES = ENG
                int movColumna = 0;
                int movFila = 0;
                boolean laberintoSalida = true;
                int filEndgame = 0;
                int colEndgame = 0;
                char[][] laberinto = null;

                switch (dificultad) {
                    case 1:
                        cantidadPartidas++;
                        System.out.println("Partida numero " + cantidadPartidas);
                        enunciadoPartida(Level.EASY);
                        numeroMovimientos = Level.EASY.getMovements();
                        // FIXME HACER LABERINTO MANUAL.
                        filEndgame = 2;
                        colEndgame = 0;
                        laberinto = new char[][]{{JUGADOR, MURO, MURO}, {PASO, MURO, MURO}, {SALIDA, MURO, MURO}};
                        break;

                    case 2:
                        cantidadPartidas++;
                        System.out.println("Partida numero : " + cantidadPartidas);
                        enunciadoPartida(Level.MEDIUM);
                        numeroMovimientos = Level.MEDIUM.getMovements();
                        filEndgame = 19;
                        colEndgame = 12;
                        laberinto = new char[][]{{MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO}, {PASO, PASO, PASO, PASO, PASO, MURO, MURO, PASO, PASO, MURO, MURO, MURO, MURO, MURO, PASO, PASO, MURO}, {MURO, PASO, PASO, MURO, PASO, MURO, MURO, PASO, MURO, MURO, MURO, PASO, MURO, MURO, MURO, PASO, PASO, PASO, MURO, MURO}, {MURO, PASO, PASO, PASO, PASO, MURO, MURO, PASO, MURO, PASO, PASO, PASO, MURO, MURO, MURO, PASO, PASO, PASO, PASO, MURO}, {MURO, MURO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, MURO, MURO, MURO, PASO, PASO, PASO, PASO, MURO}, {MURO, MURO, MURO, MURO, PASO, MURO, PASO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO}, {MURO, PASO, PASO, PASO, PASO, MURO, PASO, MURO, MURO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO}, {MURO, PASO, MURO, MURO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, PASO, MURO, PASO, PASO, PASO, PASO, PASO, MURO, MURO}, {MURO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, MURO, MURO}, {MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO}, {MURO, PASO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, MURO, PASO, PASO, PASO, PASO, PASO, MURO}, {MURO, PASO, MURO, PASO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, MURO}, {MURO, PASO, PASO, PASO, MURO, MURO, PASO, MURO, MURO, PASO, PASO, PASO, PASO, MURO, PASO, PASO, PASO, PASO, PASO, MURO}, {MURO, MURO, MURO, MURO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, MURO}, {MURO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, PASO, PASO, MURO, MURO, MURO}, {MURO, MURO, PASO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, MURO, PASO, PASO, MURO, MURO}, {MURO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, PASO, PASO, MURO, MURO}, {MURO, MURO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, PASO, MURO, MURO, MURO, PASO, MURO, PASO, MURO}, {MURO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, MURO, PASO, MURO, MURO, MURO}, {MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, SALIDA, PASO, PASO, PASO, MURO, MURO, MURO, MURO}};
                        break;
                    case 3:
                        // FIXME CAMBIAR POSICIONES LABERINTO MAS DIFICIL/LARGO
                        cantidadPartidas++;
                        System.out.println("Partida numero : " + cantidadPartidas);
                        laberinto = new char[][]{{MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO}, {PASO, PASO, PASO, PASO, PASO, MURO, MURO, PASO, PASO, MURO, MURO, MURO, MURO, MURO, PASO, PASO, MURO}, {MURO, PASO, PASO, MURO, PASO, MURO, MURO, PASO, MURO, MURO, MURO, PASO, MURO, MURO, MURO, PASO, PASO, PASO, MURO, MURO}, {MURO, PASO, PASO, PASO, PASO, MURO, MURO, PASO, MURO, PASO, PASO, PASO, MURO, MURO, MURO, PASO, PASO, PASO, PASO, MURO}, {MURO, MURO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, MURO, MURO, MURO, PASO, PASO, PASO, PASO, MURO}, {MURO, MURO, MURO, MURO, PASO, MURO, PASO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO}, {MURO, PASO, PASO, PASO, PASO, MURO, PASO, MURO, MURO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO}, {MURO, PASO, MURO, MURO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, PASO, MURO, PASO, PASO, PASO, PASO, PASO, MURO, MURO}, {MURO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, MURO, MURO}, {MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO}, {MURO, PASO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, MURO, PASO, PASO, PASO, PASO, PASO, MURO}, {MURO, PASO, MURO, PASO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, MURO}, {MURO, PASO, PASO, PASO, MURO, MURO, PASO, MURO, MURO, PASO, PASO, PASO, PASO, MURO, PASO, PASO, PASO, PASO, PASO, MURO}, {MURO, MURO, MURO, MURO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, MURO}, {MURO, PASO, PASO, PASO, PASO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, PASO, PASO, MURO, MURO, MURO}, {MURO, MURO, PASO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, MURO, PASO, PASO, MURO, MURO}, {MURO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, PASO, PASO, MURO, MURO}, {MURO, MURO, PASO, PASO, PASO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, PASO, MURO, MURO, MURO, PASO, MURO, PASO, MURO}, {MURO, MURO, MURO, PASO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, PASO, PASO, PASO, MURO, PASO, MURO, MURO, MURO}, {MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, MURO, SALIDA, PASO, PASO, PASO, MURO, MURO, MURO, MURO}};
                        break;

                }

                // MOVIMIENTOS DEL JUGADOR DENTRO DENTRO LABERINTO
                laberinto[movFila][movColumna] = JUGADOR;
                // mostramos el laberinto en cada movimiento
                while (laberintoSalida) {

                    for (int i = 0; i < laberinto.length; i++) {
                        for (int c = 0; c < laberinto[i].length; c++) {
                            System.out.print(laberinto[i][c] + " ");
                        }
                        System.out.println("");
                    }

                    String mover;
                    if (numeroMovimientos != 0) {
                        System.out.println("Mueve : \n" + "Te quedan " + numeroMovimientos + " intentos.");
                        mover = teclado.next();
                        laberinto[movFila][movColumna] = PASO;

                        // Condiciones del jugador dentro del laberinto. Controlamos si se encuentra un muro
                        if (mover.equalsIgnoreCase("q")) {
                            gameEnd = false;
                            laberintoSalida = false;
                            gameNotEnded++;

                            System.out.println("Has dejado de jugar al laberinto \n" + "Quieres volver a jugar? si/no");
                            mover = teclado.next();
                            if (mover.equalsIgnoreCase("si")) {
                                juegoLaberinto(MURO, JUGADOR, SALIDA, PASO, cantidadPartidas, gameEnded, gameNotEnded);

                            } else if (mover.equalsIgnoreCase("no")) {
                                System.out.println("Juego terminado.");
                            }
                        }
                        if (mover.equalsIgnoreCase("s") && movFila + 1 != laberinto.length && laberinto[movFila + 1][movColumna] != MURO) {
                            movFila += 1;
                            numeroMovimientos--;
                        } else if (mover.equalsIgnoreCase("w") && movFila - 1 != -1 && laberinto[movFila - 1][movColumna] != MURO) {
                            movFila -= 1;
                            numeroMovimientos--;
                        } else if (mover.equalsIgnoreCase("d") && movColumna + 1 != laberinto.length && laberinto[movFila][movColumna + 1] != MURO) {
                            movColumna += 1;
                            numeroMovimientos--;
                        } else if (mover.equalsIgnoreCase("a") && movColumna - 1 != -1 && laberinto[movFila][movColumna - 1] != MURO) {
                            movColumna -= 1;
                            numeroMovimientos--;
                        } else {
                            numeroMovimientos--;
                        }

                        // FIXME HACER QUE COINCIDAN EN POSICION,-FUNCIONA DEFINIENDO LA SALIDA ARRIBA.
                        if ((laberinto[movFila][movColumna] = JUGADOR) == (laberinto[filEndgame][colEndgame])) {
                            gameEnd = true;
                            laberintoSalida = false;
                            gameEnded++;
                            System.out.println("Has encontrado la salida. \n" + "Te han sobrado : " + numeroMovimientos + " intentos.");
                            System.out.println("Quieres volver a jugar? si/no");
                            mover = teclado.next();

                            if (mover.equalsIgnoreCase("si")) {
                                juegoLaberinto(MURO, JUGADOR, SALIDA, PASO, cantidadPartidas, gameEnded, gameNotEnded);
                            } else {
                                System.out.println("Juego terminado.");
                                System.out.println("Se han jugado un total de " + cantidadPartidas + " partidas");
                            }
                        }
                        // condicion que se cumple cuando se queda sin intentos y no ha terminado el laberinto
                    } else {
                        gameEnd = false;
                        laberintoSalida = false;
                        gameNotEnded++;
                        System.out.println("Te has quedados sin intentos \n"
                                + "No has terminado el laberinto \n" + "Quieres volver a jugar?");
                        mover = teclado.next();
                        if (mover.equalsIgnoreCase("si")) {
                            juegoLaberinto(MURO, JUGADOR, SALIDA, PASO, cantidadPartidas, gameEnded, gameNotEnded);
                        } else {
                            System.out.println("Juego terminado.");
                            System.out.println("Se han jugado un total de " + cantidadPartidas + " partidas");
                        }
                    }

                    laberinto[movFila][movColumna] = JUGADOR;

                }
                // FIXME HACER ESTO EN UNA FUNCION?
                ArrayList<String> resultado = new ArrayList<String>();
                System.out.println("");
                resultado.add("Numero de partida : " + cantidadPartidas);
                resultado.add("Movimientos restantes : " + numeroMovimientos);
                if (!gameEnd) {
                    resultado.add("Partida no terminada");

                } else {
                    resultado.add("Partida terminada");
                }

                for (int i = 0; i < resultado.size(); i++) {
                    System.out.println(resultado.get(i));
                }
                break;
            case 2:
                // FIXME RESULTADO DE LAS PARTIDAS. BUG Q MUESTRA EL LABERINTO DESPUES DE TERMINAR. SOLUCIONADO
                if (cantidadPartidas == 0) {
                    System.out.println("Todavia no se ha jugado ninguna partida.");
                    juegoLaberinto(MURO, JUGADOR, SALIDA, PASO, cantidadPartidas, gameEnded, gameNotEnded);
                } else {
                    System.out.println("Se han jugado un total de " + cantidadPartidas + " partidas");

                }
                break;
            case 3:
                // FIXME  hacer salida del programa. -hecho-
                System.out.println("Has salido del programa. ");
                break;
        }


    }

    public static void enunciadoPartida(Level level) {
        System.out.println("Has elegido el nivel " + level.getName() + ".\n Se te creara el laberinto. \n" + "Utiliza W, A, S, D para moverte e intentar llegar al final; o Q si te quieres rendir y abandonar partida\n" + "Tienes " + level.getMovements() + " movimientos para intentar salir del laberinto. \n" + "Pulsa q para salir de la partida.");
    }
}
