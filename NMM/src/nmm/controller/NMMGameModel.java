package nmm.controller;

import nmm.model.Board;
import nmm.model.user.Player;
import nmm.view.MainWindow;

public class NMMGameModel {
	private Board bd;
	private Player p1;
	private Player p2;
	private Integer turn = 0;
	private MainWindow mw;
	
	public NMMGameModel(MainWindow mw){
		this.mw = mw;
		this.bd = new Board();
		this.p1 = new Player("Steve", "Black");
		this.p2 = new Player("Steve", "Black");
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
}
