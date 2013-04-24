package nmm.view.newGame;

import javax.swing.JPanel;

import nmm.model.user.AIPlayer;
import nmm.model.user.Player;
import nmm.view.MainWindow;

public class NewGameScreen extends JPanel{

	/**
	 * New game display
	 * for nine-mens-morris
	 */
	private static final long serialVersionUID = 2841820904085389198L;
	private MainWindow mw;
	private NewPVP pvp;
	private NewPVE pve;
	private Integer mode;
	private AIPlayer computer;
	
	/***
	 * Constructor for NewGameScreen
	 * 
	 * Determines if game is PVE or PVP
	 * from passed in mode
	 * 
	 * Creates AI for Game if needed
	 * @param mw
	 * @param mode
	 */
	public NewGameScreen(MainWindow mw, Integer mode){
		this.setSize(700,700);
		this.mw = mw;
		this.mode = mode;
		if(mode == 0){
			this.pve = new NewPVE(this);
			this.add(this.pve);
		}
		else{
			this.pvp = new NewPVP(this);
			this.add(this.pvp);
		}
		this.setVisible(true);
		this.computer = new AIPlayer("Computer", "Black");
	}
	
	/***
	 * Returns the mainWindow object
	 * to the NewPVP and NewPVE classes
	 * 
	 * Used only to print dialog to main screen
	 * @return
	 */
	public MainWindow getMainWindow(){
		return this.mw;
	}
	
	/***
	 * Will start the game after
	 * receiving good input from the user
	 */
	public void startGame(){
		if(this.mode == 0){
			Player p1 = new Player(this.pve.getP1Name(), this.pve.getP1Color());
			this.mw.startGame(p1, this.computer, this.mode);
		}
		else if(this.mode == 1){
			Player p1 = new Player(this.pvp.getP1Name(), this.pvp.getP1Color());
			Player p2 = new Player(this.pvp.getP2Name(), this.pvp.getP2Color());
			this.mw.startGame(p1, p2, this.mode);
		}	
	}

	/**
	 * Will return the AI component of the game
	 * It is created earlier to avoid color clashes
	 * with the player
	 * @return
	 */
	public AIPlayer getComputer() {
		return computer;
	}
}
