import javax.swing.*;
import java.util.ArrayList;

public class DiamondPattern implements WinningPattern {
    private final int typeOfPatternIndicator;
    private ArrayList<Integer> lineToCheck;
    private int combinationIndicator;
    private ImageIcon image;
    private String name;

    public DiamondPattern(int combinationIndicator, ImageIcon image) {
        this.typeOfPatternIndicator = 3;
        this.combinationIndicator = combinationIndicator;
        this.image = image;
        this.name = "Diamond Pattern";
        lineToCheck = new ArrayList<>();
    }

    @Override
    public void setPositionsToCheck(int selectedPattern, ArrayList<Integer> usedNumbers, ArrayList<ArrayList<Integer>> cells) {
        int[] offsets = {0, 1, 2, 1, 0};

        for (int i = 0; i < 5; i++) {
            int center = 2;
            int offset = offsets[i];

            lineToCheck.add(cells.get(i).get(center - offset));
            if (offset > 0) {
                lineToCheck.add(cells.get(i).get(center + offset));
            }
        }
        checkPattern(usedNumbers);
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