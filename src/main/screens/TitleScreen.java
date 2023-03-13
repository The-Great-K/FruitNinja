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

	private Button gameButton, settingsButton, quitButton;

	private MouseHandler mouseH;

	public TitleScreen(GamePanel gp) {
		this.gp = gp;
		this.mouseH = new MouseHandler(this.gp);
	}

	public void render(Graphics2D g2) {
		if (!waited) {
			if (timer == 60) {
				waited = true;
			}
			timer++;
		} else {
			this.g2 = g2;

			if (gameButton != null && settingsButton != null && quitButton != null) {
				gameButton.render(g2);
				settingsButton.render(g2);
				quitButton.render(g2);
			}

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
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, gp.tileHeight * 2));

			// NEW GAME
			g2.setColor(Color.red);
			text = "NEW GAME";
			x = getXForCenteredText(text);
			y = gp.tileHeight * 11;
			g2.drawString(text, x, y);
			gameButton = new Button(gp, gp.tileWidth * 8, y - gp.tileHeight * 2, gp.tileWidth * 16, gp.tileHeight * 2);
			if (gp.player.hitbox != null && this.mouseH.mousePressed) {
				if (gameButton.isTouching(gp.player)) {
					gp.gameState = gp.PLAY_STATE;
					gp.score = 0;
					gp.ui.gameScreen.foodList.clear();
					gp.strikes = 0;
				}
			}

			// SWITCH BLADE

			// DONATE

			// PERKS

			// SETTINGS
			g2.setColor(Color.yellow);
			text = "SETTINGS";
			x = getXForCenteredText(text);
			y = gp.tileHeight * 13;
			g2.drawString(text, x, y);
			settingsButton = new Button(gp, gp.tileWidth * 8, y - gp.tileHeight * 2, gp.tileWidth * 16,
					gp.tileHeight * 2);
			if (gp.player.hitbox != null && mouseH.mousePressed) {
				if (settingsButton.isTouching(gp.player)) {
					gp.gameState = gp.SETTINGS_STATE;
				}
			}

			// QUIT GAME
			g2.setColor(Color.blue);
			text = "QUIT";
			x = getXForCenteredText(text);
			y = gp.tileHeight * 15;
			g2.drawString(text, x, y);

			quitButton = new Button(gp, gp.tileWidth * 8, y - gp.tileHeight * 2, gp.tileWidth * 16, gp.tileHeight * 2);
			if (gp.player.hitbox != null && mouseH.mousePressed) {
				if (quitButton.isTouching(gp.player)) {
					System.exit(0);
				}
			}
		}
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
