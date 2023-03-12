package main.screens;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.entity.food.Fruit;
import main.window.GamePanel;

public class GameScreen {

	public GamePanel gp;
	public Graphics2D g2;

	private Random rand = new Random();

	private ArrayList<Fruit> foodList = new ArrayList<>();

	private int timer = 0;

	public GameScreen(GamePanel gp) {
		this.gp = gp;
	}

	public void update() {
		if (timer == 180) {
			for (int i = 0; i < 3; i++) {
				int fruit_x = rand.nextInt(gp.screenWidth - gp.tileWidth);

				foodList.add(new Fruit(this.gp, this.gp.player, fruit_x, gp.screenHeight));
			}

			timer = 0;
		}

		for (int i = 0; i < foodList.size(); i++) {
			foodList.get(i).update();
		}

		timer++;
	}

	public void render(Graphics2D g2) {
		for (int i = 0; i < foodList.size(); i++) {
			foodList.get(i).render(g2);
		}
	}

	public void write() {
	}

}
