import javax.swing.JFrame;

public class TicTacToeGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe");
        Board board = new Board();
        frame.add(board);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
