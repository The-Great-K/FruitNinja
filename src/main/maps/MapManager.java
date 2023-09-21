package main.maps;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.window.GamePanel;

public class MapManager {

	public GamePanel gp;
	public Graphics2D g2;

	public Tile[] tile;
	public int mapTileInt[][];

	public MapManager(GamePanel gp) {
		this.gp = gp;

		tile = new Tile[2];
		mapTileInt = new int[gp.SCREEN_TILE_COL][gp.SCREEN_TILE_ROW];

		getTileImage();
		loadMap("/maps/default_map.uwtd");
	}

	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].texture = ImageIO.read(getClass().getResourceAsStream("/textures/tiles/green.png"));

			tile[1] = new Tile();
			tile[1].texture = ImageIO.read(getClass().getResourceAsStream("/textures/tiles/red.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.SCREEN_TILE_COL && row < gp.SCREEN_TILE_ROW) {
				String line = br.readLine();

				while (col < gp.SCREEN_TILE_COL) {
					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					mapTileInt[col][row] = num;
					col++;
				}
				if (col == gp.SCREEN_TILE_COL) {
					System.out.println(line);
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {
		}
	}

	public void update() {

	}

	public void render(Graphics2D g2) {
		this.g2 = g2;

		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;

		while (col < gp.SCREEN_TILE_COL && row < gp.SCREEN_TILE_ROW) {
			int tileInt = mapTileInt[col][row];

			g2.drawImage(tile[tileInt].texture, x, y, gp.tileWidth, gp.tileHeight, null);
			col++;
			x += gp.tileWidth;

			if (col == gp.SCREEN_TILE_COL) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileHeight;
			}
		}
	}

}
