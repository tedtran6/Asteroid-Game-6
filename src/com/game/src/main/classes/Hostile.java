/**
 * the interface for the hostile objects such as the MiniAstroids and Astroids
 */
package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Hostile {
	
	/**
	 * updates the friedly every tick
	 */
	public void tick();
	/**
	 * displays and draws the hostile obj
	 * @param g gets the graphics to draw obj
	 */
	public void render(Graphics g);
	/**
	 * @return Rectangle gets the dimensions of the rectangle bounding the object
	 */
	public Rectangle getBounds();
	/**
	 * @return gets the x value of the obj
	 */
	public double getX();
	/**
	 * @return gets the y value of the obj
	 */
	public double getY();
	/**
	 * 
	 * @return int gets the health of the astroid
	 */
	int getAst_Health();
	/**
	 * @param health sets the health of the astroid
	 */
	void setAst_Health(int health);

	
}
