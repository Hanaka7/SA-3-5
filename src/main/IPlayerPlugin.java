package main;
import java.io.File;
public interface IPlayerPlugin {
	void musicin(File music);
	void play();
	void stop();
	void end();
	String type();
}
