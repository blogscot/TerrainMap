package controller;

import model.Tile;
import model.TiledMap;
import view.ConsoleRenderer;
import view.JPanelRenderer;

/**
 * 
 * The main Tiled Map class.
 * 
 * @author Iain Diamond
 * @version 23/02/2015
 * 
 */

public class TiledMapApp {

	public static void main(String[] args) {

		demo2();

	}

	static void demo1() {
		TiledMap map = new TiledMap(60, 80, Tile.Grass,
				new ConsoleRenderer());

		map.setTerrain(10, 10, 20, 15, Tile.Rock);
		map.setTerrainRandomly(22, 32, 10, 12);
		// System.out.println("Passable Area: " + map.getPassableArea()+"%");

		map.setTerrain(40, 20, 10, 20, Tile.Tree);
		map.setBorder(Tile.Fence, 5);
		map.setBorder(Tile.Water, 2);

		map.render();
		
	}
	
	static void demo2() {

		TiledMap map = new TiledMap(60, 80, Tile.Grass,
				new ConsoleRenderer());
//		Tile.setColor(Tile.Grass.ordinal(), 0xff0000);

		map.setTerrain(10, 10, 20, 15, Tile.Rock);
		map.setTerrainRandomly(22, 32, 20, 20);
		map.setTerrain(40, 20, 10, 20, Tile.Tree);
		map.setBorder(Tile.Fence, 5);
		map.setBorder(Tile.Water, 2);

//		myRenderer.setTileSize(2);
		map.setRenderer(new JPanelRenderer());

		map.render();
	}
	
}
