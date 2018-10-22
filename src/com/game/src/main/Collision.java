/**
 * The Collision class checks for collisons of objects in the field 
 */
 
package com.game.src.main;

import com.game.src.main.classes.Friendly;
import com.game.src.main.classes.Hostile;

public class Collision {

	/** checks if there is a collision between the friendly and enemy objects
	 *	@param frObj includes the spaceship and projectiles, the friendly object that is colliding
	 *	@param enObj includes the asteroids, that are hostile and destroy health 
	 *  @return if there is a collision or not
	 */
	public static boolean collides(Friendly frObj, Hostile enObj) {

		if (frObj.getBounds().intersects(enObj.getBounds())) {
			return true;
		}

		return false;
	}
	
	/** checks if there is a collision between the enemy and friendly objects
	 *  @param enObj includes the asteroids, that are hostile and destroy health
	 *	@param frObj includes the spaceship and projectiles, the friendly object that is colliding 
	 *  @return if there is a collision or not
	 */
	public static boolean collides(Hostile enObj, Friendly frObj) {

		if (enObj.getBounds().intersects(frObj.getBounds())) {
			return true;
		}

		return false;
	}

}
