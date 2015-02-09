package view;

import model.TerrainMap;

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
