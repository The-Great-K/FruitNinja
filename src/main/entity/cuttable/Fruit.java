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

public class Fruit extends CuttableEntity {

	public GamePanel gp;

	private int timer = 0;
	private boolean waited = false;

	private Random rand = new Random();

	public double xSpeed, ySpeed;
	public double rotateSpeed = rand.nextDouble(-0.5, 0.5);

	public String[] fruitList = new String[6];
	private int fruitType = rand.nextInt(fruitList.length);

	public boolean isCut = false;

	private boolean strikeAdded = false;

	public Fruit(GamePanel gp, Player player, int x, int y) {
		super(gp, player, x, y, 1);

		this.gp = gp;

		fruitList[0] = "mango";
		fruitList[1] = "apple";
		fruitList[2] = "banana";
		fruitList[3] = "orange";
		fruitList[4] = "pineapple";
		fruitList[5] = "watermelon";

		setDefaultValues();
		getFruitImage();

		this.x = x;
		this.y = y;

		this.hitbox = new Rectangle(x, y);
	}

	public void setDefaultValues() {
		this.x = -100;
		this.y = -100;

		this.hitboxOn = true;
		this.isCut = false;

		this.type = EntityEnum.FOOD;
	}

	public void getFruitImage() {
		try {
			image = ImageIO.read(getClass()
					.getResourceAsStream("/textures/entity/cuttable/fruits/" + fruitList[fruitType] + ".png"));
			image_cut_top = ImageIO.read(getClass()
					.getResourceAsStream("/textures/entity/cuttable/fruits/" + fruitList[fruitType] + "_top.png"));
			image_cut_bottom = ImageIO.read(getClass()
					.getResourceAsStream("/textures/entity/cuttable/fruits/" + fruitList[fruitType] + "_bottom.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.rotatedImage = new BufferedImage(1, 1, image.getType());
		this.rotatedImage_cut_top = new BufferedImage(1, 1, image_cut_top.getType());
		this.rotatedImage_cut_bottom = new BufferedImage(1, 1, image_cut_bottom.getType());
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

					gp.score += score;
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
			this.rotatedImage_cut_top = this.rotateImage(rotation, width, height, image_cut_top);

			if (this.y >= gp.screenHeight + gp.tileHeight * 3 && !strikeAdded && !isCut) {
				gp.strikes += 1;
				strikeAdded = true;
			}
		}

		timer++;
	}

	@Override
	public void render(Graphics2D g2) {
		BufferedImage rotatedImage = this.rotatedImage;
		BufferedImage rotatedImage_cut_top = this.rotatedImage_cut_top;

		this.width = gp.tileWidth * 2;
		this.height = gp.tileHeight * 2;

		if (this.hitboxOn) {
			this.hitbox = new Rectangle(this.x, this.y, width, height);

//			g2.setColor(Color.red);
//			g2.drawRect(this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
		}

		if (!isCut) {
			g2.drawImage(rotatedImage, this.x, this.y, width, height, null);
		} else {
			g2.drawImage(rotatedImage_cut_top, this.x, this.y, width, height, null);
		}
	}

}
