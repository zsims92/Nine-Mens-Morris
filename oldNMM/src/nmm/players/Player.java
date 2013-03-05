package nmm.players;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.*;

import nmm.virtual.parts.Piece;

public class Player {
	private String name;
	private Color color;
	private ArrayList<Piece> pieces;
	private Integer score;
	final Integer MAXPIECES = 9;
	
	public Player(String name, String color){
		this.name = name;
		this.setColor(color);
		this.initPieces();
		this.setScore(MAXPIECES);
	}
	
	private void initPieces() {
		this.pieces = new ArrayList<Piece>(MAXPIECES);
		for(Integer i=0; i<MAXPIECES; i++){			
			Piece p = new Piece(this.color, this);
			this.pieces.add(p);
		}
	}
	
	public void addScore(){
		this.score++;
	}
	public void subScore(){
		this.score--;
	}
	public void setScore(Integer score){
		this.score = score;
	}
	private void setColor(String color){
		Color c = null;
		color.toUpperCase();
		Field field = null;
		
		try {
			field = Class.forName("java.awt.color").getField(color);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			c = (Color)field.get(null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		this.color = c;
	}
	public String getName() {
		return name;
	}
	public Color getColor() {
		return color;
	}
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	public Integer getScore() {
		return score;
	}	
}
