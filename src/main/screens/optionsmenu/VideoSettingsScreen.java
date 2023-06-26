package main.screens.optionsmenu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.entity.button.Button;
import main.window.GamePanel;

public class VideoSettingsScreen {

	public GamePanel gp;
	public Graphics2D g2;

	private BufferedImage image = null;
	private String text;

	private int x, y;

	public int timer = 0;
	public boolean waited = false;

	private Button colorButton;
	private Button fullScreenButton;
	private Button backButton;

	private int colorIndex = 0;

	public VideoSettingsScreen(GamePanel gp) {
		this.gp = gp;

		colorButton = new Button(gp, "16x2", "COLOR");
		fullScreenButton = new Button(gp, "16x2", "");
		backButton = new Button(gp, "16x2", "BACK");

		gp.buttonList.add(colorButton);
	}

	public void update() {
		colorButton.update();
		fullScreenButton.update();
		backButton.update();

		colorButton.setX(gp.tileWidth * 8);
		colorButton.setY(gp.tileHeight * 6);
		colorButton.setWidth(gp.tileWidth * 16);
		colorButton.setHeight(gp.tileHeight * 2);

		fullScreenButton.setX(gp.tileWidth * 8);
		fullScreenButton.setY(gp.tileHeight * 8);
		fullScreenButton.setWidth(gp.tileWidth * 16);
		fullScreenButton.setHeight(gp.tileHeight * 2);

		backButton.setX(gp.tileWidth * 8);
		backButton.setY(gp.tileHeight * 10);
		backButton.setWidth(gp.tileWidth * 16);
		backButton.setHeight(gp.tileHeight * 2);
	}

	public void render(Graphics2D g2) {
		if (!waited) {
			if (timer >= 60) {
				waited = true;
			}
			timer++;
		} else {
			this.g2 = g2;

			colorButton.render(g2);
			fullScreenButton.render(g2);
			backButton.render(g2);

			if (gp.fullScreen) {
				fullScreenButton.setText("FULLSCREEN: ON");
			} else {
				fullScreenButton.setText("FULLSCREEN: OFF");
			}

			if (gp.player.hitbox != null && gp.mouseH.mouseClicked) {
				if (colorButton.isTouching(gp.player)) {
					gp.mouseH.mouseClicked = false;

					if (gp.colorIndex + 1 >= 10) {
						gp.colorIndex = 0;
					} else {
						gp.colorIndex += 1;
					}
				}
			}

			if (gp.player.hitbox != null && gp.mouseH.mouseClicked) {
				if (fullScreenButton.isTouching(gp.player)) {
					gp.mouseH.mouseClicked = false;

					if (gp.fullScreen) {
						gp.exitFullScreen();
					} else {
						gp.setFullScreen();
					}
				}
			}

			if (gp.player.hitbox != null && gp.mouseH.mouseClicked) {
				if (backButton.isTouching(gp.player)) {
					gp.mouseH.mouseClicked = false;

					gp.settingsScreenState = gp.DEFAULT_SETTINGS_STATE;
				}
			}
		}
	}

}
