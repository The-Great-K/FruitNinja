package main.entity.button;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.entity.Entity;
import main.entity.EntityEnum;
import main.window.GamePanel;

public class Button extends Entity {

	public GamePanel gp;
	public Graphics2D g2;

	private EntityEnum type;

	public String dimensions;
	public String text;

	public int textX, textY;

	public int[] dimensionsToInt = new int[2];

	public Button(GamePanel gp, String dimensions, String text) {
		this.gp = gp;

		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.dimensions = dimensions;
		this.text = text;

		setDefaultValues();
		getButtonImage();
	}

	public Button(GamePanel gp, int x, int y, int width, int height, String dimensions, String text) {
		this.gp = gp;

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.dimensions = dimensions;
		this.text = text;

		setDefaultValues();
		getButtonImage();
	}

	public void setDefaultValues() {
		this.x = 0;
		this.y = 0;

		this.type = EntityEnum.BUTTON;
	}

	public void getButtonImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/textures/button/button_" + dimensions + ".png"));
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public void update() {
		this.hitbox = new Rectangle(this.x, this.y, this.width, this.height);
	}

	public void render(Graphics2D g2) {
		this.g2 = g2;

		BufferedImage image = this.image;

		g2.drawImage(image, x, y, width, height, null);

		g2.setColor(gp.colorState);
		double temp = gp.tileWidth * 1.25;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, (int) temp));
		this.textX = getXForCenteredText(text);
		this.textY = getYForCenteredText(text);
		g2.drawString(this.text, this.textX, this.textY);
//		if (this.hitboxOn) {
//			g2.setColor(Color.red);
//			g2.drawRect(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
//		}
	}

	public int getXForCenteredText(String text) {
		int width = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = this.x + this.width / 2 - width / 2;
		return x;
	}

	public int getYForCenteredText(String text) {
		int height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();
		int y = this.y + this.height / 2 + height / 3;
		return y;
	}

}
