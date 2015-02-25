package model;

/**
 * 
 * The Tiled Map Factory produces tiled maps
 * 
 * @author Iain Diamond
 * @version 25/02/2015
 *
 */

public final class MapFactory {

	private static Tile[][] map;
	
	public MapFactory(){}
	
	public Tileable[][] getInstance(String tileType, int width, int height) {
		if (map == null) {
			switch(tileType) {
			case "Outdoor":
				return new Tile[width][height];
			case "Indoor":
				return new IndoorTile[width][height];
			}
		}
		return map;
	}
}
