package model;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 
 * The tiled map enum type
 * 
 * @author Iain Diamond
 * @version 23/02/2015
 *
 */

public enum Tile {
	Entrance(' '), Grass('.'), Hedge('H'), Rock('x'), Water('w'), Fence('f'), Tree('T');

	private char value;
	
	private static final List<Tile> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final Random RAND = new Random();
	
	// Tile Colours for each Terrain type 
	static private int[] terrainColors = {
			0xffffff,
			0x00f000,
			0x008000,
			0xa0a0a0,
			0x20c0f0,
			0xa08040,
			0x604020,
	};
	
	private Tile(char value) {
		this.value = value;
	}
	
	/**
	 * 
	 * @param index the ordinal value of the Tile
	 * @param color the requested colour
	 */
	static public void setColor(int index, int color) {
		if (index < terrainColors.length) {
			terrainColors[index] = color % 0xffffff;
		}
	}

	/**
	 *  @return the ASCII representation
	 */
	public char toChar() {
		return value;
	}

	/**
	 * @return the Terrain colour
	 */
	public Color toColor() {
		return new Color(terrainColors[this.ordinal()]);	
	}
	
	/**
	 * @return a random Terrain type
	 */
	static public Tile getRandom() {
		return VALUES.get(RAND.nextInt(VALUES.size()));
	}

	/**
	 * @return True if a tile is considered passable (i.e. it's difficult to walk through trees)
	 */
	public boolean isPassable() {
		return this != Hedge && this != Rock && this != Fence && this != Tree;
	}
}
