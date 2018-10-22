/**
 * The SpriteSheet class allows for loading of images
 */

package com.game.src.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	/**Constructs the SpriteSheet object given the image of the entire sprite sheet.
	 */
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}
	
	/**retrieves the image of the sprite sheet
	 * @param col the column that the images are on
	 * @param row the row that the images are on
	 * @param width the width of the image
	 * @param height the height of the image
	 * @return img the image of the sprite sheet
	 */
	public BufferedImage grabImage(int col, int row, int width, int height){
		
		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		return img;
	}
	
}
