import pieces.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.fillStandardBoard();


        for (int digit = 1; digit <= 8; digit++)
        {
            for (char letter = 'a'; letter <= 'h'; letter++)
            {
                if (board.getPieceAt(letter, digit) == null) System.out.print("  ");
                else
                {
                    if (board.getPieceAt(letter, digit) instanceof Pawn) System.out.print("P ");
                    else if (board.getPieceAt(letter, digit) instanceof King) System.out.print("K ");
                    else if (board.getPieceAt(letter, digit) instanceof Knight) System.out.print("K ");
                    else if (board.getPieceAt(letter, digit) instanceof Castle) System.out.print("C ");
                    else if (board.getPieceAt(letter, digit) instanceof Queen) System.out.print("Q ");
                    else if (board.getPieceAt(letter, digit) instanceof Bishop) System.out.print("B ");
                }
            }
            System.out.print('\n');
        }
    }
}