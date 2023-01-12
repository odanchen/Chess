package root.ui.graphics;

import root.assets.colors.BoardColors;
import root.assets.colors.ColorSet;
import root.logic.pieces.ChessPiece;

import javax.swing.*;
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

    public int getCenterOfScreenX(int width) {
        return (int) (getGameBounds().getSize().getWidth() / 2) - width / 2;
    }

    public int getCenterOfScreenY(int height) {
        return (int) (getGameBounds().getSize().getHeight() / 2) - height / 2;
    }

    public Rectangle getBackgroundBounds() {
        int newRectWidth = 2 * getEdgeSize() + getBoardSize() + 5 * getSquareSize();
        int newRectHeight = 3 * getEdgeSize() + getBoardSize() + 2 * getSquareSize();
        return new Rectangle(0, 0, newRectWidth, newRectHeight);
    }

    public Rectangle getFlipButtonBounds() {
        int startX = (getBoardPanelBounds().x + getBoardPanelBounds().width + getSquareSize() + getGameBounds().width - getSquareSize() - getEdgeSize()) / 2;
        return new Rectangle(startX, getSquareSize() + getEdgeSize(), getSquareSize() + getEdgeSize(), getSquareSize() + getEdgeSize());
    }

    public Rectangle getEndPanelBounds() {
        int edge = getEdgeSize();
        int sqr = getSquareSize();
        return new Rectangle(edge + (int) (sqr * 1.5), edge + (int) (sqr * 1.5), sqr * 5, sqr * 5);
    }

    public Dimension getTextButtonDimension() {
        return new Dimension(getEdgeSize() * 6, getEdgeSize() * 2);
    }

    public Dimension getFrameDimension() {
        return getGameBounds().getSize();
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

    public String getPathToTexture(String pieceSignature) {
        return textureHolder.getPathToPieceTexture(pieceSignature);
    }

    public BufferedImage getTextureOfPiece(String signature) {
        return textureHolder.getPieceTexture(signature);
    }

    public BufferedImage getLetterImage(char letter) {
        return textureHolder.getLetterImage(letter);
    }

    public BufferedImage getBackgroundTexture(String filename) {
        return textureHolder.getTextureOf("stages", filename, getFrameDimension());
    }

    public ImageIcon getButtonIcon(String id, Dimension size) {
        return textureHolder.getIconOf("buttons", id, size);
    }

    public GraphicsManager() {
        boardSize = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3);

        Font letterFont = new Font("Monospaced", PLAIN, getSquareSize() / 3);
        boardColors = BoardColors.OPTION1;
        textureHolder = new TextureHolder(boardColors, letterFont, pieceTextureFolder, getPieceSize());
    }
}
