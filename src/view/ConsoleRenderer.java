package view;

import model.TiledMap;

/**
 * ConsoleRenderer draws a TiledMap to Standard Out
 * 
 * @author Iain Diamond
 * @version 23/02/2015
 *
 */

public class ConsoleRenderer implements MapRenderer {

	@Override
	public void render(TiledMap map) {

		for (int j = 0; j < map.getHeight(); j++) {
			for (int i = 0; i < map.getWidth(); i++) {
				System.out.print(map.getTerrain(i, j).toChar());
			}
			System.out.println();
		}
	}
}
