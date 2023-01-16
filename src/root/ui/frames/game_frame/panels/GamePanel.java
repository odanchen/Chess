package root.ui.frames.game_frame.panels;

import root.logic.moves.*;
import root.logic.pieces.*;
import root.logic.pieces.properties.PieceColor;
import root.logic.pieces.properties.Position;
import root.ui.frames.game_frame.GameFrame;
import root.ui.frames.game_frame.listener.MouseListener;
import root.ui.game_flow.GameResult;
import root.ui.game_flow.GameStates;
import root.ui.game_flow.GameStatus;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

import static root.ui.game_flow.GameStates.*;

public class GamePanel extends JPanel {
    private final BoardPanel boardPanel;
    private final PiecePanel piecePanel;
    private final IndicationPanel indicPanel;
    private final PromotionPanel promPanel;
    private final GameStatus gameStatus;
    private final GraphicsManager graphicsManager;
    private final GameFrame gameFrame;
    private final DragPanel dragPanel;
    private final MouseListener mouseListener;

    public GamePanel(GameStatus gameStatus, GraphicsManager graphicsManager, GameFrame gameFrame) {
        this.gameStatus = gameStatus;
        this.graphicsManager = graphicsManager;
        this.boardPanel = new BoardPanel(graphicsManager);
        this.piecePanel = new PiecePanel(graphicsManager, gameStatus);
        this.indicPanel = new IndicationPanel(graphicsManager, gameStatus);
        this.promPanel = new PromotionPanel(graphicsManager, gameStatus);
        this.dragPanel = new DragPanel(graphicsManager, gameStatus);
        this.gameFrame = gameFrame;
        this.mouseListener = new MouseListener(this, dragPanel);

        addBasicParameters();
    }

    private void addBasicParameters() {
        setLayout(new GroupLayout(this));
        setBounds(graphicsManager.getGamePanelBounds());
        addPanels();
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        setVisible(true);
        setOpaque(false);
        validate();
    }

    private void addPanels() {
        add(dragPanel);
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
        if (move != null) makeMove(move);
    }

    public void onMousePress(MouseEvent e) {
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

    public void onMouseRelease(MouseEvent e) {
        if (isActionPromotion(e)) actionPromotion(e);
        else if (isActionMove(e)) makeMove(e);
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
        SwingUtilities.windowForComponent(indicPanel).repaint();
        validate();
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
        makeMove(getMoveOnClick(e));
    }

    public void makeMove(Move move) {
        if (graphicsManager.flipToggle()) flipPanel();
        gameStatus.getBoard().makeMove(move);
        gameStatus.handleTimer(move);
        gameStatus.logMove(move);
        gameStatus.setGameState(stateAfterMove());
        gameStatus.deselectPiece();
        gameStatus.deselectMove();
        updatePanel();
        checkGameEnd();
        validate();
    }

    private void checkGameEnd() {
        if (gameStatus.isGameEnd()) {
            gameStatus.updateGameResult();
            gameFrame.swapToEndFrame();
        }
    }

    public void endGame(GameResult result) {
        gameStatus.setGameResult(result);
        gameFrame.swapToEndFrame();
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
        SwingUtilities.windowForComponent(indicPanel).repaint();
        validate();
    }

    private GameStates getStateAfterDeselect() {
        if (gameStatus.getState() == BLACK_SELECTED_PIECE) return BLACK_TURN;
        else return WHITE_TURN;
    }

    private boolean isActionDeselect(MouseEvent e) {
        if (isClickOutsideBoard(e)) return true;
        if (gameStatus.getPieceAt(getPositionOnTheBoard(e)) == null && !isActionMove(e)) return true;
        if (!gameStatus.isPieceSelected()) return false;
        if (gameStatus.getPieceAt(getPositionOnTheBoard(e)) != null && gameStatus.getPieceAt(getPositionOnTheBoard(e)).equals(gameStatus.getSelectedPiece()))
            return false;
        return getMoveOnClick(e) == null;
    }

    private void deselectPiece() {
        gameStatus.setGameState(getStateAfterDeselect());
        gameStatus.deselectPiece();
        SwingUtilities.windowForComponent(indicPanel).repaint();
        validate();
    }

    public void updatePanel() {
        List.of(piecePanel, indicPanel, promPanel)
                .forEach(panel -> SwingUtilities.windowForComponent(panel).repaint());
        validate();
    }

    public void flipPanel() {
        graphicsManager.flipBoard();
        List.of(piecePanel, indicPanel, promPanel, boardPanel)
                .forEach(panel -> SwingUtilities.windowForComponent(panel).repaint());
        validate();
    }

    @Override
    public Dimension getPreferredSize() {
        return graphicsManager.getGamePanelBounds().getSize();
    }

    private boolean isClickOutsideBoard(MouseEvent e) {
        int rect = graphicsManager.getEdgeSize();
        int board = graphicsManager.getBoardSize();

        return (e.getX() <= rect) || (e.getX() >= board + rect) || (e.getY() <= rect) || (e.getY() >= board + rect);
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setUndrawnPiece(MouseEvent e) {
        if (gameStatus.getPieceAt(getPositionOnTheBoard(e)) == null) return;
        if (!gameStatus.getPieceAt(getPositionOnTheBoard(e)).equals(gameStatus.getSelectedPiece())) return;
        piecePanel.setUndrawnPosition(gameStatus.getPieceAt(getPositionOnTheBoard(e)));
        piecePanel.repaint();
    }

    public void setUndrawnPieceNull() {
        piecePanel.setUndrawnPosition(null);
        piecePanel.repaint();
    }

    private Position getPositionOnTheBoard(MouseEvent e) {
        int row = Math.abs((e.getY() - graphicsManager.getEdgeSize()) / graphicsManager.getSquareSize() - (graphicsManager.isFlipped() ? 7 : 0));
        int col = Math.abs((e.getX() - graphicsManager.getEdgeSize()) / graphicsManager.getSquareSize() - (graphicsManager.isFlipped() ? 7 : 0));

        return new Position((char) (col + 'a'), (8 - row));
    }
}
