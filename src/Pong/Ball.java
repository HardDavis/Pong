package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends Rectangle{
	
	public boolean touchTop = true, touchDown;
	public boolean touchRight = true, touchLeft;
	public double spd = 2.1;
	
	public Ball() {
		super((Game.WIDTH / 2) - 2, (Game.HEIGHT / 2) - 2, 5, 5);
	}
	
	public void tick() {
		
		if (touchTop) {
			y += spd;
		} else if (touchDown) {
			y -= spd;
		}
		
		if (touchRight) {
			x -= spd;
		} else if (touchLeft) {
			x += spd;
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
		
		if ((x + width) > Game.WIDTH) {
			Game.ptsBot += 1;
			x = Game.WIDTH - width;
			touchRight = true;
			touchLeft = false;
		}else if (x < 0) {
			Game.ptsPlr += 1;
			x = 0;
			touchRight = false;
			touchLeft = true;
		}
		
		if (this.intersects(Game.player)) {
			x = (Game.WIDTH - width) - 10;
			touchRight = true;
			touchLeft = false;
		} 
		
		if (Game.multiplayer) {
			if (this.intersects(Game.player2) && Game.multiplayer) {
				x = 10;
				touchRight = false;
				touchLeft = true;
			}
		} else if (this.intersects(Game.enemy)) {
			x = 10;
			touchRight = false;
			touchLeft = true;
		}  
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}
	
}
