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
	private Glow gl;
	private boolean moving;
	private Movement mv;
	
	public static final int UNPLACED = 0;
	public static final int PLACED = 1;
	public static final int MOVED = 2;
	public static final int DEAD = 3;
	
	/***
	 * Creates a game piece given the color
	 * and owner
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
		this.status = 0;
		this.id = id;
		this.gl = new Glow();
	}
	
	public GamePiece(GamePiece gamePiece) {
		this.color = gamePiece.color;
		this.owner = gamePiece.owner;
		this.status = gamePiece.status;
		this.id = gamePiece.id;
		this.gl = gamePiece.gl;		
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
		this.selected = select;
		if(!select){
			this.gl = new Glow();
		}
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
	
	public boolean inPlay()
	{
		if (status == PLACED || status == MOVED)
			return true;
		else
			return false;
	}

	public void setGl(Glow gl) {
		this.gl = gl;
	}

	public Glow getGl() {
		return gl;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Movement getMv() {
		return mv;
	}

	public void setMv(Movement mv) {
		if(mv == null)
			this.mv = null;
		else
			this.mv = mv;
	}
}
