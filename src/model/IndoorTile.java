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
			0x00f000,
			0x008000,
			0xa0a0a0,
			0x20c0f0,
			0xa08040,
			0x604020,
	};
	
	private IndoorTile(char value) {
		this.value = value;
	}
	
	/**
	 * 
	 * @param index the ordinal value of the Tile
	 * @param color the requested colour
	 */
	static public void setColor(int index, int color) {
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
	 * @return a random Tile type
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
