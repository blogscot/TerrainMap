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
 * @version 28/02/2015
 * 
 */

public class TiledMapApp {

	public static void main(String[] args) {
		IndoorConsole();
	}
	
	static void OutdoorConsole() {
		// Let's make a map using our factory
		Tileable[][] factoryMap= new MapFactory().getInstance("Outdoor", 400, 380);
		
		// And use Dependency Injection to initialise our tiled map  
		TiledMap map = new TiledMap(factoryMap, Tile.Grass, new ConsoleRenderer());
		
		// Draw the borders first so they appear in the background
		map.setBorder(Tile.Fence, 5);
		map.setBorder(Tile.Water, 2);
		
		System.out.println("Passable Area: " + map.getPassableArea()+"%");
		System.out.println("The maps dimensions are: "+map.getWidth()+" by "+map.getHeight());

		// Set groups of tiles to a specified tile types
		map.setTerrain(10, 40, 20, 15, Tile.Rock);
		map.setTerrain(40, 0, 10, 40, Tile.Tree);

		// Set a group of tiles to a single random type 
		map.setTerrain(70, 30, 10, 5);

		// Set a group of individual tiles randomly  
		map.setTerrainRandomly(0, 0, 20, 20);
		
		// Let's create a JPanel renderer. Note, it's a singleton
		JPanelRenderer myRenderer = JPanelRenderer.getInstance(); 
		myRenderer.setTileSize(2);
		map.setRenderer(myRenderer);
//		Tile.setColor(Tile.Grass.ordinal(), 0x00a040);

		map.render();
	}

	static void IndoorConsole() {
		// Let's make a map using our factory
		Tileable[][] factoryMap= new MapFactory().getInstance("Indoor", 80, 60);
		
		// And use Dependency Injection to initialise our tiled map  
		TiledMap map = new TiledMap(factoryMap, IndoorTile.Floor, new ConsoleRenderer());
		
		// Draw the borders first so they appear in the background
		map.setBorder(IndoorTile.Desk, 5);
		map.setBorder(IndoorTile.Lecturer, 2);
		
		System.out.println("Passable Area: " + map.getPassableArea()+"%");
		System.out.println("The maps dimensions are: "+map.getWidth()+" by "+map.getHeight());

		// Set groups of tiles to a specified tile types
		map.setTerrain(10, 40, 20, 15, IndoorTile.Student);
		map.setTerrain(40, 0, 10, 40, IndoorTile.Window);

		// Set a group of tiles to a single random type 
		map.setTerrain(70, 30, 10, 5);

		// Set a group of individual tiles randomly  
		map.setTerrainRandomly(0, 0, 20, 20);
		
		// Let's create a JPanel renderer. Note, it's a singleton
		JPanelRenderer myRenderer = JPanelRenderer.getInstance(); 
//		myRenderer.setTileSize(2);
		map.setRenderer(myRenderer);
//		Tile.setColor(Tile.Grass.ordinal(), 0x00a040);
		
		map.render();
	}
}
