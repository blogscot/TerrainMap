package model;

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
