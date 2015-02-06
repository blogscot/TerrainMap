package view;

import java.awt.Dimension;

import model.TerrainMap;

public class ConsoleRenderer implements Renderer {

	@Override
	public void render(TerrainMap map) {

		Dimension size = map.getSize();

		for (int i = 0; i < size.width; i++) {
			for (int j = 0; j < size.height; j++) {
				System.out.print(map.getTerrainType(i, j).getValue());
			}
			System.out.println();
		}
	}
}
