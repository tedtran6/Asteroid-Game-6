/**
 * This class dictates what the attributes of the player are. such as the x coord, y coord, x velocity and the y velocity
 */
package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.Friendly;
import com.game.src.main.classes.Hostile;


public class Player implements Friendly {

	private double x;
	private double y;
	
	private double velX = 0;
	private double velY = 0;
	private final double HEALTHCONST = 20;
	
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;
	
	private SpriteCollection SpCol;
	
	Game game;
	Controller control;
	/**
	 * The constructor that is used to make the player obj
	 * @param x the x coord
	 * @param y the y coord
	 * @param SpCol the sprite sheet that contains the playre img
	 * @param game The game class obj
	 * @param control the controller that keeps the playre in a linked list
	 */
	public Player(double x, double y, SpriteCollection SpCol, Game game, Controller control){
		this.x = x;
		this.y = y;
		this.SpCol = SpCol;
		this.game = game;
		this.control = control;
	}
	/**
	 * This method updates the player every tick as when it collides with the wall or the astroid etc.
	 */
	public void tick(){
		x+=velX;
		y+=velY;

		//BOUNCE
		if(x < 0)
			velX *= -1;
		if(x > (Game.WIDTH * Game.SCALE) - 22)
			velX *= -1;
		if(y < 0)
			velY *= -1;
		if(y > (Game.HEIGHT * Game.SCALE) - 22)
			velY *= -1;	
		
		//TELEPORT
		if(x <= -32)
			x = (Game.WIDTH * Game.SCALE) - 22;
		if(x > (Game.WIDTH * Game.SCALE))
			x = 0;
		if(y <= -32)
			y = (Game.HEIGHT * Game.SCALE) - 22;
		if(y > (Game.HEIGHT * Game.SCALE))
			y = 0;
		
		for(int i = 0; i < game.enObj.size(); i++){
			Hostile tempHost = game.enObj.get(i);
			
			if(Collision.collides(this, tempHost)){
				control.removeEntity(tempHost);
				Game.HEALTH -= HEALTHCONST;
				game.setCollision_destroy(game.getCollision_destroy() + 1);
			}
		}
		
	}
	/**
	 * displays the health and the player itself
	 */
	public void render(Graphics g){
		
		g.drawImage(SpCol.player, (int)x, (int)y, null);
	}
	/**
	 * @return gets the x value of the player
	 */
	public double getX(){
		return x;
	}
	/**
	 * @return gets the y value of the player
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
	public void setVelX(double velX){
		this.velX = velX;
	}
	/**
	 * 
	 * @param velY sets the velocity of the y to the param
	 */
	public void setVelY(double velY){
		this.velY = velY;
	}
	/**
	 * @return Rectangle gets the dimensions of the rectangle bounding the object
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
	}
	/**
	 * @return int gets the x velocity
	 */
	public int getVelX() {

		return (int) velX;
	}
	/**
	 *  @return int gets the y velocity
	 */
	public int getVelY() {

		return (int) velY;
	}
	/**
	 * @param velX sets the xvelocity to the given param of the x velocity
	 */
	public void setVelX(int velX) {

		this.velX = velX;
	}

	/**
	 * @param velY sets the y velocity to the given param of the y velocity
	 */
	public void setVelY(int velY) {

		this.velY = velY;
	}
}
