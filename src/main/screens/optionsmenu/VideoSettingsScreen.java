package main.screens.optionsmenu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.entity.button.Button;
import main.handlers.MouseHandler;
import main.window.GamePanel;

public class VideoSettingsScreen {

	public GamePanel gp;
	public Graphics2D g2;

	private BufferedImage image = null;
	private String text;

	private int x, y;

	public int timer = 0;
	public boolean waited = false;

	private Button colorButton;

	private int colorIndex = 0;

	private MouseHandler mouseH;

	public VideoSettingsScreen(GamePanel gp) {
		this.gp = gp;
		colorButton = new Button(gp, "16x2", "COLOR");
		this.mouseH = new MouseHandler(gp);
	}

	public void update() {
		colorButton.update();

		colorButton.setX(gp.tileWidth);
		colorButton.setY(gp.tileHeight);
		colorButton.setWidth(gp.tileWidth * 16);
		colorButton.setHeight(gp.tileHeight * 2);
	}

	public void render(Graphics2D g2) {
		if (!waited) {
			if (timer >= 60) {
				waited = true;
			}
			timer++;
		} else {
			this.g2 = g2;

			colorButton.render(g2);

			if (gp.player.hitbox != null && mouseH.mouseClicked) {
				if (colorButton.isTouching(gp.player)) {
					mouseH.mouseClicked = false;

					if (gp.colorIndex + 1 >= 10) {
						gp.colorIndex = 0;
					} else {
						gp.colorIndex += 1;
					}
				}
			}
		}
	}

}
