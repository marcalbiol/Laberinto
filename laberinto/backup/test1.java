test1
class  Test {
    private ArrayList<Resultado> resultados;
    public Test(){
        ArrayList<Resultado> resultados = new ArrayList<Resultado>();
    }
    public void nuevosResultado(int partidasJugadas, int gameEnded, int gameNotEnded, boolean end){
        resultados.add(new Resultado(partidasJugadas, gameEnded, gameNotEnded, end));
    }
}
public class Resultado {
    private final int cantidadPartidasJugadas;
    private final int gameEnded;
    private final int gameNotEnded;
    private final boolean end;
    public Resultado(int cantidadPartidasJugadas, int gameEnded, int gameNotEnded, boolean end) {
        this.cantidadPartidasJugadas = cantidadPartidasJugadas;
        this.gameEnded = gameEnded;
        this.gameNotEnded =  gameNotEnded;
        this.end = end;
    }
    public int getpartidasJugadas(){
        return cantidadPartidasJugadas;
    }
    public int getGameEnded(){
        return gameEnded;
    } public int getGameNotEnded(){
        return gameNotEnded;
    }
    public boolean getEnd(){
        return end;
    }

}


PRIMERA NO TERMINADA
SEGUNDA SI
TERCERA NO POR MOVIMIENTOS