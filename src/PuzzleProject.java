import javax.swing.*;
import java.awt.*;

public class PuzzleProject extends JFrame {

    public PuzzleProject() {
        super("Puzzle Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout( new BorderLayout());
        setLocationRelativeTo(null);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout (new GridLayout(4,4,5,5));

        for ( int i = 1; i <= 15; i++ ) {
            gridPanel.add( new JButton(String.valueOf(i)));


        }
        gridPanel.add( new JButton(""));   // empty tile
        add(gridPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PuzzleProject::new);

    }
}