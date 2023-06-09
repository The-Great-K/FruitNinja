package main.screens;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

import main.screens.optionsmenu.ControlsSettingsScreen;
import main.screens.optionsmenu.SoundSettingsScreen;
import main.screens.optionsmenu.StatisticsSettingsScreen;
import main.screens.optionsmenu.VideoSettingsScreen;
import main.window.GamePanel;

public class UI {

	GamePanel gp;
	Graphics2D g2;

	public Font gameFont;

	public TitleScreen titleScreen;
	public GameScreen gameScreen;
	public GameOverScreen gameOverScreen;

	public SettingsScreen settingsScreen;
	public SoundSettingsScreen audioSettingsScreen;
	public ControlsSettingsScreen controlsSettingsScreen;
	public VideoSettingsScreen videoSettingsScreen;
	public StatisticsSettingsScreen statsSettingsSreen;

	public int commandNum = 0;

	public UI(GamePanel gp) {
		this.gp = gp;
		this.titleScreen = new TitleScreen(this.gp);
		this.gameScreen = new GameScreen(this.gp);
		this.gameOverScreen = new GameOverScreen(this.gp);

		this.settingsScreen = new SettingsScreen(this.gp);
		this.audioSettingsScreen = new SoundSettingsScreen(this.gp);
		this.controlsSettingsScreen = new ControlsSettingsScreen(this.gp);
		this.videoSettingsScreen = new VideoSettingsScreen(this.gp);
		this.statsSettingsSreen = new StatisticsSettingsScreen(this.gp);

		createFont();
	}

	public void createFont() {
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/GameFont.ttf");
			gameFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (gp.gameState == gp.TITLE_STATE && !gp.showOptionsMenu) {
			titleScreen.update();
		}

		if (gp.gameState == gp.PLAY_STATE && !gp.showOptionsMenu) {
			gameScreen.update();
		}

		if (gp.gameState == gp.GAME_OVER_STATE) {
			gameOverScreen.update();
		}

		if (gp.showOptionsMenu) {
			if (gp.settingsScreenState == gp.DEFAULT_SETTINGS_STATE) {
				settingsScreen.update();
			}
			if (gp.settingsScreenState == gp.AUDIO_SETTINGS_STATE) {
				audioSettingsScreen.update();
			}
			if (gp.settingsScreenState == gp.CONTROLS_SETTINGS_STATE) {
				controlsSettingsScreen.update();
			}
			if (gp.settingsScreenState == gp.VIDEO_SETTINGS_STATE) {
				videoSettingsScreen.update();
			}
			if (gp.settingsScreenState == gp.STATS_SETTINGS_STATE) {
				statsSettingsSreen.update();
			}
		}
	}

	public void render(Graphics2D g2) {
		this.g2 = g2;

		g2.setFont(this.gameFont);

		if (gp.gameState == gp.TITLE_STATE && !gp.showOptionsMenu) {
			titleScreen.render(g2);
		}

		if (gp.gameState == gp.PLAY_STATE) {
			gameScreen.render(g2);
		}

		if (gp.gameState == gp.GAME_OVER_STATE) {
			gameOverScreen.render(g2);
		}

		if (gp.showOptionsMenu) {
			if (gp.settingsScreenState == gp.DEFAULT_SETTINGS_STATE) {
				settingsScreen.render(g2);
			}
			if (gp.settingsScreenState == gp.AUDIO_SETTINGS_STATE) {
				audioSettingsScreen.render(g2);
			}
			if (gp.settingsScreenState == gp.CONTROLS_SETTINGS_STATE) {
				controlsSettingsScreen.render(g2);
			}
			if (gp.settingsScreenState == gp.VIDEO_SETTINGS_STATE) {
				videoSettingsScreen.render(g2);
			}
			if (gp.settingsScreenState == gp.STATS_SETTINGS_STATE) {
				statsSettingsSreen.render(g2);
			}
		}
	}

	public void write() {
		if (gp.gameState == gp.TITLE_STATE) {
			titleScreen.write();
		}
		if (gp.gameState == gp.PLAY_STATE) {
			gameScreen.write();
		}
	}

}
