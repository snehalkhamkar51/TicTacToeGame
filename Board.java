import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {
    private final JButton[][] buttons;
    private boolean xTurn; // True for X's turn, false for O's turn

    public Board() {
        this.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        xTurn = true;

        // Initialize buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setPreferredSize(new Dimension(100, 100));
                buttons[i][j].addActionListener(this);
                this.add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("") && !isGameOver()) {
            if (xTurn) {
                clickedButton.setText("X");
            } else {
                clickedButton.setText("O");
            }
            xTurn = !xTurn;
            checkForWinner();
        }
    }

    private boolean isGameOver() {
        // Check if the game is over (win or draw)
        return checkForWin("X") || checkForWin("O") || isBoardFull();
    }

    private boolean checkForWin(String player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((buttons[i][0].getText().equals(player) && buttons[i][1].getText().equals(player)
                    && buttons[i][2].getText().equals(player)) ||
                    (buttons[0][i].getText().equals(player) && buttons[1][i].getText().equals(player)
                            && buttons[2][i].getText().equals(player))) {
                showWinner(player);
                return true;
            }
        }
        // Check diagonals
        if ((buttons[0][0].getText().equals(player) && buttons[1][1].getText().equals(player)
                && buttons[2][2].getText().equals(player)) ||
                (buttons[0][2].getText().equals(player) && buttons[1][1].getText().equals(player)
                        && buttons[2][0].getText().equals(player))) {
            showWinner(player);
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        showDraw();
        return true;
    }

    private void showWinner(String player) {
        JOptionPane.showMessageDialog(this, player + " wins!");
        resetBoard();
    }

    private void showDraw() {
        JOptionPane.showMessageDialog(this, "It's a draw!");
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        xTurn = true;
    }

    private void checkForWinner() {
        if (isGameOver()) {
            // The game is over, no need to check further
            return;
        }
    }
}
