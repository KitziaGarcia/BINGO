import java.util.ArrayList;

public abstract class Board {
    protected ArrayList<ArrayList<Integer>> cells;

    public Board() {
        cells = new ArrayList<>();
        //initializeBoard();
    }

    protected abstract void initializeBoard();

    public void markNumber(int number) {
        for (ArrayList<Integer> row : cells) {
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i) == number) {
                    row.set(i, 0); // Mark as "called"
                }
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getCells() {
        return cells;
    }
}