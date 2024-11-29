import javax.swing.*;
import java.util.ArrayList;

/**
 * Representa el patrón "Cinco en línea".
 * Este patrón puede ser una fila, columna o diagonal completa de un tablero.
 * Extiende la clase Pattern para añadir lógica específica de este patrón.
 */
public class FiveInARowPattern extends Pattern {
    private ArrayList<Integer> lineToCheck;

    /**
     * Constructor de la clase FiveInARowPattern.
     * @param combinationIndicator Un valor entero que indica el identificador del patrón.
     * @param image Imagen asociada al patrón.
     */
    public FiveInARowPattern(int combinationIndicator, ImageIcon image) {
        super(combinationIndicator, image);
        this.typeOfPatternIndicator = 1;
        this.combinationIndicator = combinationIndicator;
        this.image = image;
        this.name = "Five In A Row Pattern";
        lineToCheck = new ArrayList<>();
        patternCells = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            patternCells.add(new ArrayList<>());

            for (int j = 0; j < 5; j++) {
                patternCells.get(i).add(false);
            }
        }
    }

    /**
     * Configura las posiciones necesarias para formar el patrón "Cinco en línea".
     * @param selectedPattern Patrón seleccionado.
     * @param usedNumbers Lista de números ya utilizados en el juego.
     * @param cells Matriz que representa las celdas del tablero.
     */
    @Override
    public void setPositionsToCheck(int selectedPattern, ArrayList<Integer> usedNumbers, ArrayList<ArrayList<Integer>> cells) {
        int cell = 0;
        lineToCheck.clear();

        switch (selectedPattern) {
            case 1, 2, 3, 4, 5:
                int column = selectedPattern - 1;
                for (int i = 0; i < 5; i++) {
                    lineToCheck.add(cells.get(i).get(column));
                    patternCells.get(i).set(column, true);
                }
                break;
            case 6, 7, 8, 9, 10:
                int row = selectedPattern - 6;
                for (int i = 0; i < cells.get(row).size(); i++) {
                    lineToCheck.add(cells.get(row).get(i));
                    patternCells.get(row).set(i, true);
                }
                break;
            case 11:
                for (int i = 0; i < 5; i++) {
                    lineToCheck.add(cells.get(i).get(cell));
                    patternCells.get(i).set(cell, true);
                    cell++;
                }
                break;
            case 12:
                cell = 4;
                for (int i = 0; i < 5; i++) {
                    lineToCheck.add(cells.get(i).get(cell));
                    patternCells.get(i).set(cell, true);
                    cell--;
                }
                break;
        }
        System.out.println(lineToCheck);
    }

    /**
     * Verifica si los números utilizados en el juego coinciden con los números
     * necesarios para completar el patrón seleccionado.
     * @param usedNumbers Lista de números que han sido seleccionados en el juego.
     * @return true si todos los números necesarios están en la lista de números usados.
     */
    @Override
    public boolean checkPattern(ArrayList<Integer> usedNumbers) {
        System.out.println(usedNumbers.containsAll(lineToCheck));
        return usedNumbers.containsAll(lineToCheck);
    }
}