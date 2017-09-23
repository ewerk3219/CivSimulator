package com.map.camera;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import com.map.core.SimMap;

public class Camera {

	private JPanel drawingPanel;
	public boolean renderGrid;

	public float renderX, renderY;
	public int standardUnit;

	public Camera(JPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		Rectangle clippingRegion = new Rectangle(0, 0, drawingPanel.getWidth(),
				drawingPanel.getHeight());
		this.drawingPanel.getGraphics().setClip(clippingRegion);
		renderGrid = true;
		renderX = 0;
		renderY = 0;
		standardUnit = 16;
	}

	public void render(SimMap map) {
		Graphics g = drawingPanel.getGraphics();
		if (renderGrid) {
			renderGrid(g);
		}
		renderFloor(g, map);
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
		for (int x = startX; x < drawingPanel.getWidth(); x += standardUnit) {
			g.drawLine(x, 0, x, drawingPanel.getHeight());
		}
		int startY = (int) gridYToRenderY(renderYToGridY(renderY));
		for (int y = startY; y < drawingPanel.getHeight(); y += standardUnit) {
			g.drawLine(0, y, drawingPanel.getWidth(), y);
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
