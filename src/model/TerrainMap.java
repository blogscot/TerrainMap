package model;

import view.Renderer;

final public class TerrainMap {

	private int width;
	private int height;

	private Terrain[][] tiledMap;
	private Renderer myRenderer;

	/**
	 * Creates a map of type Terrain of dimensions x by y.
	 * 
	 * @param width the map's width
	 * @param height the map's height
	 * @param initialValue the map's initial value
	 */
	public TerrainMap(int width, int height, Terrain initialValue) {
		this.width = width;
		this.height = height;

		tiledMap = new Terrain[width][height];
		createMap(initialValue);
	}

	/**
	 * Sets the map renderer
	 * 
	 * @param renderer the User specified renderer
	 */
	public void setRenderer(Renderer renderer) {
		this.myRenderer = renderer;
	}

	/**
	 * Returns the width of the Terrain map
	 * 
	 * @return the map's width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the height of the Terrain map
	 * 
	 * @return the map's height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the Terrain type at co-ordinate (x, y)
	 * 
	 * @param row the map's x position
	 * @param column the map's y position
	 * @return the Terrain type
	 */
	public Terrain getTerrain(int x, int y) {
		return tiledMap[x][y];
	}
	
	/**
	 * Sets the Terrain at co-ordinate (x,y).
	 * 
	 * @param x the starting x position
	 * @param y the starting y position
	 * @param width the new region's width
	 * @param height the new region's height
	 * @param terrain the Terrain type
	 */
	public void setTerrain(int x, int y, int width, int height, Terrain terrain) {

		// Set up the terrain borders
		int endX = x+width;
		int endY = y+height;
		
		for (int j = x; j < endX ; j++) {
			for (int i = y; i < endY; i++) {
				tiledMap[j][i] = terrain;
			}
		}
	}

	/**
	 * Calculates the percentage of passable area in the map
	 * 
	 * @return double percentage of passable area
	 */
	public double getPassableArea() {
		double totalItems = width * height;
		double nonPassableItems = 0;

		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				if (!tiledMap[i][j].isPassable()) {
					nonPassableItems += 1;
				}
			}
		}
		return (1 - nonPassableItems / totalItems) * 100;
	}

	/**
	 * Draws the map using the user defined Renderer. 
	 * Fails fast.
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
	private void createMap(Terrain terrain) {

		// initialise the map with grass
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				tiledMap[i][j] = terrain;

				// if we're at a boundary build a hedge
				if (j == 0 || i == 0 || j == height - 1 || i == width - 1) {
					tiledMap[i][j] = Terrain.Hedge;
				}
			}
		}

		// cut an entrance in the bottom hedge
		int middle = height / 2;
		int entranceStartPos = isOdd(height) ? middle - 2 : middle - 3;
		for (int _row = width - 1, _col = entranceStartPos; _col < entranceStartPos + 5; _col++) {
			tiledMap[_row][_col] = Terrain.Grass;
		}
	}
	
	public void createBorder(Terrain terrain) {

		// initialise the map with grass
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {

				// if we're at a boundary build a hedge
				if (j == 0 || i == 0 || j == height - 1 || i == width - 1) {
					tiledMap[i][j] = terrain;
				}
			}
		}

		// cut an entrance in the bottom hedge
		int middle = height / 2;
		int entranceStartPos = isOdd(height) ? middle - 2 : middle - 3;
		for (int _row = width - 1, _col = entranceStartPos; _col < entranceStartPos + 5; _col++) {
			tiledMap[_row][_col] = Terrain.Grass;
		}
	}

	/**
	 * Returns true if parameter is even
	 * 
	 * @param value integer value
	 * @return true if value is even
	 */
	private boolean isEven(int value) {
		return value % 2 == 0;
	}
	
	/**
	 * Returns true if parameter is odd
	 * 
	 * @param value integer value
	 * @return true if value is odd
	 */
	private boolean isOdd(int value) {
		return !isEven(value);
	}
}
