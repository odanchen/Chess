import BoardPackage.Board;
import pieces.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.fillTestConfiguration();


        board = board.makeMove(new Position('e', 7), new Position('d',6));

        System.out.print("finish");
    }
}