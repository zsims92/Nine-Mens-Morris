package nmm.controller;

import nmm.model.Board;
import nmm.model.user.Player;
import nmm.view.MainWindow;

public class NMMGameModel {
	private Board bd;
	private Player p1;
	private Player p2;
	private Integer turn = 0;
	private Integer gameMode = -1;
	private MainWindow mw;
	
	public NMMGameModel(MainWindow mw, Integer mode){
		this.mw = mw;
		this.gameMode = mode;
		this.bd = new Board();
		this.p1 = new Player("Steve", "Black");
		this.p2 = new Player("Steve", "Black");
	}
	
	
	public void setGameMode(Integer mode){
		this.gameMode = mode;
	}
	
	public Player getPlayer1() {
		return p1;
	}
	
	public Player getPlayer2() {
		return p2;
	}
	public Board getBoard() {
		return bd;
	}

	public void newMove(int row, int col) {
		// TODO Auto-generated method stub
		
	}


	public void setPlayer2(Player p) {
		this.p1 = p;
	}
	public void setPlayer1(Player p) {
		this.p2 = p;
	}
}
