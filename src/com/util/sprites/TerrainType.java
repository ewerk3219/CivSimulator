package com.util.sprites;

import com.util.sprites.spriteConstants.TerrainPoints;

public class TerrainType {
	public static final String[] TERRAIN_LIST = { "Grass", "Stone", "Dirt", "Brick", "Sand", "Water" };
	public static final SpriteSheet SPRITE_SHEET = new SpriteSheet("res/TileSets/TerrainTilesMinecraft.png", 16, 16);
	public static final TerrainData GRASS = new TerrainData("Grass", SPRITE_SHEET.getSprite(TerrainPoints.GRASS));
	public static final TerrainData STONE = new TerrainData("Stone", SPRITE_SHEET.getSprite(TerrainPoints.STONE));
	public static final TerrainData DIRT = new TerrainData("Dirt", SPRITE_SHEET.getSprite(TerrainPoints.DIRT));
	public static final TerrainData BRICK = new TerrainData("Brick", SPRITE_SHEET.getSprite(TerrainPoints.BRICK));
	public static final TerrainData SAND = new TerrainData("Sand", SPRITE_SHEET.getSprite(TerrainPoints.SAND));
	public static final TerrainData WATER = new TerrainData("Water", SPRITE_SHEET.getSprite(TerrainPoints.WATER));

	/**
	 * Quick way to get a terrain type via a string name of the type.
	 * 
	 * @param terrainName
	 *            The name of the given terrain type (is case sensitive)
	 * @return terrain type mapped to the given String. Will return null if
	 *         there is no match.
	 */
	public static TerrainData getTerrainDataCopy(String terrainName) {
		TerrainData copyTerrain = null;
		if (terrainName.equals("Grass")) {
			copyTerrain = GRASS;
		}
		if (terrainName.equals("Stone")) {
			copyTerrain = STONE;
		}
		if (terrainName.equals("Dirt")) {
			copyTerrain = DIRT;
		}
		if (terrainName.equals("Brick")) {
			copyTerrain = BRICK;
		}
		if (terrainName.equals("Sand")) {
			copyTerrain = SAND;
		}
		if (terrainName.equals("Water")) {
			copyTerrain = WATER;
		}
		if (copyTerrain != null) {
			return copyTerrain.copy();
		} else {
			return null;
		}
	}
}
