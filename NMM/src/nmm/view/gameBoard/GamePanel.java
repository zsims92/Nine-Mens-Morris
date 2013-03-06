package nmm.view.gameBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import nmm.controller.NMMGameModel;

public class GamePanel extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9076559530700021419L;
	private static final int ROWS = 7;
	private static final int COLS = 7;
	private static final int CELL_SIZE = 100;
	private BufferedImage board;
	private NMMGameModel gameModel;
	private GameBoard gb;
	
	public GamePanel(NMMGameModel game, GameBoard gb){
		this.gb = gb;
		this.gameModel = game;
		try {
			this.board = ImageIO.read(new File("resources\\nmmBoard.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setPreferredSize(
                new Dimension(CELL_SIZE * COLS+50, CELL_SIZE * ROWS+50));
         this.setBackground(Color.WHITE);
         this.addMouseListener(this);
	}	
	
	//This should paint 49 blocks	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
    	int[][] gameBoard = this.gameModel.getBoard().getBoardArray();
    	drawBackground(g);
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
            	int x = c * CELL_SIZE+25;
            	int y = r * CELL_SIZE+25;
            	
            	//Represents p1 piece
				if(gameBoard[r][c] == 1){
            		g.setColor(gameModel.getPlayer1().getColor());
            		g.fillOval(x+14, y+14, CELL_SIZE-28, CELL_SIZE-28);
            	}
            	//Represents p2 piece
				else if(gameBoard[r][c] == 2){
            		g.setColor(gameModel.getPlayer2().getColor());
            		g.fillOval(x+10, y+10, CELL_SIZE-20, CELL_SIZE-20);
				}
            }
        }
	}

	private void drawBackground(Graphics g) {
		g.drawImage(board, 25, 25, 725, 725, 0, 0, 700, 700, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int col = e.getX()/CELL_SIZE;
        int row = e.getY()/CELL_SIZE;
        if(row < 0 || col < 0 || row > 6 || col > 6)
        	return;
        
        this.gameModel.newMove(row, col);
        
        this.revalidate();
        this.gb.repaint();
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
