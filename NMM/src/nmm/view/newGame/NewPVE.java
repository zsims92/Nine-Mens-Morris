package nmm.view.newGame;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class NewPVE extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6048218193452708272L;
	private NewGameScreen ngs;
	private JTextField p1Name;
	private JTextField p1Color;
	private JButton startBut;

	public NewPVE(NewGameScreen ngs) {
		this.ngs = ngs;
		p1Name = new JTextField();
        p1Color = new JTextField();

        startBut = new JButton();
        this.setLayout();
        
        startBut.setText("Start Game");
        startBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                start(evt);
            }
        });
	}
	
	private void setLayout(){
        JLabel jLabel1 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel5 = new JLabel();
        jLabel1.setText("Player 1 Name");
        jLabel3.setText("Player 1 Color");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("<html>&nbsp;&nbsp;Enter the Information below<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;to start a new Game</html>");
		
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
	    layout.setHorizontalGroup(
	        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addGroup(layout.createSequentialGroup()
	                    .addGap(19, 19, 19)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)))
	                .addGroup(layout.createSequentialGroup()
	                    .addGap(93, 93, 93)
	                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                        .addComponent(jLabel3)
	                        .addComponent(p1Color, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
	                        .addComponent(jLabel1)
	                        .addComponent(p1Name, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
	                .addGroup(layout.createSequentialGroup()
	                    .addGap(81, 81, 81)
	                    .addComponent(startBut)))
	            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    );
	    layout.setVerticalGroup(
	        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	            .addContainerGap()
	            .addComponent(jLabel5)
	            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	            .addComponent(jLabel1)
	            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	            .addComponent(p1Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	            .addGap(18, 18, 18)
	            .addComponent(jLabel3)
	            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	            .addComponent(p1Color, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	            .addComponent(startBut)
	            .addContainerGap(27, Short.MAX_VALUE))
	    );
	}

	private void start(ActionEvent evt){
		if(this.getP1Name().length() < 1){
			JOptionPane.showMessageDialog(null, " Enter a valid name for Player 1\r\n (Must be at least 1 character)");
			return;
		}
		if(this.getP1Color().length() < 1){
			JOptionPane.showMessageDialog(null, "Enter a color for Player 1");
			return;
		}
		int check = checkColor(this.getP1Color());
		if(check == 1)
			return;
		this.ngs.startGame();
	}
	
	private int checkColor(String c) {
		Color color;
		try {
		    java.lang.reflect.Field field = Color.class.getField(c.toLowerCase());
		    color = (Color)field.get(null);
		} catch (Exception e) {
		    color = null; // Not defined
		}
		if(color == null){
			JOptionPane.showMessageDialog(null, c + " is not a valid color");
			return 1;
		}
		return 0;
	}

	public String getP1Name() {
		return this.p1Name.getText();
	}
	public String getP1Color() {
		return this.p1Color.getText();
	}

}
