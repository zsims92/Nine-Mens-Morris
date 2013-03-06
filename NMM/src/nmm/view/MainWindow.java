package nmm.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import nmm.controller.NMMGameModel;
import nmm.view.gameBoard.GameBoard;
import nmm.view.newGame.NewGameScreen;

public class MainWindow extends JFrame implements WindowListener, MouseListener{

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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Nine Mens Morris");
        this.setSize(600,600);

        this.createMenuBar();
        this.setLayout(new BorderLayout());
        
        this.ws = new WelcomeScreen(this);
        
        cards = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cards);

		cardPanel.add(this.ws, "WelcomScreen");
        cards.show(cardPanel, "WelcomScreen");
	        
        this.addMouseListener(this);
		this.add(cardPanel);
		this.setSize(450, 300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public void reset(ActionEvent evt){
		this.changeCard("WelcomScreen");
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);
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
	public void changeCard(String card){
		this.cards.show(cardPanel, card);
	}
	
	public void newGame(Integer mode){
		this.nmm = new NMMGameModel(this, mode);
		this.sd = new NewGameScreen(this, this.nmm, mode);
		cardPanel.add(this.sd, "NewGameScreen");
		this.changeCard("NewGameScreen");
		this.setSize(300,300);
	}
	
	public void startGame(){
		this.gb = new GameBoard(nmm, this);
		this.vs = new VictoryScreen(nmm.getPlayer1(), nmm.getPlayer2());
		cardPanel.add(this.gb, "GameBoard");
		cardPanel.add(this.vs, "GameOver");
		this.changeCard("GameBoard");
		this.setSize(900,800);
		this.setLocation(100,20);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
