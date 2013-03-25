package nmm.model;

import java.awt.Color;

public class Glow {
	private int x;
	private int y;
	private Color c;
	private boolean decreasing;
	
	public Glow(){
		this.x = 48;
		this.y = 48;
		this.c = Color.WHITE;
		this.decreasing = true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColor() {
		return c;
	}

	public void update() {
		checkDirection();
		if(this.decreasing){
			this.x--;
			this.y--;
		}
		else{
			this.x++;
			this.y++;
		}
	}

	private void checkDirection() {
		if(this.x <= 12 || this.y <= 12)
			this.decreasing = false;
		else if(this.x >= 48 || this.y >= 48)
			this.decreasing = true;
	}
}
