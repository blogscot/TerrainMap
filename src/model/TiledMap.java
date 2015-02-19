package model;

import view.Renderer;

public interface TiledMap {
	
	/**
	 * Sets the map renderer
	 * 
	 * @param renderer the User specified renderer
	 */
	public void setRenderer(Renderer renderer);

	/**
	 * Returns the width of the Terrain map
	 * 
	 * @return the map's width
	 */
	public int getWidth();
	
	/**
	 * Returns the height of the Terrain map
	 * 
	 * @return the map's height
	 */
	public int getHeight();

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
	public Terrain getTerrain(int x, int y);
	
	/**
	 * Sets the Terrain area to the defined type.
	 * 
	 * @param x the starting x position
	 * @param y the starting y position
	 * @param width the new region's width
	 * @param height the new region's height
	 * @param terrain the Terrain type
	 */
	public void setTerrain(int x, int y, int width, int height, Terrain terrain);

	/**
	 * Sets the Terrain area to a random type
	 * 
	 * @param x the starting x position
	 * @param y the starting y position
	 * @param width the new region's width
	 * @param height the new region's height
	 */
	public void setTerrain(int x, int y, int width, int height);
	
	/**
	 * Sets the Terrain individual tiles randomly
	 * 
	 * @param x the starting x position
	 * @param y the starting y position
	 * @param width the new region's width
	 * @param height the new region's height
	 */
	public void setTerrainRandomly(int x, int y, int width, int height);

	/**
	 * Calculates the percentage of passable area in the map
	 * 
	 * @return double percentage of passable area
	 */
	public double getPassableArea();

	/**
	 * Draws the map using the user defined Renderer. 
	 * Fails fast.
	 * 
	 */
	public void draw();

	public boolean setBorder(Terrain terrain, int borderWidth);	
	public boolean setBorder(Terrain terrain);
}
	