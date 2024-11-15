import java.util.ArrayList;
import java.util.Arrays;

public class FiveInARowPattern implements WinningPattern {
    private final int patternIndicator;
    private ArrayList<Integer> lineToCheck;

    public FiveInARowPattern() {
        this.patternIndicator = 1;
        lineToCheck = new ArrayList<>();
    }

    @Override
    public void setPositionsToCheck(int selectedPattern, ArrayList<Integer> usedNumbers, ArrayList<ArrayList<Integer>> cells) {
        int cell = 0;

        switch (selectedPattern) {
            case 1, 2, 3, 4, 5:
                int column = selectedPattern - 1;
                lineToCheck = cells.get(column);
                checkPattern(usedNumbers);
                break;
            case 6, 7, 8, 9, 10:
                int row = selectedPattern - 6;
                for (int i = 0; i < 5; i++) {
                    lineToCheck.add(cells.get(i).get(row));
                }
                checkPattern(usedNumbers);
                break;
            case 11:
                for (int i = 0; i < 5; i++) {
                    lineToCheck.add(cells.get(i).get(cell));
                    cell++;
                }
                checkPattern(usedNumbers);
                break;
            case 12:
                cell = 4;
                for (int i = 0; i < 5; i++) {
                    lineToCheck.add(cells.get(i).get(cell));
                    cell--;
                }
                checkPattern(usedNumbers);
                break;
        }
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