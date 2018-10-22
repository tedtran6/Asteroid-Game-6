/**
 * This class contains all of the objects in a linked list to better the efficiancy
 */
package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.game.src.main.classes.Friendly;
import com.game.src.main.classes.Hostile;
import com.game.src.main.classes.Neutral;

public class Controller {

	private LinkedList<Friendly> frObj = new LinkedList<Friendly>();
	private LinkedList<Hostile> enObj = new LinkedList<Hostile>();
	private LinkedList<Neutral> nObj = new LinkedList<Neutral>();

	Friendly objF;
	Hostile objE;
	Neutral objN;
	
	Random gen = new Random();
	

	int velx;
	int vely;
	
	private Game game;
	SpriteCollection SpCol;
	/**
	 * 
	 * @param game the Game class obj to get the instance variables
	 * @param SpCol the spriteCollection that has the images
	 */
	public Controller(Game game, SpriteCollection SpCol){
		this.game = game;
		this.SpCol = SpCol;
		
	}
	/**
	 * adds an astroid either small or big one to the Hostile LinkedList
	 * @param astroid_count the number of astroids to spawn
	 */
	public void addAstroid(int astroid_count){
		for(int i = 0; i < astroid_count; i++){
			velx = (int)(Math.random() * 2) + 1;
			vely = (int)(Math.random() * 2) + 1;
			boolean xPos = gen.nextBoolean();
			boolean yPos = gen.nextBoolean();
			if(!xPos && yPos){
				velx *= -1;
			}
			else if(xPos && !yPos){
				vely *= -1;
			}
			else if(!xPos && !yPos){
				velx *= -1;
				vely *= -1;
			}
			int rand = gen.nextInt(2);
			if(rand == 0)
				addEntity(new Astroid(gen.nextInt(Game.WIDTH * Game.SCALE), gen.nextInt(Game.HEIGHT * Game.SCALE), velx, vely, SpCol, this, game));
			if(rand == 1)
				addEntity(new MiniAstroid(gen.nextInt(Game.WIDTH * Game.SCALE), gen.nextInt(Game.HEIGHT * Game.SCALE), velx, vely, SpCol, this, game));
		}
	}
	/**
	 * adds the mineral to the LinkedList of Neutral
	 * @param x the x coord of mineral
	 * @param y the y coord of the mineral
	 */
	public void addMineral(double x, double y){
		addEntity(new Mineral(x, y, SpCol, this, game));
	}
	/**
	 * This method updates the Controller every tick
	 */
	public void tick(){
		//Friendly
		for(int i = 0; i < frObj.size(); i++){
			objF = frObj.get(i);
			
			objF.tick();
		}
		//Hostile
		for(int i = 0; i < enObj.size(); i++){
			objE = enObj.get(i);
			
			objE.tick();
		}
		//Neutral
		for(int i = 0; i < nObj.size(); i++){
			objN = nObj.get(i);
			
			objN.tick();
		}
		
		if(game.getAstroid_shot() >= game.getAstroid_count() && game.getMineral_collect() >= game.getAstroid_count()){
			removeEntity(objF);
		}
	}
	/**
	 * this method draws the objects of friendly, hostile and neutral
	 * @param g graphics param to draw the objects
	 */
	public void render(Graphics g){
		//Friendly
		for(int i = 0; i < frObj.size(); i++){
			objF = frObj.get(i);
			
			objF.render(g);
		}
		
		//Hostile
		for(int i = 0; i < enObj.size(); i++){
			objE = enObj.get(i);
			
			objE.render(g);
		}
		
		//Neutral
		for(int i = 0; i < nObj.size(); i++){
			objN = nObj.get(i);
			
			objN.render(g);
		}
	}
	/**
	 * adds a friendly obj to the friendly linked list
	 * @param obj the friendly obj to add
	 */
	public void addEntity(Friendly obj){
		frObj.add(obj);
	}
	/**
	 * removes a friendly obj to the friendly linked list
	 * @param obj the friendly obj to remove
	 */
	public void removeEntity(Friendly obj){
		frObj.remove(obj);
	}
	/**
	 * adds a hostile obj to the hostile linked list
	 * @param obj the hostile obj to add
	 */
	public void addEntity(Hostile obj){
		enObj.add(obj);
	}
	/**
	 * removes a hostile obj to the hostile linked list
	 * @param obj the hostile obj to remove
	 */
	public void removeEntity(Hostile obj){
		enObj.remove(obj);
	}
	/**
	 * adds a neutral obj to the neutral linked list
	 * @param obj the neutral obj to add
	 */
	public void addEntity(Neutral obj){
		nObj.add(obj);
	}
	/**
	 * remove a neutral obj to the neutral linked list
	 * @param obj the neutral obj to remove
	 */
	public void removeEntity(Neutral obj){
		nObj.remove(obj);
	}

	/**
	 * @return returns the friendly obj linkedlist
	 */
	public LinkedList<Friendly> getFrObj() {
		return frObj;
	}

	/**
	 * @return returns the hostile obj linkedlist
	 */
	public LinkedList<Hostile> getEnObj() {
		return enObj;
	}
	/**
	 * @return returns the neutral obj linkedlist
	 */
	public LinkedList<Neutral> getNObj() {
		return nObj;
	}
}
