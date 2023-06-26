package main.handlers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.window.GamePanel;

public class MouseHandler extends MouseAdapter {

	public GamePanel gp;

	public boolean mouseClicked;

	public int timer = 0;

	public MouseHandler(GamePanel gp) {
		this.gp = gp;

		gp.addMouseListener(this);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < gp.buttonList.size(); i++) {
			if (gp.buttonList.get(i).hitbox != null) {
				if (gp.player.isTouching(gp.buttonList.get(i))) {
					mouseClicked = true;
					timer++;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseClicked = false;
	}
}
