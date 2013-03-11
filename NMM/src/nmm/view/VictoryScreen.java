package nmm.view;

import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;

import nmm.model.user.Player;

public class VictoryScreen extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4655939260856856256L;
	private JLabel p1Name;
    private JLabel p2Name;
    private JButton pAgainBut;
    private JButton quitBut;
    private JLabel sorryText;
    private JLabel winnerLabel;
    private MainWindow mw;
    
	public VictoryScreen(MainWindow mw, Player victor, Player loser) {
		p1Name = new JLabel();
        winnerLabel = new JLabel();
        sorryText = new JLabel();
        p2Name = new JLabel();
        pAgainBut = new JButton();
        quitBut = new JButton();
        this.mw = mw;

        p1Name.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        p1Name.setText(victor.getName());

        winnerLabel.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        winnerLabel.setText("is the Winner!");

        sorryText.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        sorryText.setText("Sorry");

        p2Name.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        p2Name.setText(loser.getName() + ".");

        pAgainBut.setText("Play Again?");

        quitBut.setText("Quit");
        pAgainBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGame(evt);
            }
        });
        quitBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                end(evt);
            }
        });
        
        this.setLayout();
	}

	private void end(ActionEvent evt) {
		this.mw.quit(evt);		
	}

	private void newGame(ActionEvent evt) {
		this.mw.reset(evt);
		
	}

	private void setLayout(){
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(p1Name)
                        .addGap(10, 10, 10)
                        .addComponent(winnerLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(pAgainBut)
                        .addGap(18, 18, 18)
                        .addComponent(quitBut, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(sorryText)
                        .addGap(10, 10, 10)
                        .addComponent(p2Name)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(p1Name)
                    .addComponent(winnerLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(sorryText)
                    .addComponent(p2Name))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(pAgainBut)
                    .addComponent(quitBut)))
        );
	}

}
