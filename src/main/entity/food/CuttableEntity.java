package main.entity.food;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.entity.Entity;
import main.entity.EntityEnum;
import main.entity.Player;
import main.window.GamePanel;

public class CuttableEntity extends Entity {

	public GamePanel gp;

	public EntityEnum type;

	private int timer = 0;
	private boolean waited = false;

	public int xSpeed, ySpeed;

	private Random rand = new Random();

	public BufferedImage image_cut_top, image_cut_bottom;

	public boolean isCut = false;

	private boolean strikeAdded = false;

	public int score;

	public CuttableEntity(GamePanel gp, Player player, int x, int y, int score) {
		this.gp = gp;

		setDefaultValues();

		this.x = x;
		this.y = y;
		this.score = score;

		this.hitbox = new Rectangle(x, y);
	}

	public void setDefaultValues() {
		this.x = -100;
		this.y = -100;

		this.hitboxOn = true;
		this.isCut = false;

		this.type = EntityEnum.FOOD;
	}

	public void update() {
		if (!waited) {
			if (timer == 3) {
				waited = true;
				ySpeed = (int) gp.tileHeight / 3;
				xSpeed = rand.nextInt(-3, 3);
			}
		} else {
			if (gp.player.hitbox != null) {
				if (this.isTouching(gp.player) && !this.isCut) {
					this.isCut = true;
					this.hitboxOn = false;

					gp.score += score;
				}
			}

			y -= ySpeed;
			x -= xSpeed;
			if (timer == 5) {
				ySpeed -= 1;
				timer = 0;
			}

			if (this.y >= gp.screenHeight + gp.tileHeight * 3 && !strikeAdded && !isCut) {
				gp.strikes += 1;
				strikeAdded = true;
			}
		}

		timer++;
	}

	public void render(Graphics2D g2) {
	}

}
