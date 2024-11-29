import javax.swing.*;
import java.util.ArrayList;

/**
 * Clase base para representar un patrón en un juego de bingo.
 * Contiene información común para todos los tipos de patrones,
 * como el indicador de tipo, la combinación específica, una imagen asociada,
 * y una matriz que define las celdas del patrón.
 */
public class Pattern implements PatternChecker {
    protected int typeOfPatternIndicator;
    protected int combinationIndicator;
    protected ImageIcon image;
    protected String name;
    protected ArrayList<ArrayList<Boolean>> patternCells;

    /**
     * Constructor de la clase Pattern.
     * @param combinationIndicator el indicador de la combinación específica.
     * @param image la imagen asociada al patrón.
     */
    public Pattern(int combinationIndicator, ImageIcon image) {
        this.combinationIndicator = combinationIndicator;
        this.image = image;
        patternCells = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            patternCells.add(new ArrayList<>());

            for (int j = 0; j < 5; j++) {
                patternCells.get(i).add(false);
            }
        }
    }

    /**
     * Obtiene el indicador del tipo de patrón.
     * @return el tipo de patrón.
     */
    public int getTypeOfPatternIndicator() {
        return this.typeOfPatternIndicator;
    }

    /**
     * Obtiene la imagen asociada al patrón.
     * @return la imagen del patrón.
     */
    public ImageIcon getImage() {
        return this.image;
    }

    /**
     * Obtiene el indicador de la combinación del patrón.
     * @return el indicador de la combinación.
     */
    public int getCombinationIndicator() {
        return this.combinationIndicator;
    }

    /**
     * Obtiene el nombre del patrón.
     * @return el nombre del patrón.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtiene la matriz booleana que define las celdas activas del patrón.
     * @return la matriz de celdas del patrón.
     */
    public ArrayList<ArrayList<Boolean>> getPatternCells() {
        return patternCells;
    }

    /**
     * Establece las posiciones específicas que deben verificarse
     * para determinar si el patrón está completo.
     * @param selectedPattern el patrón seleccionado.
     * @param usedNumbers los números marcados en el tablero.
     * @param cells las celdas actuales del tablero.
     */
    @Override
    public void setPositionsToCheck(int selectedPattern, ArrayList<Integer> usedNumbers, ArrayList<ArrayList<Integer>> cells) {
    }


    /**
     * Verifica si el patrón ha sido completado.
     * @param usedNumbers los números marcados en el tablero.
     * @return true si el patrón está completo.
     */
    @Override
    public boolean checkPattern(ArrayList<Integer> usedNumbers) {
        return false;
    }
}
