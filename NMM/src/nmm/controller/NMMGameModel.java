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
	
	/**
	* Constructor for the GameModel
	* Takes in a mode, two players, and
	* a mainWindow
	*
	* The players are created by the gui
	* the mode will be to determine if AI
	* is present
	*
	* Mw is brought simply to pass to Board
	* @param mode
	* @param p1
	* @param p2
	* @param mw
	*/
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
	
	/***
	* Will return the status of the board
	*
	* Used by the gui
	* @return
	*/
	public int getStatus(){
		return this.currBoard.GetCurrentPhase(this.curPlayer);
	}
	
	/***
	* This function will restore
	* the board to its previous state
	*
	* Currently not working
	*/
	public void undoMove(){
	//TODO: implement this
		this.currBoard = new Board(this.oldBoard);
	}
	
	/***
	* The main function of the GameModel
	*
	* This will check what phase the game is in
	* and determine the correct action from there on out
	*
	* Will return false if an errors occur
	* Will update the status of the board
	* @param label
	* @return
	*/
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
	
	
	/***
	* Will select the current piece and update its
	* status
	*
	* Used for the GUI to select a piece
	* and then move that piece
	* @param label
	* @return
	*/
	private boolean selectPiece(String label) {
		Location t = this.currBoard.GetLocationByLabel(label);
		if(t.getPiece() == null || t.getPiece().getOwner() != this.curPlayer)
			return false;
		this.pieceSelected = t.getPiece();
		this.pieceSelected.select(true);
		return true;
	}
	
	  /***
	* Will determine the location and piece to
	* remove given the label
	*
	* Will then attempt to remove that piece
	* @param label
	* @return
	*/
	private boolean RemovalPhase(String label) {
		Location t = this.currBoard.GetLocationByLabel(label);
		return this.currBoard.RemovePiece(this.inactivePlayer(), t.getPiece().getID());
	}
	
	/***
	* Will determine the location and piece to
	* place given the label
	*
	* Will then attempt to place that piece
	* @param label
	* @return
	*/
	private boolean PlacementPhase(String label) {
		Location t = this.currBoard.GetLocationByLabel(label);
		return this.currBoard.PlacePiece(this.curPlayer, 8-this.curPlayer.getPiecesPlayed(), t.getLabel());
	}
	
	/***
	* Will determine the location and piece to move
	* from the given label
	*
	* Will then attempt to move that piece
	* @param label
	* @return
	*/
	private boolean MovementPhase(String label){
		if (this.currBoard.numMovesAvailable(this.curPlayer) == 0 && this.curPlayer.getScore() > 3){
			return true;
		}
		Location t = this.currBoard.GetLocationByLabel(label);	
		return this.currBoard.MovePiece(this.curPlayer, pieceSelected.getID(), t.getLabel());
	}
	
	/***
	* Will return the winner of the game
	* Used in the gui victory screen
	* @return
	*/
	public Player getVictor() {
	    	return this.Victor;
	}
	
	/***
	* Will return the loser of the game
	* Used in the gui victory screen
	* @return
	*/
	public Player getLoser() {
		return this.Loser;
	}
	
	/**
	* Will set the next player
	*/
	private void nextPlayer(){
		this.curPlayer = inactivePlayer();
	}
	
	/**
	* Will return the inactive player
	* @return
	*/
	private Player inactivePlayer(){
		if (this.curPlayer == this.p1)
			return this.p2;
		else
			return this.p1;
	}
	
	/***
	* Will return the current Player
	* @return
	*/
	public Player getCurrPlayer() {
		return this.curPlayer;
	}
	
	/**
	* Will set the CurrentPhase
	* to the passed in mode
	* @param mode
	*/
	public void setGamePhase(Integer mode){
		this.currBoard.SetCurrentPhase(mode);
	}
	
	/**
	* Get the gameMode
	* Currently unused
	* Will be used for pve game mode
	* when it is added
	* @return
	*/
	public Integer getMode(){
		return this.gameMode;
	}	
	
	/***
	* Return player 1
	* @return
	*/
	public Player getPlayer1() {
		return p1;
	}
	
	/***
	* Return player 2
	* @return
	*/
	public Player getPlayer2() {
		return p2;
	}
	
	/***
	* Return the currentBoard being used
	* @return
	*/
	public Board getBoard() {
		return this.currBoard;
	}
	
	/**
	* Set p1 to the passed in player
	* @param p
	*/
	public void setPlayer1(Player p) {
		this.p1 = p;
	}
	
	/***
	* Set p2 to the passed in player
	* @param p
	*/
	public void setPlayer2(Player p) {
		this.p2 = p;
	}
	
	/***
	* This function will return a message
	* based on the current phase of the game
	*
	* Used in the GUI for displaying what
	* to do at the top of the screen
	* @return
	*/
	public String getPhaseText() {
		if(this.currBoard.GetCurrentPhase(this.getCurrPlayer()) == Board.PLACEMENT_PHASE)
			return "place a piece on the board";
		else if(this.currBoard.GetCurrentPhase(this.getCurrPlayer()) == Board.MOVEMENT_PHASE)
			return "move one of your pieces on the board";
		else if(this.currBoard.GetCurrentPhase(this.getCurrPlayer()) == Board.REMOVAL_PHASE)
			return "remove one of your opponents pieces";
		return "";
	}
}