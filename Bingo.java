import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Bingo {
    private WinningPattern selectedPattern;
    private ArrayList<Integer> usedNumbers;


    public void test() {
        usedNumbers = new ArrayList<>(Arrays.asList(1, 16, 2, 17, 3));
        BingoBoard board = new BingoBoard(5);
        System.out.println(usedNumbers);
        SmallCenterBoxPattern pattern = new SmallCenterBoxPattern();

        pattern.setPositionsToCheck(1, usedNumbers, board.getCells());
    }

    public void selectPattern(int choice) {
        switch (choice) {
            case 1:
                this.selectedPattern = new FiveInARowPattern();
                break;
            case 2:
                this.selectedPattern = new SixPackPattern();
                break;
            case 3:
                this.selectedPattern = new DiamondPattern();
                break;
            case 4:
                this.selectedPattern = new SmallCenterBoxPattern();
                break;
            default:
                System.out.println("Opcion invalida");
        }
    }

    public WinningPattern getSelectedPattern() {
        return selectedPattern;
    }

    public void setSelectedPattern(WinningPattern selectedPattern) {
        this.selectedPattern = selectedPattern;
    }
}
