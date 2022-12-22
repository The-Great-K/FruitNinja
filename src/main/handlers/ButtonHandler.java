package main.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.screens.UI;

public class ButtonHandler implements ActionListener {

	public UI ui;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ui.button) {

		}
	}

}
