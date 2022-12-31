package chessRoot.user_interface.frames.game_frame;

import chessRoot.logic.moves.*;
import chessRoot.logic.pieces.*;
import chessRoot.user_interface.GraphicsManager;
import chessRoot.user_interface.game_flow.GameControl;
import chessRoot.user_interface.game_flow.GameStates;
import chessRoot.user_interface.game_flow.GameStatus;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static chessRoot.user_interface.game_flow.GameStates.*;

public class GamePanel extends JPanel {
    private final BoardPanel boardPanel;
    private final PiecePanel piecePanel;
    private final IndicationPanel indicPanel;
    private final PromotionPanel promPanel;
    private final GameStatus gameStatus;
    private final GameControl gameControl;
    private final GraphicsManager graphicsManager;

    public GamePanel(GameStatus gameStatus, GameControl gameControl, GraphicsManager graphicsManager) {
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
        setBounds(graphicsManager.getGamePanelBounds());
        addPanels();
        createMouseListener();
        setVisible(true);
        setOpaque(false);
        validate();
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
        else if (isActionPromotion(e)) actionPromotion(e);
        else if (isActionMove(e)) makeMove(e);
    }

    private void piecePromotionEvent(MouseEvent e) {
        Move move = createPromotionMove(e);
        if (move != null) gameControl.performMove(move);
    }

    private void onMousePress(MouseEvent e) {
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
                piecePromotionEvent(e);
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

    private Move createPromotionMove(MouseEvent e) {
        ChessPiece newPiece = desiredPieceToPromoteTo(e);
        if (newPiece == null) return null;

        if (gameStatus.getSelectedMove() instanceof RelocationMove)
            return new PromotionMove((RelocationMove) gameStatus.getSelectedMove(), newPiece);
        return new PromotionAttackMove((AttackMove) gameStatus.getSelectedMove(), newPiece);
    }

    private ChessPiece desiredPieceToPromoteTo(MouseEvent e) {
        Position clickedPos = getPositionOnTheBoard(e);
        Position endPos = gameStatus.getSelectedMove().getEndPosition();
        PieceColor color = gameStatus.getSelectedColor();
        if (clickedPos.getCol() != endPos.getCol()) return null;

        switch (Math.abs(clickedPos.getRow() - endPos.getRow())) {
            case 0:
                return new Queen(endPos, color);
            case 1:
                return new Knight(endPos, color);
            case 2:
                return new Castle(endPos, color, true);
            case 3:
                return new Bishop(endPos, color);
        }
        return null;
    }

    private void actionPromotion(MouseEvent e) {
        if (gameStatus.getState() == BLACK_SELECTED_PIECE) gameStatus.setGameState(BLACK_PROMOTION);
        else gameStatus.setGameState(WHITE_PROMOTION);
        gameStatus.selectMove(getMoveOnClick(e));
        promPanel.updatePanel();
    }

    private boolean isActionPromotion(MouseEvent e) {
        Move move = getMoveOnClick(e);
        return move != null && isMovePromotional(move);
    }

    private Move getMoveOnClick(MouseEvent e) {
        Position clickedPos = getPositionOnTheBoard(e);
        if (gameStatus.getSelectedPiece() == null) return null;
        return gameStatus.getSelectedPieceMoves().stream()
                .filter(move -> move.getEndPosition().equals(clickedPos))
                .findAny().orElse(null);
    }

    private void actionSelect(MouseEvent e) {
        ChessPiece clickedPiece = gameStatus.getPieceAt(getPositionOnTheBoard(e));
        selectPiece(clickedPiece);
    }

    private boolean isActionSelect(MouseEvent e) {
        if (isClickOutsideBoard(e)) return false;
        ChessPiece clickedPiece = gameStatus.getPieceAt(getPositionOnTheBoard(e));
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

    private GameStates stateAfterMove() {
        switch (gameStatus.getState()) {
            case BLACK_SELECTED_PIECE:
            case BLACK_PROMOTION:
                return WHITE_TURN;
            case WHITE_SELECTED_PIECE:
            case WHITE_PROMOTION:
                return BLACK_TURN;
        }
        return null;
    }

    private void makeMove(MouseEvent e) {
        gameStatus.selectMove(getMoveOnClick(e));
        gameControl.performMove(gameStatus.getSelectedMove());
    }

    public void makeMove(Move move) {
        gameStatus.getBoard().makeMove(move);
        gameStatus.setGameState(stateAfterMove());
        gameStatus.deselectPiece();
        gameStatus.deselectMove();
        updatePanel();

        checkGameEnd();
    }

    private void checkGameEnd() {
        if (gameStatus.getBoard().isCheckmate(PieceColor.WHITE)) System.out.println("player white lost due to a checkmate");
        else if (gameStatus.getBoard().isStalemate(PieceColor.WHITE)) System.out.println("draw due to a stalemate");
        else if (gameStatus.getBoard().isCheckmate(PieceColor.BLACK)) System.out.println("player black lost due to a checkmate");
        else if (gameStatus.getBoard().isStalemate(PieceColor.BLACK)) System.out.println("draw due to a stalemate");
    }

    public boolean isMovePromotional(Move move) {
        ChessPiece startPiece = move.getPieceAtStart(gameStatus.getBoard());
        if (!(startPiece instanceof Pawn)) return false;
        return ((startPiece.isWhite() && move.getEndPosition().getRow() == 8) ||
                (startPiece.isBlack() && move.getEndPosition().getRow() == 1));
    }


    private boolean isActionMove(MouseEvent e) {
        if (isActionReselect(e)) return false;
        Move move = getMoveOnClick(e);
        return move != null && !isMovePromotional(move);
    }

    private boolean isActionReselect(MouseEvent e) {
        if (isClickOutsideBoard(e)) return false;
        Position clickedPosition = getPositionOnTheBoard(e);
        ChessPiece clickedPiece = gameStatus.getPieceAt(clickedPosition);
        return (isClickedPieceRightColor(clickedPiece) && clickedPiece != gameStatus.getSelectedPiece());
    }

    private void reselectPiece(MouseEvent e) {
        Position clickedPosition = getPositionOnTheBoard(e);
        ChessPiece clickedPiece = gameStatus.getPieceAt(clickedPosition);
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
        return getMoveOnClick(e) == null;
    }

    private void deselectPiece() {
        gameStatus.setGameState(getStateAfterDeselect());
        gameStatus.deselectPiece();
        indicPanel.updatePanel();
    }

    public void updatePanel() {
        piecePanel.updatePanel();
        indicPanel.updatePanel();
        promPanel.updatePanel();
    }

    public void flipPanel() {
        graphicsManager.flipBoard();
        piecePanel.updatePanel();
        indicPanel.updatePanel();
        promPanel.updatePanel();
        boardPanel.updatePanel();
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
