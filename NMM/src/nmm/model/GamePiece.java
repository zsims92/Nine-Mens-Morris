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
	public static final int MOVED = 2;
	public static final int DEAD = 3;
	
	public GamePiece(Color color, Player owner)
	{
		this(color, owner, -1);
	}
	
	public GamePiece(Color color, Player owner, int id)
	{
		this.color = color;
		this.owner = owner;
		this.status = UNPLACED;
		this.id = id;
	}
	public boolean IsAlive()
	{
		if (this.status != DEAD)
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
		return this.id - otherPiece.getID();
	}

	public int getID() {
		return id;
	}
	public boolean inPlay()
	{
		if (status == PLACED || status == MOVED)
			return true;
		else
			return false;
	}
}
