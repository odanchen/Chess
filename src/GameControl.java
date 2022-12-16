import BoardPackage.Board;
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

    private void generatePieceMoves() {
        List<ChessPiece> currentPieces = (movingSide == PieceColor.WHITE) ? this.board.getWhitePieces() : this.board.getBlackPieces();

        for (ChessPiece piece : currentPieces) {
            piece.generateValidMoves(this.board);
        }
    }

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
