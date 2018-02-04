package com.util.sprites;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Ewerk4
 * 
 *         A pretty standard sprite array. This one will only give buffered
 *         images.
 */
public class SpriteSheet {
	private BufferedImage originalSpriteSheet;
	private int spritesAlongX;
	private int spritesAlongY;
	private BufferedImage[][] spriteArray;

	public SpriteSheet(String path, int spriteWidth, int spriteHeight) {
		File sprites = new File(path);
		BufferedImage spriteImage = null;
		try {
			spriteImage = ImageIO.read(sprites);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Find out how many sprites we have
		spritesAlongX = spriteImage.getWidth() / spriteWidth;
		spritesAlongY = spriteImage.getHeight() / spriteHeight;

		if (spritesAlongX < 1 || spritesAlongY < 1) {
			throw new IllegalArgumentException(
					"The given image at the path: " + path + " is either too narrow or short for the sprite sizes.");
		}

		// Create the array for the sprites
		spriteArray = new BufferedImage[spritesAlongX][spritesAlongY];

		// Create each sub-image that contains the sprite and save it
		for (int x = 0; x < spritesAlongX; x++) {
			for (int y = 0; y < spritesAlongY; y++) {
				spriteArray[x][y] = spriteImage.getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth,
						spriteHeight);
			}
		}
		this.originalSpriteSheet = spriteImage;
	}

	public int getSpritesAlongX() {
		return spritesAlongX;
	}

	public int getSpritesAlongY() {
		return spritesAlongY;
	}

	public int getSpriteWidth() {
		return spriteArray[0][0].getWidth();
	}

	public int getSpriteHeight() {
		return spriteArray[0][0].getHeight();
	}

	/**
	 * 
	 * @param coord
	 *            Contains the coordinates of a given sprite.
	 * @return Returns either a standard sized sprite or a large sized sprite
	 *         based on whether the SpriteCoordinates given were given two pairs
	 *         of x and y coordinates.
	 */
	public Image getSprite(SpriteCoordinates coord) {
		if (coord.isSingle()) {
			return getSprite(coord.getX1(), coord.getY1());
		} else {
			return this.originalSpriteSheet.getSubimage(coord.getX1(), coord.getY1(), coord.getX2() - coord.getX1(),
					coord.getY2() - coord.getY1());

		}
	}

	/**
	 * 
	 * @param x
	 *            X coordinate along an array of sprites
	 * @param y
	 *            Y coordinate along an array of sprites
	 * @return A buffered image at the given x and y location
	 * 
	 * @exception IllegalArgumentException
	 *                If x or y is out of bounds (0 to spriteWidth or
	 *                spriteHeight)
	 */
	public Image getSprite(int x, int y) {
		if (x < 0 || y < 0 || x > spritesAlongX || y > spritesAlongY) {
			throw new IllegalArgumentException("(" + x + ", " + ") is outside of the bounds of this sprite sheet");
		}
		return spriteArray[x][y];
	}

	/**
	 * Like the getSprite(x, y) but with a point instead
	 * 
	 * @param p
	 *            Point of the location of the sprite
	 * @return A buffered image at the given location
	 */
	public Image getSprite(Point p) {
		return getSprite(p.x, p.y);
	}
}