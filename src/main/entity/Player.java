package main.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Main;
import main.window.GamePanel;

public class Player extends Entity {

	public GamePanel gp;

	public EntityEnum type;

	public Point location = new Point(0, 0);

	public Player(GamePanel gp) {
		this.gp = gp;
		gp.setCursor(gp.getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB),
				new Point(), null));

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		this.x = 0;
		this.y = 0;

		this.hitboxOn = true;

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
		if (Main.window.isVisible()) {
			location = gp.getLocationOnScreen();
		} else {
			location.setLocation(0, 0);
		}

		this.x = mouse.x - location.x - gp.tileWidth / 6;
		this.y = mouse.y - location.y - gp.tileHeight / 6;
		this.width = gp.tileWidth / 3;
		this.height = gp.tileHeight / 3;
	}

	public void render(Graphics2D g2) {
		BufferedImage image = this.image;

		this.hitbox = new Rectangle(x, y, width, height);

		g2.drawImage(image, x, y, gp.tileWidth / 3, gp.tileHeight / 3, null);
		g2.setColor(Color.red);
		g2.drawRect(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
	}

	public void write() {
		System.out.println("x: " + this.x);
		System.out.println("y: " + this.y);
		System.out.println("window x: " + this.location.x);
		System.out.println("window y: " + this.location.y);
	}

}
