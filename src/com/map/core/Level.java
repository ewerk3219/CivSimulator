package com.map.core;

/**
 * A level contains layers: Floor Layer
 * 
 * @author Eric Hoover
 *
 */
public class Level {

	public FloorLayer floorLayer;

	public int width, height;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		floorLayer = new FloorLayer(width, height);
	}

}
