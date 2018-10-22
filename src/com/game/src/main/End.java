/**
 * This class is dictates what happenes when the game ends and the end game screen shows up
 */
package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class End {

	private PrintWriter pw;
	private FileWriter fw;
	
	private BufferedImage end;
	/**
	 * this method draws the end screen and displays the image
	 * @param g this is the graphics parameter that we use to draw the images
	 */
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		try {
			
			fw = new FileWriter("scores.txt",true);
			pw = new PrintWriter(fw);
			pw.println(Game.score);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			end = loader.loadImage("/game over screen.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(end, 0, 0, null);
		
		Font fnt1 = new Font("Impact", Font.BOLD, 30);
		g.setFont(fnt1);
		g.setColor(Color.WHITE);
		g.drawString("Your Score: " + Game.score, Game.WIDTH * Game.SCALE/Game.SCALE - 80, Game.HEIGHT * Game.SCALE - 150);
		
	}
}
