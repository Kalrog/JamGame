package assets;

import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class SoundPlayer
{

	public static void playSound(String path) throws Exception
	{

		InputStream in = new FileInputStream(path);
		AudioStream audio = new AudioStream(in);
		
		AudioPlayer.player.start(audio);
		
	}
}
