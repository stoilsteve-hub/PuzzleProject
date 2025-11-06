import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuzzleProject extends JFrame {
    private static final int SIZE = 4;
    private static final int TILE_COUNT = SIZE * SIZE;
    private final JButton[] buttons = new JButton[TILE_COUNT];
    private final int[] tiles = new int[TILE_COUNT];

    public PuzzleProject() {
        super("Puzzle Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel grid = new JPanel(new GridLayout(SIZE, SIZE, 5, 5));

        // Fill the board in solved order
        for (int i = 0; i < TILE_COUNT - 1; i++) {
            tiles[i] = i + 1;
        }
        tiles[TILE_COUNT - 1] = 0;

        // Create buttons and link to click handler
        for (int i = 0; i < TILE_COUNT; i++) {
            final int index = i;
            buttons[i] = new JButton(tiles[i] == 0 ? "" : String.valueOf(tiles[i]));
            buttons[i].addActionListener(e -> moveTile(index));
            grid.add(buttons[i]);
        }

        add(grid, BorderLayout.CENTER);

        //New Game button addition
        JPanel controls = new JPanel();
        JButton newGameButton = new JButton("New Game");
        controls.add(newGameButton);
        add(controls, BorderLayout.SOUTH);

        newGameButton.addActionListener(e -> shuffle());

        // make sure texts are in sync (safe)
        refreshAllButtons();

        setVisible(true);
    }

    private void moveTile(int index) {
        int empty = findEmpty();
        if (isNextToEmpty(index, empty)) {
            int temp = tiles[index];
            tiles[index] = tiles[empty];
            tiles[empty] = temp;

            // update only the two buttons that changed
            buttons[index].setText(tiles[index] == 0 ? "" : String.valueOf(tiles[index]));
            buttons[empty].setText(tiles[empty] == 0 ? "" : String.valueOf(tiles[empty]));

            if (isSolved()) {
                JOptionPane.showMessageDialog(this, "Congratulations, you won!");
            }
        }
    }

    private int findEmpty() {
        for (int i = 0; i < TILE_COUNT; i++) {
            if (tiles[i] == 0) return i;
        }
        return -1;
    }

    // check next to each other
    private boolean isNextToEmpty(int index, int empty) {
        if (index == empty - 1 && empty % SIZE != 0) return true;  // left
        if (index == empty + 1 && index % SIZE != 0) return true;  // right
        if (index == empty - SIZE) return true;                    // above
        if (index == empty + SIZE) return true;                    // below
        return false;
    }

    private void refreshAllButtons() {
        for (int i = 0; i < TILE_COUNT; i++) {
            buttons[i].setText(tiles[i] == 0 ? "" : String.valueOf(tiles[i]));
        }
    }

    private void shuffle() {
        List<Integer> list = new ArrayList<>(TILE_COUNT);
        for (int i = 0; i < TILE_COUNT; i++) list.add(i); // 0 = empty
        Collections.shuffle(list);
        for (int i = 0; i < TILE_COUNT; i++) tiles[i] = list.get(i);
        refreshAllButtons();
    }
    private boolean isSolved() {
        // positions 0..14 must be 1..15, and the last must be 0
        for (int i = 0; i < TILE_COUNT - 1; i++) {
            if (tiles[i] != i + 1) return false;
        }
        return tiles[TILE_COUNT - 1] == 0;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(PuzzleProject::new);
    }
}
