package controller;

import model.Terrain;
import model.TerrainMap;
import view.*;

/**
 * 
 * The main Terrain Map class. 
 * 
 * @author Iain Diamond
 * @version 19/02/2015
 * 
 */

public class TiledMapApp {

	public static void main(String[] args) {
		
		TerrainMap map = new TerrainMap(60, 80, Terrain.Grass);

		 map.setRenderer(new ConsoleRenderer());
		 map.setTerrain(10, 10, 20, 15, Terrain.Rock);
		 map.setTerrainRandomly(22, 32, 10, 12);
//		 System.out.println("Passable Area: " + map.getPassableArea()+"%");
		 
		 map.setRenderer(new JPanelRenderer());
		 map.setBorder(Terrain.Fence, 5);
		 map.setBorder(Terrain.Water, 2);
		 map.setTerrain(20, 60, 10, 20, Terrain.Tree);
		 map.draw();
		 map.setRenderer(new ConsoleRenderer());
		 map.draw();
//		 map.setRenderer(new TestRenderer());
	}
}
