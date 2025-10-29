import javax.swing.*;
import java.awt.*;

public class PuzzleProject extends JFrame {

    private final JButton [] buttons = new JButton[16];
    private final int[] tiles = new int[16];

    public PuzzleProject() {
        super("Puzzle Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout( new BorderLayout());
        setLocationRelativeTo(null);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout (new GridLayout(4,4,5,5));

        for ( int i = 1; i <= 15; i++ ) {
            JButton b = new JButton(String.valueOf(i));
            buttons[i-1] = b;
            gridPanel.add(b);


        }
        JButton empty = new JButton("");
        buttons[15] = empty;
        gridPanel.add(empty);
        add(gridPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PuzzleProject::new);

    }
}