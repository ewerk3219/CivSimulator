package com.map.core;

import java.awt.Color;

import com.util.sprites.TerrainType;

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
		System.out.println("x : " + floorGrid[0].length + ", y : " + floorGrid.length);
		for (int x = 0; x < floorGrid.length; x++) {
			for (int y = 0; y < floorGrid[0].length; y++) {
				floorGrid[x][y] = new FloorTile(TerrainType.getTerrainDataCopy("Grass"));
			}
		}
	}
}
