package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Terrain;
import model.TerrainMap;

public class JPanelRenderer implements Renderer {

	JFrame myFrame = new JFrame();
	TerrainMap myMap = null;

	public JPanelRenderer() {

		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		myFrame.setSize(600, 600);
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

	private void drawTerrain(Graphics g, int x, int y, Terrain terrain) {
		int height = 10;
		int width = height;

		switch (terrain) {
		case Entrance:
			g.setColor(new Color(250, 250, 250));
			break;
		case Grass:
			g.setColor(new Color(0, 250, 0));
			break;
		case Hedge:
			g.setColor(new Color(0, 150, 0));
			break;
		default:
		}

		g.fillRect(x * width, y * height, width, height);
	}

	private void drawMap(Graphics g) {

		for (int j = 0; j < myMap.getHeight(); j++) {
			for (int i = 0; i < myMap.getWidth(); i++) {
				drawTerrain(g, i, j, myMap.getTerrainType(i, j));
			}
		}
	}

	class MapPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.setColor(Color.blue);
			g.fillRect(40, 40, 30, 30);

			drawMap(g);
		}
	}

	@Override
	public void render(TerrainMap map) {
		myMap = map;
		// myFrame.repaint();
	}
}
