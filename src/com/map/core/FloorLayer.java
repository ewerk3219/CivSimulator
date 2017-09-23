package com.map.core;

import java.awt.Color;

/**
 * Describes the floor of a level.
 * 
 * @author Eric Hoover
 *
 */
public class FloorLayer {

	public FloorTile[][] floorGrid;
	public final int width, height;

	public FloorLayer(int width, int height) {
		floorGrid = new FloorTile[width][height];
		this.width = width;
		this.height = height;
	}

	public void defaultInitNullTiles() {
		for (int x = 0; x < floorGrid[0].length; x++) {
			for (int y = 0; y < floorGrid.length; y++) {
				floorGrid[x][y] = new FloorTile(Color.MAGENTA);
			}
		}
	}
}
