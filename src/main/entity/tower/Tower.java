package main.entity.tower;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.entity.Entity;
import main.entity.EntityEnum;
import main.window.GamePanel;

public class Tower extends Entity {

	public GamePanel gp;
	public Graphics2D g2;

	public Tower(GamePanel gp) {
		this.gp = gp;

		setDefaultValues();
		setTowerImage();
	}

	public void setDefaultValues() {
		this.x = 0;
		this.y = 0;

		this.hitboxOn = true;

		this.type = EntityEnum.TOWER;
	}

	public void setTowerImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/textures/entity/tower/tower.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
	}

	public void render(Graphics2D g2) {
		this.g2 = g2;
		this.width = gp.tileWidth * 3;
		this.height = gp.tileHeight * 3;
		this.x = gp.screenWidth - this.width - gp.tileWidth * 2;
		this.y = gp.screenHeight / 2 - this.width / 2;

		g2.drawImage(this.image, x, y, width, height, null);
	}

}
