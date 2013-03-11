package nmm.virtual.parts;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import nmm.players.Player;

public class Piece {
	private Color color;
	private Player owner;
	private Integer status;
	private Shape circle;
	final Double size = 100.0;
	
	public Piece(Color color, Player owner){
		this.color = color;
		this.owner = owner;
		this.status = 0;
		this.setCircle();
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Color getColor() {
		return color;
	}
	public Player getOwner() {
		return owner;
	}


	private void setCircle() {
		Shape c = new Ellipse2D.Double(0.0 , 0.0, size, size);
		this.circle = c;
	}


	public Shape getCircle() {
		return circle;
	}
}
