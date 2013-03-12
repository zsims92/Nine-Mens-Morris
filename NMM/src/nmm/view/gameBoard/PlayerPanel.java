package nmm.view.gameBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import nmm.model.user.Player;
public class PlayerPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3043526960369929645L;
	private static final int COLS = 1;
	private static final int CELL_SIZE = 77;
	private Player player;
	
	
	public PlayerPanel(Player p1) {
		this.player = p1;

		this.setPreferredSize(
                new Dimension(CELL_SIZE * COLS, 700));
         this.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Integer pieces = 9 - this.player.getPiecesPlayed();
        for (int r=0; r<pieces; r++) {
            for (int c=0; c<COLS; c++) {
                int x = c * CELL_SIZE;
                int y = r * CELL_SIZE + 30;
            	g.setColor(Color.WHITE);
            	g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
            	g.setColor(this.player.getColor());
            	g.fillOval(x+10, y+10, CELL_SIZE-20, CELL_SIZE-20);
            }
        }
	}

}
