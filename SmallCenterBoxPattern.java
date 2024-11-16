import javax.swing.*;
import java.util.ArrayList;

public class SmallCenterBoxPattern implements WinningPattern {
    private final int typeOfPatternIndicator;
    private ArrayList<Integer> lineToCheck;
    private int combinationIndicator;
    private ImageIcon image;
    private String name;

    public SmallCenterBoxPattern(int combinationIndicator, ImageIcon image) {
        this.typeOfPatternIndicator = 4;
        this.combinationIndicator = combinationIndicator;
        this.image = image;
        this.name = "Small Center Box Pattern";
        lineToCheck = new ArrayList<>();
    }

    @Override
    public void setPositionsToCheck(int selectedPattern, ArrayList<Integer> usedNumbers, ArrayList<ArrayList<Integer>> cells) {
        for (int i = 1; i <= 3; i++) {
            if (i == 2) {
                lineToCheck.add(cells.get(i).get(1));
                lineToCheck.add(cells.get(i).get(3));
            } else {
                for (int j = 1; j <= 3; j++) {
                    lineToCheck.add(cells.get(i).get(j));
                }
            }
        }
        System.out.println(lineToCheck);
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
}