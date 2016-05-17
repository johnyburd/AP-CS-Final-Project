import java.applet.Applet;
import java.applet.AudioClip;
public class Sound 
{
    public static synchronized void play(final String fileName)
        new Thread(new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
                    clip.open(inputStream);
                    clip.start(); 
                }
                catch (Exception e) 
                {
                    System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
                }
            }
        }
        ).start();
    }
}
