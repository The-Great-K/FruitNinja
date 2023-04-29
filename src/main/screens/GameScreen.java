package main.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.entity.cuttable.Bomb;
import main.entity.cuttable.CuttableEntity;
import main.entity.cuttable.Fruit;
import main.entity.tower.Tower;
import main.handlers.key.GameKeyHandler;
import main.window.GamePanel;

public class GameScreen {

	public GamePanel gp;
	public Graphics2D g2;

	public GameKeyHandler gameKeyH;

	private Random rand = new Random();

	public ArrayList<CuttableEntity> foodList = new ArrayList<>();

	private int timer = 0;
	public int timerMax = 120;
	public int entityAmount = 5;

	public Tower tower;

	public GameScreen(GamePanel gp) {
		this.gp = gp;

		this.gameKeyH = new GameKeyHandler(this.gp);
		tower = new Tower(this.gp);
	}

	public void update() {
		if (timer == timerMax) {
			for (int i = 0; i < entityAmount; i++) {
				int foodX = rand.nextInt(gp.tileWidth * 2, gp.screenWidth - gp.tileWidth * 2);

				int foodType = rand.nextInt(7);

				if (foodType == 0) {
					foodList.add(new Bomb(this.gp, this.gp.player, foodX, gp.screenHeight));
				} else {
					foodList.add(new Fruit(this.gp, this.gp.player, foodX, gp.screenHeight));
				}
			}

			timer = 0;
		}

		if (gp.score > gp.highScore) {
			gp.highScore = gp.score;
		}

		for (int i = 0; i < foodList.size(); i++) {
			foodList.get(i).update();
		}

		if (gp.strikes >= 3) {
			gp.gameState = gp.GAME_OVER_STATE;
			gp.saveData.save();
		}

		timer++;
	}

	public void render(Graphics2D g2) {
		String text;
		int x;
		int y;

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, gp.tileHeight * 1));

		g2.setColor(Color.red);

		for (int i = 0; i < foodList.size(); i++) {
			foodList.get(i).render(g2);

//			text = Integer.toString(i);
//
//			g2.drawString(text, foodList.get(i).getX(), foodList.get(i).getY());
		}

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, gp.tileHeight * 2));

		text = "Score: " + Integer.toString(gp.score);
		x = 0;
		y = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();

		g2.drawString(text, x, y);

		tower.render(g2);
	}

	public void write() {
		System.out.println(foodList.size());
	}

}
