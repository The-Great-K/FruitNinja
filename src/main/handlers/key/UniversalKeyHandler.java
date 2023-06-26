package main.handlers.key;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.window.GamePanel;

public class UniversalKeyHandler extends KeyAdapter {

	public GamePanel gp;

	public UniversalKeyHandler(GamePanel gp) {
		this.gp = gp;

		gp.addKeyListener(this);
	}

	public boolean fullScreen = false;

	public boolean quit = false;

	private boolean quitPrompt1 = false;
	private boolean quitPrompt2 = false;

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_ESCAPE) {
			quitPrompt1 = true;
		}
		if (code == KeyEvent.VK_SHIFT) {
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
			fullScreen = !fullScreen;
		}
	}

}
