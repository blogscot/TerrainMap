package controller;
import model.TerrainMap;
import view.ConsoleRenderer;


public class BasicMapApp {

	public static void main(String[] args) {
		
		TerrainMap myMap = new TerrainMap(40,16);
		myMap.setRenderer(new ConsoleRenderer());
//		myMap.setRenderer(new TestRenderer());
		myMap.draw();
		System.out.println("Passable Area: " + myMap.getPassableArea()+"%");
	}
}
