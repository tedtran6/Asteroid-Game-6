/**
 * This class is the home to making bullets and giving them the x, y and the x velocity
 *  and y velocity and ect.
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.Friendly;

public class Bullet implements Friendly {

	private double x;
	private double y;
	private double velX = 0;
	private double velY = 0;
	public static final int WIDTH = 13;
	public static final int HEIGHT = 12;
	
	
	private SpriteCollection SpCol;
	private Game game;
	/**
	 * 
	 * @param x the x coordinate of the bullet
	 * @param y the y coordinate of the bullet
	 * @param velX the x velocity of the bullet
	 * @param velY the y velocity of the bullet
	 * @param SpCol the sprite collection that contains the image of the bullet
	 * @param game the Game class
	 */
	public Bullet(double x, double y, SpriteCollection SpCol, Game game){
		this.x = x;
		this.y = y;
		this.SpCol = SpCol; 
		this.game = game;
	}
	/**
	 * This method updates the bullet every tick as when it collides with the astroid or the player ect.
	 */
	public void tick(){
		x+=velX;
		y+=velY;
		
		if(game.getAstroid_shot() >= game.getAstroid_count() && game.getMineral_collect() >= game.getAstroid_count()){
			game.getControl().removeEntity(game.getControl().objF);
		}
	}
	/**
	 * displays the bullet
	 */
	public void render(Graphics g){
		g.drawImage(SpCol.bullet, (int) x, (int) y, null);
	}
	/**
	 * @return gets the x value of the bullet
	 */
	public double getX(){
		return x;
	}
	/**
	 * @return gets the y value of the bullet
	 */
	public double getY(){
		return y;
	}
	/**
	 * 
	 * @param x sets the value of the player x coord
	 */
	public void setX(double x){
		this.x = x;
	}
	/**
	 * 
	 * @param y sets the value of the playre y coord
	 */
	public void setY(double y){
		this.y = y;
	}

	/**
	 * 
	 * @param velX sets the velocity of the x to the param
	 */
	public void setVelX(int velX){
		this.velX = velX;
	}
	/**
	 * 
	 * @param vely sets the velocity of the y to the param
	 */
	public void setVelY(int velY){
		this.velY = velY;
	}
	/**
	 * @return int gets the x velocity
	 */
	public int getVelX(){
		return (int) velX;
	}
	/**
	 *  @return int gets the y velocity
	 */
	public int getVelY(){
		return (int) velY;
	}
	/**
	 * @return Rectangle gets the dimensions of the rectangle bounding the object
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
	}

	
}
