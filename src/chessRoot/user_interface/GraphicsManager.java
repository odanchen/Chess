package chessRoot.user_interface;

import chessRoot.assets.board_colors.BoardColors;
import chessRoot.assets.board_colors.ColorSet;
import chessRoot.logic.pieces.ChessPiece;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static java.awt.Font.PLAIN;


public class GraphicsManager {
    private String pieceTextureFolder = "cburnett";
    private final String[] pieceTextureFolders = {"cburnett", "kilifiger", "kosal", "leipzig", "maya", "pirat", "regular"};
    private final String[] pieceSignatures = {"bb", "bk", "bn", "bp", "bq", "br", "wb", "wk", "wn", "wp", "wq", "wr"};
    private final Map<String, BufferedImage> activeTextures;
    private final List<Character> letters = List.of('1','2','3','4','5','6','7','8','A','B','C','D','E','F','G','H');
    private Map<Character, BufferedImage> font;
    private ColorSet boardColors;
    private final int boardSize;
    private boolean isFlipped = false;

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

    public Rectangle getFrameBounds() {
        int newRectWidth = 2 * getEdgeSize() + getBoardSize() + 5 * getSquareSize();
        int newRectHeight = 3 * getEdgeSize() + getBoardSize() + 2 * getSquareSize();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int xCor = (int) ((screen.getWidth() - newRectWidth) / 2);
        int yCor = (int) ((screen.getHeight() - newRectHeight) / 2);
        return new Rectangle(xCor, yCor, newRectWidth, newRectHeight);
    }

    public Rectangle getFlipButtonBounds() {
        int frameWidth = 2 * getEdgeSize() + getBoardSize() + 5 * getSquareSize();
        return new Rectangle(frameWidth / 10 * 9, 0, getSquareSize(), getSquareSize() / 2);
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

    private void fillActiveTextures() {
        for (String signature : pieceSignatures) {
            activeTextures.put(signature, getNewTexture(signature));
        }
    }

    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bImage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bImage;
    }

    private BufferedImage rescale(BufferedImage image) {
        return toBufferedImage(image.getScaledInstance(getPieceSize(), getPieceSize(), Image.SCALE_SMOOTH));
    }

    private BufferedImage getNewTexture(String signature) {
        String root = Paths.get("").toAbsolutePath().toString();
        String[] fullPath = {root, "src", "chessRoot", "assets", "pieces_textures", pieceTextureFolder, signature + ".png"};

        try {
            return rescale(ImageIO.read(new File(String.join(File.separator, fullPath))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getTextureOfPiece(ChessPiece piece) {
        return activeTextures.get(piece.getPieceSignature());
    }

    public BufferedImage getTextureOfPiece(String signature) {
        return activeTextures.get(signature);
    }

    public BufferedImage getLetterImage(char letter) {
        return font.get(letter);
    }

    public GraphicsManager() {
        boardSize = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3);
        boardColors = BoardColors.OPTION1;
        activeTextures = new HashMap<>();
        fillActiveTextures();
        font = new BitmapFontGenerator(boardColors, letters, new Font("Monospaced", PLAIN, getSquareSize() / 3)).getBitmapFont();
    }
}
