package main.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

	public int x = -100;
	public int y = -100;
	public int width, height;

	public BufferedImage image;

	public Rectangle hitbox;
	public boolean hitboxOn = false;

	public boolean isTouching(Entity entity) {
		return entity.hitbox.intersects(hitbox);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}