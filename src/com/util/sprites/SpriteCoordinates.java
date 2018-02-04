package com.util.sprites;

/**
 * 
 * @author Ewerk4
 *
 *         This is for use by location constants for sprite maps to make calling
 *         for a specific sprite easier.
 * 
 *         Should be used only
 */
public class SpriteCoordinates {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private boolean singleTile;

	/**
	 * For single tile sprites
	 * 
	 * @param x
	 * @param y
	 */
	public SpriteCoordinates(int x, int y) {
		this.x1 = x;
		this.y1 = y;

		// Don't need this data, should throw exception if used as a large
		// sprite.
		this.x2 = -1;
		this.y2 = -1;
		singleTile = true;
	}

	/**
	 * For large sprites
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public SpriteCoordinates(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		singleTile = false;
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}

	public int getX2() {
		return x2;
	}

	public int getY2() {
		return y2;
	}

	/**
	 * 
	 * Returns false if the sprite at this location is larger than a single
	 * tile.
	 * 
	 * @return True if only x1, and y1 coordinates are valid. False if x1, y1,
	 *         x2, y2 are all valid coordinates.
	 */
	public boolean isSingle() {
		return this.singleTile;
	}
}