package nmm.view.gameBoard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import nmm.controller.NMMGameModel;
import nmm.view.MainWindow;

public class GameBoard extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2364448613335062368L;
	private NMMGameModel gameModel;
	private MainWindow mw;
	private PlayerPanel p1;
	private PlayerPanel p2;
	private GamePanel gp;
	
	private boolean status = true;
	
	public GameBoard(NMMGameModel nmm, MainWindow mw){
		this.gameModel = nmm;
		this.mw = mw;
		this.p1 = new PlayerPanel(nmm.getPlayer1());
		this.p2 = new PlayerPanel(nmm.getPlayer2());
		this.gameModel = nmm;
		this.gp = new GamePanel(this.gameModel, this);
		
		this.setSize(950, 800);
		this.setBackground(Color.BLUE);
		this.setLayout(new BorderLayout());
		this.add(this.p1, BorderLayout.WEST);
		this.add(this.gp, BorderLayout.CENTER);
		this.add(this.p2, BorderLayout.EAST);

		this.setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(this.gameModel.getStatus() == 1){
			this.mw.showEnd();
		}
		
		this.p1.repaint();
		this.p2.repaint();
		this.gp.repaint();
	}
	
	public boolean getStatus(){
		return this.status;
	}	
}
