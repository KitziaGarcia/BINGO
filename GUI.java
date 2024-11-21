import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GUI extends JFrame {
    private ArrayList<WinningPattern> patterns = new ArrayList<>();
    private WinningPattern selectedPattern;
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


    public GUI() {
        createPatternList();
        selectPatternToWin();
    }



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

        for (WinningPattern pattern : patterns) {
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
                            "Patrón Seleccionado", JOptionPane.INFORMATION_MESSAGE);
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

    public void initializeGame() {
        game = new Bingo(getSelectedPattern());
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
                    numberLabel = new JLabel(String.valueOf(cellValue), SwingConstants.CENTER);
                    numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    numberLabel.setVerticalAlignment(SwingConstants.CENTER);
                    numberLabel.setFont(new Font("Arial", Font.BOLD, 16));
                    numberLabel.setPreferredSize(new Dimension(40, 40));
                    numberLabel.setMinimumSize(new Dimension(40, 40));
                    numberLabel.setMaximumSize(new Dimension(40, 40));
                    numberLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    numberLabel.setBackground(Color.YELLOW);
                    numberLabel.setOpaque(false);

                    boardPanel.add(numberLabel);
                    boardCellLabels.add(numberLabel);
                }
            }
        }

        boardContainer.add(boardPanel, BorderLayout.CENTER);
        add(boardContainer);

        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBackground(new Color(3, 60, 0));

        JLabel photoLabel = new JLabel();
        photoLabel.setIcon(getSelectedPattern().getImage());
        panel1.add(photoLabel);

        cagePanel = new JPanel();
        cagePanel.setLayout(new GridLayout(1, 2, 3, 3));
        JButton spinCage = new JButton("Girar tombola");
        spinCage.setPreferredSize(new Dimension(50, 50));
        cagePanel.add(spinCage);
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

                panel1.revalidate();
                panel1.repaint();

                boardPanel.revalidate();
                boardPanel.repaint();

                isGameFinished();
                isGameOver();
            }
        });

        panel1.add(cagePanel);
        add(panel1, BorderLayout.EAST);

        createUsedNumbersDisplay();
    }

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
            JLabel ballLabel = new JLabel(String.valueOf(allNumbers.get(i-1)));
            ballLabel.setHorizontalAlignment(SwingConstants.CENTER);
            ballLabel.setVerticalAlignment(SwingConstants.CENTER);

            if (allNumbers.get(i-1) < 16) {
                ballLabel.setBackground(Color.RED);
            } else if (allNumbers.get(i-1) < 31) {
                ballLabel.setBackground(Color.BLUE);
            } else if (allNumbers.get(i-1) < 46) {
                ballLabel.setBackground(Color.GREEN);
            } else if (allNumbers.get(i-1) < 61) {
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

    private ImageIcon loadImage(String fileName) {
        java.net.URL imageUrl = getClass().getResource(fileName);
        if (imageUrl != null) {
            return new ImageIcon(imageUrl);
        } else {
            return null;
        }
    }

    public void markUsedNumbers(int value, ArrayList<JLabel> collection) {
        for(JLabel label : collection) {
            if (label.getText().equals(String.valueOf(value))) {
                label.setOpaque(true);
                break;
            }
        }
    }

    public boolean isGameFinished() {
        boolean gameFinished;
        selectedPattern.setPositionsToCheck(selectedPattern.getCombinationIndicator(), game.getUsedNumbers(), game.getBoard().getCells());

        if (selectedPattern.checkPattern(game.getUsedNumbers())) {
            JOptionPane.showMessageDialog(null, "Has hecho BINGO!");
            gameFinished = true;
        } else {
            gameFinished = false;
        }
        return gameFinished;
    }

    public void isGameOver() {
        if (cage.getBalls().isEmpty() && !isGameFinished()) {
            JOptionPane.showMessageDialog(null, "Ya no hay mas bolas de bingo, has perdido.");
        }
    }

    public WinningPattern getSelectedPattern() {
        return selectedPattern;
    }

    public void setSelectedPattern(WinningPattern selectedPattern) {
        this.selectedPattern = selectedPattern;
    }


}
