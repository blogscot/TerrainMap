package model;

import java.awt.Color;

public enum Terrain {
	Entrance(' '), Grass('.'), Hedge('H'), Rock('x');

	private char value;

	private Terrain(char value) {
		this.value = value;
	}

	public char getValue() {
		return value;
	}

	public Color getTerrainColor() {

		switch (this) {
		case Entrance:
			return new Color(250, 250, 250);
		case Grass:
			return new Color(0, 250, 0);
		case Hedge:
			return new Color(0, 150, 0);
		case Rock:
			return new Color(150,150,0);
		default:
			return Color.red;
		}
	}

	public boolean isPassable() {
		return this != Hedge && this != Rock;
	}
}
