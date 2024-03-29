package main.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.SaveData;
import main.Main;
import main.entity.Player;
import main.entity.button.Button;
import main.handlers.MouseHandler;
import main.handlers.WindowHandler;
import main.handlers.key.GameKeyHandler;
import main.handlers.key.UniversalKeyHandler;
import main.screens.UI;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = -5621533987836333041L;

	// SCREEN SETTINGS
	public Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize(); // SCREEN DIMENSIONS

	public int realScreenWidth = (int) screenDimensions.getWidth() + 1; // SCREEN WIDTH
	public int realScreenHeight = (int) screenDimensions.getHeight() + 1; // SCREEN HEIGHT

	public int screenWidth, screenHeight; // WINDOW DIMENSIONS

	public final int SCREEN_TILE_ROW = 32; // TILES PER ROW
	public final int SCREEN_TILE_COL = 18; // TILES PER COLUMN
	public int tileWidth, tileHeight;

	public boolean fullScreen = false; // IS FULLSCREEN

	public final int TPS = 60; // TICKS PER SECOND

	// SAVE AND LOAD
	public SaveData saveData = new SaveData(this);
	public int saveTimer = TPS * 299;
	public final int SAVE_TIMER_MAX = TPS * 300;

	public UniversalKeyHandler universalKeyH = new UniversalKeyHandler(this); // UNIVERSAL KEY CONTROL
	public GameKeyHandler gameKeyH = new GameKeyHandler(this); // GAME KEY CONTROL
	public MouseHandler mouseH = new MouseHandler(this); // MOUSE CONTROL
	public WindowHandler windowH = new WindowHandler(this); // WINDOW CONTROL

	public Thread gameThread; // CONTROLS TIME

	// BUTTONS
	public ArrayList<Button> buttonList = new ArrayList<>();

	// GAME STATES
	public int gameState;
	public boolean showOptionsMenu = false;
	public final int TITLE_STATE = 0;
	public final int PLAY_STATE = 1;
	public final int GAME_OVER_STATE = 2;

	// SETTINGS STATES
	public int settingsScreenState;
	public final int DEFAULT_SETTINGS_STATE = 0;
	public final int AUDIO_SETTINGS_STATE = 1;
	public final int CONTROLS_SETTINGS_STATE = 2;
	public final int VIDEO_SETTINGS_STATE = 3;
	public final int STATS_SETTINGS_STATE = 4;

	// COLOR THEMES
	public Color[] colorList = new Color[10];
	public int colorIndex = 0;
	public Color colorState;
	public final Color RED = new Color(255, 0, 0);
	public final Color ORANGE = new Color(191, 94, 10);
	public final Color YELLOW = new Color(209, 181, 21);
	public final Color LIME = new Color(0, 255, 0);
	public final Color GREEN = new Color(6, 105, 11);
	public final Color CYAN = new Color(0, 255, 255);
	public final Color BLUE = new Color(24, 9, 153);
	public final Color PURPLE = new Color(57, 2, 77);
	public final Color PINK = new Color(235, 16, 202);
	public final Color WHITE = new Color(255, 255, 255);

	// SCREENS
	public UI ui = new UI(this);

	// CURSORS
	public Cursor defaultCursor = new Cursor(0);
	public Cursor selectCursor = new Cursor(12);

	// OBJECTS
	public Player player = new Player(this);

	// GAME SCORES
	public int score = 0;
	public int highScore = 0;
	public int strikes = 0;

	public GamePanel() {
		setFullScreen();
		this.setMinimumSize(new Dimension(160, 90)); // SETS MINIMUM SCREEN SIZE
		this.setPreferredSize(new Dimension(800, 450)); // SETS SCREEN SIZE
		this.setBackground(Color.black); // DEFAULT BACKGROUND COLOR
		this.setDoubleBuffered(true); // BETTER PERFORMANCE, LOWER FPS, TURN OFF IF YOU WANT TO FLEX FPS
		this.setFocusable(true); // FOCUSES ON LISTENERS
	}

	public void setupGame() { // SETS UP GAME INFORMATION
		gameState = TITLE_STATE;
		settingsScreenState = DEFAULT_SETTINGS_STATE;
		ui.preInit();
		saveData.load();
	}

	public void setupColorList() {
		colorIndex = 0;
		colorList[0] = RED;
		colorList[1] = ORANGE;
		colorList[2] = YELLOW;
		colorList[3] = LIME;
		colorList[4] = GREEN;
		colorList[5] = CYAN;
		colorList[6] = BLUE;
		colorList[7] = PURPLE;
		colorList[8] = PINK;
		colorList[9] = WHITE;
		colorState = colorList[colorIndex];
	}

	public void startGameThread() { // STARTS GAME TIME
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
		// RUNS THE LISTENERS
		requestFocus(true);

		if (Button.buttonNum < Button.realButtonNum) {
			setCursor(selectCursor);
		} else {
			setCursor(defaultCursor);
		}

		// UPDATES FOR OBJECTS
		player.update();
		ui.update();

		// SAVES EVERY 5 MINUTES
		if (saveTimer == SAVE_TIMER_MAX) {
			saveData.save();

			saveTimer = 0;
		}
		saveTimer++;
	}

	@Override
	public void paintComponent(Graphics g) { // RENDERS
		// TELLS THE GAME HOW BIG THE WINDOW IS
		screenWidth = Main.window.getWidth();
		screenHeight = Main.window.getHeight();
		if (screenWidth == 0 || screenHeight == 0) {
			screenWidth = realScreenWidth;
			screenHeight = realScreenHeight;
		}

		// RUNS FULLSCREEN
		if (universalKeyH.fullScreen != fullScreen) {
			if (universalKeyH.fullScreen) {
				setFullScreen();
			} else {
				exitFullScreen();
			}
		}

		// QUIT SWITCH
		if (universalKeyH.quit) {
			System.exit(0);
		}

		// SETS TILE SIZE
		tileWidth = screenWidth / SCREEN_TILE_ROW;
		tileHeight = screenHeight / SCREEN_TILE_COL;

		// RUNS THE GRAPHICS ENGINE
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		// SETS THE COLOR THEME
		this.colorState = colorList[colorIndex];

		// RENDERS FOR OBJECTS
		ui.render(g2);
		player.render(g2);

		g2.dispose(); // DELETES OLD IMAGE
	}

	public void write() { // CONSOLE
		// DEBUG TEXT DISPLAYED IN CONSOLE
		player.write();
		ui.write();
		System.out.println("Button Count: " + Button.buttonNum);
		System.out.println();
	}

	public void setFullScreen() { // ENTERS FULLSCREEN
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
		universalKeyH.fullScreen = true;
	}

	public void exitFullScreen() { // EXITS FULLSCREEN
		Main.window.dispose();

		Main.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // HOW TO CLOSE
		Main.window.setResizable(true); // RESIZABLE?
		Main.window.setUndecorated(false);

		Main.window.pack(); // SETS THE WINDOW SIZE TO THE PANEL SIZE

		Main.window.setLocationRelativeTo(null); // PREFFERED LOCATION TO SHOW UP?
		Main.window.setVisible(true); // CAN YOU SEE IT?

		fullScreen = false;
		universalKeyH.fullScreen = false;
	}

	public void restartGame() {
		this.score = 0;
		this.ui.gameScreen.foodList.clear();
		this.strikes = 0;
		this.gameState = this.PLAY_STATE;
	}

}