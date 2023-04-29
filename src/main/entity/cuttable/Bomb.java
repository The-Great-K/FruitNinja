package main.entity.cuttable;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.entity.EntityEnum;
import main.entity.Player;
import main.window.GamePanel;

public class Bomb extends CuttableEntity {

	public GamePanel gp;

	public EntityEnum type;

	private int timer = 0;
	private boolean waited = false;

	private Random rand = new Random();

	public double xSpeed, ySpeed;
	public double rotateSpeed = rand.nextDouble(-0.5, 0.5);

	public boolean isCut = false;

	public Bomb(GamePanel gp, Player player, int x, int y) {
		super(gp, player, x, y, 0);

		this.gp = gp;

		setDefaultValues();
		getBombImage();

		this.x = x;
		this.y = y;

		this.hitbox = new Rectangle(x, y);
	}

	public void setDefaultValues() {
		this.x = -100;
		this.y = -100;

		this.hitboxOn = true;
		this.isCut = false;

		this.type = EntityEnum.BOMB;
	}

	public void getBombImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/textures/bomb/bomb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.rotatedImage = new BufferedImage(1, 1, image.getType());
	}

	@Override
	public void update() {
		if (!waited) {
			if (timer == 3) {
				waited = true;
				ySpeed = (int) gp.tileHeight / 3;
				double randSpeed = rand.nextDouble(-2, 2);
				ySpeed += randSpeed;
				xSpeed = rand.nextDouble(-2, 2);
			}
		} else {
			if (gp.player.hitbox != null) {
				if (this.isTouching(gp.player) && !this.isCut) {
					this.isCut = true;
					this.hitboxOn = false;

					gp.gameState = gp.GAME_OVER_STATE;
				}
			}

			y -= ySpeed;
			x -= xSpeed;
			rotation -= rotateSpeed;
			if (timer == 5) {
				ySpeed -= 1;
				timer = 0;
			}

			this.rotatedImage = this.rotateImage(rotation, width, height, image);
		}

		timer++;
	}

	@Override
	public void render(Graphics2D g2) {
		BufferedImage rotatedImage = this.rotatedImage;

		this.width = gp.tileWidth * 2;
		this.height = gp.tileHeight * 2;

		if (this.hitboxOn) {
			this.hitbox = new Rectangle(this.x, this.y, width, height);

//			g2.setColor(Color.red);
//			g2.drawRect(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
		}

		g2.drawImage(rotatedImage, this.x, this.y, width, height, null);

	}

	public void write() {
		System.out.println(this.isCut);
	}

}
