package chessRoot.user_interface.game_flow;

import chessRoot.user_interface.GameManager;
import chessRoot.user_interface.frames.menu_frame.MenuMainFrame;

public class MenuControl {
    private MenuMainFrame menuMainFrame;
    private GameManager gameManager;

    public MenuControl(GameManager gameManager) {
        this.gameManager = gameManager;
        this.menuMainFrame = new MenuMainFrame();
        menuMainFrame.MainMenu(gameManager);
    }

}
