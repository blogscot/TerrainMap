package model;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 
 * An outdoor tiled map enum type
 * 
 * @author Iain Diamond
 * @version 25/02/2015
 *
 */

public enum Tile implements Tileable {
	Entrance(' '), Grass('.'), Hedge('H'), Rock('x'), Water('w'), Fence('f'), Tree('T');

	private char value;
	
	private static final List<Tile> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final Random RAND = new Random();
	
	// Colours for each tile type 
	static private int[] tileColors = {
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
	 * @param tile the tile
	 * @param color the requested colour
	 */
	static public void setColor(Tileable tile, int color) {
		int index = ((Tile)tile).ordinal();
		if (index < tileColors.length) {
			tileColors[index] = color % 0xffffff;
		}
	}

	/**
	 *  @return the ASCII representation
	 */
	public char toChar() {
		return value;
	}

	/**
	 * @return the Tile colour
	 */
	public Color toColor() {
		return new Color(tileColors[this.ordinal()]);	
	}
	
	/**
	 * @return a random Tileable type
	 */
	public Tileable getRandom() {
		return VALUES.get(RAND.nextInt(VALUES.size()));
	}

	/**
	 * @return True if a tile is considered passable (i.e. it's difficult to walk through trees)
	 */
	public boolean isPassable() {
		return this != Hedge && this != Rock && this != Fence && this != Tree;
	}
}
