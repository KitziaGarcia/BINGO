public class BingoBall {
    private int number;
    private String letter;

    public BingoBall() {
        this.number = 1;
        this.letter = "B";
    }

    public BingoBall(int number, String letter) {
        this.number = number;
        this.letter = letter;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String toString() {
        return this.letter + "-" + this.number;
    }
}
