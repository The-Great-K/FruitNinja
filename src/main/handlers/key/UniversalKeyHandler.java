package main.handlers.key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.window.GamePanel;

public class UniversalKeyHandler implements KeyListener {

	public GamePanel gp;

	public UniversalKeyHandler(GamePanel gp) {
		this.gp = gp;

		gp.addKeyListener(this);
	}

	public boolean quit = false;
	public boolean fullScreen = false;

	private boolean quitPrompt1 = false;
	private boolean quitPrompt2 = false;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_1) {
			quitPrompt1 = true;
		}
		if (code == KeyEvent.VK_2) {
			quitPrompt2 = true;
		}
		if (quitPrompt1 && quitPrompt2) {
			quit = true;
			gp.saveData.save();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_F11) {
			if (!fullScreen) {
				fullScreen = true;
			} else {
				fullScreen = false;
			}
		}
	}

}
