package nmm.model;

import java.awt.Color;

import nmm.model.user.Player;

public class GamePiece implements Comparable<GamePiece>
{
	private Color color;
	private Player owner;
	private Integer status;
	private int id;
	private boolean selected;
	
	public static final int UNPLACED = 0;
	public static final int PLACED = 1;
	public static final int MOVED = 2;
	public static final int DEAD = 3;
	
	private int glow = 0;
	
	/***
	 * Creates a game piece given the color
	 * and owener
	 * @param color
	 * @param owner
	 */
	public GamePiece(Color color, Player owner){
		this(color, owner, -1);
	}
	
	/***
	 * Creates a game piece
	 * given the parameters
	 * and sets statys ti 0
	 * @param color
	 * @param owner
	 * @param id
	 */
	public GamePiece(Color color, Player owner, int id)
	{
		this.color = color;
		this.owner = owner;
		this.status = UNPLACED;
		this.id = id;
	}
	
	public int getGlow(){
		return this.glow;
	}
	
	public void updateGlow(){
		this.glow++;
		this.glow %= 4;
	}
	
	/***
	 * returns the boolean
	 * of whether the piece is selected
	 * or not
	 * @return
	 */
	public boolean getSelected(){
		return this.selected;
	}
	
	/***
	 * Sets the piece to be selected or not
	 * @param select
	 */
	public void select(boolean select){
		if(!select){
			this.glow = 0;
		}
		
		this.selected = select;
	}
	
	/**
	 * Determines if the piece is alive
	 * @return
	 */
	public boolean IsAlive()
	{
		if (this.status != DEAD)
			return true;
		else
			return false;
	}
	
	/***
	 * Returns the status of the piece
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}
	
	/***
	 * Sets the status of the piece to
	 * that of status
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/***
	 * Returns the color
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	
	/***
	 * returns the owner
	 * @return
	 */
	public Player getOwner() {
		return owner;
	}

	/***
	 * Compares a piece to another
	 */
	@Override
	public int compareTo(GamePiece otherPiece) 
	{
		return this.id - otherPiece.getID();
	}

	/***
	 * Returns the id of the piece
	 * @return
	 */
	public int getID() {
		return id;
	}
<<<<<<< HEAD
	
=======
>>>>>>> c379f9df8ec1ff154b8f75ef10727c60a963a919
	public boolean inPlay()
	{
		if (status == PLACED || status == MOVED)
			return true;
		else
			return false;
	}
}
