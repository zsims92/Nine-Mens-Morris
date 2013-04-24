package nmm.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class WelcomeScreen extends JComponent{
	/**
	 * Welcome screen for
	 * nine-mens-morris
	 */
	private static final long serialVersionUID = 5219597153026471980L;
	private MainWindow mw;
	private JLabel welcome;
	private JButton pve;
	private JButton pvp;
	private JPanel top;
	private JPanel bot;
	
	/***
	 * Constructor for the welcome
	 * screen
	 * 
	 * Lets the player decide what type of game to play
	 * @param mw
	 */
	public WelcomeScreen(MainWindow mw){
		this.mw = mw;

        welcome = new JLabel();
        pve = new JButton();
        pvp = new JButton();

        welcome.setFont(new Font("Traditional Arabic", 1, 34));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setText("Nine Men's Morris");



        top = new JPanel();
        welcome = new JLabel();
        bot = new JPanel();
        pve = new JButton();
        pvp = new JButton();

        setLayout(new GridLayout(2, 2));

        top.setLayout(new BorderLayout());

        welcome.setFont(new Font("Tahoma", 0, 80)); // NOI18N
        welcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcome.setText("Nine Men's Morris");
        top.add(welcome, BorderLayout.CENTER);

        add(top);

        bot.setLayout(new GridLayout());

        pve.setFont(new Font("Traditional Arabic", 0, 35)); // NOI18N
        pve.setText("Plaver VS Computer");
        bot.add(pve);

        pvp.setFont(new Font("Traditional Arabic", 0, 35)); // NOI18N
        pvp.setText("Player VS Player");

        pve.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		changeWindow(evt);
        	}
        });
		pvp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		changeWindow(evt);
        	}
        });
		
        bot.add(pvp);
        //this.pve.setEnabled(false);

        add(bot);
	}
	
	/**
	 * Will start a new game depending
	 * on the button that was clicked
	 * @param evt
	 */
	private void changeWindow(ActionEvent evt) {
		if((JButton)evt.getSource() == this.pve)
			this.mw.newGame(0);
		else if((JButton)evt.getSource() == this.pvp)
			this.mw.newGame(1);
	}
}
