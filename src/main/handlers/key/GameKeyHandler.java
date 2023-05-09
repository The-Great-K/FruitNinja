package main.handlers.key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.window.GamePanel;

public class GameKeyHandler implements KeyListener {

	public GamePanel gp;

	public GameKeyHandler(GamePanel gp) {
		this.gp = gp;

		gp.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
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
