package main.screens.optionsmenu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.handlers.MouseHandler;
import main.window.GamePanel;

public class StatisticsSettingsScreen {

	public GamePanel gp;
	public Graphics2D g2;

	private BufferedImage image = null;
	private String text;

	private int x, y;

	public int timer = 0;
	public boolean waited = false;

	private MouseHandler mouseH;

	public StatisticsSettingsScreen(GamePanel gp) {
		this.gp = gp;

		this.mouseH = new MouseHandler(gp);
	}

	public void update() {
	}

	public void render(Graphics2D g2) {
		if (!waited) {
			if (timer >= 60) {
				waited = true;
			}
			timer++;
		} else {
		}
	}

}
