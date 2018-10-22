/**
 * This class has all of the graphics that represent the game.
 */
package com.game.src.main;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.game.src.main.classes.Friendly;
import com.game.src.main.classes.Hostile;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 390;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Mineral Collection";

	private boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;

	private int astroid_count = 1;
	private int astroid_shot = 0;
	private int mineral_collect = 0;
	private int collision_destroy = 0;
	public static int score = 0;

	private Player p;
	private Controller c;
	private KeyInput ki;
	private SpriteCollection SpCol;
	private Menu menu;
	private End end;
	private HighScore hs;
	private Help help;

	public LinkedList<Friendly> frObj;
	public LinkedList<Hostile> enObj;
	// private PrintWriter pw;

	public static int HEALTH = HEIGHT;
	public Graphics g;

	private enum STATE {
		MENU, GAME, END, HIGHSCORE, HELP
	};

	private STATE state = STATE.MENU;

	/**
	 * this method initiates almost all of the variables and starts the display
	 */
	public void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			background = loader.loadImage("/game background.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		ki = new KeyInput(this);
		this.addMouseListener(ki);

		SpCol = new SpriteCollection(this);

		c = new Controller(this, SpCol);
		p = new Player((WIDTH * SCALE) / 2, (HEIGHT * SCALE) / 2, SpCol, this, c);

		menu = new Menu();
		end = new End();
		hs = new HighScore();
		help = new Help();

		frObj = c.getFrObj();
		enObj = c.getEnObj();

		c.addAstroid(astroid_count);
	}

	
	/**
	 * this method starts the thread
	 * @author ZACK(RealTutsGML)
	 */
	private synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * this method ends the thread
	 * @author ZACK(RealTutsGML)
	 */
	private synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}


	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}

		}
		stop();
	}

	/**
	 * This method updates the game every tick as dictated by the run method
	 */
	private void tick() {
		if (state == STATE.GAME) {
			p.tick();
			c.tick();
		}

		if((astroid_shot >= astroid_count && mineral_collect >= astroid_count)
				|| ((collision_destroy + mineral_collect) >= astroid_count)) {
			astroid_count += 1;
			astroid_shot = 0;
			mineral_collect = 0;
			collision_destroy = 0;
			c.addAstroid(astroid_count);
		}

		if (HEALTH <= 0){

			state = STATE.END;
		}
		
		if (state == STATE.END) {
			render();
		}
	}

	/**
	 * This method draws the objects and shapes that are displayed in the GUI
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		if (state == STATE.GAME) {
			g.drawImage(background, 0, 0, null);
			p.render(g);
			c.render(g);

			g.setColor(Color.RED);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			g.fillRect((WIDTH * SCALE) - 20, (HEIGHT * SCALE) - HEIGHT - 15, 20, HEIGHT);

			g.setColor(Color.GREEN);
			g.fillRect((WIDTH * SCALE) - 20, (HEIGHT * SCALE) - HEIGHT - 15, 20, HEALTH);

			g.setColor(Color.WHITE);
			g.drawRect((WIDTH * SCALE) - 20, (HEIGHT * SCALE) - HEIGHT - 15, 20, HEALTH);

			Font fnt0 = new Font("Impact", Font.BOLD, 50);
			g.setFont(fnt0);
			g.setColor(Color.WHITE);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
			g.drawString("" + score, (WIDTH * SCALE)/2, 50);
			
			Font fnt1 = new Font("Impact", Font.BOLD, 12);
			g.setFont(fnt1);
			g.drawString("When Stuck in the edges as the spaceship keep clicking on top of it!", (WIDTH * SCALE)/2, HEIGHT * SCALE);

		} else if (state == STATE.MENU) {
			menu.render(g);
		} else if (state == STATE.END) {
			end.render(g);
		} else if (state == STATE.HIGHSCORE) {
			hs.render(g);
		} else if (state == STATE.HELP) {
			help.render(g);
		}

		g.dispose();
		bs.show();
	}

	/**
	 * This method is invoked when the mouse is pressed and takes actions according to where and when it is pressed
	 * @param e MouseEvent sends in a signal when the mouse is pressed
	 */
	public void mousePressed(MouseEvent e) {

		/**
		 * public Rectangle play = new Rectangle(358, 320, 94, 36); 
		 * public Rectangle help = new Rectangle(363, 368, 85, 32); 
		 * public Rectangle highScore = new Rectangle(294, 413, 223, 32); 
		 * public Rectangle quit = new Rectangle(367, 502, 77, 30);
		 */
		int mx = e.getX();
		int my = e.getY();
		// PLAY
		if (state == STATE.MENU) {
			if (mx >= 358 && mx <= 358 + 94 && my >= 320 && my <= 320 + 36) {
				state = STATE.GAME;
			}
		}
		// HIGHSCORE 
		if (state == STATE.MENU) {
			if (mx >= 294 && mx <= 294 + 223 && my >= 413 && my <= 413 + 32) {
				state = STATE.HIGHSCORE;
			}
		}
		// QUIT
		if (state == STATE.MENU) {
			if (mx >= 367 && mx <= 367 + 77 && my >= 502 && my <= 502 + 30) {
				System.exit(1);
			}
		}
		// HELP
		if (state == STATE.MENU) {
			if (mx >= 363 && mx <= 363 + 85 && my >= 368 && my <= 368 + 32) {
				state = STATE.HELP;
			}
		}

		if (state == STATE.GAME) {
			double dx = mx - p.getX();
			double dy = my - p.getY();
			ki.setX(mx);
			ki.setY(my);
			Bullet bull = new Bullet(p.getX() + 16, p.getY() + 16, SpCol, this);
			c.addEntity(bull);

			if (dx > 0 && dy > 0) {
				p.setVelX(-dx / 70);
				p.setVelY(-dy / 70);
				bull.setVelX((int) (dx / 50));
				bull.setVelY((int) (dy / 50));
			} else if (dx > 0 && dy < 0) {
				p.setVelX(-dx / 70);
				p.setVelY(-dy / 70);
				bull.setVelX((int) (dx / 50));
				bull.setVelY((int) (dy / 50));
			} else if (dx < 0 && dy > 0) {
				p.setVelX(-dx / 70);
				p.setVelY(-dy / 70);
				bull.setVelX((int) (dx / 50));
				bull.setVelY((int) (dy / 50));
			} else if (dx < 0 && dy < 0) {
				p.setVelX(-dx / 70);
				p.setVelY(-dy / 70);
				bull.setVelX((int) (dx / 50));
				bull.setVelY((int) (dy / 50));
			}
		}
		// END
		if (state == STATE.END) {
			if (mx >= 0 && mx <= (WIDTH * SCALE) && my >= 0 && my <= (HEIGHT * SCALE)) {
				System.exit(1);
			}
		}
		// Rectangle returnButton = new Rectangle(696, 43, 49, 22);
		if (state == STATE.HIGHSCORE) {
			if (mx >= 696 && mx <= 696 + 49 && my >= 43 && my <= 43 + 22) {
				state = STATE.MENU;
			}
		}
		// Rectangle play = new Rectangle(367, 502, 77, 30);
		//public Rectangle play = new Rectangle(697, 43, 48, 22);
		if (state == STATE.HELP) {
			if (mx >= 697 && mx <= 697 + 48 && my >= 43 && my <= 43 + 22) {
				state = STATE.MENU;
			}
		}
	}


	/**
	 * invoked when the mouse is released
	 * @param e sends in where the mouse is released
	 */
	public void mouseReleased(MouseEvent e) {
	
	}

	/**
	 * the main method creates the JFrame on which the game is being displayed
	 * @param args
	 */
	public static void main(String args[]) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();

	}
	
	/**
	 * 
	 * @return returns the spritesheet
	 */
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	
	/**
	 * 
	 * @return returns the KeyInput object
	 */
	public KeyInput getKI() {
		return ki;
	}

	/**
	 * 
	 * @return returns the number of astroids created in the playing field
	 */
	public int getAstroid_count() {
		return astroid_count;
	}

	/**
	 * 
	 * @param astroid_count number of astroids to create
	 */
	public void setAstroid_count(int astroid_count) {
		this.astroid_count = astroid_count;
	}

	/**
	 * 
	 * @return returns the number of astroids shot
	 */
	public int getAstroid_shot() {
		return astroid_shot;
	}

	/**
	 * 
	 * @param astroid_shot number of astroids shot
	 */
	public void setAstroid_shot(int astroid_shot) {
		this.astroid_shot = astroid_shot;
	}

	/**
	 * 
	 * @return number of minerals collected
	 */
	public int getMineral_collect() {
		return mineral_collect;
	}

	/**
	 * 
	 * @param mineral_collect number of minerals collected
	 */
	public void setMineral_collect(int mineral_collect) {
		this.mineral_collect = mineral_collect;
	}

	/**
	 * 
	 * @return the player object from the Game class
	 */
	public Player getPlayer() {
		return p;
	}

	/**
	 * 
	 * @return the controller object that stores the Friendly, Hostile, Neutral objects
	 */
	public Controller getControl() {
		return c;
	}

	/**
	 * 
	 * @return number of asteroids destroyed by collision
	 */
	public int getCollision_destroy() {
		return collision_destroy;
	}

	/**
	 * 
	 * @param collision_destroy number of asteroids destroyed by collision
	 */
	public void setCollision_destroy(int collision_destroy) {
		this.collision_destroy = collision_destroy;
	}

	/**
	 * 
	 * @return the score when the minerals created
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 
	 * @param score score when the minerals created
	 */
	public void setScore(int score) {
		this.score = score;
	}

	

}
