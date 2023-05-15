package Main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/death.wav");
		soundURL[1] = getClass().getResource("/sound/shot.wav");
		soundURL[2] = getClass().getResource("/sound/hit.wav");
		soundURL[3] = getClass().getResource("/sound/win.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}
		catch(Exception e) {
		}		
	}
	public void play() {
		clip.start();
	}
	public void stop() {
		clip.stop();
	}
}
