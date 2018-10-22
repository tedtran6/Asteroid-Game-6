/**
 * The SpriteCollection class allows for the use of the sprites of the game to be seen in game
 */
package com.game.src.main;

import java.awt.image.BufferedImage;

public class SpriteCollection {

	public BufferedImage player;
	public BufferedImage bullet;
	public BufferedImage astroid;
	public BufferedImage mineral;
	public BufferedImage miniAstroid;
	public BufferedImage miniMineral;
	
	private SpriteSheet ss = null;
	
	/**Constructs the SpriteCollection object given the game
	 * @param the game object
	 */
	public SpriteCollection(Game game){
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getCollection();
	}

	/**getCollection retrieves the objects images at the certain points on the picture
	 */
	private void getCollection() {
		player = ss.grabImage(1, 1, 32, 32);
		bullet = ss.grabImage(1, 2, 13, 12);
		astroid = ss.grabImage(4, 1, 32, 32);
		mineral = ss.grabImage(4, 2, 32, 32);
		miniAstroid = ss.grabImage(3, 1, 20, 20);
		miniMineral = ss.grabImage(3, 2, 28, 25);
	}
	
	
	
}