package main;

import javax.swing.JFrame;

import main.window.GamePanel;

public class Main {

	public static JFrame window;

	public static void main(String[] args) {
		// MAKES A WINDOW
		window = new JFrame(); // THE WINDOW
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // HOW TO CLOSE
		window.setResizable(true); // RESIZABLE?
		window.setUndecorated(false); // WINDOW BUTTON OFF
		window.setTitle("Union Wars - Tower Defense \"pre-alpha2\""); // WINDOW TITLE

		window.pack(); // SETS THE WINDOW SIZE TO THE PANEL SIZE

		window.setLocationRelativeTo(null); // PREFFERED LOCATION TO SHOW UP?
		window.setVisible(true); // CAN YOU SEE IT?

		GamePanel gamePanel = new GamePanel(); // THE PANEL
		window.add(gamePanel); // ADDS THE PANEL TO THE WINDOW

		gamePanel.startGameThread(); // STARTS GAME TIME

		gamePanel.setupColorList(); // SETS UP COLOR LIST
		gamePanel.setupGame(); // SETS UP GAME
	}

}
