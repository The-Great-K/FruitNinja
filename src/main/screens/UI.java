package main.screens;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import main.window.GamePanel;

public class UI {

	GamePanel gp;
	Graphics2D g2;

	public int commandNum = 0;
	public BufferedImage image = null;
	public String text;
	public JButton button;

	private int x, y;

	public UI(GamePanel gp) {
		this.gp = gp;
	}

	public void render(Graphics2D g2) {
		this.g2 = g2;

		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
	}

	public void drawTitleScreen() {
		// BACKGROUND
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/textures/backgrounds/wood.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// TITLE IMAGE
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/textures/title/title.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		x = gp.tileWidth * 2;
		y = gp.tileHeight * 2;
		g2.drawImage(image, x, y, gp.tileWidth * 10, gp.tileHeight * 6, null);

		// MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, gp.tileHeight * 1));

		text = "NEW GAME";
		x = getXForCenteredText(text);
		y = gp.tileHeight * 11;
		g2.drawString(text, x, y); // NEW GAME

		text = "SETTINGS";
		x = getXForCenteredText(text);
		y = gp.tileHeight * 13;
		g2.drawString(text, x, y); // SETTINGS

		text = "QUIT";
		x = getXForCenteredText(text);
		y = gp.tileHeight * 15;
		g2.drawString(text, x, y); // QUIT
	}

	public int getXForCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}

}
