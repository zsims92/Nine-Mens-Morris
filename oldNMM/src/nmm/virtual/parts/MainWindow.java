package nmm.virtual.parts;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class MainWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4887557454377614021L;
	private GameBoard gb;
	private Settings set;
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
	
	private Integer windShown;
	//Window Shown status, 1 = settings, 2 = gameboard;
	
	public MainWindow(String s1, String s2){
		gb = new GameBoard(s1, s2);
		set = new Settings();
	}
	
	public void init(){
		
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
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jMenu1.setText("File");
        jMenu2.setText("Edit");
        jMenu3.setText("Help");

        jMenuItem1.setText("New Game");
        jMenuItem2.setText("Cheat Mode");
        jMenuItem3.setText("Quit");
        jMenuItem4.setText("Undo");
        jMenuItem5.setText("How to Play");
        jMenuItem6.setText("About");
       
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
        
        jMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameAction(evt);
            }
        });
        
        this.pack();
        this.setBackground(new Color(1.0f,1.0f,1.0f));
	}
	
    private void  newGameAction(ActionEvent a){
    	
    }
	
    public void showGameBoard(){
    	this.remove(this.set);
    	this.add(this.gb);
    	this.pack();
    	this.setVisible(true);
		this.setAlwaysOnTop(true);
    }
    
    public void showSettings(){
    	this.remove(this.gb);
    	this.add(this.set);
    	this.pack();
    	this.setVisible(true);
		this.setAlwaysOnTop(true);
    }

	public void run() {
		this.windShown = 1;
		this.showSettings();
		this.setVisible(true);
		this.setAlwaysOnTop(true);	
	}
	
	public Integer getWindShown(){
		return this.windShown;
	}
	
	public void setWindShown(Integer window){
		this.windShown = window;
	}
	
	public void endGame(){
		MainWindow.this.processWindowEvent(
				new WindowEvent(
						MainWindow.this, WindowEvent.WINDOW_CLOSED)
				);
		System.exit(0);
	}

	public GameBoard getGb() {
		return gb;
	}

	public Settings getSet() {
		return set;
	}

}
