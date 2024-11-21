import javax.swing.*;
import java.util.ArrayList;

public class DiamondPattern implements WinningPattern {
    private final int typeOfPatternIndicator;
    private ArrayList<Integer> lineToCheck;
    private int combinationIndicator;
    private ImageIcon image;
    private String name;
    private ArrayList<ArrayList<Boolean>> patternCells;

    public DiamondPattern(int combinationIndicator, ImageIcon image) {
        this.typeOfPatternIndicator = 3;
        this.combinationIndicator = combinationIndicator;
        this.image = image;
        this.name = "Diamond Pattern";
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