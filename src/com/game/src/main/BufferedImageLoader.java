/**
 * This class allows for us to load images
 */
package com.game.src.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

	private BufferedImage image;
	/**
	 * @param path the path of the image to load
	 * @return the image linked to the path given by the param
	 * @throws IOException exception if the image isnt there
	 */
	public BufferedImage loadImage(String path) throws IOException{
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}
}
