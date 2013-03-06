package nmm.view.newGame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NewPVP extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4800315689039990773L;
	private NewGameScreen ngs;
	private JTextField p1Name;
	private JTextField p2Name;
	private JTextField p1Color;
	private JTextField p2Color;
	private JButton startBut;
	
	public NewPVP(NewGameScreen ngs) {
		this.ngs = ngs;
		
		p1Name = new JTextField();
		p2Name = new JTextField();
		p1Color = new JTextField();
		p2Color = new JTextField();
		startBut = new JButton();
		
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JLabel jLabel6 = new JLabel();

        jLabel1.setText("Player 1 Name");
        jLabel2.setText("Player 2 Name");
        jLabel3.setText("Player 1 Color");
        jLabel4.setText("Player 2 Color");
        startBut.setText("Start Game");

        jLabel5.setFont(new Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Enter the required Information");

        jLabel6.setFont(new Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("below to start a new PVP Game");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(p1Color, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(p1Name, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(p2Name, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(p2Color, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(95, Short.MAX_VALUE)
                    .addComponent(startBut)
                    .addGap(104, 104, 104))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(p1Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(p2Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(p1Color, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(p2Color, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 81)
                .addComponent(startBut)
                .addGap(33,33,33))
        );
        
		startBut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		start(evt);
        	}
        });
	}

	private void start(ActionEvent evt){
		this.ngs.startGame();
	}
	
	public String getP1Name() {
		return this.p1Name.getText();
	}
	public String getP1Color() {
		return this.p1Color.getText();
	}
	public String getP2Name() {
		return this.p2Name.getText();
	}
	public String getP2Color() {
		return this.p2Color.getText();
	}
}
