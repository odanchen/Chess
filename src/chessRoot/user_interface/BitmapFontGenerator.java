package chessRoot.user_interface;

import chessRoot.assets.board_colors.ColorSet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BitmapFontGenerator {
    private final ColorSet colorSet;
    private final Map<Character, BufferedImage> bitmapFont;
    private final Font font;

    public BitmapFontGenerator(ColorSet colorSet, List<Character> charset, Font font) {
        this.colorSet = colorSet;
        this.font = font;
        this.bitmapFont = charset.stream().collect(Collectors.toMap(Function.identity(), this::createBitmap));
    }

    public Map<Character, BufferedImage> getBitmapFont() {
        return bitmapFont;
    }

    private BufferedImage createBitmap(Character character) {
        BufferedImage image = new BufferedImage(font.getSize(), font.getSize(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(colorSet.getWhiteCell());
        drawCenteredString(g, String.valueOf(character), new Rectangle(0,0,font.getSize(), font.getSize()), font);
        g.dispose();
        return image;
    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
}
