package model;

import java.awt.Color;

public interface Tileable {
	/**
	 *  @return the ASCII representation
	 */
	public char toChar();

	/**
	 * @return the Terrain colour
	 */
	public Color toColor();
	
	/**
	 * @return True if a tile is considered passable (i.e. it's difficult to walk through trees)
	 */
	public boolean isPassable();
	
	/**
	 * @return a random Terrain type
	 */
	public Tileable getRandom();
}
