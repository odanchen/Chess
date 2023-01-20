package ui.frames.end_frame;

import ui.GameManager;
import ui.frames.components.BaseFrame;
import ui.frames.components.CustomButton;
import ui.frames.end_frame.panels.BoardStatePanel;
import ui.frames.end_frame.panels.EndMessagePanel;
import ui.game_flow.GameStatus;
import ui.graphics.GraphicsManager;

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
        buttonsPanel.setBounds(graphicsManager.getGameBounds().width * 3 / 4, buttonY, size.width, (int) (size.height * 1.5));
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
