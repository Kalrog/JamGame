package assets;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;

public class SoundPlayer
{

	public static void playSound(String path , int vol) throws Exception
	{
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
		//Clip clip = AudioSystem.getClip();
        Clip clip = (Clip)AudioSystem.getLine(info);
		clip.open(audioInputStream);
        FloatControl volume  = (FloatControl) clip.getControl(FloatControl.Type.VOLUME);
        volume.setValue(vol);
        clip.start();
		/*InputStream in = new FileInputStream(path);
		AudioStream audio = new AudioStream(in);

		AudioPlayer.player.start(audio);*/
		
	}
}
