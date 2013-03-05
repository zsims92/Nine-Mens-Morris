package nmm.virtual.parts;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Settings extends JComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6598388904347414869L;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JButton start;

	public Settings(){
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        start = new JButton();

        jLabel1.setText("Insert Name Below");

        jLabel2.setText("Insert color choice below");
        
        start.setText("Start Game");
        
        
        start.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		startAction(evt);
        	}
        });
        
        makeLayout();
        this.setBackground(new Color(1.0f,1.0f,1.0f));
	}

	private void startAction(ActionEvent evt) {
		if(jTextField1.getText() == "" || jTextField2.getText() == ""){
			JOptionPane.showMessageDialog(null, "Invalid Move!");
			return;
		}
		
	}
	private void makeLayout(){
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField1, GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField2, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
		
	}

}
