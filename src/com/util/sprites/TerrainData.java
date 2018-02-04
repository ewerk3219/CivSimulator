package com.util.sprites;

import java.awt.Image;

/**
 * 
 * @author Ewerk4
 *
 *         Contains data of a specific terrain type.
 */
public class TerrainData {
	private String name;
	private Image terrain;

	public TerrainData(String terrainName, Image terrain) {
		this.name = terrainName;
		this.terrain = terrain;
	}

	public TerrainData copy() {
		return new TerrainData(name, terrain);
	}

	/**
	 * @return A new String object identical to the original.
	 */
	public String getTerrainData() {
		return name + "";
	}

	public Image getTerrainImage() {
		return terrain;
	}
	// Could put all sorts of terrain data in here.
}
