package nmm.model;

import nmm.model.user.*;

public class NMMGameModel {
	private int[][] boardArray = new int[7][7];

	public Player getPlayerTurn() {
		this.boardArray = newBoard();
		return new Player();
	}

	public int[][] newBoard() {
		int[][] temp = new int[7][7];
        for (int r=0; r<7; r++) {
            for (int c=0; c<7; c++) {
            	temp[r][c] = 0;
            }
        }
		return temp;
	}
	
	public int[][] getBoardArray() {
		return this.boardArray;
	}

	public Player getPlayer1() {
		// TODO Auto-generated method stub
		return null;
	}

	public Player getPlayer2() {
		// TODO Auto-generated method stub
		return null;
	}

	public void changeColor(int row, int col) {
		this.boardArray[row][col] = 1;	
	}

}
