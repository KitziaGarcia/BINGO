import java.util.ArrayList;

public class SmallCenterBoxPattern implements WinningPattern {
    private final int patternIndicator;
    private ArrayList<Integer> lineToCheck;

    public SmallCenterBoxPattern() {
        this.patternIndicator = 4;
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
    public int getPatternIndicator() {
        return this.patternIndicator;
    }

}