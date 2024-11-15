import java.util.ArrayList;

public interface WinningPattern {
    void setPositionsToCheck(int selectedPattern, ArrayList<Integer> usedNumbers, ArrayList<ArrayList<Integer>> cells);
    boolean checkPattern(ArrayList<Integer> usedNumbers);
    int getPatternIndicator();
}