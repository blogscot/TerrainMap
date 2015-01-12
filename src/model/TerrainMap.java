package model;

import java.awt.Dimension;

import view.Renderer;

public class TerrainMap {

	private int row;
	private int column;

	private Terrain[][] tiledMap;

	private Renderer myRenderer;

	public TerrainMap(int row, int column) {
		this.row = row;
		this.column = column;

		tiledMap = new Terrain[row][column];
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
		return new Dimension(row, column);
	}

	public Terrain getTerrainType(int x, int y) {
		return tiledMap[x][y];
	}

	/**
	 * Calculates the percentage of passable area in the map
	 * 
	 * @return double percentage of passable area
	 */
	public double getPassableArea() {
		double totalItems = row * column;
		double nonPassableItems = 0;
		
		for (int j = 0; j < column; j++) {
			for (int i = 0; i < row; i++) {
				char tile = tiledMap[i][j].getValue();
				if (tile == Terrain.Hedge.getValue()) {
					nonPassableItems += 1;
				}
			}
		}
		return (1 - nonPassableItems / totalItems) * 100;
	}

	/**
	 * Calls the user defined Renderer. Fails fast.
	 * 
	 */
	public void draw() {

		if (myRenderer == null) {
			System.out.println("Error: No render defined.");
			return;
		}
		myRenderer.render(this);
	}

	/**
	 * Creates an ASCII Map using '.' for grass, 'H' for hedge and ' ' to
	 * represent an entrance to the tiled map.
	 */
	private void createMap() {
		
		// initialise the map with grass
			for (int j = 0; j < column; j++) {
				for (int i = 0; i < row; i++) {
				tiledMap[i][j] = Terrain.Grass;
				
				// if we're at a boundary build a hedge
				if (j == 0 || i == 0 || j == column - 1 || i == row - 1) {
					tiledMap[i][j] = Terrain.Hedge;
				}
			}
		}

		// cut an entrance in the bottom hedge
		int middle = column / 2;
		int entranceStartPos = isOdd(row) ? middle -2 : middle - 3;
		for (int i = row-1, j = entranceStartPos; j < entranceStartPos+6; j++) {
			tiledMap[i][j] = Terrain.Grass;
		}
	}
	
	private boolean isEven(int value) {
		return value % 2 == 0; 
	}
	
	private boolean isOdd(int value) {
		return !isEven(value);
	}
}
