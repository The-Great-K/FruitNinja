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

	public Button(GamePanel gp, int x, int y, int width, int height) {
		this.gp = gp;

		setDefaultValues();
		getButtonImage();

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.hitbox = new Rectangle(x, y, width, height);
	}

	public void setDefaultValues() {
		this.x = 0;
		this.y = 0;

		this.type = EntityEnum.BUTTON;
	}

	public void getButtonImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/textures/button/button.png"));
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public void update() {
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
