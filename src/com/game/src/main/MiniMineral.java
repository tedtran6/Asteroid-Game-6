/**
 * This class dictates what happenes to a mineral such as when it hits the spaceship
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.Neutral;

public class MiniMineral implements Neutral{

	private double x;
	private double y;
	public static final int WIDTH = 28;
	public static final int HEIGHT = 25;

	private SpriteCollection SpCol;
	
	private Game game;
	private Controller c;
	/**
	 * The construtor of the mineral
	 * @param x the x coord
	 * @param y the y coord
	 * @param SpCol the spriteCollection that has all of the object images
	 * @param c the controller obj that has all of the LinkedList obj
	 * @param game The game obj that hass the instances of other obj
	 */
	public MiniMineral(double x, double y, SpriteCollection SpCol, Controller c, Game game){
		this.x = x;
		this.y = y;
		this.SpCol = SpCol;
		this.c = c;
		this.game = game;
	}
	/**
	 * This method updates the Mineral every tick
	 */
	public void tick(){
		if(this.getBounds().intersects(game.getPlayer().getBounds())){
			c.removeEntity(this);
			game.setMineral_collect(game.getMineral_collect() + 1);
			game.setScore(game.getScore() + 5);
		}
	}
	/**
	 * this method draws the minerals indirectly
	 * @param g graphics param to draw the minerals
	 */
	public void render(Graphics g){
		g.drawImage(SpCol.miniMineral, (int)x, (int)y, null);
	}

	/**
	 * @return gets the x value of the player
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return gets the y value of the player
	 */
	public double getY() {
		return y;
	}
	/**
	 * @return Rectangle gets the dimensions of the rectangle bounding the object
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
	}
}
