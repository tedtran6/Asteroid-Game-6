/**
 * This class is the home to making astroids and giving them the x, y and the x velocity
 *  and y velocity and ect.
 */
package com.game.src.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.src.main.classes.Friendly;
import com.game.src.main.classes.Hostile;

public class Astroid implements Hostile {

	private double x;
	private double y;
	private int velX;
	private int velY;
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;
	private int ast_health = 30;

	private SpriteCollection SpCol;

	private Game game;
	private Controller c;

	/**
	 * the constructor of the astroid
	 * @param x the x coordinate of the big astroid
	 * @param y the y coordinate of the big astroid
	 * @param velX the x velocity of the big astroid
	 * @param velY the y velocity of the big astroid
	 * @param SpCol the sprite collection that contains the image of the big astroid
	 * @param c the controller that contains the linked list and all of the big astroid
	 * @param game the Game class
	 */
	public Astroid(double x, double y, int velX, int velY, SpriteCollection SpCol, Controller c, Game game) {
		this.velX = velX;
		this.velY = velY;
		this.x = x;
		this.y = y;
		this.SpCol = SpCol;
		this.c = c;
		this.game = game;
	}

	/**
	 * This method updates the astroid every tick as when it collides with the wall or the bullet ect.
	 */

	public void tick() {
		x += velX;
		y += velY;

		if (x < 0)
			velX *= -1;
		if (x > (Game.WIDTH * Game.SCALE) - 22)
			velX *= -1;
		if (y < 0)
			velY *= -1;
		if (y > (Game.HEIGHT * Game.SCALE) - 22)
			velY *= -1;

		// TELEPORT
		if (x <= -10)
			x = (Game.WIDTH * Game.SCALE) - 22;
		if (x > (Game.WIDTH * Game.SCALE) - 10)
			x = 0;
		if (y <= -10)
			y = (Game.HEIGHT * Game.SCALE) - 22;
		if (y > (Game.HEIGHT * Game.SCALE) - 10)
			y = 0;
		
		for(int i = 0; i < game.frObj.size(); i++){
			Friendly tempBull = game.frObj.get(i);
			
			if(Collision.collides(this, tempBull)){
				velX = (tempBull.getVelX())/4;
				velY = (tempBull.getVelY())/4;
				c.removeEntity(tempBull);
				ast_health -= 10;
			}
		}
		
		if(ast_health <= 0){
			c.removeEntity(this);
			c.addEntity(new Mineral(x, y, SpCol, c, game));
		}

	}
	/**
	 * displays the health and the astroid itself
	 */
	public void render(Graphics g) {
		g.drawImage(SpCol.astroid, (int) x, (int) y, null);


			g.setColor(Color.RED);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			g.fillRect((int) x, (int) y, 30, 1);

			g.setColor(Color.GREEN);
			g.fillRect((int)x, (int)y, ast_health, 2);

			g.setColor(Color.WHITE);
			g.drawRect((int)x, (int)y, ast_health, 2);
		
	}

	/**
	 * @return gets the x value of the astroid
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return gets the y value of the astroid
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return Rectangle gets the dimensions of the rectangle bounding the object
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, WIDTH, HEIGHT);
	}

	/**
	 * 
	 * @return int gets the health of the astroid
	 */
	@Override
	public int getAst_Health() {
		return ast_health;
	}

	/**
	 * @param health sets the health of the astroid
	 */
	@Override
	public void setAst_Health(int health) {
		ast_health = health;

	}

}
