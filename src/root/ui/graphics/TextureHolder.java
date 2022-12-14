package root.ui.graphics;

import root.assets.colors.ColorSet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    private final int pieceSize;
    private final Dimension stageSize;
    private final Font font;
    private final List<String> pieceSignatures = List.of("bb", "bk", "bn", "bp", "bq", "br", "wb", "wk", "wn", "wp", "wq", "wr");

    public void generateLetterTextures(ColorSet colorSet) {
        this.colorSet = colorSet;
        List<Character> letters = new ArrayList<>();
        for (char letter = '0'; letter <= '9'; letter++) letters.add(letter);
        for (char letter = 'a'; letter <= 'z'; letter++) letters.add(letter);
        for (char letter = 'A'; letter <= 'Z'; letter++) letters.add(letter);
        letters.add(' ');
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

    private BufferedImage rescale(BufferedImage image, int height, int width) {
        return toBufferedImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public String getPathToTexture(String signature) {
        String root = Paths.get("").toAbsolutePath().toString();
        String[] fullPath = {root, "src", "root", "assets", "pieces", pieceTextureFolder, signature + ".png"};
        return String.join(File.separator, fullPath);
    }

    private BufferedImage getNewTexture(String signature) {
        try {
            return rescale(ImageIO.read(new File(getPathToTexture(signature))), pieceSize, pieceSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getPathToBackground(String filename) {
        String root = Paths.get("").toAbsolutePath().toString();
        String[] fullPath = {root, "src", "root", "assets", "stages", filename + ".png"};
        return String.join(File.separator, fullPath);
    }

    public ImageIcon getImageIconOf(String id, int width, int height) {
        String root = Paths.get("").toAbsolutePath().toString();
        String[] fullPath = {root, "src", "root", "assets", "buttons", id + ".png"};
        try {
            return new ImageIcon(rescale(ImageIO.read(new File(String.join(File.separator, fullPath))), height, width));
        } catch (IOException e) {
            System.out.println(getPathToBackground(String.join(File.separator, fullPath)));
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getBackgroundTexture(String filename) {
        try {
            BufferedImage img = ImageIO.read(new File(getPathToBackground(filename)));
            return rescale(img, stageSize.height, stageSize.width);
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

    public TextureHolder(ColorSet colorSet, Font font, String pieceTextureFolder, int pieceSize, Dimension stageSize) {
        this.colorSet = colorSet;
        this.font = font;
        this.pieceTextureFolder = pieceTextureFolder;
        this.pieceSize = pieceSize;
        this.stageSize = stageSize;

        activeTextures = new HashMap<>();
        fillActiveTextures();
        generateLetterTextures(colorSet);
    }
}
