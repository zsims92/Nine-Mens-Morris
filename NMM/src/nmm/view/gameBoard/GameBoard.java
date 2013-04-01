package nmm.view.gameBoard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nmm.controller.NMMGameModel;
import nmm.model.Board;
import nmm.view.MainWindow;

public class GameBoard extends JPanel{

	/**
	 * GameBoard display
	 * for nine-mens-morris
	 */
	private static final long serialVersionUID = 2364448613335062368L;
	private NMMGameModel gameModel;
	private MainWindow mw;
	private PlayerPanel p1;
	private PlayerPanel p2;
	private GamePanel gp;
	private JLabel p1Name;
	private JLabel p2Name;
	private JLabel p1Score;
	private JLabel p2Score;
	private JLabel currentPlayer;
	private JPanel topPanel;
	private JPanel scorePanel;
	
	/***
	 * The constructor for the GameBoard
	 * The gamemodel is used by itself and
	 * the player panels and gameBoard
	 * 
	 * The Mainwindow is passed to change
	 * to victory screen when game is over	
	 * @param nmm
	 * @param mw
	 */
	public GameBoard(NMMGameModel nmm, MainWindow mw){
		this.gameModel = nmm;
		this.mw = mw;
		this.p1 = new PlayerPanel(nmm.getPlayer1());
		this.p2 = new PlayerPanel(nmm.getPlayer2());
		this.gameModel = nmm;
		this.gp = new GamePanel(this.gameModel, this);
		
		this.setSize(950, 800);
		
		this.topPanel = new JPanel();
		this.scorePanel = new JPanel();
				
		p1Name = new JLabel(this.gameModel.getPlayer1().getName());
		p2Name = new JLabel(this.gameModel.getPlayer2().getName());
		p1Score = new JLabel("Score: " + String.valueOf(this.gameModel.getPlayer1().getScore()));
		p2Score = new JLabel("Score: " + String.valueOf(this.gameModel.getPlayer1().getScore()));
		currentPlayer = new JLabel(this.gameModel.getCurrPlayer().getName() + ", " + this.gameModel.getPhaseText());
		
		p1Name.setFont(new java.awt.Font("Times New Roman", 0, 18));
		p2Name.setFont(new java.awt.Font("Times New Roman", 0, 18));
		currentPlayer.setFont(new java.awt.Font("Times New Roman", 0, 30));
		p1Name.setHorizontalAlignment(JLabel.CENTER);
		currentPlayer.setHorizontalAlignment(JLabel.CENTER);
		p2Name.setHorizontalAlignment(JLabel.CENTER);
		p1Score.setHorizontalAlignment(JLabel.CENTER);
		p2Score.setHorizontalAlignment(JLabel.CENTER);
	
		this.topPanel.setLayout(new BorderLayout());
		this.topPanel.add(this.p1Name, BorderLayout.WEST);
		this.topPanel.add(this.p2Name, BorderLayout.EAST);
		this.topPanel.add(this.currentPlayer, BorderLayout.CENTER);
		
		this.scorePanel.setLayout(new BorderLayout());
		this.scorePanel.add(p1Score, BorderLayout.WEST);
		this.scorePanel.add(p2Score, BorderLayout.EAST);
		this.topPanel.add(this.scorePanel, BorderLayout.SOUTH);
		
		this.topPanel.setBackground(Color.WHITE);
		this.scorePanel.setBackground(Color.WHITE);
		
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(this.p1, BorderLayout.WEST);
		this.add(this.gp, BorderLayout.CENTER);
		this.add(this.p2, BorderLayout.EAST);
		
		this.setVisible(true);
	}
	
	@Override
	/***
	 * This function will first check if
	 * the game is over, and if so
	 * move on the the victory screen
	 * 
	 * It will then update itself, the
	 * player panels, and the gameBoard
	 * display
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(this.gameModel.getStatus() == Board.GAMEOVER_PHASE){
			this.mw.showEnd();
		}
		
		this.currentPlayer.setText(this.gameModel.getCurrPlayer().getName() + ", " + this.gameModel.getPhaseText());

		this.p1Score.setText("Score: " + String.valueOf(this.gameModel.getPlayer1().getScore()));
		this.p2Score.setText("Score: " + String.valueOf(this.gameModel.getPlayer2().getScore()));
		
		this.p1.repaint();
		this.p2.repaint();
		this.gp.repaint();
		
		int steps = 0;
		while(this.gameModel.isMoving() && steps < 10){
			this.gp.repaint();
			steps++;
		}
		
		this.gp.repaint();
	}
}
