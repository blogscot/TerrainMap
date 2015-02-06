package controller;
import model.TerrainMap;
import view.*;


public class TiledMapApp {

	public static void main(String[] args) {
		
		TerrainMap myMap = new TerrainMap(40,40);
//		myMap.setRenderer(new ConsoleRenderer());
		myMap.setRenderer(new JPanelRenderer());		
//		myMap.setRenderer(new TestRenderer());
		myMap.draw();
//		System.out.println("Passable Area: " + myMap.getPassableArea()+"%");
	}
}
