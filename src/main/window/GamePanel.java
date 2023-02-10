package main.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JPanel;

import main.Main;
import main.entity.Player;
import main.handlers.SettingsKeyHandler;
import main.screens.UI;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = -5621533987836333041L;

	// SCREEN SETTINGS
	public Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();

	public int realScreenWidth = (int) screenDimensions.getWidth() + 1;
	public int realScreenHeight = (int) screenDimensions.getHeight() + 1;

	public int screenWidth, screenHeight;

	public final int screenTileRow = 32;
	public final int screenTileCol = 18;
	public int tileWidth, tileHeight;

	public boolean fullScreen = false;

	int TPS = 60; // TICKS PER SECOND

	public SettingsKeyHandler settingsKeyH = new SettingsKeyHandler(); // KEY CONTROL

	public Thread gameThread; // CONTROLS TIME

	// GAME STATES
	public int gameState;
	public final int titleState = 0;

	// SCREENS
	public UI ui = new UI(this);

	// OBJECTS
	public Player player = new Player(this);

	public GamePanel() {
		setFullScreen();
		this.setMinimumSize(new Dimension(160, 90)); // SETS MINIMUM SCREEN SIZE
		this.setPreferredSize(new Dimension(1600, 900)); // SETS SCREEN SIZE
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // BETTER PERFORMANCE, LOWER FPS, TURN OFF IF YOU WANT TO FLEX FPS
		this.setFocusable(true); // FOCUSES ON KEY INPUT
		this.addKeyListener(settingsKeyH); // LISTENS FOR KEY INPUT
	}

	public void setupGame() {
		gameState = titleState;
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() { // GAME LOOP
		while (gameThread != null) {
			// DELTA GAME LOOP
			double drawInterval = 1000000000 / TPS;
			double delta = 0;
			long lastTime = System.nanoTime();
			long timer = System.currentTimeMillis();
			long fps = 0;

			while (gameThread != null) {
				long currentTime = System.nanoTime();
				delta += (currentTime - lastTime) / drawInterval;
				lastTime = currentTime;

				while (delta >= 1) {
					update(); // UPDATE
					delta--;
				}
				if (gameThread != null) {
					repaint(); // RENDER
				}

				fps++;
				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					System.out.println("FPS: " + fps);
					write();
					fps = 0;
				}
			}
		}

	}

	public void update() { // UPDATES
		player.update();
	}

	@Override
	public void paintComponent(Graphics g) { // RENDERS
		screenWidth = Main.window.getWidth();
		screenHeight = Main.window.getHeight();
		if (screenWidth == 0 || screenHeight == 0) {
			screenWidth = realScreenWidth;
			screenHeight = realScreenHeight;
		}

		if (settingsKeyH.fullScreen != fullScreen) {
			if (settingsKeyH.fullScreen) {
				setFullScreen();
			} else {
				exitFullScreen();
			}
		}

		if (settingsKeyH.quit) {
			System.exit(0);
		}

		tileWidth = screenWidth / screenTileRow;
		tileHeight = screenHeight / screenTileCol;

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		if (gameState == titleState) {
			ui.render(g2);
		}

		player.render(g2);

		g2.dispose(); // DELETES OLD IMAGE
	}

	public void write() {
		player.write();
	}

	public void setFullScreen() {
		GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gDevice = gEnvironment.getDefaultScreenDevice();

		gDevice.setFullScreenWindow(Main.window);

		Main.window.dispose();

		Main.window.setResizable(false);
		Main.window.setUndecorated(true);

		Main.window.setVisible(true);

		screenWidth = Main.window.getWidth();
		screenHeight = Main.window.getHeight();

		fullScreen = true;
	}

	public void exitFullScreen() {
		Main.window.dispose();

		Main.window.setResizable(true);
		Main.window.setUndecorated(false);

		Main.window.setVisible(true);

		fullScreen = false;
	}

}
