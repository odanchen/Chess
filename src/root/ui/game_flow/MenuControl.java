package root.ui.game_flow;

import root.ui.GameManager;
import root.ui.frames.menu_frame.MenuMainFrame;

public class MenuControl {
    private MenuMainFrame menuMainFrame;
    private GameManager gameManager;

    public MenuControl(GameManager gameManager) {
        this.gameManager = gameManager;
        this.menuMainFrame = new MenuMainFrame();
        menuMainFrame.MainMenu(gameManager);
    }
}
