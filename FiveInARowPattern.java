import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FiveInARowPattern implements WinningPattern {
    private final int typeOfPatternIndicator;
    private int combinationIndicator;
    private ArrayList<Integer> lineToCheck;
    private ArrayList<FiveInARowPattern> patterns;
    private ImageIcon image;
    private String name;
    private ArrayList<ArrayList<Boolean>> patternCells;

    public FiveInARowPattern(int combinationIndicator, ImageIcon image) {
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
    }

    @Override
    public boolean checkPattern(ArrayList<Integer> usedNumbers) {
        System.out.println(usedNumbers.containsAll(lineToCheck));
        return usedNumbers.containsAll(lineToCheck);
    }

    @Override
    public int getTypeOfPatternIndicator() {
        return this.typeOfPatternIndicator;
    }

    @Override
    public ImageIcon getImage() {
        return this.image;
    }

    @Override
    public int getCombinationIndicator() {
        return this.combinationIndicator;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ArrayList<ArrayList<Boolean>> getPatternCells() {
        return patternCells;
    }
}