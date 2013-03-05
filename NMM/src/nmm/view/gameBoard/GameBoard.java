package nmm.view.gameBoard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import nmm.controller.NMMGameModel;
import nmm.view.MainWindow;

public class GameBoard extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2364448613335062368L;
	private NMMGameModel gameModel;
	private MainWindow mw;
	private JButton but;
	private PlayerPanel p1;
	private PlayerPanel p2;
	private GamePanel gp;
	
	private boolean status = true;
	
	public GameBoard(NMMGameModel nmm, MainWindow mw){
		this.gameModel = nmm;
		this.mw = mw;
		this.p1 = new PlayerPanel(nmm.getPlayer1());
		this.p2 = new PlayerPanel(nmm.getPlayer2());
		this.gameModel = new NMMGameModel();
		this.gp = new GamePanel(this.gameModel, this);
		
		
		this.setSize(950, 800);
		this.setBackground(Color.BLUE);
		this.setLayout(new BorderLayout());
		this.add(this.p1, BorderLayout.WEST);
		this.add(this.gp, BorderLayout.CENTER);
		this.add(this.p2, BorderLayout.EAST);
		
		but = new JButton("This is the game Display");
		but.setActionCommand("This is the game Display");
        but.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		changeWindow(evt);
        	}
        });
		
		this.add(but, BorderLayout.NORTH);
		this.setVisible(true);
	}
	
	public void setGameModel(NMMGameModel game){
		this.gameModel = game;
	}
	private void changeWindow(ActionEvent evt) {
		this.mw.changeCard("StartScreen");			
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.p1.repaint();
		this.p2.repaint();
		this.gp.repaint();
	}
	
	public JButton getButton(){
		return this.but;
	}
	public boolean getStatus(){
		return this.status;
	}	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
