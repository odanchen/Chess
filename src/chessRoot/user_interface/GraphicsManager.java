package chessRoot.user_interface;

import chessRoot.assets.board_colors.BoardColors;
import chessRoot.assets.board_colors.ColorSet;
import chessRoot.logic.pieces.ChessPiece;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.logic.pieces.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

import static chessRoot.logic.pieces.PieceColor.WHITE;

public class GraphicsManager {
    private String pieceTextureFolder = "cburnett";
    private final String[] pieceTextureFolders = {"cburnett", "kilifiger", "kosal", "leipzig", "maya", "pirat", "regular"};
    private final String[] pieceSignatures = {"bb", "bk", "bn", "bp", "bq", "br", "wb", "wk", "wn", "wp", "wq", "wr"};
    private final HashMap<String, BufferedImage> activeTextures;
    private ColorSet boardColors;
    private int boardSize;
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
        return getSquareSize() / 3;
    }

    public Rectangle getPlayAreaBounds() {
        return new Rectangle(getEdgeSize(), getEdgeSize(), getBoardSize(), getBoardSize());
    }

    public Rectangle getGameFrameBounds() {
        int newRectSize = 2 * getEdgeSize() + getBoardSize();
        return new Rectangle(0, 0, newRectSize, newRectSize);
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

    public GraphicsManager() {
        boardSize = 512;
        boardColors = BoardColors.OPTION1;
        activeTextures = new HashMap<>();
        fillActiveTextures();
    }
}
