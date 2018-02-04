package com.map.core;

import com.util.sprites.TerrainData;

public class FloorTile {

	public TerrainData terrainData;

	public FloorTile(TerrainData terrainData) {
		this.terrainData = terrainData;
	}

	public TerrainData getTerrainData() {
		return this.terrainData;
	}
}
