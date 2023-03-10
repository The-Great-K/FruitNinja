package main.handlers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.window.GamePanel;

public class MouseHandler implements MouseListener {

	public GamePanel gp;

	public MouseHandler(GamePanel gp) {
		this.gp = gp;

		gp.addMouseListener(this);
	}

	public boolean mousePressed = false;

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
