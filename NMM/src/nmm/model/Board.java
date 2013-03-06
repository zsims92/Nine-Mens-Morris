package nmm.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

	private int[][] boardArray;
	
	public Board(){
		this.boardArray = newBoard();
		
	}

	private int[][] newBoard() {
		int[][] bd = new int[7][7];
		for(int i=0; i<7; i++)
			for(int j=0; j<7; j++){
				bd[i][j] = 1;
			}
		ArrayList<ArrayList<Integer>> placeableSpots = validSpots();
		int k = 0;
		for(ArrayList<Integer> i: placeableSpots){
			for(Integer j: i){
				bd[k][j] = -1;
			}
			k++;
		}
		
		return bd;
	}
	private ArrayList<ArrayList<Integer>> validSpots() {
		ArrayList<ArrayList<Integer>> p = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row0 = new ArrayList<Integer>(Arrays.asList(1,2,4,5));
		ArrayList<Integer> row1 = new ArrayList<Integer>(Arrays.asList(0,2,4,6));
		ArrayList<Integer> row2 = new ArrayList<Integer>(Arrays.asList(0,1,5,6));
		ArrayList<Integer> row3 = new ArrayList<Integer>(Arrays.asList(3));
		ArrayList<Integer> row4 = new ArrayList<Integer>(Arrays.asList(0,1,5,6));
		ArrayList<Integer> row5 = new ArrayList<Integer>(Arrays.asList(0,2,4,6));
		ArrayList<Integer> row6 = new ArrayList<Integer>(Arrays.asList(1,2,4,5));
		
		p.add(row0);
		p.add(row1);
		p.add(row2);
		p.add(row3);
		p.add(row4);
		p.add(row5);
		p.add(row6);
		return p;
	}

	public int[][] getBoardArray() {
		return this.boardArray;
	}
	
}
