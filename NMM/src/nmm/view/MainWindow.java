package nmm.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import nmm.controller.NMMGameModel;
import nmm.model.Board;
import nmm.model.user.Player;
import nmm.view.gameBoard.GameBoard;
import nmm.view.newGame.NewGameScreen;

public class MainWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3638794002119631337L;
	private CardLayout cards;
	
	private GameBoard gb;
	private WelcomeScreen ws;
	private NewGameScreen sd;
	private VictoryScreen vs;
	private NMMGameModel nmm;
	
	private JPanel cardPanel;
	
	private JMenuBar jMenuBar1;
	  
	private JMenu jMenu1;
	private JMenu jMenu2;
	private JMenu jMenu3;
	
	private JMenuItem jMenuItem1;
	private JMenuItem jMenuItem2;
	private JMenuItem jMenuItem3;
	private JMenuItem jMenuItem4;
	private JMenuItem jMenuItem5;
	private JMenuItem jMenuItem6;
	
	public MainWindow(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Nine Mens Morris");
        this.setSize(600,600);

        this.createMenuBar();
        this.setLayout(new BorderLayout());
        
        this.ws = new WelcomeScreen(this);
        
        cards = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cards);

		cardPanel.add(this.ws, "WelcomeScreen");
        cards.show(cardPanel, "WelcomeScreen");
	        
		this.add(cardPanel);
		this.setSize(900,900);
		this.setLocation(100,20);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private void createMenuBar() {
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenuItem1 = new JMenuItem();
        jMenuItem2 = new JMenuItem();
        jMenuItem3 = new JMenuItem();
        jMenu2 = new JMenu();
        jMenuItem4 = new JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new JMenuItem();
        jMenuItem6 = new JMenuItem();
           
        jMenu1.setText("File");
        jMenu2.setText("Edit");
        jMenu3.setText("Help");

        jMenuItem1.setText("New Game");
        jMenuItem2.setText("Cheat Mode");
        jMenuItem3.setText("Quit");
        jMenuItem4.setText("Undo");
        jMenuItem5.setText("How to Play");
        jMenuItem6.setText("About");
       
        jMenuItem1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		reset(evt);
        	}
        });
        jMenuItem3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		quit(evt);
        	}
        });
        jMenuItem2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		cheatMode(evt);
        	}
        });
        jMenuItem4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		undo(evt);
        	}
        });
        jMenuItem5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		showHowTo(evt);
        	}
        });
        jMenuItem6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		showAbout(evt);
        	}
        });
        
        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem2);
        jMenu1.add(jMenuItem3);
        jMenu2.add(jMenuItem4);
        jMenu3.add(jMenuItem5);
        jMenu3.add(jMenuItem6);
        
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenuBar1.add(jMenu3);

        this.setJMenuBar(jMenuBar1);
	}
		
	private void showAbout(ActionEvent evt) {
		JLabel about = new JLabel();
		about.setText("<html>Created by:<br>"+"" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Zachary Sims</br><br>"+
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Jerad Gerber</br><br>" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Travis Sweetser</br></html>");
		JOptionPane.showMessageDialog(this, about);
	}

	private void showHowTo(ActionEvent evt) {
		JEditorPane editorPane = new JEditorPane();;
		editorPane.setEditable(false);
		java.net.URL howToUrl = null;
		try {
			howToUrl = new java.net.URL("http://www.themathlab.com/games/Nine%20Man%20Morris/howtoplay.htm");
		} catch (MalformedURLException e) {
		}
		try {
	        editorPane.setPage(howToUrl);
	    } catch (IOException e) {
	        System.err.println("Attempted to read a bad URL: " + howToUrl);
	    }
	    JOptionPane.showMessageDialog(this, editorPane);
    }

	private void undo(ActionEvent evt) {
		int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
		if(confirm == 0){
			this.nmm.undoMove();
			this.gb.repaint();
		}
		else
			return;		
	}

	private void cheatMode(ActionEvent evt) {
		if(this.nmm == null){
			JOptionPane.showMessageDialog(this, "A game has not started yet");
			return;
		}
		if(this.nmm.getBoard().GetCurrentPhase(this.nmm.getCurrPlayer()) == Board.PLACEMENT_PHASE){
			JOptionPane.showMessageDialog(this, "Please wait until after placement phase\r\nto enter cheat mode");
			return;			
		}
		int confirm = JOptionPane.showConfirmDialog(this, "Do you want to enter cheat mode?");
		if(confirm == 0){
			this.nmm.getBoard().setCheatMode();
		}
		else
			return;	
		
	}

	public void quit(ActionEvent evt) {
		int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
		if(confirm == 0){
			this.dispose();
		}
		else
			return;	
	}

	public void changeCard(String card){
		this.cards.show(cardPanel, card);
	}
	
	public void reset(ActionEvent evt){
		if(evt.getSource() == this.jMenuItem1){
			int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
			if(confirm == 0){
				this.clear();
				
			}
			else
				return;
		}
		this.clear();
	}
	
	private void clear() {
		this.changeCard("WelcomeScreen");
		this.nmm = null;
		this.sd = null;
		this.gb = null;
		this.vs = null;
	}

	public void newGame(Integer mode){
		this.sd = new NewGameScreen(this, mode);
		cardPanel.add(this.sd, "NewGameScreen");
		this.changeCard("NewGameScreen");
		this.setSize(900,900);
		this.setLocation(100,20);
	}
	
	public void startGame(Player p1, Player p2, Integer mode){
		this.nmm = new NMMGameModel(mode, p1, p2, this);
		this.nmm.setPlayer1(p1);
		this.nmm.setPlayer2(p2);
		this.gb = new GameBoard(nmm, this);
		cardPanel.add(this.gb, "GameBoard");
		this.changeCard("GameBoard");
		this.setSize(900,900);
		this.setLocation(100,20);
	}
	
	public void showEnd() {
		this.vs = new VictoryScreen(this, this.nmm.getVictor(), this.nmm.getLoser());
		cardPanel.add(this.vs, "EndGame");
		this.changeCard("EndGame");
		this.setLocationRelativeTo(null);
		this.setSize(900,900);
		this.setLocation(100,20);
	}
}
