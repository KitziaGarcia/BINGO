import java.util.ArrayList;

/**
 * Esta clase abstracta representa un tablero genérico para un juego.
 * Proporciona una estructura para almacenar las celdas del tablero y
 * métodos para inicializarlo y obtener su estado.
 */
public abstract class Board {
    protected ArrayList<ArrayList<Integer>> cells;

    /**
     * Constructor de la clase Board.
     * Inicializa la lista de celdas.
     */
    public Board() {
        cells = new ArrayList<>();
    }

    /**
     * Método abstracto para inicializar el tablero.
     */
    protected abstract void initializeBoard();

    /**
     * Método que devuelve la lista que representa las celdas del tablero.
     * @return Una lista de enteros que representan las celdas.
     */
    public ArrayList<ArrayList<Integer>> getCells() {
        return cells;
    }
}