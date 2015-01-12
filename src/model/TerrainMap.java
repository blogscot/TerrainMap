package model;

import java.awt.Dimension;

import view.Renderer;

public class TerrainMap {

	private int width;
	private int height;

	private Terrain[][] tiledMap;

	private Renderer myRenderer;

	public TerrainMap(int width, int height) {
		this.width = width;
		this.height = height;

		tiledMap = new Terrain[width][height];
		createMap();
	}

	/*
	 *  Sets the map renderer using Dependency Injection
	 */
	public void setRenderer(Renderer renderer) {
		this.myRenderer = renderer;
	}

	/**
	 * 
	 * @return Dimension contains the dimensions of the tiled map
	 */
	public Dimension getSize() {
		return new Dimension(width, height);
	}

	public Terrain getTerrainType(int x, int y) {
		return tiledMap[x][y];
	}

	/**
	 * Calculates the percentage of passable area in the map
	 * 
	 * @return double percentage of passable area
	 */
	public double calcPassableArea() {
		return 0.0;
	}

	public void draw() {
		myRenderer.render(this);
	}

	/**
	 * Creates an ASCII Map using '.' for grass, 'H' for hedge and ' ' to
	 * represent an entrance to the tiled map.
	 */
	private void createMap() {

		// initialise the map with grass
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++)
				tiledMap[i][j] = Terrain.Grass;
		}
	}
}
