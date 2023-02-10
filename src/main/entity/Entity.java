package main.entity;

import java.awt.image.BufferedImage;

public class Entity {

	public int x, y;

	public BufferedImage image;

}

enum EntityEnum {
	PLAYER, FRUIT, BOMB, BUTTON
}