package model;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 
 * An indoor tiled map enum type
 * 
 * @author Iain Diamond
 * @version 25/02/2015
 *
 */

public enum IndoorTile implements Tileable {
	Floor('_'), Door('d'), Chair('h'), Student('s'), Lecturer('L'), Desk('D'), Window('w');

	private char value;
	
	private static final List<IndoorTile> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final Random RAND = new Random();
	
	// Colours for each tile type 
	static private int[] tileColors = {
			0xffffff,
			0x00f0f0,
			0x408000,
			0x2050a0,
			0x20c030,
			0x208040,
			0xf04020,
	};
	
	private IndoorTile(char value) {
		this.value = value;
	}
	
	/**
	 * 
	 * @param tile the tile
	 * @param color the requested colour
	 */
	static public void setColor(Tileable tile, int color) {
		int index = ((IndoorTile)tile).ordinal();
		if (index < tileColors.length) {
			// prevent invalid colours from raising an exception
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
		return this != Chair && this != Desk && this != Student && this != Lecturer;
	}
}
