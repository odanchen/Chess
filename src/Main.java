import BoardPackage.Board;
import pieces.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.fillTestConfiguration();


        for (int digit = 1; digit <= 8; digit++)
        {
            for (char letter = 'a'; letter <= 'h'; letter++)
            {
                if (board.getPieceAt(letter, digit) != null)
                {
                    board.getPieceAt(letter, digit).calculateMoves(board);
                }
            }
        }

        System.out.print("finish");
    }
}