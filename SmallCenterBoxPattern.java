import javax.swing.*;
import java.util.ArrayList;

/**
 * Clase que representa el patrón "Small Center Box".
 */
public class SmallCenterBoxPattern extends Pattern {
    private ArrayList<Integer> lineToCheck;

    /**
     * Constructor de la clase SmallCenterBoxPattern.
     * @param combinationIndicator el indicador de combinación específica.
     * @param image la imagen asociada al patrón.
     */
    public SmallCenterBoxPattern(int combinationIndicator, ImageIcon image) {
        super(combinationIndicator, image);
        this.typeOfPatternIndicator = 4;
        this.combinationIndicator = combinationIndicator;
        this.image = image;
        this.name = "Small Center Box Pattern";
        lineToCheck = new ArrayList<>();
    }

    /**
     * Establece las posiciones específicas que deben verificarse para determinar
     * si el patrón está completo.
     * @param selectedPattern el patrón seleccionado.
     * @param usedNumbers los números marcados en el tablero.
     * @param cells la representación de las celdas del tablero.
     */
    @Override
    public void setPositionsToCheck(int selectedPattern, ArrayList<Integer> usedNumbers, ArrayList<ArrayList<Integer>> cells) {
        lineToCheck.clear();
        for (int i = 1; i <= 3; i++) {
            if (i == 2) {
                lineToCheck.add(cells.get(i).get(1));
                lineToCheck.add(cells.get(i).get(3));
                patternCells.get(i).set(1, true);
                patternCells.get(i).set(3, true);
            } else {
                for (int j = 1; j <= 3; j++) {
                    lineToCheck.add(cells.get(i).get(j));
                    patternCells.get(i).set(j, true);
                }
            }
        }
        System.out.println(lineToCheck);
    }


    /**
     * Verifica si el patrón está completo.
     * @param usedNumbers los números marcados en el tablero.
     * @return true si el patrón está completo.
     */
    @Override
    public boolean checkPattern(ArrayList<Integer> usedNumbers) {
        System.out.println(usedNumbers.containsAll(lineToCheck));
        return usedNumbers.containsAll(lineToCheck);
    }
}