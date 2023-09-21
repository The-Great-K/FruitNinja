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

	public Button colorButton, fullScreenButton, backButton;

	private boolean colorButtonChecker = false;
	private boolean fullScreenButtonChecker = false;
	private boolean backButtonChecker = false;

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

			if (gp.player.hitbox != null) {
				if (colorButton.isTouching(gp.player)) {
					if (!colorButtonChecker) {
						Button.buttonNum--;
						colorButtonChecker = true;
					}
					if (gp.mouseH.mouseClicked) {
						Button.buttonNum++;
						colorButtonChecker = false;

						gp.mouseH.mouseClicked = false;

						if (gp.colorIndex + 1 >= 10) {
							gp.colorIndex = 0;
						} else {
							gp.colorIndex += 1;
						}
					}
				} else if (colorButtonChecker) {
					Button.buttonNum++;
					colorButtonChecker = false;
				}
			}

			if (gp.player.hitbox != null) {
				if (fullScreenButton.isTouching(gp.player)) {
					if (!fullScreenButtonChecker) {
						Button.buttonNum--;
						fullScreenButtonChecker = true;
					}
					if (gp.mouseH.mouseClicked) {
						Button.buttonNum++;
						fullScreenButtonChecker = false;

						gp.mouseH.mouseClicked = false;

						if (gp.fullScreen) {
							gp.exitFullScreen();
						} else {
							gp.setFullScreen();
						}
					}
				} else if (fullScreenButtonChecker) {
					Button.buttonNum++;
					fullScreenButtonChecker = false;
				}
			}

			if (gp.player.hitbox != null) {
				if (backButton.isTouching(gp.player)) {
					if (!backButtonChecker) {
						Button.buttonNum--;
						backButtonChecker = true;
					}
					if (gp.mouseH.mouseClicked) {
						Button.buttonNum++;
						backButtonChecker = false;

						gp.mouseH.mouseClicked = false;

						gp.settingsScreenState = gp.DEFAULT_SETTINGS_STATE;
					}
				} else if (backButtonChecker) {
					Button.buttonNum++;
					backButtonChecker = false;
				}
			}
		}
	}

}
