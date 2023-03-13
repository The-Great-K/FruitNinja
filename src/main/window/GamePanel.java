package main.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Main;
import main.entity.Player;
import main.handlers.MouseHandler;
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

	public int TPS = 60; // TICKS PER SECOND

	public SettingsKeyHandler settingsKeyH = new SettingsKeyHandler(this); // KEY CONTROL
	public MouseHandler mouseH = new MouseHandler(this);

	public Thread gameThread; // CONTROLS TIME

	// GAME STATES
	public int gameState;
	public final int TITLE_STATE = 0;
	public final int SETTINGS_STATE = 1;
	public final int PLAY_STATE = 2;
	public final int PAUSE_STATE = 3;
	public final int GAME_OVER_STATE = 4;

	// SCREENS
	public UI ui = new UI(this);

	// OBJECTS
	public Player player = new Player(this);

	public int score = 0;
	public int highScore = 0;

	public GamePanel() {
		setFullScreen();
		this.setMinimumSize(new Dimension(160, 90)); // SETS MINIMUM SCREEN SIZE
		this.setPreferredSize(new Dimension(800, 450)); // SETS SCREEN SIZE
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); // BETTER PERFORMANCE, LOWER FPS, TURN OFF IF YOU WANT TO FLEX FPS
		this.setFocusable(true); // FOCUSES ON KEY INPUT
	}

	public void setupGame() {
		gameState = TITLE_STATE;
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
					write(); // CONSOLE
					fps = 0;
				}
			}
		}

	}

	public void update() { // UPDATES
		player.update();
		ui.update();
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

		ui.render(g2);

		player.render(g2);

		g2.dispose(); // DELETES OLD IMAGE
	}

	public void write() {
		player.write();
		ui.write();
	}

	public void setFullScreen() {
		GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gDevice = gEnvironment.getDefaultScreenDevice();

		gDevice.setFullScreenWindow(Main.window);

		Main.window.dispose();

		Main.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main.window.setResizable(false);
		Main.window.setUndecorated(true);

		Main.window.setSize(realScreenWidth, realScreenHeight);
		Main.window.setLocation(0, 0);
		Main.window.setVisible(true);

		screenWidth = Main.window.getWidth();
		screenHeight = Main.window.getHeight();

		fullScreen = true;
	}

	public void exitFullScreen() {
		Main.window.dispose();

		Main.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // HOW TO CLOSE
		Main.window.setResizable(true); // RESIZABLE?
		Main.window.setUndecorated(false);
		Main.window.setTitle("Fruit Ninja"); // WINDOW TITLE

		Main.window.pack(); // SETS THE WINDOW SIZE TO THE PANEL SIZE

		Main.window.setLocationRelativeTo(null); // PREFFERED LOCATION TO SHOW UP?
		Main.window.setVisible(true); // CAN YOU SEE IT?

		fullScreen = false;
	}

}
