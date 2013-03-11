package nmm.controller;

import nmm.model.Board;
import nmm.model.user.AIPlayer;
import nmm.model.user.Player;
import nmm.view.MainWindow;

public class NMMGameModel {
	private Board bd;
	private Player p1;
	private Player p2;
	private Integer turn = 0;
	private Integer gameMode = -1;
	private Integer status = 0;
	
	public NMMGameModel(Integer mode){
		this.gameMode = mode;
		this.bd = new Board();
		this.p1 = new Player("Steve", "Black");
		this.p2 = new AIPlayer("Steve", "Black");
	}
	
	public void newMove(int row, int col) {
		this.status = 1;
	}
	public void setGameMode(Integer mode){
		this.gameMode = mode;
	}
	public Integer getMode(){
		return this.gameMode;
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
	public void setPlayer1(Player p) {
		this.p1 = p;
	}
	public void setPlayer2(Player p) {
		this.p2 = p;
	}
	public Player getVictor() {
		return this.p1;
	}
	public Integer getStatus() {
		return this.status;
	}
	public Player getLoser() {
		return this.p2;
	}
	public void setTurn(Integer turn) {
		this.turn = turn;
	}
	public Integer getTurn() {
		return turn;
	}
}
