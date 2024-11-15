import java.util.ArrayList;

public class DiamondPattern implements WinningPattern {
    private final int patternIndicator;
    private ArrayList<Integer> lineToCheck;

    public DiamondPattern() {
        this.patternIndicator = 3;
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
        System.out.println(usedNumbers.containsAll(lineToCheck));
        return usedNumbers.containsAll(lineToCheck);
    }

    @Override
    public int getPatternIndicator() {
        return this.patternIndicator;
    }
}