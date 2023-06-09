package main.handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.window.GamePanel;

public class MouseHandler extends MouseAdapter {

	public GamePanel gp;

	public boolean mouseClicked;

	public MouseHandler(GamePanel gp) {
		this.gp = gp;

		gp.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseClicked = true;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
