import java.util.ArrayList;
import java.util.Arrays;

public class BingoCage {
    private ArrayList<BingoBall> balls;
    private BingoBall ball;
    private ArrayList<String> letters;

    public BingoCage() {
        balls = new ArrayList<>();
        letters = new ArrayList<>(Arrays.asList("B", "I", "N", "G", "O"));
        initializeCage();
    }

    public void initializeCage() {
        for (int i = 1; i < 76; i++) {
            if (i < 16) {
                balls.add(ball = new BingoBall(i, letters.getFirst()));
            } else if (i < 31) {
                balls.add(ball = new BingoBall(i, letters.get(1)));
            } else if (i < 46) {
                balls.add(ball = new BingoBall(i, letters.get(2)));
            } else if (i < 61) {
                balls.add(ball = new BingoBall(i, letters.get(3)));
            } else {
                balls.add(ball = new BingoBall(i, letters.get(4)));
            }
        }
    }

    public void showBallsInCage() {
        balls.forEach(ball ->{
            System.out.print(ball.toString() + ", ");
        });
    }

    public ArrayList<BingoBall> getBalls() {
        return balls;
    }

    public void removeBall(BingoBall ball) {
        balls.remove(ball);
    }
}
