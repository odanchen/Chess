import board_package.Board;
import pieces.ChessPiece;
import pieces.PieceColor;
import pieces.moves.Move;

import java.util.List;

public class GameControl {
    private PieceColor movingSide;
    private Board board;
    private List<Move> gameLog;

    private void setMovingSide(PieceColor movingSide) {
        this.movingSide = movingSide;
    }

    private void setBoard(Board board) {
        this.board = board;
    }

    private void swapPlayer() {
        this.movingSide = (this.movingSide == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    /**
     * Generates the valid moves for all pieces in given board object.
     */
    private void generatePieceMoves() {
        List<ChessPiece> currentPieces = (movingSide == PieceColor.WHITE) ? this.board.getWhitePieces() : this.board.getBlackPieces();

        for (ChessPiece piece : currentPieces) {
            piece.generateValidMoves(this.board);
        }
    }

    /**
     * Default way to run the game. Is usable at any starting position.
     * @param board The given board.
     * @param movingSide The side moving first.
     * @return 1 If white has won, 0 if stalemated, and -1 if black has won.
     */
    public int runTheGame(Board board, PieceColor movingSide) {
        this.setMovingSide(movingSide);
        this.setBoard(board);

        generatePieceMoves();
        while (!this.board.isMate(this.movingSide)) {
            //The player picks the move
            //this.board.makeMove(move);
            //this.gameLog.add(move);
            this.swapPlayer();
            this.generatePieceMoves();
        }

        return 0;
    }
}
