import java.util.ArrayList;

public abstract class Board {
    protected ArrayList<ArrayList<Integer>> cells;

    public Board() {
        cells = new ArrayList<>();
        //initializeBoard();
    }

    protected abstract void initializeBoard();

    public ArrayList<ArrayList<Integer>> getCells() {
        return cells;
    }
}