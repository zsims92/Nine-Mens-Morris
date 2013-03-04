package nmm.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import nmm.model.user.Player;
import nmm.view.gameBoard.GameBoard;

public class MainWindow extends JFrame implements WindowListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3638794002119631337L;
	private CardLayout cards;
	
	private GameBoard gb;
	private StartDisplay sd;
	private VictoryScreen vs;
	
	private JPanel cardPanel;
	
	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem quit;
	
	public MainWindow(Player p1, Player p2){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Nine Mens Morris");
        this.setSize(600,600);
        
        this.menuBar = new JMenuBar();
        this.file = new JMenu();
        this.quit = new JMenuItem("Quit");
        
        this.file.add(this.quit);
        this.menuBar.add(this.file);
        
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        this.sd = new StartDisplay();
        this.gb = new GameBoard(p1, p2);
        this.vs = new VictoryScreen(p1, p2);
        
        ///
        this.vs.add(new JButton());
        ///
        
		this.gb.setButtonListener(this);
		this.sd.setButtonListener(this);
        
        cards = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cards);
        
		cardPanel.add(this.gb, "GameBoard");
		cardPanel.add(this.sd, "StartScreen");
        cards.show(cardPanel, "StartScreen");
		
        
        
        this.addMouseListener(this);
		this.add(cardPanel);
		this.setSize(950,950);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void changeCard(String card){
		this.cards.show(cardPanel, card);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(this.gb.getButton())){
			this.changeCard("StartScreen");
		}
		
		if(e.getSource().equals(this.sd.getButton())){
			this.changeCard("GameBoard");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource().equals(this.gb.getButton())){
			this.changeCard("StartScreen");
		}
		
		if(e.getSource().equals(this.sd.getButton())){
			this.changeCard("GameBoard");
		}
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
