package view;

import model.TiledMap;

/**
 * 
 * A TestRenderer, used for debugging. 
 * 
 * @author Iain Diamond
 * @version 23/02/2015
 * 
 */

public class TestRenderer implements MapRenderer {

	@Override
	public void render(TiledMap map) {

		for (int j = 0; j < map.getHeight(); j++) {
			for (int i = 0; i < map.getWidth(); i++) {
				System.out.print(i + "-" + j + " ");
			}
			System.out.println();
		}
	}
}
