package chessRoot;

import chessRoot.board_package.Board;
import chessRoot.pieces.PieceColor;
import chessRoot.pieces.moves.Move;

import java.io.IOException;
import java.util.List;

public class GameControl {
    private PieceColor movingSide;
    private Board board;
    private List<Move> gameLog;
    private Display display;

    public GameControl(Board board) {
        this.display = new Display(board);
        this.movingSide = PieceColor.WHITE ;
        this.board = board;
    }


    private void setMovingSide(PieceColor movingSide) {
        this.movingSide = movingSide;
    }

    private void setBoard(Board board) {
        this.board = board;
    }

    private void swapPlayer() {
        this.movingSide = (this.movingSide == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    private void performMove(Move move)
    {
        board.makeMove(move);
        display.updatePieces();
    }

    /**
     * Default way to run the game. Is usable at any starting position.
     * @param board The given board.
     * @param movingSide The side moving first.
     * @return Enum for different outcomes of game.
     */
    public GameResult runTheGame(Board board, PieceColor movingSide) throws IOException {
        this.setMovingSide(movingSide);
        this.setBoard(board);

        while (!this.board.isMate(this.movingSide)) {
            performMove(display.pickTheMove(this.movingSide));
            this.swapPlayer();
        }

        return GameResult.PLAYER_WHITE_WON_BY_CHECKMATE;
    }
}
