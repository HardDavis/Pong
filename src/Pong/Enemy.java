package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends Rectangle{

	private static final long serialVersionUID = 1L;
	public boolean touchTop = true, touchDown;
	public int spd = 2;
	
	public Enemy(int x, int y) {
		super((Game.WIDTH - Game.WIDTH), (Game.HEIGHT / 2) - (y / 2), x, y);
	}
	
	public void tick() {
		if (y > Game.ball.y) {
			y -= spd;
		} else if (y < Game.ball.y) {
			y += spd;
		}
		
		if ((y + height) > Game.HEIGHT) {
			y = Game.HEIGHT - height;
			touchDown = true;
			touchTop = false;
		}else if (y < 0) {
			y = 0;
			touchDown = false;
			touchTop = true;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}
}
