package plugin;

import java.io.File;

import javax.swing.JOptionPane;

import main.IPlayerPlugin;

public class MP3Player implements IPlayerPlugin {
	public void musicin(File music) {
		
	}
	public void play() {
		JOptionPane.showMessageDialog(null, "play mp3");
	}
	public void stop() {
		JOptionPane.showMessageDialog(null, "stop mp3");
	}
	public void end() {
		JOptionPane.showMessageDialog(null, "end mp3");
	}
	public String type() {
		return ".mp3";
	}
}
