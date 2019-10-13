package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AAPFrame {
	
	private JFrame f;
	private IPlayerPlugin curPlugin=null;
	private JTextField ouTextField;
	private JTextField name;
	private AAplayer player;
	public AAPFrame() {}
	public AAPFrame(AAplayer pl) {
		player=pl;
	}
	public void Init() {
		
		
		f =new JFrame("AAPlayer");
		f.setLocation(40,40);
		f.setSize(600,500);
		f.setResizable(false);
		
		JPanel panel =new JPanel();
		f.add(panel);
		panel.setLayout(null);
		
		
		ouTextField =new JTextField();
		panel.add(ouTextField);
		ouTextField.setEditable(false);
		ouTextField.setBounds(10, 10, 200, 50);
		
		name =new JTextField();
		panel.add(name);
		name.setEditable(false);
		name.setBounds(220, 10, 200, 50);
		
		JButton button = new JButton("choose");
		panel.add(button);
		button.setBounds(430, 10, 80, 50);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jf.showOpenDialog(null);
				File f = jf.getSelectedFile();
				if(f!=null) {
					IPlayerPlugin p = player.PlayMusic(f);
					if(p!=null) {
						 curPlugin = p;
						 ouTextField.setText(f.getAbsolutePath());
						 name.setText(jf.getName());
					}
					else {
						name.setText( "no support");
					}
				}
			}
		});
		
		JButton playbutton =new JButton("play");
		panel.add(playbutton);
		playbutton.setBounds(10, 300, 80, 50);
		playbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(curPlugin!=null) {
					curPlugin.play();
					System.out.print("the music start.");
				}
				 
			}
		});
		JButton endbutton =new JButton("end");
		panel.add(endbutton);
		endbutton.setBounds(110, 300, 80, 50);
		endbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(curPlugin!=null) {
					curPlugin.end();
					System.out.print("the music end.");
				}
			}
		});
		
		JButton stopbutton =new JButton("stop");
		panel.add(stopbutton);
		stopbutton.setBounds(210, 300, 80, 50);
		stopbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(curPlugin!=null) {
					curPlugin.stop();
					System.out.print("the music stop.");
				}
			}
		});
		
		AboutBox a =new AboutBox();
		panel.add(a);
		a.setBounds(10,300,80,50);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
}
