package view;

import model.TerrainMap;

/**
 * 
 * The main Terrain Map class. 
 * 
 * @author Iain Diamond
 * @version 18/02/2015
 * 
 */

public class TestRenderer implements Renderer {

	@Override
	public void render(TerrainMap map) {

		for (int j = 0; j < map.getHeight(); j++) {
			for (int i = 0; i < map.getWidth(); i++) {
				System.out.print(i + "-" + j + " ");
			}
			System.out.println();
		}
	}
}
