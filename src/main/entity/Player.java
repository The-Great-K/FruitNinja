package main.entity;

import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.handlers.SettingsKeyHandler;
import main.window.GamePanel;

public class Player extends Entity {

	GamePanel gp;
	SettingsKeyHandler keyH;

	EntityEnum type;

	public Player(GamePanel gp) {
		this.gp = gp;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		x = -500;
		y = -500;

		this.type = EntityEnum.PLAYER;
	}

	public void getPlayerImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/textures/player/player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		Point mouse = MouseInfo.getPointerInfo().getLocation();

		this.x = mouse.x;
		this.y = mouse.y;
	}

	public void render(Graphics2D g2) {
		BufferedImage image = this.image;

		g2.drawImage(image, x, y, gp.tileWidth / 2, gp.tileHeight / 2, null);
	}

	public void write() {
		System.out.println("x: " + this.x);
		System.out.println("y: " + this.y);
	}

}
