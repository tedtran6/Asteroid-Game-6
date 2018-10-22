/**
 * the interface for the friendly objects such as the player and the bullets	
 */
package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Friendly {

	/**
	 * updates the friedly every tick
	 */
	public void tick();
	/**
	 * displays and draws the friedly obj
	 * @param g gets the graphics to draw obj
	 */
	public void render(Graphics g);
	/**
	 * @return Rectangle gets the dimensions of the rectangle bounding the object
	 */
	public Rectangle getBounds();
	/**
	 * @return gets the x value of the player
	 */
	public double getX();
	/**
	 * @return gets the y value of the player
	 */
	public double getY();
	/**
	 * @return int gets the x velocity
	 */
	public int getVelX();
	/**
	 *  @return int gets the y velocity
	 */
	public int getVelY();
	/**
	 * @param velX sets the velocity of the x to the param
	 */
	public void setVelX(int velX);
	/**
	 * 
	 * @param velY sets the velocity of the y to the param
	 */
	public void setVelY(int velY);
	
}
