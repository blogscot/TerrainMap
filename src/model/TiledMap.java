package model;

import view.MapRenderer;

/**
 * 
 * The TiledMap class constructs, renders and updates 2D maps of IndoorTiletype. 
 * 
 * @author Iain Diamond
 * @version 23/02/2015
 * 
 */

final public class TiledMap implements Mappable {

	// The stored map dimensions
	private int mapWidth;
	private int mapHeight;
	private Tileable initTile;

	private Tileable[][] tiledMap;
	private MapRenderer myRenderer;

	// method input parameter validation return indicators
	private static final boolean INVALID = false;
	private static final boolean SUCCESS = true;
	
	/**
	 * Constructs a tiled map with dimensions width by height.
	 * 
	 * @param width the map's width
	 * @param height the map's height
	 * @param terrain the default terrain type
	 */
	public TiledMap(Tileable[][] map, Tileable terrain) {
		mapWidth = map.length;
		mapHeight = map[0].length;
		
		initTile = terrain;
		tiledMap = map;
		createMap(terrain);
	}
	
	/**
	 * Constructs a tiled map with dimensions width by height.
	 * 
	 * @param width the map's width
	 * @param height the map's height
	 * @param terrain the default terrain type
	 */
	public TiledMap(int width, int height, Tileable terrain) {
		this.mapWidth = width;
		this.mapHeight = height;
		initTile = terrain;

		MapFactory factory = new MapFactory();
		tiledMap= factory.getInstance("Outdoor", width, height);
		createMap(terrain);
	}
	
	/**
	 * Constructs a tiled map as above, with a Map renderer
	 * 
	 * @param width the map's width
	 * @param height the map's height
	 * @param terrain the default terrain type
	 * @param renderer the initial renderer
	 */
	public TiledMap(int width, int height, Tileable terrain, MapRenderer renderer) 
	{
		this(width, height, terrain);
		setRenderer(renderer);
	}


	/**
	 * Returns the width of the tiled map
	 * 
	 * @return the map's width
	 */
	public int getWidth() {
		return mapWidth;
	}
	
	/**
	 * Returns the height of the tiled map
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
	public void setRenderer(MapRenderer renderer) {
		this.myRenderer = renderer;
	}

	/**
	 * Returns the IndoorTiletype at co-ordinate (x, y).
	 * 
	 * Note: internally the origin is top-left. However, by
	 * translating the Y-axis the origin appears to be bottom-left
	 * when the map is rendered.
	 * 
	 * @param row the map's x position
	 * @param column the map's y position
	 * @return the IndoorTiletype
	 */
	public Tileable getTerrain(int x, int y) {
		return tiledMap[x][translateY(y)];
	}
	
	/**
	 * Sets the Terrain area to the defined type.
	 * 
	 * @param x the starting x position
	 * @param y the starting y position
	 * @param width the new region's width
	 * @param height the new region's height
	 * @param terrain the IndoorTiletype
	 * 
	 * @return True if terrain was set successfully
	 */
	public boolean setTerrain(int x, int y, int width, int height, Tileable terrain) {

		// Set up the terrain borders
		int endX = x+width;
		int endY = y+height;
		
		if (!isInMap(x, y, endX, endY)){
			System.err.println("Invalid Terrain parameters:"+"("+x+","+y+","+width+","+height+")");
			return INVALID;
		}
		
		for (int j = x; j < endX ; j++) {
			for (int i = y; i < endY; i++) {
				tiledMap[j][i] = terrain;
			}
		}
		return SUCCESS;
	}

	/**
	 * Sets the Terrain area to a random type
	 * 
	 * @param x the starting x position
	 * @param y the starting y position
	 * @param width the new region's width
	 * @param height the new region's height
	 * 
	 * @return True if terrain was set successfully
	 */
	public boolean setTerrainTileRandomly(int x, int y, int width, int height) {
		return setTerrain(x, y, width, height, initTile.getRandom());
	}
	
	/**
	 * Sets the Terrain area's individual tiles randomly
	 * 
	 * @param x the starting x position
	 * @param y the starting y position
	 * @param width the new region's width
	 * @param height the new region's height
	 * 
	 * @return True if terrain was set successfully
	 */
	public boolean setTerrainRandomly(int x, int y, int width, int height) {

		// Set up the terrain borders
		int endX = x+width;
		int endY = y+height;
		
		if (!isInMap(x, y, endX, endY)){
			System.err.println("Invalid Terrain parameters:"+"("+x+","+y+","+width+","+height+")");
			return INVALID;
		}
		
		for (int j = x; j < endX ; j++) {
			for (int i = y; i < endY; i++) {
				
				tiledMap[j][i] = initTile.getRandom();
			}
		}
		return SUCCESS;
	}

	/**
	 * Calculates the percentage of passable area in the map
	 * 
	 * @return percentage of passable area
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
	 * Draws the map using the user defined renderer. 
	 * 
	 */
	public void render() {

		if (myRenderer == null) {
			System.out.println("Error: No render defined.");
			return;
		}
		myRenderer.render(this);
	}


	/**
	 * Draws a border around the tiled map
	 * 
	 * @param terrain the IndoorTiletype
	 * @param borderWidth the border width
	 * 
	 * @return True is the border was set successfully
	 */
	public boolean setBorder(Tileable terrain, int borderWidth) {
		
		if (borderWidth > mapWidth / 2 ){
			System.err.println("Invalid Border set: "+borderWidth);
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
	
	/**
	 *  Sets the IndoorTilemap border with a default border width
	 *  
	 * @param terrain the IndoorTiletype
	 * 
	 * @return True if border was set successfully
	 */
	public boolean setBorder(Tileable terrain) {
		return setBorder(terrain, 1);
	}
	
	
	/**
	 * Checks if the start and end co-ordinates are contained with
	 * the tiled map
	 * 
	 * @param x starting x position
	 * @param y starting y position
	 * @param endX end x position
	 * @param endY end y position
	 * 
	 * @return True is co-ordinates are contained by the map 
	 */
	private boolean isInMap(int x, int y, int endX, int endY) {
		if (x < 0 || y < 0 || endX > mapWidth || endY > mapHeight) {
			return INVALID;
		}
		return SUCCESS;
	}

	/**
	 * Initialises the tiled map 
	 * 
	 * @param terrain the IndoorTiletype
	 */
	private void createMap(Tileable terrain) {
	
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
	 * @param value the original y value
	 * @return the translated y value
	 */
	private int translateY(int value) {
		return mapHeight - 1 - value;
	}

}
