import javax.swing.*;
import java.util.ArrayList;

/**
 * Representa un patrón de juego en forma de diamante.
 * Extiende la clase Pattern, añadiendo funcionalidad específica para
 * identificar y verificar el patrón de diamante.
 */
public class DiamondPattern extends Pattern {
    private ArrayList<Integer> lineToCheck;

    /**
     * Constructor de la clase DiamondPattern.
     * @param combinationIndicator Un valor entero que indica el identificador del patrón.
     * @param image Imagen asociada al patrón.
     */
    public DiamondPattern(int combinationIndicator, ImageIcon image) {
        super(combinationIndicator, image);
        this.typeOfPatternIndicator = 3;
        this.combinationIndicator = combinationIndicator;
        this.image = image;
        this.name = "Diamond Pattern";
        lineToCheck = new ArrayList<>();
    }

    /**
     * Configura las posiciones necesarias para formar el patrón de diamante.
     * Marca las posiciones importantes en la lista patternCells y las agrega a
     * lineToCheck para su validación.
     *
     * @param selectedPattern Número del patrón seleccionado.
     * @param usedNumbers Lista de números ya utilizados en el juego.
     * @param cells Matriz que representa las celdas del tablero.
     */
    @Override
    public void setPositionsToCheck(int selectedPattern, ArrayList<Integer> usedNumbers, ArrayList<ArrayList<Integer>> cells) {
        int[] offsets = {0, 1, 2, 1, 0};
        lineToCheck.clear();

        for (int i = 0; i < 5; i++) {
            int center = 2;
            int offset = offsets[i];

            lineToCheck.add(cells.get(i).get(center - offset));
            patternCells.get(i).set(center - offset, true);
            if (offset > 0) {
                lineToCheck.add(cells.get(i).get(center + offset));
                patternCells.get(i).set(center + offset, true);
            }
        }
    }

    /**
     * Verifica si los números utilizados en el juego coinciden con los números
     * necesarios para completar el patrón de diamante.
     *
     * @param usedNumbers Lista de números que han sido seleccionados en el juego.
     * @return true si todos los números necesarios están en la lista de números usados.
     */
    @Override
    public boolean checkPattern(ArrayList<Integer> usedNumbers) {
        System.out.println(usedNumbers.containsAll(lineToCheck));
        return usedNumbers.containsAll(lineToCheck);
    }
}