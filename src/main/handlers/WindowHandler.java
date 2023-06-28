package main.handlers;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import main.Main;
import main.window.GamePanel;

public class WindowHandler implements WindowListener {

	public GamePanel gp;

	public WindowHandler(GamePanel gp) {
		this.gp = gp;

		Main.window.addWindowListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		gp.saveData.load();
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		gp.saveData.save();
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
//		if (gp.paused) {
//			gp.paused = false;
//		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
//		if (!gp.paused) {
//			gp.paused = true;
//		}
	}

}