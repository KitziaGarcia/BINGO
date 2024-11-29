import javax.swing.*;
import java.util.ArrayList;

/**
 * Clase que representa el patrón "Six Pack".
 */
public class SixPackPattern extends Pattern {
    private ArrayList<Integer> lineToCheck;

    /**
     * Constructor de la clase SixPackPattern.
     * @param combinationIndicator el indicador de combinación específica.
     * @param image la imagen asociada al patrón.
     */
    public SixPackPattern(int combinationIndicator, ImageIcon image) {
        super(combinationIndicator, image);
        this.typeOfPatternIndicator = 2;
        this.combinationIndicator = combinationIndicator;
        this.image = image;
        this.name = "Six Pack Pattern";
        lineToCheck = new ArrayList<>();
    }

    /**
     * Establece las posiciones específicas que deben verificarse para determinar
     * si el patrón "Six Pack" está completo, dependiendo del patrón seleccionado.
     * @param selectedPattern el patrón seleccionado por el usuario.
     * @param usedNumbers los números marcados en el tablero.
     * @param cells la representación de las celdas del tablero.
     */
    @Override
    public void setPositionsToCheck(int selectedPattern, ArrayList<Integer> usedNumbers, ArrayList<ArrayList<Integer>> cells) {
        int firstColumn = 0;
        int secondColumn = 0;
        int thirdColumn = 0;
        int startingRow = 0;
        int offset = 1;
        lineToCheck.clear();

        switch(selectedPattern) {
            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12:
                if (selectedPattern == 5 || selectedPattern == 6 || selectedPattern == 7 || selectedPattern == 8) {
                    startingRow = 1;
                    offset = 5;
                } else if (selectedPattern == 9 || selectedPattern == 10 || selectedPattern == 11 || selectedPattern == 12) {
                    startingRow = 2;
                    offset = 9;
                }
                firstColumn = selectedPattern - offset;
                secondColumn = firstColumn + 1;
                setLineToCheckForFirstCase(startingRow, firstColumn, secondColumn, cells);
                break;

            case 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24:
                if (selectedPattern == 13 || selectedPattern == 14 || selectedPattern == 15) {
                    offset = 13;
                } else if (selectedPattern == 16 || selectedPattern == 17 || selectedPattern == 18) {
                    startingRow = 1;
                    offset = 16;
                } else if (selectedPattern == 19 || selectedPattern == 20 || selectedPattern == 21) {
                    startingRow = 2;
                    offset = 19;
                } else if (selectedPattern == 22 || selectedPattern == 23 || selectedPattern == 24) {
                    startingRow = 3;
                    offset = 22;
                }
                firstColumn = selectedPattern - offset;
                secondColumn = firstColumn + 1;
                thirdColumn = secondColumn + 1;
                setLineToCheckForSecondCase(startingRow, firstColumn, secondColumn, thirdColumn, cells);
                break;
        }
    }

    /**
     * Establece las celdas a verificar para patrones 2x3.
     *
     * @param startingRow la fila inicial del patrón.
     * @param firstRow la primera fila del patrón.
     * @param secondRow la segunda fila del patrón.
     * @param cells las celdas del tablero.
     */
    public void setLineToCheckForFirstCase(int startingRow, int firstRow, int secondRow, ArrayList<ArrayList<Integer>> cells) {
        for (int i = startingRow; i < startingRow + 3; i++) {
            lineToCheck.add((cells.get(i)).get(firstRow));
            lineToCheck.add((cells.get(i)).get(secondRow));

            patternCells.get(i).set(firstRow, true);
            patternCells.get(i).set(secondRow, true);
        }
    }

    /**
     * Establece las celdas a verificar para patrones 3x2.
     * @param startingRow la fila inicial del patrón.
     * @param firstColumn la primera columna del patrón.
     * @param secondColumn la segunda columna del patrón.
     * @param thirdColumn la tercera columna del patrón.
     * @param cells las celdas del tablero.
     */
    public void setLineToCheckForSecondCase(int startingRow, int firstColumn, int secondColumn, int thirdColumn, ArrayList<ArrayList<Integer>> cells) {
        for (int i = startingRow; i < startingRow + 2; i++) {
            lineToCheck.add((cells.get(i)).get(firstColumn));
            lineToCheck.add((cells.get(i)).get(secondColumn));
            lineToCheck.add((cells.get(i)).get(thirdColumn));

            patternCells.get(i).set(firstColumn, true);
            patternCells.get(i).set(secondColumn, true);
            patternCells.get(i).set(thirdColumn, true);
        }
    }

    /**
     * Verifica si el patrón stá completo.
     * @param usedNumbers los números marcados en el tablero.
     * @return true si el patrón está completo.
     */
    @Override
    public boolean checkPattern(ArrayList<Integer> usedNumbers) {
        return usedNumbers.containsAll(lineToCheck);
    }
}