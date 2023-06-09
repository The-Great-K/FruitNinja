package main.handlers.key;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.window.GamePanel;

public class GameKeyHandler extends KeyAdapter {

	public GamePanel gp;

	public GameKeyHandler(GamePanel gp) {
		this.gp = gp;

		gp.addKeyListener(this);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (gp.gameState == gp.PLAY_STATE) {
			if (code == KeyEvent.VK_ESCAPE) {
				if (!gp.showOptionsMenu) {
					gp.showOptionsMenu = true;
				} else {
					gp.showOptionsMenu = false;
				}
			}
		}
	}

}
