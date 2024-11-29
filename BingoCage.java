import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Esta clase representa una tómbola de Bingo que contiene todas las bolas numeradas
 * y las letras correspondientes (B, I, N, G, O). Permite inicializar la tómbola,
 * extraer bolas de forma aleatoria y verificar si la tómbola está vacía.
 */
public class BingoCage {
    private ArrayList<BingoBall> balls;
    private BingoBall ball;
    private ArrayList<String> letters;

    /**
     * Constructor de la clase BingoCage.
     * Inicializa la tómbola con todas las bolas numeradas del 1 al 75,
     * a las letras correspondientes.
     */
    public BingoCage() {
        balls = new ArrayList<>();
        letters = new ArrayList<>(Arrays.asList("B", "I", "N", "G", "O"));
        initializeCage();
    }

    /**
     * Inicializa la tómbola de Bingo llenándola con 75 bolas.
     * Cada bola está numerada del 1 al 75 y se le asigna una letra según su rango:
     * B (1-15), I (16-30), N (31-45), G (46-60), O (61-75).
     */
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

    /**
     * Devuelve la lista de bolas actualmente en la tómbola.
     * @return Una lista de objetos BingoBall que representan las bolas en la tómbola.
     */
    public ArrayList<BingoBall> getBalls() {
        return balls;
    }

    /**
     * Selecciona y devuelve una bola aleatoria de la jaula.
     * @return Un objeto BingoBall que representa la bola seleccionada.
     */
    public BingoBall getRandomBall() {
        int randomIndex;
        BingoBall selectedBall;
        randomIndex = new Random().nextInt(balls.size());
        selectedBall = balls.get(randomIndex);
        return selectedBall;
    }

    /**
     * Elimina una bola específica de la tómbola
     * @param ball La bola que se desea eliminar de la tómbola.
     */
    public void removeBall(BingoBall ball) {
        balls.remove(ball);
    }

    /**
     * Verificar si la tómbola está vacía.
     * @return true si no quedan bolas en la tómbola.
     */
    public boolean isCageEmpty() {
        return balls.isEmpty();
    }
}
