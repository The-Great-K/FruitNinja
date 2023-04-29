package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.window.GamePanel;

public class SaveData {

	public GamePanel gp;

	public SaveData(GamePanel gp) {
		this.gp = gp;
	}

	public void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

			DataStorage ds = new DataStorage();

			ds.highScore = gp.highScore;

			oos.writeObject(ds);
		} catch (Exception e) {
			System.out.println("Save Exception");
		}
	}

	public void load() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

			DataStorage ds = (DataStorage) ois.readObject();

			gp.highScore = ds.highScore;
		} catch (Exception e) {
			System.out.println("Load Exception");
		}
	}

}
