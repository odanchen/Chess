package ui.graphics;

import assets.colors.ColorSet;
import logic.pieces.ChessPiece;
import assets.settings.IOSettings;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;

import static java.awt.Font.PLAIN;


public class GraphicsManager {
    private final int boardSize;
    private final TextureHolder textureHolder;
    private ColorSet boardColors;
    private boolean isFlipped = false;
    private boolean flipToggle;

    public GraphicsManager() {
        boardSize = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3);

        IOSettings ioSettings = new IOSettings();
        flipToggle = ioSettings.getFlipToggle();
        boardColors = ioSettings.getBoardColors();

        Font letterFont = new Font("Monospaced", PLAIN, getSquareSize() / 3);
        textureHolder = new TextureHolder(letterFont, getPieceSize());
    }

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

    public boolean flipToggle() {
        return flipToggle;
    }

    public Rectangle getPlayAreaBounds() {
        return new Rectangle(getEdgeSize(), getEdgeSize(), getBoardSize(), getBoardSize());
    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect) {
        int fSize = textureHolder.getLetterSize() / 2;
        int left = (rect.width - fSize * text.length()) / 2;
        int top = (rect.height - fSize * 2) / 2;
        for (int i = 0; i < text.length(); i++) {
            g.drawImage(textureHolder.getLetterImage(text.charAt(i)), left + i * fSize, rect.y + top, null);
        }
    }

    public Rectangle getBoardPanelBounds() {
        int newRectSize = 2 * getEdgeSize() + getBoardSize();
        return new Rectangle(0, 0, newRectSize, newRectSize);
    }

    public Rectangle getGamePanelBounds() {
        int newRectSize = 2 * getEdgeSize() + getBoardSize();
        return new Rectangle(getEdgeSize(), getSquareSize(), newRectSize, newRectSize);
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

    public Dimension getButtonDimensions() {
        return new Dimension(getSquareSize(), getSquareSize());
    }

    public Rectangle getLogPanelBounds() {
        int startX = getBoardPanelBounds().x + getBoardPanelBounds().width + getSquareSize() * 2;
        return new Rectangle(startX, getSquareSize() - 5, getGameBounds().width - startX - getEdgeSize(), getGameBounds().height - getSquareSize() * 2 - getEdgeSize());
    }

    public Rectangle getTimerPanelBounds() {
        return new Rectangle(getEdgeSize(), getEdgeSize(), getSquareSize() * 2, getBoardPanelBounds().height + getSquareSize());
    }

    public Dimension getTextButtonDimension() {
        return new Dimension(getEdgeSize() * 6, getEdgeSize() * 2);
    }

    public Dimension getFrameDimension() {
        return getGameBounds().getSize();
    }

    public Dimension getEndDimension() {
        return new Dimension(getFrameDimension().width * 2 / 7, getFrameDimension().height / 4);
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

    public BufferedImage getBackgroundTexture(String filename) {
        return textureHolder.getTextureOf("stages", filename, getFrameDimension());
    }

    public BufferedImage getMessageTexture(String filename) {
        return textureHolder.getTextureOf("messages", filename, getEndDimension());
    }

    public ImageIcon getButtonIcon(String id, Dimension size) {
        return textureHolder.getIconOf("buttons", id, size);
    }

    private void refreshBoardColors(ColorSet newColor) {
        if (boardColors != newColor) {
            boardColors = newColor;
            textureHolder.refreshColors();
        }
    }

    public void refreshTextures() {
        IOSettings ioSettings = new IOSettings();
        textureHolder.refreshPieceTextures(ioSettings.getTexturePack());
        flipToggle = ioSettings.getFlipToggle();
        refreshBoardColors(ioSettings.getBoardColors());
    }

    public Font getSideFont() {
        return new Font("Monospaced", Font.BOLD, (int) (getEdgeSize() / 2.5));
    }
}
