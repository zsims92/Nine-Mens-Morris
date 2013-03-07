package nmm.view.newGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

public class NewPVP extends JPanel{
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
        startBut.setText("Start Game");
        
        this.setLayout();
        
		startBut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		start(evt);
        	}
        });
	}
	
	private void setLayout(){
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();

        jLabel1.setText("Player 1 Name");
        jLabel2.setText("Player 2 Name");
        jLabel3.setText("Player 1 Color");
        jLabel4.setText("Player 2 Color");
		
        jLabel5.setFont(new Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("<html>&nbsp;&nbsp;Enter the Information below<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;to start a new Game</html>");
        jLabel5.setSize(200, 200);

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
            .addGroup(layout.createSequentialGroup()
            	.addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
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
		
	}

	private void start(ActionEvent evt){
		if(this.getP1Name().length() < 2){
			JOptionPane.showMessageDialog(null, "    Enter a valid name for Player 1\r\n (Must be longer than 1 character)");
			return;
		}
		if(this.getP2Name().length() < 2){
			JOptionPane.showMessageDialog(null, "    Enter a valid name for Player 2\r\n (Must be longer than 1 character)");
			return;
		}
		
		if(this.getP1Color().length() < 2){
			JOptionPane.showMessageDialog(null, "Enter a color for Player 1");
			return;
		}
		int check = checkColor(this.getP1Color());
		if(check == 1)
			return;
		
		if(this.getP2Color().length() < 2){
			JOptionPane.showMessageDialog(null, "Enter a color for Player 2");
			return;
		}
		check = checkColor(this.getP2Color());
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
	public String getP2Name() {
		return this.p2Name.getText();
	}
	public String getP2Color() {
		return this.p2Color.getText();
	}
}
