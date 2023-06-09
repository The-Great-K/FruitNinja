package main.entity;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Entity extends Object {

	public int x = -100;
	public int y = -100;
	public double rotation;
	public int width, height;

	public EntityEnum type;

	public BufferedImage image;
	public BufferedImage rotatedImage;

	public Rectangle hitbox;
	public boolean hitboxOn = false;

	public boolean isTouching(Entity entity) {
		return entity.hitbox.intersects(hitbox);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	protected BufferedImage rotateImage(double rotation, int width, int height, BufferedImage imageToRotate) {
		double rads, sin, cos;
		int w = 1;
		int h = 1;

		BufferedImage rotatedImage;
		AffineTransform at = new AffineTransform();
		AffineTransformOp atOp;

		// STUFF THAT ROTATES IMAGE
		rads = Math.toRadians(rotation);
		sin = Math.abs(Math.sin(rads));
		cos = Math.abs(Math.cos(rads));
		w = (int) Math.floor(imageToRotate.getWidth() * cos + imageToRotate.getHeight() * sin);
		h = (int) Math.floor(imageToRotate.getHeight() * cos + imageToRotate.getWidth() * sin);
		rotatedImage = new BufferedImage(w, h, imageToRotate.getType());
		at.translate(w / 2, h / 2);
		at.rotate(rads, 0, 0);
		at.translate(-w / 2, -h / 2);
		atOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		atOp.filter(imageToRotate, rotatedImage);

		return rotatedImage;
	}

}