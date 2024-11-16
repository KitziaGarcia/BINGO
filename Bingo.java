import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Bingo {
    private WinningPattern selectedPattern;
    private ArrayList<Integer> usedNumbers;
    //private ArrayList<BingoBall> usedNumbers;
    private BingoCage cage;
    private BingoBoard board;

    public Bingo(WinningPattern selectedPattern) {
        this.selectedPattern = selectedPattern;
        cage = new BingoCage();
        board = new BingoBoard();
    }

    //ESTO ES LO QUE HARAS EN LA GUI.
    public void play() {

    }

    public boolean isGameOver() {
        WinningPattern selectedPattern = getSelectedPattern();
        selectedPattern.setPositionsToCheck(selectedPattern.getCombinationIndicator(), usedNumbers, board.getCells());
        selectedPattern.checkPattern(usedNumbers);
        return false;
    }

    public WinningPattern getSelectedPattern() {
        return this.selectedPattern;
    }

    public void setSelectedPattern(WinningPattern selectedPattern) {
        this.selectedPattern = selectedPattern;
    }

    public void setUsedNumbers(int number) {
        usedNumbers.add(number);
    }

    public BingoBoard getBoard() {
        return board;
    }
}
