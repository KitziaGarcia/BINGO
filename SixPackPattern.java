import java.util.ArrayList;

public class SixPackPattern implements WinningPattern {
    private final int patternIndicator;
    private ArrayList<Integer> lineToCheck;


    public SixPackPattern() {
        this.patternIndicator = 2;
        lineToCheck = new ArrayList<>();
    }

    @Override
    public void setPositionsToCheck(int selectedPattern, ArrayList<Integer> usedNumbers, ArrayList<ArrayList<Integer>> cells) {
        int firstColumn = 0;
        int secondColumn = 0;
        int thirdColumn = 0;
        int startingRow = 0;
        int offset = 1;

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
                checkPattern(usedNumbers);
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
                checkPattern(usedNumbers);
                break;
        }
    }

    public void setLineToCheckForFirstCase(int startingRow, int firstColumn, int secondColumn, ArrayList<ArrayList<Integer>> cells) {
        for (int i = startingRow; i < startingRow + 3; i++) {
            lineToCheck.add((cells.get(firstColumn)).get(i));
            lineToCheck.add((cells.get(secondColumn)).get(i));
        }
    }

    public void setLineToCheckForSecondCase(int startingRow, int firstColumn, int secondColumn, int thirdColumn, ArrayList<ArrayList<Integer>> cells) {
        for (int i = startingRow; i < startingRow + 2; i++) {
            lineToCheck.add((cells.get(firstColumn)).get(i));
            lineToCheck.add((cells.get(secondColumn)).get(i));
            lineToCheck.add((cells.get(thirdColumn)).get(i));
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