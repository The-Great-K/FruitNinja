package main.screens;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import main.entity.button.Button;
import main.window.GamePanel;

public class GameOverScreen {

	public GamePanel gp;
	public Graphics2D g2;

	private int x, y;
	private String text;

	public int timer = 0;
	public boolean waited = false;

	private Button restartButton, homeButton;

	public GameOverScreen(GamePanel gp) {
		this.gp = gp;

		restartButton = new Button(gp, "12x2", "RESTART");
		homeButton = new Button(gp, "12x2", "HOME");

		gp.buttonList.add(restartButton);
		gp.buttonList.add(homeButton);
	}

	public void update() {
		restartButton.update();
		homeButton.update();

		restartButton.setX(gp.tileWidth * 4);
		restartButton.setY(gp.tileHeight * 10);
		restartButton.setWidth(gp.tileWidth * 12);
		restartButton.setHeight(gp.tileHeight * 2);

		homeButton.setX(gp.tileWidth * 16);
		homeButton.setY(gp.tileHeight * 10);
		homeButton.setWidth(gp.tileWidth * 12);
		homeButton.setHeight(gp.tileHeight * 2);

	}

	public void render(Graphics2D g2) {
		if (!waited) {
			if (timer >= 60) {
				waited = true;
			}
			timer++;
		} else {
			this.g2 = g2;

			restartButton.render(g2);
			homeButton.render(g2);

			g2.setColor(gp.colorState);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, gp.tileWidth * 3));

			// GAME OVER TEXT
			text = "Game Over!";
			x = (int) getLocationForCenteredText(text).getX();
			y = (int) getLocationForCenteredText(text).getY() - gp.tileHeight * 3;
			g2.drawString(text, x, y);

			// SCORES
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, gp.tileWidth * 1));

			text = "Score: " + gp.score + "  High Score: " + gp.highScore;
			x = (int) getLocationForCenteredText(text).getX();
			y = (int) getLocationForCenteredText(text).getY();
			g2.drawString(text, x, y);

			// RESTART BUTTON
			if (gp.player.hitbox != null && gp.mouseH.mouseClicked) {
				if (restartButton.isTouching(gp.player)) {
					gp.mouseH.mouseClicked = false;

					gp.restartGame();

					timer = 0;
					waited = false;
				}
			}

			// HOME BUTTON
			if (gp.player.hitbox != null && gp.mouseH.mouseClicked) {
				if (homeButton.isTouching(gp.player)) {
					gp.mouseH.mouseClicked = false;

					gp.ui.titleScreen.waited = false;
					gp.ui.titleScreen.timer = 0;
					gp.gameState = gp.TITLE_STATE;

					timer = 0;
					waited = false;
				}
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
