import java.util.ArrayList;

/**
 * Representa el juego de Bingo.
 * Esta clase gestiona el estado del juego, incluyendo el patrón seleccionado,
 * los números usados, el tablero y las bolas seleccionadas.
 */
public class Bingo {
    private Pattern selectedPattern;
    private ArrayList<Integer> usedNumbers;
    private BingoCage cage;
    private BingoBoard board;
    private BingoBall selectedBall;

    /**
     * Constructor de la clase Bingo.
     * Inicializa el patrón seleccionado, la tómbola de bolas, el tablero y la lista de números usados.
     * @param selectedPattern Patrón seleccionado para el juego.
     */
    public Bingo(Pattern selectedPattern) {
        this.selectedPattern = selectedPattern;
        cage = new BingoCage();
        board = new BingoBoard();
        usedNumbers = new ArrayList<>();
    }

    /**
     * Verifica si el juego ha terminado.
     * Configura las posiciones necesarias para verificar el patrón seleccionado y comprueba si ha sido completado.
     * @return true si el patrón seleccionado se ha completado.
     */
    public boolean isGameOver() {
        Pattern selectedPattern = getSelectedPattern();
        selectedPattern.setPositionsToCheck(selectedPattern.getCombinationIndicator(), usedNumbers, board.getCells());
        selectedPattern.checkPattern(usedNumbers);
        return false;
    }

    /**
     * Obtiene el patrón seleccionado en el juego.
     * @return El patrón seleccionado.
     */
    public Pattern getSelectedPattern() {
        return this.selectedPattern;
    }

    /**
     * Establece el patrón seleccionado para el juego.
     * @param selectedPattern El patrón que se seleccionará.
     */
    public void setSelectedPattern(Pattern selectedPattern) {
        this.selectedPattern = selectedPattern;
    }

    /**
     * Obtiene la lista de números que ya han sido utilizados en el juego.
     * @return Lista de números utilizados.
     */
    public ArrayList<Integer> getUsedNumbers() {
        return usedNumbers;
    }

    /**
     * Agrega un número a la lista de números usados.
     * @param number El número que se ha utilizado en el juego.
     */
    public void addUsedNumber(int number) {
        usedNumbers.add(number);
    }

    /**
     * Obtiene el tablero de Bingo actual.
     * @return El tablero de Bingo.
     */
    public BingoBoard getBoard() {
        return board;
    }

    /**
     * Obtiene la bola de Bingo seleccionada.
     * @return La bola seleccionada.
     */
    public BingoBall getSelectedBall() {
        return selectedBall;
    }

    /**
     * Establece la bola seleccionada en el juego.
     * @param selectedBall La bola seleccionada.
     */
    public void setSelectedBall(BingoBall selectedBall) {
        this.selectedBall = selectedBall;
    }
}
