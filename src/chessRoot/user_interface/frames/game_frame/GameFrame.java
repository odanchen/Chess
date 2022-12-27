package chessRoot.user_interface.frames.game_frame;

import chessRoot.logic.moves.AttackMove;
import chessRoot.logic.moves.PromotionAttackMove;
import chessRoot.logic.moves.PromotionMove;
import chessRoot.logic.pieces.*;
import chessRoot.user_interface.GraphicsManager;
import chessRoot.user_interface.game_flow.GameControl;
import chessRoot.logic.moves.Move;
import chessRoot.user_interface.game_flow.GameStates;
import chessRoot.user_interface.game_flow.GameStatus;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static chessRoot.user_interface.game_flow.GameStates.*;

public class GameFrame extends JFrame {
    private final BoardPanel boardPanel;
    private final PiecePanel piecePanel;
    private final IndicationPanel indicPanel;
    private final PromotionPanel promPanel;
    private final GameStatus gameStatus;
    private final GameControl gameControl;
    private final GraphicsManager graphicsManager;

    public GameFrame(GameStatus gameStatus, GameControl gameControl, GraphicsManager graphicsManager) {
        this.gameStatus = gameStatus;
        this.gameControl = gameControl;
        this.graphicsManager = graphicsManager;

        boardPanel = new BoardPanel(graphicsManager);
        piecePanel = new PiecePanel(graphicsManager, gameStatus);
        indicPanel = new IndicationPanel(graphicsManager, gameStatus);
        promPanel = new PromotionPanel(graphicsManager, gameStatus);
        addBasicParameters();
    }

