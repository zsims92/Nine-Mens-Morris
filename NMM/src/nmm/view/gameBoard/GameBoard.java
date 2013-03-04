package nmm.view.gameBoard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import nmm.model.NMMGameModel;
import nmm.model.user.Player;
import nmm.view.MainWindow;

public class GameBoard extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2364448613335062368L;
	private NMMGameModel gameModel;
	private JButton but;
	private PlayerPanel p1;
	private PlayerPanel p2;
	private GamePanel gp;
	
	private boolean status = true;
	
	public GameBoard(Player p1, Player p2){
		this.p1 = new PlayerPanel(p1);
		this.p2 = new PlayerPanel(p2);
		this.gameModel = new NMMGameModel();
		this.gp = new GamePanel(this.gameModel);
		
		
		this.setSize(950, 950);
		this.setBackground(Color.BLUE);
		this.setLayout(new BorderLayout());
		this.add(this.p1, BorderLayout.WEST);
		this.add(this.gp, BorderLayout.CENTER);
		this.add(this.p2, BorderLayout.EAST);
		
		but = new JButton("This is the game Display");
		but.setActionCommand("This is the game Display");
		
		this.add(but, BorderLayout.NORTH);
		this.setVisible(true);
	}
	
	public void setGameModel(NMMGameModel game){
		this.gameModel = game;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.p1.repaint();
		this.p2.repaint();
		this.gp.repaint();
	}
	
	public void setButtonListener(MainWindow mw){
		this.but.addMouseListener(mw);
	}	
	public void setPanelListener(MainWindow mw){
		this.addMouseListener(mw);
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
