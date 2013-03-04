package nmm.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartDisplay extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2841820904085389198L;
	private JButton but;
	private boolean status = true;

	
	public StartDisplay(){
		this.setSize(800,600);
		this.setBackground(Color.RED);
		this.setLayout(new BorderLayout());
		
		but = new JButton("This is the start Display");
		but.setActionCommand("This is the start Display");
		
		this.add(but, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void setButtonListener(MainWindow mw){
		this.but.addMouseListener(mw);
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

	public JButton getButton() {
		// TODO Auto-generated method stub
		return this.but;
	}

}
