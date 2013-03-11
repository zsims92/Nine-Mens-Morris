package nmm.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WelcomeScreen extends JComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5219597153026471980L;
	private MainWindow mw;
	private JLabel welcome;
	private JButton pve;
	private JButton pvp;
	
	public WelcomeScreen(MainWindow mw){
		this.mw = mw;

        welcome = new JLabel();
        pve = new JButton();
        pvp = new JButton();

        welcome.setFont(new Font("Traditional Arabic", 1, 34));
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.setText("Nine Men's Morris");

        pve.setText("Plaver VS Computer");
        pve.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		changeWindow(evt);
        	}
        });
        
        pvp.setText("Player VS Player");
		pvp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		changeWindow(evt);
        	}
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pve)
                        .addGap(76, 76, 76)
                        .addComponent(pvp, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                    .addComponent(welcome, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(welcome, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(pve, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                    .addComponent(pvp, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        
		this.setSize(100,100);
		this.setVisible(true);
	}
	
	private void changeWindow(ActionEvent evt) {
		if((JButton)evt.getSource() == this.pve)
			this.mw.newGame(0);
		else if((JButton)evt.getSource() == this.pvp)
			this.mw.newGame(1);
	}
}
