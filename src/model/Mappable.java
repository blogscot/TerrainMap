package model;

import view.MapRenderer;

/**
 * 
 * The Tiled Map interface 
 * 
 * @author Iain Diamond
 * @version 25/02/2015
 *
 */

public interface Mappable {
	
	/**
	 * Returns the width of the map
	 * 
	 * @return the map's width
	 */
	public int getWidth();
	
	/**
	 * Returns the height of the map
	 * 
	 * @return the map's height
	 */
	public int getHeight();

	/**
	 * Sets the map renderer
	 * 
	 * @param renderer the User specified renderer
	 */
	public void setRenderer(MapRenderer renderer);

	/**
	 * Returns the Tile type at co-ordinate (x, y).
	 * 
	 * Note: internally the origin is top-left. However, by
	 * translating the Y-axis the origin appears to be bottom-left
	 * when the map is rendered.
	 * 
	 * @param row the map's x position
	 * @param column the map's y position
	 * @return the Tile type
	 */
	public Tileable getTerrain(int x, int y);
	
	/**
	 * Sets the Terrain area to the defined type.
	 * 
	 * @param x the starting x position
	 * @param y the starting y position
	 * @param width the new region's width
	 * @param height the new region's height
	 * @param tileType the Tile type
	 * 
	 * @return True if terrain was set successfully
	 */
	public boolean setTerrain(int x, int y, int width, int height, Tileable tileType);

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
	public boolean setTerrainTileRandomly(int x, int y, int width, int height);
	
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
	public boolean setTerrainRandomly(int x, int y, int width, int height);

	/**
	 * Draws the map using the user defined renderer. 
	 * 
	 */
	public void render();

	/**
	 * Draws a border around the map
	 * 
	 * @param typeType the tile type
	 * @param borderWidth the border width
	 * 
	 * @return True is the border was set successfully
	 */
	public boolean setBorder(Tileable typeType, int borderWidth);

	/**
	 *  Sets the tile map border with a default border width
	 *  
	 * @param typeType the tile type
	 * 
	 * @return True if border was set successfully
	 */	
	public boolean setBorder(Tileable typeType);

	/**
	 * Calculates the percentage of passable area in the map
	 * 
	 * @return percentage of passable area
	 */
	public double getPassableArea();
}
	