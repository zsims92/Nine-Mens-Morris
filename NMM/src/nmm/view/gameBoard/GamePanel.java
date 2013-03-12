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
import nmm.model.Board;
import nmm.model.GamePiece;

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
        GamePiece[][] gameBoard = this.gameModel.getBoard().getBoardArray();
    	drawBackground(g);
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
            	int x = c * CELL_SIZE+25;
            	int y = r * CELL_SIZE+25;
            	
            	//Represents p1 piece
				if(gameBoard[r][c] == null || gameBoard[r][c].getID() == -1){
					continue;
            	}
            	//Represents p2 piece
				else if(gameBoard[r][c].getSelected()){
            		g.setColor(gameBoard[r][c].getColor());
            		g.fillOval(x+10, y+10, CELL_SIZE-20, CELL_SIZE-20);
            		g.setColor(Color.WHITE);
            		g.fillOval(x+30, y+30, CELL_SIZE-60, CELL_SIZE-60);
				}
				else{
					g.setColor(gameBoard[r][c].getColor());
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
        
        if(!this.convertToLabel(row, col))
        	return;
        
        this.revalidate();
        this.gb.repaint();
	}
	
	//Converts the given row and col into its correct location label
	private boolean convertToLabel(int row, int col) {
		char[] labels =Board.ALPHABET;
		String[] points = Board.BOARDREFERENCE;
		String label = "Z";
		
		for(int i=0; i<24; i++){
			String t[] = points[i].split(",");
			int row2 = Integer.parseInt(t[0]);
			int col2 = Integer.parseInt(t[1]);
			if(row2 == row && col2 == col)
				label = String.valueOf(labels[i]);
		}
		if(label == "Z")
			return false;
			
		if(!this.gameModel.newMove(label)){
			return false;
		}
		
		return true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}	
}
