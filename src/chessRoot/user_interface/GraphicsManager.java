package chessRoot.user_interface;

import chessRoot.assets.board_colors.BoardColors;
import chessRoot.assets.board_colors.ColorSet;
import chessRoot.logic.pieces.ChessPiece;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.Font.PLAIN;


public class GraphicsManager {
    private String pieceTextureFolder = "cburnett";
    private final String[] pieceTextureFolders = {"cburnett", "kilifiger", "kosal", "leipzig", "maya", "pirat", "regular"};
    private ColorSet boardColors;
    private final int boardSize;
    private boolean isFlipped = false;
    private final TextureHolder textureHolder;

    public int getBoardSize() {
        return boardSize;
    }

    public int getOutlineSize() {
        return getSquareSize() / 6;
    }

    public Color getWhiteSquareColor() {
        return boardColors.getWhiteCell();
    }

    public Color getBlackSquareColor() {
        return boardColors.getBlackCell();
    }

    public Color getSelectionColor() {
        return boardColors.getCellSelection();
    }

    public int getSquareSize() {
        return getBoardSize() / 8;
    }

    public int getEdgeSize() {
        return getSquareSize() / 2;
    }

    public Rectangle getPlayAreaBounds() {
        return new Rectangle(getEdgeSize(), getEdgeSize(), getBoardSize(), getBoardSize());
    }

    public Rectangle getBoardPanelBounds() {
        int newRectSize = 2 * getEdgeSize() + getBoardSize();
        return new Rectangle(0, 0, newRectSize, newRectSize);
    }

    public Rectangle getGamePanelBounds() {
        int newRectSize = 2 * getEdgeSize() + getBoardSize();
        return new Rectangle(getSquareSize(), getSquareSize(), newRectSize, newRectSize);
    }

    public Rectangle getGameBounds() {
        int newRectWidth = 2 * getEdgeSize() + getBoardSize() + 5 * getSquareSize();
        int newRectHeight = 3 * getEdgeSize() + getBoardSize() + 2 * getSquareSize();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int xCor = (int) ((screen.getWidth() - newRectWidth) / 2);
        int yCor = (int) ((screen.getHeight() - newRectHeight) / 2);
        return new Rectangle(xCor, yCor, newRectWidth, newRectHeight);
    }

    public Rectangle getMenuBounds() {
        int newRectWidth = 600;
        int newRectHeight = 600;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int xCor = (int) ((screen.getWidth() - newRectWidth) / 2);
        int yCor = (int) ((screen.getHeight() - newRectHeight) / 2);
        return new Rectangle(xCor, yCor, newRectWidth, newRectHeight);
    }

    public Rectangle getFlipButtonBounds() {
        int frameWidth = 2 * getEdgeSize() + getBoardSize() + 5 * getSquareSize();
        return new Rectangle(frameWidth / 10 * 8, getSquareSize() + getEdgeSize(), getSquareSize(), getSquareSize() / 2);
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void flipBoard() {
        isFlipped = !isFlipped;
    }

    private int getPieceSize() {
        return (int) (getSquareSize() * 0.875);
    }

    public int getPieceCoordinate(int idx) {
        return (getSquareSize() * idx) + (getSquareSize() - getPieceSize()) / 2;
    }

    public int getMovingOvalSize() {
        return (int) (getSquareSize() * 0.3);
    }

    public int getMovingCoordinate(int idx) {
        return (getSquareSize() * idx) + (getSquareSize() - getMovingOvalSize()) / 2;
    }

    public int getAttackingOvalSize() {
        return (int) (getSquareSize() * 0.85);
    }

    public int getAttackingLineSize() {
        return getSquareSize() / 12;
    }

    public int getAttackingCoordinate(int idx) {
        return (getSquareSize() * idx) + (getSquareSize() - getAttackingOvalSize()) / 2;
    }

    public BufferedImage getTextureOfPiece(ChessPiece piece) {
        return textureHolder.getPieceTexture(piece.getPieceSignature());
    }

    public BufferedImage getTextureOfPiece(String signature) {
        return textureHolder.getPieceTexture(signature);
    }

    public BufferedImage getLetterImage(char letter) {
        return textureHolder.getLetterImage(letter);
    }

    public GraphicsManager() {
        boardSize = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3);

        Font letterFont = new Font("Monospaced", PLAIN, getSquareSize() / 3);
        boardColors = BoardColors.OPTION1;
        textureHolder = new TextureHolder(boardColors, letterFont, pieceTextureFolder, getPieceSize());
    }
}
