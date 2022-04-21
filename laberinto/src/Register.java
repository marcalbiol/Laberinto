
public class Register {

    // atributos
    private int cantidadPartidas;
    private int numeroMovimientos;
    private  boolean gameEnd;
    private  Level nivel;

    public Register(int cantidadPartidas, int numeroMovimientos, boolean gameEnd, Level nivel) {
        this.cantidadPartidas = cantidadPartidas;
        this.numeroMovimientos = numeroMovimientos;
        this.gameEnd = gameEnd;
        this.nivel = nivel;
    }

    public void setCantidadPartidas(int cantidadPartidas) {
        this.cantidadPartidas = cantidadPartidas;
    }

    public void setNumeroMovimientos(int numeroMovimientos) {
        this.numeroMovimientos = numeroMovimientos;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    public void setNivel(Level nivel) {
        this.nivel = nivel;
    }

    public int getCantidadPartidas() {
        return cantidadPartidas;
    }

    public int getNumeroMovimientos() {
        return numeroMovimientos;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public Level getNivel() {
        return nivel;
    }

    @Override
    public String toString() {
        return "Numero de partida : " + cantidadPartidas +
                "\nMovimientos restantes : " + numeroMovimientos +
                "\nPartida terminada : " + gameEnd +
                "\nDificultad : " + nivel + " " + "\n";
    }
}
