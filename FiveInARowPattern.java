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

    public FiveInARowPattern(int combinationIndicator, ImageIcon image) {
        this.typeOfPatternIndicator = 1;
        this.combinationIndicator = combinationIndicator;
        this.image = image;
        this.name = "Five In A Row Pattern";
        lineToCheck = new ArrayList<>();
    }

    @Override
    public void setPositionsToCheck(int selectedPattern, ArrayList<Integer> usedNumbers, ArrayList<ArrayList<Integer>> cells) {
        int cell = 0;

        switch (selectedPattern) {
            case 1, 2, 3, 4, 5:
                int column = selectedPattern - 1;
                for (int i = 0; i < 5; i++) {
                    lineToCheck.add(cells.get(i).get(column));
                }
                break;
            case 6, 7, 8, 9, 10:
                int row = selectedPattern - 6;
                lineToCheck = cells.get(row);
                break;
            case 11:
                for (int i = 0; i < 5; i++) {
                    lineToCheck.add(cells.get(i).get(cell));
                    cell++;
                }
                break;
            case 12:
                cell = 4;
                for (int i = 0; i < 5; i++) {
                    lineToCheck.add(cells.get(i).get(cell));
                    cell--;
                }
                break;
        }
    }

    @Override
    public boolean checkPattern(ArrayList<Integer> usedNumbers) {
        System.out.println("LINE TO CHECK: " + lineToCheck);
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
}