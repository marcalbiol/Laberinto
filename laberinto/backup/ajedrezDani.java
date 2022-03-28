import java.util.Scanner;
public class ajedrezDani {
        public static void main(String[] args) {
            Scanner teclado = new Scanner(System.in);
            String[][] board = new String[8][8];

            // Demanar les coordenades del cavall
            System.out.println("Inserir coordenades del cavall:");
            // Al guardar li restem 1 per treballar directament amb els index correctes
            int houseRow = teclado.nextInt() - 1;
            int houseColumn = teclado.nextInt() - 1;

            // Comprovem que les coordenades del cavall son correctes
            if ((houseRow < 0 || houseRow >= 8) || (houseColumn < 0 || houseColumn >= 8)) {
                System.out.println("Posició invàlida.");
            } else {
                // Demanar les coordenades de la torre
                System.out.println("Inserir coordenades del torre:");
                int towerRow = teclado.nextInt() - 1;
                int towerColumn = teclado.nextInt() - 1;

                // Tancar el scanner
                teclado.close();

                // Posar la posició del cavall o torre
                board[houseRow][houseColumn] = " C ";
                board[towerRow][towerColumn] = " t ";

                // Comprovar cada moviment possible del cavall
                // Per fer això calculem si el moviment és possible (no és surt del tauler)
                // Comprovem si no és null, és a dir hi ha la torre i si no només marquem amb la "*" com possible moviment

                // Aquest codi és pot millorar utilitzant condicions ternàries i així poder estalviar codi

                if (houseRow - 1 >= 0 && houseColumn - 2 >= 0) {
                    if (board[houseRow - 1][houseColumn - 2] != null) {
                        board[houseRow - 1][houseColumn - 2] = " x ";
                    } else {
                        board[houseRow - 1][houseColumn - 2] = " * ";
                    }
                }

                if (houseRow - 2 >= 0 && houseColumn - 1 >= 0) {
                    if (board[houseRow - 2][houseColumn - 1] != null) {
                        board[houseRow - 2][houseColumn - 1] = " x ";
                    } else {
                        board[houseRow - 2][houseColumn - 1] = " * ";
                    }
                }

                if (houseRow + 1 <= 7 && houseColumn - 2 >= 0) {
                    if (board[houseRow + 1][houseColumn - 2] != null) {
                        board[houseRow + 1][houseColumn - 2] = " x ";
                    } else {
                        board[houseRow + 1][houseColumn - 2] = " * ";
                    }
                }

                if (houseRow + 2 <= 7 && houseColumn - 1 >= 0) {
                    if (board[houseRow + 2][houseColumn - 1] != null) {
                        board[houseRow + 2][houseColumn - 1] = " x ";
                    } else {
                        board[houseRow + 2][houseColumn - 1] = " * ";
                    }
                }

                if (houseRow - 1 >= 0 && houseColumn + 2 <= 7) {
                    if (board[houseRow - 1][houseColumn + 2] != null) {
                        board[houseRow - 1][houseColumn + 2] = " x ";
                    } else {
                        board[houseRow - 1][houseColumn + 2] = " * ";
                    }
                }

                if (houseRow - 2 >= 0 && houseColumn + 1 <= 7) {
                    if (board[houseRow - 2][houseColumn + 1] != null) {
                        board[houseRow - 2][houseColumn + 1] = " x ";
                    } else {
                        board[houseRow - 2][houseColumn + 1] = " * ";
                    }
                }

                if (houseRow + 1 <= 7 && houseColumn + 2 <= 7) {
                    if (board[houseRow + 1][houseColumn + 2] != null) {
                        board[houseRow + 1][houseColumn + 2] = " x ";
                    } else {
                        board[houseRow + 1][houseColumn + 2] = " * ";
                    }
                }

                if (houseRow + 2 <= 7 && houseColumn + 1 <= 7) {
                    if (board[houseRow + 2][houseColumn + 1] != null) {
                        board[houseRow + 2][houseColumn + 1] = " x ";
                    } else {
                        board[houseRow + 2][houseColumn + 1] = " * ";
                    }
                }

                // Salt de línia
                System.out.println("");

                // Recorre la matriu
                for (int i = 0; i < board.length; i++) {
                    for (int x = 0; x < board[0].length; x++) {
                        // Si no hi té valor la casella posem el guió (perquè en aquest punt tots els altres valors ja hi són)
                        if (board[i][x] == null) {
                            board[i][x] = " - ";
                        }

                        // Imprimir els valors
                        System.out.print(board[i][x]);
                    }

                    // Salt de línia
                    System.out.println("\n");
                }
            }
        }
}
