package nmm.model.user;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;

import nmm.model.GamePiece;

public class Player {
	private String name;
	private Color color;
	private ArrayList<GamePiece> pieces;
	//private Integer score;
	final Integer MAXPIECES = 9;
	
	public Player(String name, String color){
		this.name = name;
		this.setColor(color);
		this.initPieces();
		//this.setScore(MAXPIECES);
	}
	
	private void initPieces() {
		this.pieces = new ArrayList<GamePiece>(MAXPIECES);
		for(Integer i=0; i<MAXPIECES; i++){			
			GamePiece p = new GamePiece(this.color, this, i);
			this.pieces.add(p);
		}
	}
	
	public GamePiece getPiece(int id)
	{
		for(int i=0; i < pieces.size(); i++)
			if (pieces.get(i).getID() == id)
				return pieces.get(i);
		
		return null;
	}
	/* not neccessary
	public void addScore(){
		this.score++;
	}
	public void subScore(){
		this.score--;
	}
	
	public void setScore(Integer score){
		this.score = score;
	}
	*/
	private void setColor(String color){
		Color c = null;	
		try {
		    Field field = Color.class.getField(color.toLowerCase());
		    c = (Color)field.get(null);
		} catch (Exception e) {
		    c = Color.BLACK; // Not defined
		}
		
		this.color = c;
	}
	public String getName() {
		return this.name;
	}
	public Color getColor() {
		return this.color;
	}
	public ArrayList<GamePiece> getPieces() {
		return this.pieces;
	}
	public Integer getScore() {
		int curscore = 0;
		
		for(int i=0; i < pieces.size(); i++)
			if (pieces.get(i).getStatus() != GamePiece.DEAD)
				curscore++;
				
		return curscore;
	}
	
	public int getPiecesPlayed() {
		int played = 0;
		for (int i=0; i < pieces.size(); i++)
		{
			if (pieces.get(i).getStatus() != GamePiece.UNPLACED)
				played++;
		}
		
		return played;
	}

	public boolean isHuman() {
		return true;
	}
}
