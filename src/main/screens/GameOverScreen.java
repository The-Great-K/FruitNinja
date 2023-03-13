package main.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import main.entity.button.Button;
import main.handlers.MouseHandler;
import main.window.GamePanel;

public class GameOverScreen {

	public GamePanel gp;
	public Graphics2D g2;

	private int x, y;
	private String text;

	private Button restartButton, homeButton;

	public MouseHandler mouseH;

	public GameOverScreen(GamePanel gp) {
		this.gp = gp;
		this.mouseH = new MouseHandler(this.gp);
	}

	public void render(Graphics2D g2) {
		this.g2 = g2;

		if (restartButton != null && homeButton != null) {
			restartButton.render(g2);
			homeButton.render(g2);
		}

		g2.setColor(Color.red);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, gp.tileHeight * 4));

		text = "Game Over!";
		x = (int) getLocationForCenteredText(text).getX();
		y = (int) getLocationForCenteredText(text).getY() - gp.tileHeight * 3;
		g2.drawString(text, x, y);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, gp.tileHeight * 1));
		text = "Score: " + gp.score + "  High Score: " + gp.highScore;
		x = (int) getLocationForCenteredText(text).getX();
		y = (int) getLocationForCenteredText(text).getY();
		g2.drawString(text, x, y);

		text = "Restart";
		x = (int) getLocationForCenteredText(text).getX() - gp.screenWidth / 4 + gp.tileWidth / 8;
		y += gp.tileHeight * 2;
		g2.drawString(text, x, y);
		restartButton = new Button(gp, gp.tileWidth * 8, y - gp.tileHeight * 2, gp.tileWidth * 8, gp.tileHeight * 2);
		if (gp.player.hitbox != null && this.mouseH.mousePressed) {
			if (restartButton.isTouching(gp.player)) {
				gp.score = 0;
				gp.ui.gameScreen.foodList.clear();
				gp.strikes = 0;
				gp.gameState = gp.PLAY_STATE;
			}
		}

		text = "Home";
		x = (int) getLocationForCenteredText(text).getX() + gp.screenWidth / 4 - gp.tileWidth / 8;
		g2.drawString(text, x, y);
		restartButton = new Button(gp, gp.tileWidth * 16, y - gp.tileHeight * 2, gp.tileWidth * 8, gp.tileHeight * 2);
		if (gp.player.hitbox != null && this.mouseH.mousePressed) {
			if (restartButton.isTouching(gp.player)) {
				gp.ui.titleScreen.waited = false;
				gp.ui.titleScreen.timer = 0;
				gp.gameState = gp.TITLE_STATE;
			}
		}
	}

	public Point getLocationForCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();

		int x = gp.screenWidth / 2 - length / 2;
		int y = gp.screenHeight / 2 + height / 2;

		return new Point(x, y);
	}

}
