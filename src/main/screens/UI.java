package main.screens;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

import main.window.GamePanel;

public class UI {

	GamePanel gp;
	Graphics2D g2;

	public Font gameFont;

	public TitleScreen titleScreen;
	public GameScreen gameScreen;
	public GameOverScreen gameOverScreen;

	public int commandNum = 0;

	public UI(GamePanel gp) {
		this.gp = gp;
		this.titleScreen = new TitleScreen(this.gp);
		this.gameScreen = new GameScreen(this.gp);
		this.gameOverScreen = new GameOverScreen(this.gp);

		createFont();
	}

	public void createFont() {
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/GameFont.ttf");
			gameFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (gp.gameState == gp.PLAY_STATE) {
			gameScreen.update();
		}
	}

	public void render(Graphics2D g2) {
		this.g2 = g2;

		g2.setFont(this.gameFont);

		if (gp.gameState == gp.TITLE_STATE) {
			drawTitleScreen();
		}

		if (gp.gameState == gp.PLAY_STATE) {
			drawGameScreen();
		}

		if (gp.gameState == gp.GAME_OVER_STATE) {
			drawGameOverScreen();
		}
	}

	public void write() {
		if (gp.gameState == gp.PLAY_STATE) {
			gameScreen.write();
		}
	}

	public void drawTitleScreen() {
		titleScreen.render(g2);
	}

	public void drawGameScreen() {
		gameScreen.render(g2);
	}

	public void drawGameOverScreen() {
		gameOverScreen.render(g2);
	}

}
