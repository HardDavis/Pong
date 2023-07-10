package Pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 220, HEIGHT = 160;
	public static int SCALE = 3;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public static boolean multiplayer = false;
	
	public static Player player;
	public static Player2 player2;
	public static Ball ball;
	public static Enemy enemy;

	public static int ptsPlr, ptsBot;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.addKeyListener(this);
		player = new Player(10, 40);
		if (multiplayer) {
			player2 = new Player2(10, 40);			
		} else {
			enemy = new Enemy(10, 40);
		}
		ball = new Ball();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		initFrame(game);
		
		new Thread(game).start();
	}

	public void tick() {
		player.tick();
		ball.tick();
		
		if (multiplayer) {		
			player2.tick();
		} else {
			enemy.tick();
		}
		
		
		
		System.out.println("Pts Player: " + ptsPlr + "\nPts Bot: " + ptsBot);

	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = layer.getGraphics();
		g.setColor(new Color(17,17,17));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setFont(new Font("Arial", 0, 20));
		g.setColor(Color.WHITE);
		g.drawString("" + ptsBot, 20, 25);
		
		g.setFont(new Font("Arial", 0, 20));
		g.setColor(Color.WHITE);
		g.drawString("" + ptsPlr, WIDTH - 30, 25);
		
		player.render(g);
		ball.render(g);
		
		if (multiplayer) {		
			player2.render(g);
		} else {
			enemy.render(g);
		}
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		bs.show();
	}
	
	@Override
	public void run() {
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void initFrame(Game game) {
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player2.up = true;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			player2.down = true;
		}
	}

	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player2.up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			player2.down = false;
		}
	}
}
