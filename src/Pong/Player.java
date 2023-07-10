package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	public boolean up, down;
	public int spd = 2;
	
	public Player(int x, int y) {
		super((Game.WIDTH - x), (Game.HEIGHT / 2)  - (y / 2), x, y);
	}
	
	public void tick() {
		if (up) {
			y -= spd;
		} else if (down) {
			y += spd;
		}
		
		if ((y + height) > Game.HEIGHT) {
			y = Game.HEIGHT - height;
		}else if (y < 0) {
			y = 0;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

}
