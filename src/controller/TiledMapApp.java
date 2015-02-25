package controller;

import model.IndoorTile;
import model.MapFactory;
import model.Tile;
import model.Tileable;
import model.TiledMap;
import view.ConsoleRenderer;
import view.JPanelRenderer;

/**
 * 
 * The Tiled Map Application main class.
 * 
 * @author Iain Diamond
 * @version 25/02/2015
 * 
 */

public class TiledMapApp {

	public static void main(String[] args) {

		OutdoorConsole();

	}
	
	static void IndoorJPanel() {
		
		Tileable[][] factoryMap= new MapFactory().getInstance("Indoor", 60, 80);
		
		TiledMap map = new TiledMap(factoryMap, IndoorTile.Floor, JPanelRenderer.getInstance());
		
		map.setTerrain(10, 10, 20, 15, IndoorTile.Desk);
		map.setTerrainRandomly(22, 32, 20, 20);
		map.setTerrain(40, 20, 10, 20, IndoorTile.Window);
		map.setBorder(IndoorTile.Student, 5);
		map.setBorder(IndoorTile.Lecturer, 2);
		
		JPanelRenderer myRenderer = JPanelRenderer.getInstance(); 
		myRenderer.setTileSize(2);
		map.setRenderer(myRenderer);

		map.render();

	}
	
	static void IndoorConsole() {
		
		Tileable[][] factoryMap= new MapFactory().getInstance("Indoor", 60, 80);
		
		TiledMap map = new TiledMap(factoryMap, IndoorTile.Floor, new ConsoleRenderer());
		
		map.setTerrain(10, 10, 20, 15, IndoorTile.Desk);
		map.setTerrainRandomly(22, 32, 20, 20);
		map.setTerrain(40, 20, 10, 20, IndoorTile.Window);
		map.setBorder(IndoorTile.Student, 5);
		map.setBorder(IndoorTile.Lecturer, 2);
		
		map.render();

	}
	
	static void OutdoorConsole() {
		
		Tileable[][] factoryMap= new MapFactory().getInstance("Outdoor", 60, 80);
		
		TiledMap map = new TiledMap(factoryMap, Tile.Grass, new ConsoleRenderer());
		
		map.setTerrain(10, 10, 20, 15, Tile.Rock);
		map.setTerrainRandomly(22, 32, 10, 12);
		// System.out.println("Passable Area: " + map.getPassableArea()+"%");

		map.setTerrain(40, 20, 10, 20, Tile.Tree);
		map.setBorder(Tile.Fence, 5);
		map.setBorder(Tile.Water, 2);

		map.render();

	}

	static void demo2() {
		
		Tileable[][] factoryMap= new MapFactory().getInstance("Outdoor", 60, 80);
		
		TiledMap map = new TiledMap(factoryMap, Tile.Grass, new ConsoleRenderer());		

		map.setTerrain(10, 10, 20, 15, Tile.Rock);
		map.setTerrainRandomly(22, 32, 10, 12);
		// System.out.println("Passable Area: " + map.getPassableArea()+"%");

		map.setTerrain(40, 20, 10, 20, Tile.Tree);
		map.setBorder(Tile.Fence, 5);
		map.setBorder(Tile.Water, 2);

		map.render();
		
	}
	
	static void demo3() {

		Tileable[][] factoryMap= new MapFactory().getInstance("Outdoor", 60, 80);
		
		TiledMap map = new TiledMap(factoryMap, Tile.Grass, new ConsoleRenderer());	
//		Tile.setColor(Tile.Grass.ordinal(), 0xff0000);

		map.setTerrain(10, 10, 20, 15, Tile.Rock);
		map.setTerrainRandomly(22, 32, 20, 20);
		map.setTerrain(40, 20, 10, 20, Tile.Tree);
		map.setBorder(Tile.Fence, 5);
		map.setBorder(Tile.Water, 2);

//		myRenderer.setTileSize(2);
		map.setRenderer(JPanelRenderer.getInstance());

		map.render();
	}
	
}
