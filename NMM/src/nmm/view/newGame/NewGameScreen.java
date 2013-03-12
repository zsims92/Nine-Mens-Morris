package nmm.view.newGame;

import javax.swing.JPanel;

import nmm.model.user.AIPlayer;
import nmm.model.user.Player;
import nmm.view.MainWindow;

public class NewGameScreen extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2841820904085389198L;
	private MainWindow mw;
	private NewPVP pvp;
	private NewPVE pve;
	private Integer mode;
	private AIPlayer computer;
	
	public NewGameScreen(MainWindow mw, Integer mode){
		this.setSize(800,600);
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
	
	
	public MainWindow getMainWindow(){
		return this.mw;
	}
	
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


	public AIPlayer getComputer() {
		return computer;
	}
}
