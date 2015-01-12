package controller;
import view.ConsoleRenderer;
import model.TerrainMap;


public class BasicMapApp {

	public static void main(String[] args) {
		
		TerrainMap myMap = new TerrainMap(40,35);
		myMap.setRenderer(new ConsoleRenderer());
		myMap.draw();
	}
}
