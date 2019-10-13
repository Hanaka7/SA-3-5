package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class AboutBox extends JButton{
	public AboutBox() {
		this.setText("about");
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "music player", "about me", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
}
