package main.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.handlers.KeyHandler;
import main.window.GamePanel;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		x = -500;
		y = -500;
	}

	public void getPlayerImage() {
		try {
			sword = ImageIO.read(getClass().getResourceAsStream("/textures/player/player_sword.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {

	}

	public void render(Graphics2D g2) {
		BufferedImage image = sword;

		g2.drawImage(image, x, y, 50, 50, null);
	}

}
