/**
 * The Help class allows for the highscore menu to work
 */
package com.game.src.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HighScore {

	private Scanner fileReader;
	private File file;

	public Rectangle returnButton = new Rectangle(696, 43, 49, 22);
	
	private BufferedImage highScores;
	/**
	 * this method draws the highscore screen and displays the image
	 * @param g this is the graphics parameter that we use to draw the images
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int highScore = 0;
		file = new File("scores.txt");
		try {
			fileReader = new Scanner(file);
			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				int score = Integer.parseInt(line);
				if (score > highScore) {
					highScore = score;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			highScores = loader.loadImage("/highscores no press.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(highScores, 0, 0, null);
		
		Font fnt0 = new Font("Times New Roman", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
		g.drawString("" + highScore, Game.WIDTH, Game.HEIGHT);
		
		
		g2d.draw(returnButton);

	}

}
