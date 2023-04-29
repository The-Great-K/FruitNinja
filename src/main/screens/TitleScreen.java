package main.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.entity.button.Button;
import main.handlers.MouseHandler;
import main.window.GamePanel;

public class TitleScreen {

	public GamePanel gp;
	public Graphics2D g2;

	private BufferedImage image = null;
	private String text;

	private int x, y;

	public int timer = 0;
	public boolean waited = false;

	private Button gameButton, settingsButton, perksButton, quitButton;

	private MouseHandler mouseH;

	public TitleScreen(GamePanel gp) {
		this.gp = gp;
		gameButton = new Button(gp);
		settingsButton = new Button(gp);
		perksButton = new Button(gp);
		quitButton = new Button(gp);
		this.mouseH = new MouseHandler(this.gp);
	}

	public void update() {
		gameButton.update();
		settingsButton.update();
		perksButton.update();
		quitButton.update();

		gameButton.setX(gp.tileWidth * 8);
		gameButton.setY(gp.tileHeight * 9);
		gameButton.setWidth(gp.tileWidth * 16);
		gameButton.setHeight(gp.tileHeight * 2);

		settingsButton.setX(gp.tileWidth * 8);
		settingsButton.setY(gp.tileHeight * 11);
		settingsButton.setWidth(gp.tileWidth * 8);
		settingsButton.setHeight(gp.tileHeight * 2);

		perksButton.setX(gp.tileWidth * 16);
		perksButton.setY(gp.tileHeight * 11);
		perksButton.setWidth(gp.tileWidth * 8);
		perksButton.setHeight(gp.tileHeight * 2);

		quitButton.setX(gp.tileWidth * 8);
		quitButton.setY(gp.tileHeight * 13);
		quitButton.setWidth(gp.tileWidth * 16);
		quitButton.setHeight(gp.tileHeight * 2);
	}

	public void render(Graphics2D g2) {
		if (!waited) {
			if (timer == 60) {
				waited = true;
			}
			timer++;
		} else {
			this.g2 = g2;

			gameButton.render(g2);
			settingsButton.render(g2);
			quitButton.render(g2);
			perksButton.render(g2);

			// TITLE
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/textures/title/title.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			int width = gp.tileWidth * 10;
			x = getXForCenteredImage(width);
			y = gp.tileHeight * 1;
			g2.drawImage(image, x, y, width, gp.tileHeight * 6, null);

			// MENU
			float temp = (float) (gp.tileHeight * 1.5);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, temp));

			// NEW GAME
			g2.setColor(Color.red);
			text = "NEW GAME";
			x = getXForCenteredText(text);
			y = gp.tileHeight * 11;
			g2.drawString(text, x, y - gp.tileHeight / 3);
			if (gp.player.hitbox != null && this.mouseH.mousePressed) {
				if (gameButton.isTouching(gp.player)) {
					gp.restartGame();
				}
			}

			// SWITCH BLADE

			// DONATE

			// SETTINGS
			temp = (float) (gp.tileHeight * 1.25);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, temp));

			g2.setColor(Color.yellow);
			text = "SETTINGS";
			x = getXForCenteredText(text) - gp.tileWidth * 4 - gp.tileWidth / 4;
			y = gp.tileHeight * 13;
			g2.drawString(text, x, y - gp.tileHeight / 3);
			if (gp.player.hitbox != null && mouseH.mousePressed) {
				if (settingsButton.isTouching(gp.player)) {
					gp.gameState = gp.SETTINGS_STATE;
				}
			}

			// PERKS
			temp = (float) (gp.tileHeight * 1.25);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, temp));

			g2.setColor(Color.green);
			text = "PERKS";
			x = getXForCenteredText(text) + gp.tileWidth * 4 - gp.tileWidth / 4;
			y = gp.tileHeight * 13;
			g2.drawString(text, x, y - gp.tileHeight / 3);
			if (gp.player.hitbox != null && mouseH.mousePressed) {
				if (perksButton.isTouching(gp.player)) {
					gp.gameState = gp.SETTINGS_STATE;
				}
			}

			// QUIT GAME
			temp = (float) (gp.tileHeight * 1.5);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, temp));

			g2.setColor(Color.blue);
			text = "QUIT";
			x = getXForCenteredText(text);
			y = gp.tileHeight * 15;
			g2.drawString(text, x, y - gp.tileHeight / 3);
			if (gp.player.hitbox != null && mouseH.mousePressed) {
				if (quitButton.isTouching(gp.player)) {
					System.exit(0);
					gp.saveData.save();
				}
			}
		}
	}

	public void write() {
		System.out.println("Play Button X: " + gameButton.x);
	}

	public int getXForCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}

	public int getXForCenteredImage(int width) {
		int x = gp.screenWidth / 2 - width / 2;
		return x;
	}

}
