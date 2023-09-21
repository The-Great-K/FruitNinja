package main.screens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.entity.button.Button;
import main.window.GamePanel;

public class SettingsScreen {

	public GamePanel gp;
	public Graphics2D g2;

	private BufferedImage image = null;
	private String text;

	private int x, y;

	public int timer = 0;
	public boolean waited = false;

	public Button backButton, soundButton, controlsButton, videoButton, statisticsButton, quitButton;

	private boolean backButtonChecker = false;
	private boolean soundButtonChecker = false;
	private boolean controlsButtonChecker = false;
	private boolean videoButtonChecker = false;
	private boolean statisticsButtonChecker = false;
	private boolean quitButtonChecker = false;

	public SettingsScreen(GamePanel gp) {
		this.gp = gp;

		backButton = new Button(gp, "16x2", "BACK");
		soundButton = new Button(gp, "8x2", "AUDIO");
		controlsButton = new Button(gp, "8x2", "CONTROLS");
		videoButton = new Button(gp, "8x2", "VIDEO");
		statisticsButton = new Button(gp, "8x2", "STATS");
		quitButton = new Button(gp, "16x2", "QUIT");

		gp.buttonList.add(backButton);
		gp.buttonList.add(soundButton);
		gp.buttonList.add(controlsButton);
		gp.buttonList.add(videoButton);
		gp.buttonList.add(statisticsButton);
		gp.buttonList.add(quitButton);
	}

	public void update() {
		backButton.update();
		soundButton.update();
		controlsButton.update();
		videoButton.update();
		statisticsButton.update();
		quitButton.update();

		backButton.setX(gp.tileWidth * 8);
		backButton.setY(gp.tileHeight * 5);
		backButton.setWidth(gp.tileWidth * 16);
		backButton.setHeight(gp.tileHeight * 2);

		soundButton.setX(gp.tileWidth * 8);
		soundButton.setY(gp.tileHeight * 7);
		soundButton.setWidth(gp.tileWidth * 8);
		soundButton.setHeight(gp.tileHeight * 2);

		controlsButton.setX(gp.tileWidth * 16);
		controlsButton.setY(gp.tileHeight * 7);
		controlsButton.setWidth(gp.tileWidth * 8);
		controlsButton.setHeight(gp.tileHeight * 2);

		videoButton.setX(gp.tileWidth * 8);
		videoButton.setY(gp.tileHeight * 9);
		videoButton.setWidth(gp.tileWidth * 8);
		videoButton.setHeight(gp.tileHeight * 2);

		statisticsButton.setX(gp.tileWidth * 16);
		statisticsButton.setY(gp.tileHeight * 9);
		statisticsButton.setWidth(gp.tileWidth * 8);
		statisticsButton.setHeight(gp.tileHeight * 2);

		quitButton.setX(gp.tileWidth * 8);
		quitButton.setY(gp.tileHeight * 11);
		quitButton.setWidth(gp.tileWidth * 16);
		quitButton.setHeight(gp.tileHeight * 2);
	}

	public void render(Graphics2D g2) {
		if (!waited) {
			if (timer >= 60) {
				waited = true;
			}
			timer++;
		} else {
			this.g2 = g2;

			backButton.render(g2);
			soundButton.render(g2);
			controlsButton.render(g2);
			videoButton.render(g2);
			statisticsButton.render(g2);
			quitButton.render(g2);

			if (gp.player.hitbox != null) {
				if (backButton.isTouching(gp.player)) {
					if (!backButtonChecker) {
						Button.buttonNum--;
						backButtonChecker = true;
					}
					if (gp.mouseH.mouseClicked) {
						gp.mouseH.mouseClicked = false;

						gp.showOptionsMenu = false;

						timer = 0;
						waited = false;
					}
				} else if (backButtonChecker) {
					Button.buttonNum++;
					backButtonChecker = false;
				}
			}
			if (gp.player.hitbox != null) {
				if (soundButton.isTouching(gp.player)) {
					if (!soundButtonChecker) {
						Button.buttonNum--;
						soundButtonChecker = true;
					}
					if (gp.mouseH.mouseClicked) {
						gp.mouseH.mouseClicked = false;

						timer = 0;
						waited = false;
					}
				} else if (soundButtonChecker) {
					Button.buttonNum++;
					soundButtonChecker = false;
				}
			}
			if (gp.player.hitbox != null) {
				if (controlsButton.isTouching(gp.player)) {
					if (!controlsButtonChecker) {
						Button.buttonNum--;
						controlsButtonChecker = true;
					}
					if (gp.mouseH.mouseClicked) {
						gp.mouseH.mouseClicked = false;

						timer = 0;
						waited = false;
					}
				} else if (controlsButtonChecker) {
					Button.buttonNum++;
					controlsButtonChecker = false;
				}
			}
			if (gp.player.hitbox != null) {
				if (videoButton.isTouching(gp.player)) {
					if (!videoButtonChecker) {
						Button.buttonNum--;
						videoButtonChecker = true;
					}
					if (gp.mouseH.mouseClicked) {
						gp.mouseH.mouseClicked = false;

						gp.settingsScreenState = gp.VIDEO_SETTINGS_STATE;

						timer = 0;
						waited = false;
					}
				} else if (videoButtonChecker) {
					Button.buttonNum++;
					videoButtonChecker = false;
				}
			}
			if (gp.player.hitbox != null) {
				if (statisticsButton.isTouching(gp.player)) {
					if (!statisticsButtonChecker) {
						Button.buttonNum--;
						statisticsButtonChecker = true;
					}
					if (gp.mouseH.mouseClicked) {
						Button.buttonNum++;
						statisticsButtonChecker = false;

						gp.mouseH.mouseClicked = false;

						timer = 0;
						waited = false;
					}
				} else if (statisticsButtonChecker) {
					Button.buttonNum++;
					statisticsButtonChecker = false;
				}
			}
			if (gp.player.hitbox != null) {
				if (quitButton.isTouching(gp.player)) {
					if (!quitButtonChecker) {
						Button.buttonNum--;
						quitButtonChecker = true;
					}
					if (gp.mouseH.mouseClicked) {
						gp.mouseH.mouseClicked = false;

						gp.saveData.save();
						if (gp.gameState == gp.TITLE_STATE) {
							System.exit(0);
						}
						if (gp.gameState == gp.PLAY_STATE) {
							gp.gameState = gp.TITLE_STATE;
							gp.settingsScreenState = gp.DEFAULT_SETTINGS_STATE;
						}

						timer = 0;
						waited = false;
					}
				} else if (quitButtonChecker) {
					Button.buttonNum++;
					quitButtonChecker = false;
				}
			}

		}
	}

}
