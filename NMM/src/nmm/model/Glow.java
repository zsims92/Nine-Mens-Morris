package nmm.model;

import java.awt.Color;

public class Glow {
	private double x;
	private double y;
	private Color c;
	private boolean decreasing;
	
	public Glow(){
		this.x = 48;
		this.y = 48;
		this.c = Color.WHITE;
		this.decreasing = true;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Color getColor() {
		return c;
	}

	public void update() {
		checkDirection();
		if(this.decreasing){
			this.x -= .2;
			this.y -= .2;
		}
		else{
			this.x += .2;
			this.y += .2;
		}
	}

	private void checkDirection() {
		if(this.x <= 12 || this.y <= 12)
			this.decreasing = false;
		else if(this.x >= 48 || this.y >= 48)
			this.decreasing = true;
	}
}
