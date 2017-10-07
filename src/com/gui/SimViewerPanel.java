package com.gui;

import java.awt.Graphics;
import javax.swing.JPanel;
import com.map.core.SimMap;

public class SimViewerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5457420362972555491L;

	/**
	 * The renderer for this viewer
	 */
	private SimMap simMap;
	private boolean renderGrid;

	public float renderX, renderY;
	public int standardUnit;

	public SimViewerPanel() {
		renderGrid = true;
		renderX = 0;
		renderY = 0;
		standardUnit = 16;
	}

	public void setRenderGrid(boolean renderGrid) {
		this.renderGrid = renderGrid;
	}

	public void setMapToDraw(SimMap simMap) {
		this.simMap = simMap;
	}

	/**
	 * Must use the switchToPanel method before using
	 * 
	 * @param map
	 *            The map to be rendered
	 * @throws IllegalStateException
	 *             If a JPanel hasn't been switched to yet
	 */
	@Override
	public void paint(Graphics g) {
		if (simMap == null) {
			throw new IllegalStateException("Need to initialize simMap first");
		}
		renderFloor(g, simMap);
		if (renderGrid) {
			renderGrid(g);
		}
	}

	/**
	 * Renders the floor of the given map
	 * 
	 * @param g
	 *            Graphics for drawing
	 * @param map
	 *            Map holding the floor to be drawn
	 */
	private void renderFloor(Graphics g, SimMap map) {
		for (int x = (int) renderX; x < map.level.width; x++) {
			for (int y = (int) renderY; y < map.level.height; y++) {
				g.setColor(map.level.floorLayer.floorGrid[x][y].color);
				g.fillRect(x * standardUnit, y * standardUnit, standardUnit,
						standardUnit);
			}
		}
	}

	/**
	 * Renders a grid over the floor
	 * 
	 * @param g
	 *            Graphics of the drawing panel
	 */
	private void renderGrid(Graphics g) {
		int startX = (int) gridXToRenderX(renderXToGridX(renderX));
		for (int x = startX; x < this.getWidth(); x += standardUnit) {
			g.drawLine(x, 0, x, this.getHeight());
		}
		int startY = (int) gridYToRenderY(renderYToGridY(renderY));
		for (int y = startY; y < this.getHeight(); y += standardUnit) {
			g.drawLine(0, y, this.getWidth(), y);
		}
	}

	/**
	 * Translates grid-X coordinate to renderCoordinate-X for tile grid only.
	 * 
	 * @param gridX
	 *            x-axis coordinate value.
	 */
	public float gridXToRenderX(int gridX) {
		return gridX * standardUnit + this.renderX;
	}

	/**
	 * Translates grid-Y coordinate to renderCoordinate-Y for tile grid only.
	 * 
	 * @param gridY
	 *            y-axis coordinate value.
	 */
	public float gridYToRenderY(int gridY) {
		return gridY * standardUnit + this.renderY;
	}

	/**
	 * Translates render-X coordinate to grid-X coordinate for tile grid only.
	 * 
	 * @param renderX
	 *            x-axis coordinate value for rendering.
	 */
	public int renderXToGridX(float renderX) {
		return (int) ((renderX - this.renderX) / standardUnit);
	}

	/**
	 * Translates render-Y coordinate to grid-Y coordinate for tile grid only.
	 * 
	 * @param renderY
	 *            y-axis coordinate value for rendering.
	 */
	public int renderYToGridY(float renderY) {
		return (int) ((renderY - this.renderY) / standardUnit);
	}
}
