package model;

import view.Renderer;

/**
 * 
 * The main Terrain Map class. 
 * 
 * @author Iain Diamond
 * @version 18/02/2015
 * 
 */

final public class TerrainMap implements TiledMap {

	// The stored terrain map dimensions
	private int mapWidth;
	private int mapHeight;

	private Terrain[][] tiledMap;
	private Renderer myRenderer;

	// method input parameter validation return indicators
	private static final boolean INVALID = false;
	private static final boolean SUCCESS = true;
	
	/**
	 * Creates a map of type Terrain with dimensions width by height.
	 * 
	 * @param width the map's width
	 * @param height the map's height
	 * @param terrain the default terrain type
	 */
	public TerrainMap(int width, int height, Terrain terrain) {
		this.mapWidth = width;
		this.mapHeight = height;

		tiledMap = new Terrain[width][height];
		createMap(terrain);
	}

	/**
	 * Returns the width of the Terrain map
	 * 
	 * @return the map's width
	 */
	public int getWidth() {
		return mapWidth;
	}
	
	/**
	 * Returns the height of the Terrain map
	 * 
	 * @return the map's height
	 */
	public int getHeight() {
		return mapHeight;
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
	 * Returns the Terrain type at co-ordinate (x, y).
	 * 
	 * Note: internally the origin is top-left. However, by
	 * translating the Y-axis the origin appears to be bottom-left
	 * when the map is rendered.
	 * 
	 * @param row the map's x position
	 * @param column the map's y position
	 * @return the Terrain type
	 */
	public Terrain getTerrain(int x, int y) {
		return tiledMap[x][translateY(y)];
	}
	
	/**
	 * Sets the Terrain area to the defined type.
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
		
		if (x < 0 || y < 0 || endX > mapWidth || endY > mapHeight) {
			System.err.println("Invalid Terrain parameters:"+"("+x+","+y+","+width+","+height+")");
			return;
		}
		
		for (int j = x; j < endX ; j++) {
			for (int i = y; i < endY; i++) {
				tiledMap[j][i] = terrain;
			}
		}
	}

	/**
	 * Sets the Terrain area to a random type
	 * 
	 * @param x the starting x position
	 * @param y the starting y position
	 * @param width the new region's width
	 * @param height the new region's height
	 */
	public void setTerrain(int x, int y, int width, int height) {
		setTerrain(x, y, width, height, Terrain.getRandom());
	}
	
	/**
	 * Sets the Terrain individual tiles randomly
	 * 
	 * @param x the starting x position
	 * @param y the starting y position
	 * @param width the new region's width
	 * @param height the new region's height
	 */
	public void setTerrainRandomly(int x, int y, int width, int height) {

		// Set up the terrain borders
		int endX = x+width;
		int endY = y+height;
		
		for (int j = x; j < endX ; j++) {
			for (int i = y; i < endY; i++) {
				tiledMap[j][i] = Terrain.getRandom();
			}
		}
	}

	/**
	 * Calculates the percentage of passable area in the map
	 * 
	 * @return double percentage of passable area
	 */
	public double getPassableArea() {
		double totalItems = mapWidth * mapHeight;
		double nonPassableItems = 0;

		for (int j = 0; j < mapHeight; j++) {
			for (int i = 0; i < mapWidth; i++) {
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

	public boolean setBorder(Terrain terrain, int borderWidth) {
		
		if (borderWidth >= mapWidth / 2 ){
			return INVALID;
		}

		for (int j = 0; j < mapHeight; j++) {
			for (int i = 0; i < mapWidth; i++) {

				// if we're at a boundary build a hedge
				if (j < borderWidth || i < borderWidth || 
					j >= mapHeight - borderWidth || i >= mapWidth - borderWidth) {
					tiledMap[i][j] = terrain;
				}
			}
		}
		
		return SUCCESS;
	}
	
	// Sets the Map border with a default value
	public boolean setBorder(Terrain terrain) {
		return setBorder(terrain, 1);
	}

	/**
	 * Creates an ASCII Map using '.' for grass, 'H' for hedge and ' ' to
	 * represent an entrance to the tiled map.
	 */
	private void createMap(Terrain terrain) {
	
		// initialise the map with grass
		for (int j = 0; j < mapHeight; j++) {
			for (int i = 0; i < mapWidth; i++) {
				tiledMap[i][j] = terrain;
			}
		}
	}
	
	/**
	 * Translates the y-axis so that the origin becomes
	 * bottom left, instead of top left.
	 * 
	 * @param value
	 * @return
	 */
	private int translateY(int value) {
		return mapHeight-1 - value;
	}
}
