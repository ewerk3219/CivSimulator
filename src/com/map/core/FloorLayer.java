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
<<<<<<< HEAD
		System.out.println(
				"x : " + floorGrid[0].length + ", y : " + floorGrid.length);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
=======
		System.out.println("x : " + floorGrid[0].length + ", y : " + floorGrid.length);
		for (int x = 0; x < floorGrid.length; x++) {
			for (int y = 0; y < floorGrid[0].length; y++) {
>>>>>>> 3415391987dd3345e8bab5eea4f4cf97751dc622
				floorGrid[x][y] = new FloorTile(Color.MAGENTA);
			}
		}
	}
}