    private void addBasicParameters() {
        setUndecorated(true);
        setBounds(graphicsManager.getGameFrameBounds());
        addPanels();
        createMouseListener();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addPanels() {
        add(promPanel);
        add(piecePanel);
        add(indicPanel);
        add(boardPanel);
    }

    private void playerTurnEvent(MouseEvent e) {
        if (isActionSelect(e)) actionSelect(e);
    }

    private void playerSelectedAPieceEvent(MouseEvent e) {
        if (isActionReselect(e)) reselectPiece(e);
        else if (isActionDeselect(e)) deselectPiece();
        else if (isActionMove(e)) makeMove(e);
    }

    private void playerPromotionEvent(MouseEvent e) {
        ChessPiece desiredPiece = desiredPieceToPromoteTo(e);
        if (desiredPiece != null) {
            if (gameStatus.getBoard().getPieceAt(promPanel.getMove().getEndPosition()) == null) { // Promotion move
                PromotionMove promMove = new PromotionMove(promPanel.getMove().getStartPosition(), promPanel.getMove().getEndPosition(),desiredPiece);
                makeMove(promMove);
            }
            else { // Attacking promotion move.
                PromotionAttackMove promMove = new PromotionAttackMove(promPanel.getMove().getStartPosition(),promPanel.getMove().getEndPosition(),desiredPiece,promPanel.getMove().getEndPosition());
                makeMove(promMove);
            }
        }
    }

    private ChessPiece desiredPieceToPromoteTo(MouseEvent e) {
        Position clickedPos = getPositionOnTheBoard(e);
        Position endPos = promPanel.getMove().getEndPosition();
        PieceColor color = promPanel.getMove().getPieceAtStart(gameStatus.getBoard()).getPieceColor();
        if (clickedPos.getCol() != endPos.getCol()) return null;
        else if (Math.abs(clickedPos.getRow() - endPos.getRow()) == 0) return new Queen(endPos, color);
        else if (Math.abs(clickedPos.getRow() - endPos.getRow()) == 1) return new Knight(endPos, color);
        else if (Math.abs(clickedPos.getRow() - endPos.getRow()) == 2) return new Castle(endPos, color);
        else if (Math.abs(clickedPos.getRow() - endPos.getRow()) == 3) return new Bishop(endPos, color);
        return null;

    }


    private void onMousePress(MouseEvent e) {
        System.out.println(gameStatus.getState().toString());
        switch (gameStatus.getState()) {
            case WHITE_TURN:
            case BLACK_TURN:
                playerTurnEvent(e);
                break;
            case BLACK_SELECTED_PIECE:
            case WHITE_SELECTED_PIECE:
                playerSelectedAPieceEvent(e);
                break;
            case WHITE_PROMOTION:
            case BLACK_PROMOTION:
                playerPromotionEvent(e);
                break;
        }
    }

    private void createMouseListener() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                onMousePress(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void actionSelect(MouseEvent e) {
        ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(getPositionOnTheBoard(e));
        selectPiece(clickedPiece);
    }

    private boolean isActionSelect(MouseEvent e) {
        if (isClickOutsideBoard(e)) return false;
        ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(getPositionOnTheBoard(e));
        return (isClickedPieceRightColor(clickedPiece));
    }

    private boolean isClickedPieceRightColor(ChessPiece clickedPiece) {
        if (clickedPiece == null) return false;

        if (gameStatus.getState() == BLACK_SELECTED_PIECE || gameStatus.getState() == BLACK_TURN) {
            return clickedPiece.isBlack();
        } else {
            return clickedPiece.isWhite();
        }
    }

    private GameStates stateAfterMove(Move move) {
        if (isPromotingMove(move) && gameStatus.getState() == BLACK_SELECTED_PIECE) return BLACK_PROMOTION;
        else if (isPromotingMove(move) && gameStatus.getState() == WHITE_SELECTED_PIECE) return WHITE_PROMOTION;
        else if (gameStatus.getState() == BLACK_SELECTED_PIECE || gameStatus.getState() == BLACK_PROMOTION) return WHITE_TURN;
        return BLACK_TURN;
    }

    private void makeMove(MouseEvent e) {
        Position clickedPosition = getPositionOnTheBoard(e);

        Move moveToMake = gameStatus.getSelectedPieceMoves().stream()
                .filter(move -> move.getEndPosition().equals(clickedPosition))
                .findFirst().orElse(null);
        gameControl.performMove(moveToMake);
    }

    public void makeMove(Move move) {
        if (!isPromotingMove(move)) gameStatus.getBoard().makeMove(move);
        gameStatus.setGameState(stateAfterMove(move));
        gameStatus.deselectPiece();
        updateFrame(move);
    }

    public boolean isPromotingMove(Move move) {
        // If it's a pawn and is moving to row 1 or 8.
        return (!(move instanceof PromotionAttackMove) && !(move instanceof PromotionMove) &&
                move.getPieceAtStart(gameStatus.getBoard()) instanceof Pawn && (move.getEndPosition().getRow() == 1 || move.getEndPosition().getRow() == 8));
    }


    private boolean isActionMove(MouseEvent e) {
        if (isActionReselect(e)) return false;
        Position clickedPosition = getPositionOnTheBoard(e);
        return gameStatus.getSelectedPieceMoves().stream().anyMatch(move -> move.getEndPosition().equals(clickedPosition));
    }

    private boolean isActionReselect(MouseEvent e) {
        if (isClickOutsideBoard(e)) return false;
        Position clickedPosition = getPositionOnTheBoard(e);
        ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(clickedPosition);
        return (isClickedPieceRightColor(clickedPiece) && clickedPiece != gameStatus.getSelectedPiece());
    }

    private void reselectPiece(MouseEvent e) {
        Position clickedPosition = getPositionOnTheBoard(e);
        ChessPiece clickedPiece = gameStatus.getBoard().getPieceAt(clickedPosition);
        selectPiece(clickedPiece);
    }

    private GameStates getStateAfterSelect() {
        switch (gameStatus.getState()) {
            case BLACK_TURN:
            case BLACK_SELECTED_PIECE:
                return BLACK_SELECTED_PIECE;
            case WHITE_TURN:
            case WHITE_SELECTED_PIECE:
                return WHITE_SELECTED_PIECE;
        }
        return null;
    }

    private void selectPiece(ChessPiece piece) {
        gameStatus.setGameState(getStateAfterSelect());
        gameStatus.selectPiece(piece);
        indicPanel.updatePanel();
    }

    private GameStates getStateAfterDeselect() {
        if (gameStatus.getState() == BLACK_SELECTED_PIECE) return BLACK_TURN;
        else return WHITE_TURN;
    }

    private boolean isActionDeselect(MouseEvent e) {
        if (isClickOutsideBoard(e)) return true;
        Position clickedPosition = getPositionOnTheBoard(e);
        return gameStatus.getSelectedPieceMoves().stream().noneMatch(move -> move.getEndPosition().equals(clickedPosition));
    }

    private void deselectPiece() {
        gameStatus.setGameState(getStateAfterDeselect());
        gameStatus.deselectPiece();
        indicPanel.updatePanel();
    }

    public void updateFrame(Move move) {
        piecePanel.updatePanel();
        indicPanel.updatePanel();
        promPanel.updatePanel((isPromotingMove(move)) ? move : null);
    }

    private boolean isClickOutsideBoard(MouseEvent e) {
        int rect = graphicsManager.getEdgeSize();
        int board = graphicsManager.getBoardSize();

        return (e.getX() <= rect) || (e.getX() >= board + rect) || (e.getY() <= rect) || (e.getY() >= board + rect);
    }

    private Position getPositionOnTheBoard(MouseEvent e) {
        int row = Math.abs((e.getY() - graphicsManager.getEdgeSize()) / graphicsManager.getSquareSize() - (graphicsManager.isFlipped() ? 7 : 0));
        int col = Math.abs((e.getX() - graphicsManager.getEdgeSize()) / graphicsManager.getSquareSize() - (graphicsManager.isFlipped() ? 7 : 0));

        return new Position((char) (col + 'a'), (8 - row));
    }
}
