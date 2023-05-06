package main.entity.button;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.entity.Entity;
import main.entity.EntityEnum;
import main.window.GamePanel;

public class Button extends Entity {

	protected GamePanel gp;

	private EntityEnum type;

	public String dimensions;

	public Button(GamePanel gp, String dimensions) {
		this.gp = gp;

		setDefaultValues();
		getButtonImage();

		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}

	public Button(GamePanel gp, int x, int y, int width, int height, String dimensions) {
		this.gp = gp;

		setDefaultValues();
		getButtonImage();

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.dimensions = dimensions;
	}

	public void setDefaultValues() {
		this.x = 0;
		this.y = 0;

		this.type = EntityEnum.BUTTON;
	}

	public void getButtonImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/textures/button/button_12x2.png"));
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public void update() {
		this.hitbox = new Rectangle(this.x, this.y, this.width, this.height);
	}

	public void render(Graphics2D g2) {
		BufferedImage image = this.image;

		g2.drawImage(image, x, y, width, height, null);
		if (this.hitboxOn) {
			g2.setColor(Color.red);
			g2.drawRect(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
		}
	}

}
