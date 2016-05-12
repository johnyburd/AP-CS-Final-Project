import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Soundeffecttest
{
	public static void main(String[] args) 
		throws Exception
		{
					//System.out.println("Sound play");
					URL url = new URL(
					"file:/C:/Users/jamedgreen/Google Drive/APCOMPSCI 2016/AP Comp Game/freemusic.wav");
					
					AudioClip clip = Applet.newAudioClip(url);
					
					clip.play();
					
					Thread.sleep(1000);
					System.out.println("end");
		}
}
