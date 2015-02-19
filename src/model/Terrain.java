package model;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 
 * Defines a set of Terrain values and supporting methods
 * 
 * @author Iain Diamond
 * @version 19/02/2015
 *
 */

public enum Terrain {
	Entrance(' '), Grass('.'), Hedge('H'), Rock('x'), Water('w'), Fence('f'), Tree('T');

	private char value;
	
	private static final List<Terrain> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final Random RAND = new Random();
	
	// Tile Colours for each Terrain type 
	private int[] terrainColors = {
			0xffffff,
			0x00f000,
			0x008000,
			0xa0a0a0,
			0x20c0f0,
			0xa08040,
			0x604020,
	};
	
	private Terrain(char value) {
		this.value = value;
	}

	/**
	 *  @return the ASCII representation for the enum instance
	 */
	public char toChar() {
		return value;
	}

	/**
	 * @return the Terrain colour for the enum instance
	 */
	public Color toColor() {
		return new Color(terrainColors[this.ordinal()]);	
	}
	
	/**
	 * @return a random Terrain type
	 */
	static public Terrain getRandom() {
		return VALUES.get(RAND.nextInt(VALUES.size()));
	}

	/**
	 * @return true if a person can walk through a terrain area. i.e. Trees are hard to walk through.
	 */
	public boolean isPassable() {
		return this != Hedge && this != Rock && this != Fence && this != Tree;
	}
}