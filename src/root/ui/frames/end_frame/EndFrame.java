package root.ui.frames.end_frame;

import root.ui.GameManager;
import root.ui.frames.components.BaseFrame;
import root.ui.frames.components.button.CustomButton;
import root.ui.frames.end_frame.panels.BoardStatePanel;
import root.ui.frames.end_frame.panels.EndMessagePanel;
import root.ui.game_flow.GameStatus;
import root.ui.graphics.GraphicsManager;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JComponent;
import java.awt.*;

public class EndFrame extends BaseFrame {

    private final GameStatus gameStatus;

    public EndFrame(GameManager gameManager, GraphicsManager graphicsManager, GameStatus gameStatus) {
        super(gameManager, graphicsManager);
        this.gameStatus = gameStatus;
        addMenuButton();
        addGmeEndBoard();
        addGameResultMessage();
        addBackgroundPanel("gameBackground");
    }

    private void addGameResultMessage() {
        EndMessagePanel textPanel = new EndMessagePanel(graphicsManager, gameStatus.getGameResult());
        add(textPanel);
    }

    private void addGmeEndBoard() {
        BoardStatePanel boardStatePanel = new BoardStatePanel(graphicsManager, gameStatus);
        add(boardStatePanel);
        validate();
    }

    private void addMenuButton() {
        JPanel buttonsPanel = new JPanel();

        Dimension size = graphicsManager.getTextButtonDimension();
        int buttonY = (int) (graphicsManager.getGameBounds().getSize().getHeight() * 17 / 20);
        buttonsPanel.setBounds(graphicsManager.getCenterOfScreenX(size.width), buttonY, size.width, (int) (size.height * 1.5));
        buttonsPanel.setOpaque(false);

        CustomButton menuReturnButton = new CustomButton("menuButtonReleased", graphicsManager, size);
        menuReturnButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor((JComponent) e.getSource()).dispose();
            gameManager.runMenu();
        });

        buttonsPanel.add(menuReturnButton);
        add(buttonsPanel);
        validate();
    }
}
