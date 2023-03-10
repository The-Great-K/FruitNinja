package main.screens;

import java.awt.Graphics2D;

import main.entity.food.Fruit;
import main.window.GamePanel;

public class GameScreen {

	public GamePanel gp;
	public Graphics2D g2;

	private Fruit fruit;

	public GameScreen(GamePanel gp) {
		this.gp = gp;
		fruit = new Fruit(this.gp, this.gp.player, 0, 450);
	}

	public void update() {
		fruit.update();
	}

	public void render(Graphics2D g2) {
		fruit.render(g2);
	}

	public void write() {
		fruit.write();
	}

}
