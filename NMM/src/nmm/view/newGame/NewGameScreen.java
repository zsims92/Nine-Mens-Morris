package nmm.view.newGame;

import javax.swing.JPanel;

import nmm.controller.NMMGameModel;
import nmm.model.user.Player;
import nmm.view.MainWindow;

public class NewGameScreen extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2841820904085389198L;
	private MainWindow mw;
	private NMMGameModel nmm;
	private NewPVP pvp;
	private NewPVE pve;
	private Integer mode;
	
	public NewGameScreen(MainWindow mw, NMMGameModel nmm, Integer mode){
		this.setSize(800,600);
		this.mw = mw;
		this.nmm = nmm;
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
	}
	
	public void startGame(){
		if(this.mode == 0){
			this.nmm.setPlayer1(new Player(this.pve.getP1Name(), this.pve.getP1Color()));
			this.mw.startGame();
		}
		else if(this.mode == 1){
			this.nmm.setPlayer1(new Player(this.pvp.getP1Name(), this.pvp.getP1Color()));
			this.nmm.setPlayer2(new Player(this.pvp.getP2Name(), this.pvp.getP2Color()));
			this.mw.startGame();
		}	
	}
}
