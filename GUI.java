import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Representa la interfaz gráfica de un juego de Bingo.
 * Gestiona la visualización de los patrones disponibles, la selección del patrón,
 * la creación y gestión del tablero de juego, y la interacción con los botones de la interfaz.
 * Permite iniciar el juego, mostrar números aleatorios, marcar números utilizados en el tablero
 * y verificar si se ha completado el patrón seleccionado.
 */
public class GUI extends JFrame {
    private ArrayList<Pattern> patterns = new ArrayList<>();
    private Pattern selectedPattern;
    private JPanel patternsPanel;
    private JPanel boardContainer;
    private JPanel boardPanel;
    private JPanel panel1;
    private Bingo game;
    private BingoCage cage;
    private ArrayList<Integer> allNumbers;
    private JLabel ballDisplay;
    private JPanel cagePanel;
    private ArrayList<JLabel> usedNumbersLabels;
    private JPanel historicoPanel;
    private ArrayList<JLabel> boardCellLabels;
    private JButton bingoButton;
    private JButton spinCage;

    /**
     * Contructor de la clase GUI.
     */
    public GUI() {
        createPatternList();
        selectPatternToWin();
    }

    /**
     * Configura y muestra la interfaz para que el jugador seleccione un patrón para ganar.
     * Muestra una lista de patrones disponibles, y cuando el usuario selecciona uno,
     * lo establece como el patrón a seguir en el juego.
     */
    public void selectPatternToWin() {
        setTitle("Bingo Patterns");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Seleccione el patrón a jugar", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.NORTH);

        patternsPanel = new JPanel();
        patternsPanel.setLayout(new GridLayout(6, 7, 10, 10));
        add(patternsPanel, BorderLayout.CENTER);

        for (Pattern pattern : patterns) {
            ImageIcon originalImage = pattern.getImage();
            Image resizedImage = originalImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

            JButton button = new JButton();
            button.setIcon(resizedIcon);
            button.setPreferredSize(new Dimension(100, 100));

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setSelectedPattern(pattern);
                    JOptionPane.showMessageDialog(GUI.this,
                            "Has seleccionado: " + selectedPattern.getName(),
                            "Patrón Seleccionado.", JOptionPane.INFORMATION_MESSAGE);
                    remove(patternsPanel);
                    remove(label);
                    revalidate();
                    repaint();
                    initializeGame();

                }
            });
            patternsPanel.add(button);
        }
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * Inicializa el tablero de juego con el patrón seleccionado y configura la interfaz para el juego de Bingo.
     * Crea las celdas del tablero, agrega los botones de girar tómbola y marcar Bingo
     * y muestra los números utilizados.
     */
    public void initializeGame() {
        game = new Bingo(getSelectedPattern());
        addFreeSpace();
        boardCellLabels = new ArrayList<>();

        setLayout(new BorderLayout());

        boardContainer = new JPanel();
        boardContainer.setLayout(new BorderLayout());
        boardContainer.setPreferredSize(new Dimension(100, 100));

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(6, 5, 5, 5));

        String[] headers = {"B", "I", "N", "G", "O"};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                JLabel numberLabel;

                if (i == 0) {
                    JLabel headerLabel = new JLabel(headers[j], SwingConstants.CENTER);
                    headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    headerLabel.setVerticalAlignment(SwingConstants.CENTER);
                    headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
                    headerLabel.setPreferredSize(new Dimension(40, 40));
                    headerLabel.setMinimumSize(new Dimension(40, 40));
                    headerLabel.setMaximumSize(new Dimension(40, 40));
                    headerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    boardPanel.add(headerLabel);
                } else {
                    int cellValue = game.getBoard().getCells().get(i - 1).get(j);

                    if (cellValue == -1) {
                        numberLabel = new JLabel("Free Space", SwingConstants.CENTER);
                        numberLabel.setOpaque(true);
                    } else {
                        numberLabel = new JLabel(String.valueOf(cellValue), SwingConstants.CENTER);
                        numberLabel.setOpaque(false);
                    }

                    numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    numberLabel.setVerticalAlignment(SwingConstants.CENTER);
                    numberLabel.setFont(new Font("Arial", Font.BOLD, 16));
                    numberLabel.setPreferredSize(new Dimension(40, 40));
                    numberLabel.setMinimumSize(new Dimension(40, 40));
                    numberLabel.setMaximumSize(new Dimension(40, 40));
                    numberLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    selectedPattern.setPositionsToCheck(selectedPattern.getCombinationIndicator(), game.getUsedNumbers(), game.getBoard().getCells());
                    if (!selectedPattern.getPatternCells().get(i - 1).get(j)) {
                        numberLabel.setBackground(new Color(255, 255, 150));
                    } else if (selectedPattern.getPatternCells().get(i - 1).get(j)) {
                        numberLabel.setBackground(new Color(2, 250, 200));
                    }

                    boardPanel.add(numberLabel);
                    boardCellLabels.add(numberLabel);
                }
            }
        }

        System.out.println(selectedPattern.getPatternCells());
        boardContainer.add(boardPanel, BorderLayout.CENTER);
        add(boardContainer);

        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBackground(new Color(3, 60, 0));

        JPanel photoPanel = new JPanel();
        JLabel photoLabel = new JLabel();
        photoLabel.setIcon(getSelectedPattern().getImage());
        photoPanel.add(photoLabel);
        photoPanel.setBackground(new Color(3, 60, 0));
        panel1.add(photoPanel);

        cagePanel = new JPanel();
        cagePanel.setLayout(new GridLayout(1, 2, 3, 3));
        spinCage = new JButton("GIRAR");
        spinCage.setPreferredSize(new Dimension(50, 50));
        cagePanel.add(spinCage);
        spinCage.setVisible(true);

        bingoButton = new JButton("BINGO");
        bingoButton.setPreferredSize(new Dimension(50, 50));
        cagePanel.add(bingoButton);
        spinCage.setVisible(true);

        ballDisplay = new JLabel();

        spinCage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                game.setSelectedBall(cage.getRandomBall());
                cage.removeBall(game.getSelectedBall());

                ballDisplay.setText(game.getSelectedBall().toString());
                ballDisplay.setPreferredSize(new Dimension(25, 25));
                ballDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                ballDisplay.setHorizontalAlignment(SwingConstants.CENTER);
                ballDisplay.setVerticalAlignment(SwingConstants.CENTER);
                ballDisplay.setFont(new Font("Arial", Font.BOLD, 16));
                cagePanel.add(ballDisplay);

                game.addUsedNumber(game.getSelectedBall().getNumber());
                markUsedNumbers(game.getSelectedBall().getNumber(), usedNumbersLabels);
                markUsedNumbers(game.getSelectedBall().getNumber(), boardCellLabels);

                if (cage.isCageEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ya no hay bolas en la tómbola.");
                    spinCage.setEnabled(false);
                }

                panel1.revalidate();
                panel1.repaint();

                boardPanel.revalidate();
                boardPanel.repaint();
            }
        });

        bingoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isGameFinished();
            }
        });

        panel1.add(cagePanel);
        add(panel1, BorderLayout.EAST);

        createUsedNumbersDisplay();
    }

    /**
     * Crea y muestra la lista de los números utilizados en el juego de Bingo.
     */
    public void createUsedNumbersDisplay() {
        cage = new BingoCage();
        allNumbers = game.getBoard().getAllNumbers();
        usedNumbersLabels = new ArrayList<>();

        System.out.println(allNumbers);

        ArrayList<String> headers = new ArrayList<>(Arrays.asList("B", "I", "N", "G", "O"));

        historicoPanel = new JPanel();
        historicoPanel.setLayout(new GridLayout(16, 5, 3, 3));

        for (int i = 0; i < 5; i++) {
            JLabel label = new JLabel(headers.get(i), SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(25, 25));
            label.setMinimumSize(new Dimension(25, 25));
            label.setMaximumSize(new Dimension(25, 25));
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            historicoPanel.add(label);
        }

        for (int i = 1; i < 76; i++) {
            JLabel ballLabel = new JLabel(String.valueOf(allNumbers.get(i - 1)));
            ballLabel.setHorizontalAlignment(SwingConstants.CENTER);
            ballLabel.setVerticalAlignment(SwingConstants.CENTER);

            if (allNumbers.get(i - 1) < 16) {
                ballLabel.setBackground(Color.RED);
            } else if (allNumbers.get(i - 1) < 31) {
                ballLabel.setBackground(Color.BLUE);
            } else if (allNumbers.get(i - 1) < 46) {
                ballLabel.setBackground(Color.GREEN);
            } else if (allNumbers.get(i - 1) < 61) {
                ballLabel.setBackground(Color.YELLOW);
            } else {
                ballLabel.setBackground(Color.MAGENTA);
            }

            usedNumbersLabels.add(ballLabel);
            ballLabel.setOpaque(false);
            ballLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            historicoPanel.add(ballLabel);
        }

        panel1.add(historicoPanel);
        panel1.revalidate();
        panel1.repaint();

    }

    /**
     * Crea y carga la lista de patrones disponibles en el juego de Bingo.
     */
    public void createPatternList() {
        ArrayList<ImageIcon> images = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            String fileName = i + "-Five.png";
            ImageIcon image = loadImage(fileName);
            if (image != null) {
                patterns.add(new FiveInARowPattern(i, image));
            } else {
                System.err.println("Imagen no encontrada: " + fileName);
            }
        }

        for (int i = 1; i <= 24; i++) {
            String fileName = i + "-SixPack.png";
            ImageIcon image = loadImage(fileName);
            if (image != null) {
                patterns.add(new SixPackPattern(i, image));
            } else {
                System.err.println("Imagen no encontrada: " + fileName);
            }
        }

        String fileName = "Diamond.png";
        ImageIcon image = loadImage(fileName);
        if (image != null) {
            patterns.add(new DiamondPattern(1, image));
        } else {
            System.err.println("Imagen no encontrada: " + fileName);
        }

        String fileName2 = "SmallBox.png";
        ImageIcon image2 = loadImage(fileName2);
        if (image != null) {
            patterns.add(new SmallCenterBoxPattern(1, image2));
        } else {
            System.err.println("Imagen no encontrada: " + fileName);
        }
    }

    /**
     * Carga una imagen desde un archivo dentro del proyecto y la devuelve como un objeto ImageIcon.
     * Si la imagen no se encuentra, devuelve null.
     * @param fileName el nombre del archivo de la imagen a cargar.
     * @return un objeto ImageIcon que representa la imagen cargada.
     */
    private ImageIcon loadImage(String fileName) {
        java.net.URL imageUrl = getClass().getResource(fileName);
        if (imageUrl != null) {
            return new ImageIcon(imageUrl);
        } else {
            return null;
        }
    }

    /**
     * Compara cada número con los números en una colección y actualiza el estado visual
     * de las celdas para reflejar los números que ya han sido seleccionados.
     * @param value el número que se ha seleccionado.
     * @param collection la lista de etiquetas que muestran los números en la interfaz.
     */
    public void markUsedNumbers(int value, ArrayList<JLabel> collection) {
        for (JLabel label : collection) {
            if (label.getText().equals(String.valueOf(value))) {
                label.setOpaque(true);
                break;
            }
        }
    }

    /**
     * Verifica si el jugador ha completado el patrón seleccionado y ha ganado el juego de Bingo.
     */
    public void isGameFinished() {
        if (selectedPattern.checkPattern(game.getUsedNumbers())) {
            JOptionPane.showMessageDialog(null, "Has hecho BINGO!");
            bingoButton.setEnabled(false);
            spinCage.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No has hecho BINGO con el patrón seleccionado.");
        }
    }

    /**
     * Agrega un espacio libre en el tablero de Bingo, dependiendo del patrón seleccionado.
     */
    public void addFreeSpace() {
        int indicator = selectedPattern.getCombinationIndicator();

        switch (selectedPattern.getTypeOfPatternIndicator()) {
            case 1:
                if (indicator == 3 || indicator == 8 || indicator == 11 || indicator == 12) {
                    game.addUsedNumber(-1);
                }
                break;
            case 2:
                if (indicator == 2 || indicator == 3 || indicator == 6 || indicator == 7 || indicator == 10 || indicator == 11 || indicator == 16 || indicator == 17
                        || indicator == 18 || indicator == 19 || indicator == 20 || indicator == 21) {
                    game.addUsedNumber(-1);
                }
                break;
        }
    }

    /**
     * Obtiene el patrón seleccionado actualmente.
     * @return el patrón seleccionado.
     */
    public Pattern getSelectedPattern() {
        return selectedPattern;
    }

    /**
     * Establece el patrón seleccionado actualmente.
     * @param selectedPattern el patrón a seleccionar.
     */
    public void setSelectedPattern(Pattern selectedPattern) {
        this.selectedPattern = selectedPattern;
    }
}
