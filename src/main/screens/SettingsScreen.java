package main.screens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.entity.button.Button;
import main.handlers.MouseHandler;
import main.window.GamePanel;

public class SettingsScreen {

	public GamePanel gp;
	public Graphics2D g2;

	private BufferedImage image = null;
	private String text;

	private int x, y;

	public int timer = 0;
	public boolean waited = false;

	private Button backButton, soundButton, controlsButton, videoButton, statisticsButton, quitButton;

	private MouseHandler mouseH;

	public SettingsScreen(GamePanel gp) {
		this.gp = gp;
		backButton = new Button(gp, "16x2", "BACK");
		soundButton = new Button(gp, "8x2", "AUDIO");
		controlsButton = new Button(gp, "8x2", "CONTROLS");
		videoButton = new Button(gp, "8x2", "VIDEO");
		statisticsButton = new Button(gp, "8x2", "STATS");
		quitButton = new Button(gp, "16x2", "QUIT");
		this.mouseH = new MouseHandler(gp);
	}

	public void update() {
		backButton.update();
		soundButton.update();
		controlsButton.update();
		videoButton.update();
		statisticsButton.update();
		quitButton.update();

		backButton.setX(gp.tileWidth * 8);
		backButton.setY(gp.tileHeight * 9);
		backButton.setWidth(gp.tileWidth * 16);
		backButton.setHeight(gp.tileHeight * 2);

		soundButton.setX(gp.tileWidth * 8);
		soundButton.setY(gp.tileHeight * 9);
		soundButton.setWidth(gp.tileWidth * 16);
		soundButton.setHeight(gp.tileHeight * 2);

		controlsButton.setX(gp.tileWidth * 8);
		controlsButton.setY(gp.tileHeight * 9);
		controlsButton.setWidth(gp.tileWidth * 16);
		controlsButton.setHeight(gp.tileHeight * 2);

		videoButton.setX(gp.tileWidth * 8);
		videoButton.setY(gp.tileHeight * 9);
		videoButton.setWidth(gp.tileWidth * 16);
		videoButton.setHeight(gp.tileHeight * 2);

		statisticsButton.setX(gp.tileWidth * 8);
		statisticsButton.setY(gp.tileHeight * 9);
		statisticsButton.setWidth(gp.tileWidth * 16);
		statisticsButton.setHeight(gp.tileHeight * 2);

		quitButton.setX(gp.tileWidth * 8);
		quitButton.setY(gp.tileHeight * 9);
		quitButton.setWidth(gp.tileWidth * 16);
		quitButton.setHeight(gp.tileHeight * 2);
	}

	public void render(Graphics2D g2) {

	}

}
