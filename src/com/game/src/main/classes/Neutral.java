/**
 * the interface for the Neutral objects such as the MiniMinerals and Mineral
 */
package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Neutral {
	/**
	 * updates the friedly every tick
	 */
	public void tick();
	/**
	 * displays and draws the friedly obj
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
}
