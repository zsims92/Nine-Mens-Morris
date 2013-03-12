package nmm.controller;

import nmm.model.Board;
import nmm.model.GamePiece;
import nmm.model.Location;
import nmm.model.user.Player;
import nmm.view.MainWindow;

public class NMMGameModel {
	private Board oldBoard;
	private Board currBoard;
	private Player p1;
	private Player p2;
	private Player curPlayer;
	private Player Victor;
	private Player Loser;
	private Integer gameMode = -1;
	private GamePiece pieceSelected;
	
	public NMMGameModel(Integer mode, Player p1, Player p2, MainWindow mw){
		this.gameMode = mode;
		this.oldBoard = new Board(mw);
		this.currBoard = new Board(oldBoard);
		this.p1 = p1;
		this.p2 = p2;
		double t;
		t = Math.random() * 50;
		if(t <= 25.000)
			this.curPlayer = this.p1;
		else
			this.curPlayer = this.p2;
		this.Victor = p1;
		this.Loser = p2;
	}
	
	
	public int getStatus(){
		return this.currBoard.GetCurrentPhase(this.curPlayer);
	}
	
	public void undoMove(){
		this.currBoard = new Board(this.oldBoard);
	}
	
	public boolean newMove(String label) {
		int gamephase = this.currBoard.GetCurrentPhase(this.curPlayer);
		
		if(gamephase == Board.GAMEOVER_PHASE){
			return true;
		}
		switch(gamephase)
		{
		case Board.PLACEMENT_PHASE: //placement
			// If placement is successful, move on to next player.
			if (PlacementPhase(label))
				nextPlayer();
			else
				return false;
			break;
			
		case Board.MOVEMENT_PHASE: //movement
			// Next player on successful move.
			// Will return false if a move results in a mill, so that the player isn't skipped.
			if(this.pieceSelected == null)
				selectPiece(label);
			else if(this.pieceSelected != null){
				if(this.pieceSelected == this.currBoard.GetLocationByLabel(label).getPiece()){
					this.pieceSelected.select(false);
					this.pieceSelected = null;
				}
				else if(MovementPhase(label)){
					nextPlayer();
					this.pieceSelected.select(false);
					this.pieceSelected = null;
				}else{
					if(this.currBoard.GetCurrentPhase(this.curPlayer) == Board.REMOVAL_PHASE){
						this.pieceSelected.select(false);
						this.pieceSelected = null;
					}
				}
			}
			else
				return false;
			break;
			
		case Board.REMOVAL_PHASE: //removal
			boolean passed = RemovalPhase(label);
			if(passed)
				nextPlayer();
			else
				if(this.currBoard.GetCurrentPhase(this.inactivePlayer()) == Board.GAMEOVER_PHASE){
					this.Victor = this.curPlayer;
					this.Loser = this.inactivePlayer();
					return true;
				}
				else
					return false;
			break;
			
		default:
			System.out.println("Invalid game phase. Exiting.");
			System.exit(1);
			break;
		}
		
		this.currBoard.updateBoard();
		return true;
	}
	

	
 	private boolean selectPiece(String label) {
		Location t = this.currBoard.GetLocationByLabel(label);
		if(t.getPiece() == null || t.getPiece().getOwner() != this.curPlayer)
			return false;
		this.pieceSelected = t.getPiece();
		this.pieceSelected.select(true);
		return true;
	}

	private boolean RemovalPhase(String label) {
		Location t = this.currBoard.GetLocationByLabel(label);
		return this.currBoard.RemovePiece(this.inactivePlayer(), t.getPiece().getID());
	}
	
	private boolean PlacementPhase(String label) {
		Location t = this.currBoard.GetLocationByLabel(label);
		return this.currBoard.PlacePiece(this.curPlayer, 8-this.curPlayer.getPiecesPlayed(), t.getLabel());
	}
	
    private boolean MovementPhase(String label){

		if (this.currBoard.numMovesAvailable(this.curPlayer) == 0 && this.curPlayer.getScore() > 3){
			return true;
		}
		Location t = this.currBoard.GetLocationByLabel(label);		
		return this.currBoard.MovePiece(this.curPlayer, pieceSelected.getID(), t.getLabel());
	}
	
	//Functions below here need comments added, and are complete otherwise
	public Player getVictor() {
		return this.Victor;
	}
	public Player getLoser() {
		return this.Loser;
	}
	private void nextPlayer(){
		this.curPlayer = inactivePlayer();
	}	
	private Player inactivePlayer(){
		if (this.curPlayer  == this.p1)
			return this.p2;
		else
			return this.p1;
	}
	public Player getCurrPlayer() {
		return this.curPlayer;
	}
	public void setGamePhase(Integer mode){
		this.currBoard.SetCurrentPhase(mode);
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
		return this.currBoard;
	}
	public void setPlayer1(Player p) {
		this.p1 = p;
	}
	public void setPlayer2(Player p) {
		this.p2 = p;
	}
	public String getPhaseText() {
		if(this.currBoard.GetCurrentPhase(this.getCurrPlayer()) == 0)
			return "place a piece on the board";
		else if(this.currBoard.GetCurrentPhase(this.getCurrPlayer()) == 1)
			return "move on of your pieces on the board";
		else if(this.currBoard.GetCurrentPhase(this.getCurrPlayer()) == 2)
			return "remove one of your opponents pieces";
		return "";
	}
}
