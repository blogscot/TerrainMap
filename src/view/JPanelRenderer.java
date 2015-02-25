package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Tileable;
import model.TiledMap;

/**
 * 
 * JPanelRenderer draws a TiledMap using Swing components. 
 * 
 * @author Iain Diamond
 * @version 23/02/2015
 * 
 */

public class JPanelRenderer implements MapRenderer {

	private JFrame myFrame = new JFrame();
	private TiledMap myMap = null;
	private int tileSize = 10;
	private int mapWidth = 0;
	private int mapHeight = 0;

	// Constructors
	public JPanelRenderer() {
		// Note: the dimensions are arbitrary as the frame resizes on render.
		this(600, 600);
	}
	
	public JPanelRenderer(int width, int height) {

		mapWidth = width;
		mapHeight = height;

		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		myFrame.setSize(mapWidth, mapHeight);
		myFrame.setTitle("Terrain Map");
		myFrame.setBackground(new Color(150, 255, 220));
		myFrame.setVisible(true);
	}

	private void init() {
		
		JPanel panel = new MapPanel();
		panel.setBackground(new Color(200, 100, 255));
		
		Container pane = myFrame.getContentPane();
		pane.add(panel, BorderLayout.CENTER);
	}
	
	@Override
	public void render(TiledMap map) {
		
		// To make the panel look pretty we need some margins
		int widthMargin = 6;
		int heightMargin = 28;
		
		myMap = map;
		mapWidth = myMap.getWidth();
		mapHeight = myMap.getHeight();
		
		// resize the frame to fit the map
		myFrame.setSize(tileSize * mapWidth + widthMargin, 
				tileSize * mapHeight + heightMargin);
		myFrame.setResizable(false);
	}
	
	/**
	 * Sets the tile Size
	 * 
	 * @param size the requested tile size
	 */
	public void setTileSize(int size) {
		if (size > 0 && size < 100) {
			tileSize = size;
		}
	}

	/**
	 * Draws a graphics tile at position x , y
	 * 
	 * @param g the graphics object
	 * @param x the tile's starting x co-ordinate 
	 * @param y the tile's starting y co-ordinate 
	 * @param terrain the tile's terrain type
	 */
	private void drawTile(Graphics g, int x, int y, Tileable terrain) {
		int height = tileSize;
		int width = height;
		
		g.setColor(terrain.toColor());
		g.fillRect(x * width, y * height, width, height);
	}

	private void drawMap(Graphics g) {

		for (int j = 0; j < mapHeight; j++) {
			for (int i = 0; i < mapWidth; i++) {
				drawTile(g, i, j, myMap.getTerrain(i, j));
			}
		}
	}

	// A Panel container class for our map
	private class MapPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			drawMap(g);
		}
	}
}
