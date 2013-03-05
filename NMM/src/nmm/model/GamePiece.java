package nmm.model;

import java.awt.Color;

import nmm.model.user.Player;

public class GamePiece {
	private Color color;
	private Player owner;
	private Integer status;
	
	public GamePiece(Color color, Player owner){
		this.color = color;
		this.owner = owner;
		this.status = 0;
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
}
