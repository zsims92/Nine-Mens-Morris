package nmm.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
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
import nmm.model.user.AIPlayer;
import nmm.model.user.Player;
import nmm.view.gameBoard.GameBoard;
import nmm.view.newGame.NewGameScreen;

public class MainWindow extends JFrame{

	/**
	 * Main Window for
	 * nine-mens-morris
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
	private JMenu jMenu3;
	
	private JMenuItem jMenuItem1;
	private JMenuItem jMenuItem2;
	private JMenuItem jMenuItem3;
	private JMenuItem jMenuItem5;
	private JMenuItem jMenuItem6;
	private JMenuItem jMenuItem7;
	
	/***
	 * Constructor for the mainWindow
	 * 
	 * Will load the intial state of the
	 * game which is the welcome screen
	 * 
	 */
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
		
		BufferedImage Icon = null;
		try {
			Icon = ImageIO.read(new File("resources\\taskBarIcon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		};
		
		this.setIconImage(Icon);
		this.setSize(700,700);
		this.setLocation(0,0);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/***
	 * Creates the menuBar that
	 * the mainWindow will use
	 */
	private void createMenuBar() {
        jMenuBar1 = new JMenuBar();
        jMenu1 = new JMenu();
        jMenuItem1 = new JMenuItem();
        jMenuItem2 = new JMenuItem();
        jMenuItem3 = new JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new JMenuItem();
        jMenuItem6 = new JMenuItem();
        jMenuItem7 = new JMenuItem();
           
        jMenu1.setText("File");
        jMenu3.setText("Help");

        jMenuItem1.setText("New Game");
        jMenuItem2.setText("Cheat Mode");
        jMenuItem3.setText("Quit");
        jMenuItem5.setText("How to Play");
        jMenuItem6.setText("About");
        jMenuItem7.setText("Color options");
       
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
        jMenuItem7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		showColors(evt);
        	}
        });
        
        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem2);
        jMenu1.add(jMenuItem3);
        jMenu3.add(jMenuItem5);
        jMenu3.add(jMenuItem6);
        jMenu3.add(jMenuItem7);
        
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu3);

        this.setJMenuBar(jMenuBar1);
	}
	
	private void showColors(ActionEvent evt) {
		String colors[] = AIPlayer.colors;
		JLabel colours = new JLabel();
		String s= "<HTML> ";
		for(int i=0; i<colors.length; i++)
				s = s + "<br>" + colors[i] + "</br>";
		s = s + "</html>";
		colours.setText(s);
		JOptionPane.showMessageDialog(this, colours);
		
	}

	/***
	 * Will show information about
	 * the creation of the game
	 * @param evt
	 */
	private void showAbout(ActionEvent evt) {
		JLabel about = new JLabel();
		about.setText("<html>Created by:<br>"+"" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Zachary Sims</br><br>"+
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Jerad Gerber</br><br>" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Travis Sweetser</br></html>");
		JOptionPane.showMessageDialog(this, about);
	}

	/***
	 * Will show an html
	 * page showing the user how to play
	 * 
	 * Will be updated with one of
	 * our user pages instead of using
	 * a random website
	 * @param evt
	 */
	private void showHowTo(ActionEvent evt) {
		JEditorPane editorPane = new JEditorPane();
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

	/***
	 * Will activate cheat mode for the
	 * started game
	 * 
	 * If not started will alert user and return
	 * 
	 * If still in placement phase will alert user
	 * and return
	 * 
	 * Else will confirm selection and
	 * activate cheatmode
	 * @param evt
	 */
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

	/***
	 * When the user selects to quit
	 * from the Menu Bar or from the 
	 * victory screen
	 * @param evt
	 */
	public void quit(ActionEvent evt) {
		int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
		if(confirm == 0){
			this.dispose();
		}
		else
			return;	
	}

	/***
	 * Will display the card that is
	 * determined by the passed in string
	 * @param card
	 */
	public void changeCard(String card){
		this.cards.show(cardPanel, card);
	}
	
	/***
	 * Will confirm the user wants
	 * reset the game
	 * 
	 * if So calls the clear function
	 * Else returns to current game
	 * @param evt
	 */
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
	
	/**
	 * Creates a new game by
	 * setting everything to null for a
	 * recreation
	 */
	private void clear() {
		this.changeCard("WelcomeScreen");
		this.nmm = null;
		this.sd = null;
		this.gb = null;
		this.vs = null;
	}

	/***
	 * After welcome screen
	 * shows screen to enter
	 * information for a new game
	 * @param mode
	 */
	public void newGame(Integer mode){
		this.sd = new NewGameScreen(this, mode);
		cardPanel.add(this.sd, "NewGameScreen");
		this.changeCard("NewGameScreen");
		this.setSize(700,700);
		this.setLocation(0,0);
	}
	
	/***
	 * Starts the game after getting
	 * information about the games
	 * players and the mode
	 * @param p1
	 * @param p2
	 * @param mode
	 */
	public void startGame(Player p1, Player p2, Integer mode){
		this.nmm = new NMMGameModel(mode, p1, p2, this);
		this.nmm.setPlayer1(p1);
		this.nmm.setPlayer2(p2);
		this.gb = new GameBoard(nmm, this);
		cardPanel.add(this.gb, "GameBoard");
		this.changeCard("GameBoard");
		this.setSize(700,700);
		this.setLocation(0,0);
	}
	
	/***
	 * Shows the victory screen after
	 * the game is over
	 */
	public void showEnd() {
		this.vs = new VictoryScreen(this, this.nmm.getVictor(), this.nmm.getLoser());
		cardPanel.add(this.vs, "EndGame");
		this.changeCard("EndGame");
		this.setLocationRelativeTo(null);
		this.setSize(700,700);
		this.setLocation(0,0);
	}

	public GameBoard getGameBoard() {
		return this.gb;
	}
}
