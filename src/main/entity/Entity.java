package main.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

	public int x, y;
	public int width, height;

	public BufferedImage image;

	public Rectangle hitbox;
	public boolean hitboxOn = false;

	public boolean isTouching(Entity entity) {
		if (entity.hitboxOn) {
			if (entity.hitbox.intersects(hitbox)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}