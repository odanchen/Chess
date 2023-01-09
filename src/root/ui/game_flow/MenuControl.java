package root.ui.game_flow;

import root.ui.GameManager;
import root.ui.frames.menu_frame.MenuFrame;

public class MenuControl {
    private MenuFrame menuMainFrame;
    private GameManager gameManager;

    public MenuControl(GameManager gameManager) {
        this.gameManager = gameManager;
        this.menuMainFrame = new MenuFrame();
        menuMainFrame.MainMenu(gameManager);
    }
}
