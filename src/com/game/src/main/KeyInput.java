/**
 * The KeyInput class allows for the input of mouse keys for the game
 */
 
package com.game.src.main;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyInput extends MouseAdapter implements MouseListener{

	Game game;
	private double x, y;
	
	/**constructs our KeyInput class
	 * @param game the game object that key input communicates with
	 */
	public KeyInput(Game game){
		this.game = game;
	}
	
	/**triggers game's mousePressed if mouse is pressed.
	 * @param e the MouseEvent object that occurs on mouse press.
	 */
	public void mousePressed(MouseEvent e){
		game.mousePressed(e);
	}
	
	/**triggers game's mouseReleased if mouse is released.
	 * @param e the MouseEvent object that occurs on mouse release.
	 */
	public void mouseReleased(MouseEvent e){
		game.mouseReleased(e);
	}
	
	/**set the x coordinate of the mouse
	 * @param x the x coordinate
	 */
	public void setX(double x){
		this.x = x;
	}
	
	/**set the y coordinate of the mouse
	 * @param y the y coordinate
	 */
	public void setY(double y){
		this.y = y;
	}
	
	/**get the x coordinate of the mouse
	 * @param x the x coordinate
	 */
	public double getX(){
		return x;
	}
	
	/**get the y coordinate of the mouse
	 * @param y the y coordinate
	 */
	public double getY(){
		return y;
	}
}
