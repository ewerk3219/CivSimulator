package com.map.core;

public class SimMap {

	/**
	 * Each tile is 1 by 1 meters in size.
	 */
	public static final float STANDARD_UNIT = 1;

	public Level level;

	public SimMap(int width, int height) {
		level = new Level(width, height);
		defaultInit();
	}

	public void defaultInit() {
		level.floorLayer.defaultInitNullTiles();
	}
}
