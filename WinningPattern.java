import javax.swing.*;
import java.util.ArrayList;

public interface WinningPattern {
    void setPositionsToCheck(int selectedPattern, ArrayList<Integer> usedNumbers, ArrayList<ArrayList<Integer>> cells);
    boolean checkPattern(ArrayList<Integer> usedNumbers);
    int getTypeOfPatternIndicator();
    ImageIcon getImage();
    int getCombinationIndicator();
    String getName();
}