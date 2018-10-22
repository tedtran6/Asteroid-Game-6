/**
 * The Help class allows for the help menu to work
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Help {
	public Rectangle play = new Rectangle(697, 43, 48, 22);

	private BufferedImage help;

	/**render renders the image for the help menu
	 * @param g, the Graphics objects
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			help = loader.loadImage("/final help menu no mouse.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(help, 0, 0, null);


		g2d.draw(play);
	}
}
