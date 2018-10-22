/**
 * This class dictates what happens when the player is in the main menu
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Menu {

	public Rectangle play = new Rectangle(358, 320, 94, 36);
	public Rectangle help = new Rectangle(363, 368, 85, 32);
	public Rectangle highScore = new Rectangle(294, 413, 223, 32);
	public Rectangle quit = new Rectangle(367, 502, 77, 30);
	
	private BufferedImage menu;
	/**
	 * this method draws the menu screen and displays the image
	 * @param g this is the graphics parameter that we use to draw the images
	 */
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			menu = loader.loadImage("/menu_no_mose.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(menu, 0, 0, null);
		
		g2d.draw(play);
		g2d.draw(help);
		g2d.draw(highScore);
		g2d.draw(quit);
	}
	
}
