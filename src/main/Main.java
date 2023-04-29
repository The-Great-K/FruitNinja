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
		window.setTitle("Fruit Ninja"); // WINDOW TITLE

		GamePanel gamePanel = new GamePanel(); // THE PANEL
		window.add(gamePanel); // ADDS THE PANEL TO THE WINDOW

		window.pack(); // SETS THE WINDOW SIZE TO THE PANEL SIZE

		window.setLocationRelativeTo(null); // PREFFERED LOCATION TO SHOW UP?
		window.setVisible(true); // CAN YOU SEE IT?

		gamePanel.startGameThread(); // STARTS GAME TIME

		gamePanel.setupGame(); // SETS UP GAME
	}

}
