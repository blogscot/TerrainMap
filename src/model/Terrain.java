package model;

public enum Terrain {
	Entrance(' '),	Grass('.'), Hedge('H');
	
	private char value;
	
	private Terrain(char value) {
		this.value = value;
	}
	
	public char getValue() {
		return value;
	}
	
	public boolean isPassable() {
		return this.value != 'H';
	}
}
