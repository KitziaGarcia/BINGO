import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {
    private ArrayList<WinningPattern> patterns = new ArrayList<>();
    private WinningPattern selectedPattern;
    private JPanel patternsPanel;
    private JPanel boardContainer;
    private JPanel boardPanel;
    private Bingo game;
    private BingoCage cage;


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
                    /*patternsPanel.removeAll();
                    patternsPanel.revalidate();
                    patternsPanel.repaint();*/

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

                    boardPanel.add(numberLabel);
                }
            }
        }

        boardContainer.add(boardPanel, BorderLayout.CENTER);
        add(boardContainer);





        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBackground(new Color(3, 60, 0));

        JLabel photoLabel = new JLabel();
        photoLabel.setIcon(getSelectedPattern().getImage());
        panel1.add(photoLabel);

        JButton spinCage = new JButton("Girar tombola");
        spinCage.setPreferredSize(new Dimension(200, 200));
        panel1.add(spinCage);
        spinCage.setVisible(true);

        add(panel1, BorderLayout.EAST);

        createHistoricoPanel();
    }

    public void createHistoricoPanel() {
        cage = new BingoCage();

        JPanel historicoPanel = new JPanel();
        historicoPanel.setLayout(new GridLayout(5, 16, 5, 5));

        String[] headers = {"B", "I", "N", "G", "O"};

        for (int i = 0; i < 5; i++) {
            JLabel label = new JLabel(headers[i], SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(40, 40));
            label.setMinimumSize(new Dimension(40, 40));
            label.setMaximumSize(new Dimension(40, 40));
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            historicoPanel.add(label);
        }

        for (int i = 0; i < 75; i++) {
            JLabel ballLabel = new JLabel(String.valueOf(cage.getBalls().get(i).getNumber()));
            ballLabel.setHorizontalAlignment(SwingConstants.CENTER);
            ballLabel.setVerticalAlignment(SwingConstants.CENTER);

            switch (cage.getBalls().get(i).getLetter()) {
                case "B":
                    ballLabel.setBackground(Color.RED);
                    break;
                case "I":
                    ballLabel.setBackground(Color.BLUE);
                    break;
                case "N":
                    ballLabel.setBackground(Color.GREEN);
                    break;
                case "G":
                    ballLabel.setBackground(Color.YELLOW);
                    break;
                case "O":
                    ballLabel.setBackground(Color.MAGENTA);
                    break;
            }

            ballLabel.setOpaque(true);

            historicoPanel.add(ballLabel);
        }

        add(historicoPanel, BorderLayout.NORTH);


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

    public WinningPattern getSelectedPattern() {
        return selectedPattern;
    }

    public void setSelectedPattern(WinningPattern selectedPattern) {
        this.selectedPattern = selectedPattern;
    }


}
