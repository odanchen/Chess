//package root.ui.frames.end_frame;
//
//import root.ui.frames.menu_frame.CustomButton;
//import root.ui.graphics.GraphicsManager;
//import root.ui.game_flow.GameResult;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class GameEndPanel extends JPanel {
//
//    private final GraphicsManager graphicsManager;
//    private GameResult gameResult;
//
//    public GameEndPanel(GraphicsManager graphicsManager) {
//        this.graphicsManager = graphicsManager;
//        this.setBounds(graphicsManager.getEndPanelBounds());
//        addMenuButton();
//        this.setVisible(true);
//
//    }
//
//
//    private void addMenuButton() {
//        JPanel buttonsPanel = new JPanel();
//
//        Dimension size = graphicsManager.getTextButtonDimension();
//        buttonsPanel.setPreferredSize(size);
//        buttonsPanel.setBounds(0, 0, size.width, size.height);
//
//        buttonsPanel.setVisible(true);
//        //buttonsPanel.setOpaque(false);
//
//        CustomButton menuReturnButton = new CustomButton("menuButtonReleased", graphicsManager, size);
//        menuReturnButton.setPreferredSize(size);
//        buttonsPanel.add(menuReturnButton);
//
//        buttonsPanel.validate();
//        add(buttonsPanel);
//        validate();
//    }
//
//}