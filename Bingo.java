import java.util.ArrayList;
import java.util.Arrays;

public class Bingo {
    private WinningPattern selectedPattern;
    private ArrayList<Integer> usedNumbers;
    private BingoCage cage;
    private BingoBoard board;
    private BingoBall selectedBall;

    public Bingo(WinningPattern selectedPattern) {
        this.selectedPattern = selectedPattern;
        cage = new BingoCage();
        board = new BingoBoard();
        usedNumbers = new ArrayList<>();
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

    public ArrayList<Integer> getUsedNumbers() {
        return usedNumbers;
    }

    public void addUsedNumber(int number) {
        usedNumbers.add(number);
    }

    public BingoBoard getBoard() {
        return board;
    }

    public BingoBall getSelectedBall() {
        return selectedBall;
    }

    public void setSelectedBall(BingoBall selectedBall) {
        this.selectedBall = selectedBall;
    }
}
