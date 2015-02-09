package controller;

import model.Terrain;
import model.TerrainMap;
import view.*;

public class TiledMapApp {

	public static void main(String[] args) {

		TerrainMap map = new TerrainMap(40, 30);

		 map.setRenderer(new ConsoleRenderer());
		 map.setTerrain(10, 10, 4, 8, Terrain.Rock);
		 map.draw();
		 
		 map.setRenderer(new JPanelRenderer());
		 map.createBorder(Terrain.Rock);
		 map.draw();
//		 map.setRenderer(new TestRenderer());
		System.out.println("Passable Area: " + map.getPassableArea()+"%");
	}
}
