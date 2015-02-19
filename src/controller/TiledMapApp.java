package controller;

import model.Terrain;
import model.TiledMap;
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

		TiledMap map = new TiledMap(60, 80, Terrain.Grass,
				new ConsoleRenderer());

		map.setTerrain(10, 10, 20, 15, Terrain.Rock);
		map.setTerrainRandomly(22, 32, 10, 12);
		// System.out.println("Passable Area: " + map.getPassableArea()+"%");

		map.setTerrain(40, 20, 10, 20, Terrain.Tree);
		map.setBorder(Terrain.Fence, 5);
		map.setBorder(Terrain.Water, 2);
		map.render();
		map.setRenderer(new JPanelRenderer());
		map.render();
		// map.setRenderer(new TestRenderer());
	}
}
