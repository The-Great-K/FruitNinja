package main.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import main.window.GamePanel;

public class GameOverScreen {

	public GamePanel gp;
	public Graphics2D g2;

	public GameOverScreen(GamePanel gp) {
		this.gp = gp;
	}

	public void render(Graphics2D g2) {
		this.g2 = g2;

		int x, y;
		String text;

		g2.setColor(Color.red);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, gp.tileHeight * 4));

		text = "Game Over!";
		x = (int) getLocationForCenteredText(text).getX();
		y = (int) getLocationForCenteredText(text).getY() - gp.tileHeight * 1;
		g2.drawString(text, x, y);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, gp.tileHeight * 1));
		text = "Score: " + gp.score + "  High Score: " + gp.highScore;
		x = (int) getLocationForCenteredText(text).getX();
		y = (int) getLocationForCenteredText(text).getY() + gp.tileHeight * 2;
		g2.drawString(text, x, y);
	}

	public Point getLocationForCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();

		int x = gp.screenWidth / 2 - length / 2;
		int y = gp.screenHeight / 2 + height / 2;

		return new Point(x, y);
	}

}
