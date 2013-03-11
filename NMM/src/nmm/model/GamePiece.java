package nmm.model;

import java.awt.Color;

import nmm.model.user.Player;

public class GamePiece implements Comparable<GamePiece>
{
	private Color color;
	private Player owner;
	private Integer status;
	private int id;
	
	public static final int UNPLACED = 0;
	public static final int PLACED = 1;
	public static final int DEAD = 2;
	
	public GamePiece(Color color, Player owner)
	{
		this(color, owner, -1);
	}
	
	public GamePiece(Color color, Player owner, int id)
	{
		this.color = color;
		this.owner = owner;
		this.status = 0;
		this.id = id;
	}
	public boolean IsAlive()
	{
		if (this.status == UNPLACED || this.status == PLACED)
			return true;
		else
			return false;
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

	@Override
	public int compareTo(GamePiece otherPiece) 
	{
		if (this == otherPiece)
			return 0;
		else
			return -1;
	}

	public int getID() {
		return id;
	}
}
