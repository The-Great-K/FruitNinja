package main.screens;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.window.GamePanel;

public class UI {

	GamePanel gp;
	Graphics2D g2;

	public Font gameFont;

	public TitleScreen titleScreen;
	public GameScreen gameScreen;

	public int commandNum = 0;
	private BufferedImage image;
	private String text;

	private int x, y;

	public UI(GamePanel gp) {
		this.gp = gp;
		this.titleScreen = new TitleScreen(this.gp);
		this.gameScreen = new GameScreen(this.gp);

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
	}

	public void write() {
		if (gp.gameState == gp.PLAY_STATE) {
			gameScreen.write();
		}
	}

	public void drawTitleScreen() {
		// BACKGROUND
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/textures/backgrounds/wood.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		titleScreen.render(g2);
	}

	public void drawGameScreen() {
		// BACKGROUND
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/textures/backgrounds/wood.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		gameScreen.render(g2);
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
