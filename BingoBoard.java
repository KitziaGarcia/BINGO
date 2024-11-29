import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Esta clase extiende la clase abstracta Board y representa un tablero de Bingo.
 * Proporciona métodos para inicializar el tablero y acceder a sus valores.
 */
public class BingoBoard extends Board {
    private ArrayList<Integer> numbers;
    private ArrayList<Integer> valuesForB;
    private ArrayList<Integer> valuesForI;
    private ArrayList<Integer> valuesForN;
    private ArrayList<Integer> valuesForG;
    private ArrayList<Integer> valuesForO;

    /**
     * Constructor de la clase BingoBoard.
     * Inicializa el tablero de Bingo con valores aleatorios en cada columna.
     */
    public BingoBoard() {
        initializeBoard();
        System.out.println(cells);
    }

    /**
     * Inicializa el tablero de Bingo con números aleatorios en cada columna.
     * Asigna valores dentro del rango específico para cada columna (B, I, N, G, O).
     */
    public void initializeBoard() {
        int numberIndex;

        valuesForB = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        valuesForI = new ArrayList<>(Arrays.asList(16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30));
        valuesForN = new ArrayList<>(Arrays.asList(31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45));
        valuesForG = new ArrayList<>(Arrays.asList(46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60));
        valuesForO = new ArrayList<>(Arrays.asList(61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75));

        numbers = new ArrayList<Integer>();
        for (int i = 0; i < 15; i++) {
            numbers.add(valuesForB.get(i));
            numbers.add(valuesForI.get(i));
            numbers.add(valuesForN.get(i));
            numbers.add(valuesForG.get(i));
            numbers.add(valuesForO.get(i));
        }

        for (int i = 0; i < 5; i++) {
            cells.add(new ArrayList<>());

            numberIndex = new Random().nextInt(valuesForB.size());
            cells.get(i).add(valuesForB.get(numberIndex));
            cells.get(i).add(valuesForI.get(numberIndex));
            cells.get(i).add(valuesForN.get(numberIndex));
            cells.get(i).add(valuesForG.get(numberIndex));
            cells.get(i).add(valuesForO.get(numberIndex));

            valuesForB.remove(numberIndex);
            valuesForI.remove(numberIndex);
            valuesForN.remove(numberIndex);
            valuesForG.remove(numberIndex);
            valuesForO.remove(numberIndex);
        }

        cells.get(2).set(2, -1);

        System.out.println(cells);
    }


    /**
     * Devuelve el estado actual del tablero de Bingo.
     * @return Una lista de enteros que representan el tablero.
     */
    public ArrayList<ArrayList<Integer>> getCells() {
        return cells;
    }

    /**
     * Devuelve la lista de todos los números posibles utilizados en el tablero.
     * @return Una lista de enteros que contiene todos los números del rango de Bingo.
     */
    public ArrayList<Integer> getAllNumbers() {
        return numbers;
    }
}