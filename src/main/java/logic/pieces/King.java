package logic.pieces;

import logic.Board;
import logic.moves.CastlingMove;
import logic.moves.Move;
import logic.pieces.properties.PieceColor;
import logic.pieces.properties.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {
    private boolean hasMoved;

    public King(Position position, PieceColor color) {
        super(position, color);
        this.hasMoved = false;
    }

    public King(King king) {
        super(Position.copyOf(king.getPosition()), king.getPieceColor());
        this.hasMoved = king.getHasMoved();
    }

    @Override
    public List<Move> calculatePotentialMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Position endPosition;

        endPosition = new Position(getPosition(), 1, 0);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));
        endPosition = new Position(getPosition(), 1, 1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));

        endPosition = new Position(getPosition(), 1, -1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));
        endPosition = new Position(getPosition(), -1, 0);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));

        endPosition = new Position(getPosition(), -1, 1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));
        endPosition = new Position(getPosition(), -1, -1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));

        endPosition = new Position(getPosition(), 0, 1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));
        endPosition = new Position(getPosition(), 0, -1);
        if (canMoveTo(endPosition, board)) moves.add(newMove(getPosition(), endPosition, board));

        if (!hasMoved && getShortCastling(board) != null) moves.add(getShortCastling(board));
        if (!hasMoved && getLongCastling(board) != null) moves.add(getLongCastling(board));

        return moves;
    }

    private CastlingMove getShortCastling(Board board) {
        Position checkPos = positionRight(getPosition());
        Position newKingPos = positionRight(positionRight(getPosition()));
        while (positionRight(checkPos).insideBoard()) {
            if (board.isNotEmptyAt(checkPos)) return null;
            checkPos = positionRight(checkPos);
        }
        ChessPiece edgePiece = board.getPieceAt(checkPos);
        if (edgePiece instanceof Castle && !((Castle) edgePiece).getHasMoved()) {
            return new CastlingMove(getPosition(), newKingPos, checkPos, positionRight(getPosition()));
        }
        return null;
    }

    private CastlingMove getLongCastling(Board board) {
        Position checkPos = positionLeft(getPosition());
        Position newKingPos = positionLeft(positionLeft(getPosition()));
        while (positionLeft(checkPos).insideBoard()) {
            if (board.isNotEmptyAt(checkPos)) return null;
            checkPos = positionLeft(checkPos);
        }
        ChessPiece edgePiece = board.getPieceAt(checkPos);
        if (edgePiece instanceof Castle && !((Castle) edgePiece).getHasMoved()) {
            return new CastlingMove(getPosition(), newKingPos, checkPos, positionLeft(getPosition()));
        }
        return null;
    }

    private Position positionRight(Position position) {
        return new Position(position, 1, 0);
    }

    private Position positionLeft(Position position) {
        return new Position(position, -1, 0);
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public ChessPiece copy() {
        return new King(this);
    }

    @Override
    public String getPieceSignature() {
        return pieceColor.getColorSign() + "k";
    }

    @Override
    public String getNotationSignature() {
        return "K";
    }
}
