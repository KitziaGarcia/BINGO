import java.util.ArrayList;
import java.util.Random;

public class BingoBoard extends Board {
    private ArrayList<ArrayList<Integer>> numbers;

    public BingoBoard(int number) {
        initializeNumbers();
        for (int i = 0; i < 5; i++) {
            cells.add(new ArrayList<>());

            for (int j = 0; j < 5; j++) {
                cells.get(i).add(numbers.get(i).get(j));
            }
        }

        System.out.println(cells);
    }

    public void initializeBoard() {
        int numberIndex;

        initializeNumbers();

        for (int i = 0; i < 5; i++) {
            cells.add(new ArrayList<>());

            for (int j = 0; j < 5; j++) {
                numberIndex = new Random().nextInt(numbers.get(i).size());
                cells.get(i).add(numbers.get(i).get(numberIndex));
            }

        /*for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                numberIndex = new Random().nextInt(numbers.get(i).size());
                cells.add(numbers.get(i).get(numberIndex));
            }
        }*/
        }

        System.out.println(cells);
    }

    public void initializeNumbers() {
        numbers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            numbers.add(new ArrayList<>());
        }

        for (int i = 1; i < 76; i++) {
            if (i < 16) {
                numbers.getFirst().add(i);
            } else if (i < 31) {
                numbers.get(1).add(i);
            } else if (i < 46) {
                numbers.get(2).add(i);
            } else if (i < 61) {
                numbers.get(3).add(i);
            } else {
                numbers.getLast().add(i);
            }
        }
    }

   /*public void markNumber(int number) {
        for (ArrayList<Integer> row : cells) {
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i) == number) {
                    row.set(i, 0);
                }
            }
        }
    }*/

    public ArrayList<ArrayList<Integer>> getCells() {
        return cells;
    }

    public void setCells(ArrayList<ArrayList<Integer>> cells) {
        this.cells = cells;
    }
}
