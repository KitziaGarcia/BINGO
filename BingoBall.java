/**
 * Representa una bola de Bingo.
 * Esta clase proporciona métodos para acceder y modificar los atributos de la bola.
 */
public class BingoBall {
    private int number;
    private String letter;

    /**
     * Constructor por defecto de la clase BingoBall.
     */
    public BingoBall() {
        this.number = 1;
        this.letter = "B";
    }

    /**
     * Constructor de la clase BingoBall con parámetros.
     * Inicializa la bola con un número y una letra específicos.
     * @param number Número de la bola.
     * @param letter Letra de la bola (B, I, N, G, O).
     */
    public BingoBall(int number, String letter) {
        this.number = number;
        this.letter = letter;
    }

    /**
     * Obtiene el número de la bola.
     * @return El número de la bola.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Establece el número de la bola.
     * @param number El número de la bola.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Obtiene la letra de la bola.
     * @return La letra de la bola (B, I, N, G, O).
     */
    public String getLetter() {
        return letter;
    }

    /**
     * Establece la letra de la bola.
     * @param letter La letra de la bola (B, I, N, G, O).
     */
    public void setLetter(String letter) {
        this.letter = letter;
    }

    /**
     * Devuelve una representación en cadena de la bola de Bingo.
     * @return Representación en cadena de la bola.
     */
    public String toString() {
        return this.letter + "-" + this.number;
    }
}
