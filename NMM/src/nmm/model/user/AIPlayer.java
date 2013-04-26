package nmm.model.user;

import java.util.Random;

import nmm.controller.NMMGameModel;
import nmm.model.Board;
import nmm.model.GamePiece;
import nmm.model.Location;

public class AIPlayer extends Player {
	
	public static final String[] colors = {"Black", "Red", "Blue", "Gray", "Green"};
	private NMMGameModel nmm;
	public static Random R;
	/***
	 * Default constructor for an
	 * AI player
	 * @param name
	 * @param color
	 */
	public AIPlayer(String name, String color) {
		super(name, chooseRandomColor(color));
		
	}
	
	/***
	 * Will choose a random color for the AI
	 * @param color
	 * @return
	 */
	private static String chooseRandomColor(String color) {
		R = new Random();
		int i = R.nextInt() % 5;
		while(i < 0 || i > 4)
			i = R.nextInt() % 5;
		if(AIPlayer.colors[i] != null){
			return AIPlayer.colors[i].toLowerCase();
		}
		return color;
	}
	
	
	public String newMove(){
		return "A";
	}
	

	/**
	 * Will be in used in PVE mode
	 * 
	 * Tells the model that this is a
	 * computer
	 */
	@Override
	public boolean isHuman(){
		return false;
	}

	public boolean placeMove() {
		int i = R.nextInt() % 24;
		while(i < 0 || i > 24){
			i = R.nextInt() % 24;
		}
		char move = Board.ALPHABET[i];
		while(!this.nmm.newMove(String.valueOf(move))){
			i = R.nextInt() % 24;
			while(i < 0 || i > 24){
				i = R.nextInt() % 24;
			}
			move = Board.ALPHABET[i];;
		}
		return true;
	}

	public boolean moveMove() {		
		for(GamePiece p : this.getPieces()){
			Location t = this.nmm.getBoard().GetPieceLocation(p);

			for(char lab: Board.ALPHABET){
				Location newLoc = this.nmm.getBoard().GetLocationByLabel(String.valueOf(lab));
				if(t == newLoc)
					continue;
				if(!newLoc.ContainsPiece(null))
					continue;
				if(this.nmm.getBoard().AreNeighbors(t, newLoc)){
					this.nmm.setSelected(p);
					if(this.nmm.newMove(newLoc.getLabel())){
						return true;
					}
					else{
						this.nmm.clearSelected();
					}
				}
				
			}
		}
		return false;
	}

	public boolean remoMove() {
		Player p = this.nmm.getPlayer1();
		for(GamePiece gp: p.getPieces()){
			if(gp.IsAlive() && gp.getStatus() != GamePiece.UNPLACED){
				if(this.nmm.newMove(this.nmm.getBoard().GetPieceLocation(gp).getLabel())){
					return true;
				}
			}
		}
		return false;
	}

	public void setNmm(NMMGameModel nmm) {
		this.nmm = nmm;
	}

	public NMMGameModel getNmm() {
		return nmm;
	}

}
