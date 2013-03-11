package nmm.virtual.parts;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class GameBoard extends JComponent{	
    /**
	 * 
	 */
	private static final long serialVersionUID = 499684581899983218L;
	private JLabel currentMove;
    private JLabel play1Score;
    private JLabel play2BotLab;
    private JLabel play2Name;
    private JLabel play1BotLab;
    private JLabel play1Name;
    private JLabel play2Score;
        
    private JPanel play1Pieces;
    private JPanel play2Pieces;
    private JPanel boardPanel;
    private JPanel outBoardPanel;
    
    BufferedImage redCir = null;
    BufferedImage blaCir = null;
    BufferedImage bluCir = null;
    BufferedImage horBar = null;
    BufferedImage verBar = null;

    private JButton undoButton;
    private JButton lastButton;
    private JComponent[] boardPieces = new JComponent[49];
    private JComponent[] play1PiecesC = new JComponent[9];
    private JComponent[] play2PiecesC = new JComponent[9];
    private int count = 0;
    private int phase = 0;
    private int recent = 0;
    private Integer[] boardLayout = null;
    private Boolean gameOver = false;
    
    public GameBoard(String play1, String play2){
        play1Pieces = new JPanel();
        play2Pieces = new JPanel();
        play1Name = new JLabel();
        play2Name = new JLabel();
        boardPanel = new JPanel();
        outBoardPanel = new JPanel();
        undoButton = new JButton();
        lastButton = new JButton();
        play1BotLab = new JLabel();
        play2BotLab = new JLabel();
        play1Score = new JLabel();
        play2Score = new JLabel();
        currentMove = new JLabel();
        
        play1Name.setText(play1);
        play2Name.setText(play2);
        String s = String.format("Current Player's Move: %s, Place a piece in an open spot", play1);
        currentMove.setText(s);

        setPanelLayouts();
        makeLayout();

        try {
			blaCir = ImageIO.read(new File("src/blaCir.bmp"));
			bluCir = ImageIO.read(new File("src/bluCir.bmp"));
			redCir = ImageIO.read(new File("src/redCir.bmp"));
			horBar = ImageIO.read(new File("src/horiBar.bmp"));
			verBar = ImageIO.read(new File("src/verBar.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
        ActionListener t = new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
            	piecePlaced(evt);
            }
        };
        
        undoButton.setText("Undo");
        
        play1BotLab.setText("Stones left");
        play2BotLab.setText("Stones left");
        play1Score.setText("9");
        play2Score.setText("9");
        
        undoButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		undoAction(evt);
        	}
        });
        
        
        
        this.boardLayout = loadBoard();
        setBoardInit();
        outBoardPanel.add(boardPanel);
        
        this.setBackground(new Color(1.0f,1.0f,1.0f));
    }
    
    public void resetGame(String s1, String s2){
    	play2Pieces.removeAll();
    	play1Pieces.removeAll();
    	boardPanel.removeAll();
    	
    	for(JComponent jb: play1PiecesC){
    		
    	}
    	for(JComponent jb: boardPieces){
    		
    	}
    	for(JComponent jb: play2PiecesC){
    		
    	}
    	
    }
    
    private void setBoardInit(){
    	boardPanel.removeAll();
        ActionListener t = new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
            	piecePlaced(evt);
            }
        };
        
        boardPanel.setPreferredSize(new Dimension(500, 500));
        boardPanel.setMaximumSize(new Dimension(500, 500));
        boardPanel.setMinimumSize(new Dimension(500, 500));
        
        boardPanel.setLayout(new GridLayout(7,7));
        boardPanel.setBackground(new Color(1.0f,1.0f,1.0f));
        
		for(int i=0; i<49; i++){
        	if(boardLayout[i] == 0){
        		JLabel jb = new JLabel(new ImageIcon(horBar));
        		boardPieces[i] = jb;
        	}
        	else if(boardLayout[i] == 1){
        		JLabel jb = new JLabel(new ImageIcon(verBar));
        		boardPieces[i] = jb;
        	}
        	else if(boardLayout[i] == 3){
        		JLabel jb = new JLabel();
        		boardPieces[i] = jb;
        	}
        	else{
        		JButton jb = new JButton(new ImageIcon(blaCir));
        		jb.addActionListener(t);
        		jb.setName("Black Circle");
        		jb.setBackground(new Color(1.0f,1.0f,1.0f));
        		boardPieces[i] = jb;
        	}
        	boardPanel.add(boardPieces[i]);
        }
		
        play1Pieces.setLayout(new GridLayout(9,1));
        play1Pieces.setBackground(new Color(1.0f,1.0f,1.0f));
        for(int i=0; i<9; i++){
        	JLabel jb = new JLabel(new ImageIcon(bluCir));
    		play1PiecesC[i] = jb;
    		play1Pieces.add(play1PiecesC[i]);
        }
        
        play2Pieces.setLayout(new GridLayout(9,1));
        play2Pieces.setBackground(new Color(1.0f,1.0f,1.0f));
        for(int i=0; i<9; i++){
        	JLabel jb = new JLabel(new ImageIcon(redCir));
    		play2PiecesC[i] = jb;
    		play2Pieces.add(play2PiecesC[i]);
        }
    }
    
    private void setPanelLayouts(){
        GroupLayout play1PiecesLayout = new GroupLayout(play1Pieces);
        play1Pieces.setLayout(play1PiecesLayout);
        play1PiecesLayout.setHorizontalGroup(
            play1PiecesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 91, Short.MAX_VALUE)
        );
        play1PiecesLayout.setVerticalGroup(
            play1PiecesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        GroupLayout play2PiecesLayout = new GroupLayout(play2Pieces);
        play2Pieces.setLayout(play2PiecesLayout);
        play2PiecesLayout.setHorizontalGroup(
            play2PiecesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );
        play2PiecesLayout.setVerticalGroup(
            play2PiecesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        GroupLayout boardPanelLayout = new GroupLayout(outBoardPanel);
        outBoardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );
    } 
    private void makeLayout() {
    	GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(play1Name)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 379, Short.MAX_VALUE)
                .addComponent(play2Name, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(play1Pieces, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(play1BotLab))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(play1Score)))
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boardPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(undoButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentMove)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(play2Pieces, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(play2BotLab)))
                        .addContainerGap())
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(play2Score)
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(play1Name)
                    .addComponent(play2Name))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(boardPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(play1Pieces, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(play2Pieces, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(play2BotLab)
                    .addComponent(play1BotLab))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(play1Score)
                    .addComponent(play2Score)
                    .addComponent(undoButton)
                    .addComponent(currentMove))
                .addGap(33, 33, 33))
        );
		
	}
	private Integer[] loadBoard(){
    	String fileName = "src/boardLayout.txt";
    	Scanner s = null;
    	
    	try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
    	Integer size = Integer.parseInt(s.nextLine());
    	Integer[] layout = new Integer[size];
    	for(int i=0; i<size; i++){
        	String line = s.nextLine();
    		layout[i] = Integer.parseInt(line);
    	}
    	
    	return layout;
    }      
	
	private void undoAction(ActionEvent evt) {
        String s = "";
		if(phase == 0){
			if(count > 18){
				return;
			}
	        if(recent == 1)
	        	return;
			
			JLabel jb = new JLabel(lastButton.getIcon());
			if(count % 2 == 0){
	        	play2Pieces.add(jb);
	        } else {
	        	play1Pieces.add(jb);
	        }
			lastButton.setIcon(new ImageIcon(blaCir));
			lastButton.setName("Black Circle");
	        count--;
	        recent = 1;
        }
        else if(phase == 1){
        	s = play1Name.getText();
			s = String.format("Current Player's Move: %s, Working so far", s);
	    	currentMove.setText(s);
        	
        }
	}
	
	private void piecePlaced(ActionEvent a) {
		if(count > 17){
			phase = 1;
			this.gameOver = true;
		}
		if(phase == 0){
			JButton pressedButton = (JButton)a.getSource();
			String s = "";
	
			if(pressedButton.getName() != "Black Circle"){
				JOptionPane.showMessageDialog(null, "Invalid Move!");
				return;
			}
			
			count++;
	        ImageIcon im = null;
	        
	        
	        /*Calculate whose turn it is*/
	        if(count % 2 == 0){
	            im = new ImageIcon(redCir);
	            s = play1Name.getText();
	        } else {
	            im = new ImageIcon(bluCir);
	            s = play2Name.getText();
	        }
	
	    	s = String.format("Current Player's Move: %s, Place a piece in an open spot", s);
	    	currentMove.setText(s);
	    	
	        /*Write the letter to the button and deactivate it*/
			pressedButton = (JButton)a.getSource(); 
			pressedButton.setIcon(im);
			pressedButton.setName("NB");
			
			if(count < 19){
				if(count % 2 == 0){
					play2Pieces.remove(0);
				}
				else{
					play1Pieces.remove(0);
				}
			}
			lastButton = pressedButton;
			recent = 0;
		}
		else if(phase == 1){
		
		}
		         
	}

	public boolean getGameOver() {
		return this.gameOver;
	}
}
