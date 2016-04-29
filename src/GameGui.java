

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;


public class GameGui extends JFrame{
	JLabel lblMessage = null;
	JPanel pnlButtons;
	public GameGui(JButton btn1,JButton btn2,JButton btn3){
		setSize(500,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JLabel lblHeader = new JLabel("Select how many stone you'll pick :");
		
			JButton b1 = btn1;
			JButton b2 = btn2;
			JButton b3 = btn3;
			pnlButtons = new JPanel();
			pnlButtons.setLayout(new FlowLayout());
			
			lblMessage = new JLabel();
			
			pnlButtons.add(b1);
			pnlButtons.add(b2);
			pnlButtons.add(b3);
			add(pnlButtons,BorderLayout.CENTER);
			add(lblMessage,BorderLayout.SOUTH);
		
		setVisible(true);
	}
	public void setMessage(String msg){
		lblMessage.setText(msg);
		this.setVisible(false);
		this.setVisible(true);
	}
}
