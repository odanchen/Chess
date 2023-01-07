package chessRoot.user_interface;

import chessRoot.assets.board_colors.ColorSet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextureHolder {
    private ColorSet colorSet;
    private Map<Character, BufferedImage> bitmapFont;
    private Map<String, BufferedImage> activeTextures;
    private String pieceTextureFolder;
    private int pieceSize;
    private final Font font;
    private final List<Character> letters = List.of('1', '2', '3', '4', '5', '6', '7', '8', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H');
    private final List<String> pieceSignatures = List.of("bb", "bk", "bn", "bp", "bq", "br", "wb", "wk", "wn", "wp", "wq", "wr");

    public void generateLetterTextures(ColorSet colorSet) {
        this.colorSet = colorSet;
        this.bitmapFont = letters.stream().collect(Collectors.toMap(Function.identity(), this::createBitmap));
    }

    private BufferedImage createBitmap(Character character) {
        BufferedImage image = new BufferedImage(font.getSize(), font.getSize(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(colorSet.getWhiteCell());
        drawCenteredString(g, String.valueOf(character), new Rectangle(0, 0, font.getSize(), font.getSize()), font);
        g.dispose();
        return image;
    }

    private void fillActiveTextures() {
        pieceSignatures.forEach(signature -> activeTextures.put(signature, getNewTexture(signature)));
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
        return toBufferedImage(image.getScaledInstance(pieceSize, pieceSize, Image.SCALE_SMOOTH));
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

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public BufferedImage getPieceTexture(String signature) {
        return activeTextures.get(signature);
    }

    public BufferedImage getLetterImage(char letter) {
        return bitmapFont.get(letter);
    }

    public TextureHolder(ColorSet colorSet, Font font, String pieceTextureFolder, int pieceSize) {
        this.colorSet = colorSet;
        this.font = font;
        this.pieceTextureFolder = pieceTextureFolder;
        this.pieceSize = pieceSize;

        activeTextures = new HashMap<>();
        fillActiveTextures();
        generateLetterTextures(colorSet);
    }
}
