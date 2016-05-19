import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.sound.sampled.*;

public class Sound
{//sounds
	public static void playPlayerHit()
	{
		try 
   	 	{
    		File file = new File("/res/sounds/playerhit.wav");
         
     	   	Clip clip = AudioSystem.getClip();
        	clip.open(AudioSystem.getAudioInputStream(file));
        	clip.start();
        
        	Thread.sleep(clip.getMicrosecondLength()/1000);
   		} 
    	catch(Exception ex) 
    	{
        	System.out.println("Error with playing sound.");
        	ex.printStackTrace();
   		}
	}
	
	public static void playWalking()
	{
		try 
   	 	{
    		File file2 = new File("/res/sounds/footsteps.wav");
         
     	   	Clip clip = AudioSystem.getClip();
        	clip.open(AudioSystem.getAudioInputStream(file2));
        	clip.start();
        
        	Thread.sleep(clip.getMicrosecondLength()/1000);
   		} 
    	catch(Exception ex) 
    	{
        	System.out.println("Error with playing sound.");
        	ex.printStackTrace();
   		}
	}
	
	public static void playEnemyGrunt()
	{
		try 
   	 	{
    		File file3 = new File("/res/sounds/enemygrunt.wav");
         
     	   	Clip clip = AudioSystem.getClip();
        	clip.open(AudioSystem.getAudioInputStream(file3));
        	clip.start();
        
        	Thread.sleep(clip.getMicrosecondLength()/1000);
   		} 
    	catch(Exception ex) 
    	{
        	System.out.println("Error with playing sound.");
        	ex.printStackTrace();
   		}
	}
	
	public static void playEnemyHit()
	{
		try 
   	 	{
    		File file4 = new File("/res/sounds/enemyhit.wav");
         
     	   	Clip clip = AudioSystem.getClip();
        	clip.open(AudioSystem.getAudioInputStream(file4));
        	clip.start();
        
        	Thread.sleep(clip.getMicrosecondLength()/1000);
   		} 
    	catch(Exception ex) 
    	{
        	System.out.println("Error with playing sound.");
        	ex.printStackTrace();
   		}
	}
	
	public static void playGameOver()
	{
		try 
   	 	{
    		File file5 = new File("/res/soundsgameover.wav");
         
     	   	Clip clip = AudioSystem.getClip();
        	clip.open(AudioSystem.getAudioInputStream(file5));
        	clip.start();
        
        	Thread.sleep(clip.getMicrosecondLength()/1000);
   		} 
    	catch(Exception ex) 
    	{
        	System.out.println("Error with playing sound.");
        	ex.printStackTrace();
   		}
	}
	
	public static void playGameWon()
	{
		try 
   	 	{
    		File file6 = new File("/res/sounds/gamewon.wav");
         
     	   	Clip clip = AudioSystem.getClip();
        	clip.open(AudioSystem.getAudioInputStream(file6));
        	clip.start();
        
        	Thread.sleep(clip.getMicrosecondLength()/1000);
   		} 
    	catch(Exception ex) 
    	{
        	System.out.println("Error with playing sound.");
        	ex.printStackTrace();
   		}
	}
	
	public static void playEnterSmileZone()
	{
		try 
   	 	{
    		File file7 = new File("/res/sounds/smilezoneenter.wav");
         
     	   	Clip clip = AudioSystem.getClip();
        	clip.open(AudioSystem.getAudioInputStream(file7));
        	clip.start();
        
        	Thread.sleep(clip.getMicrosecondLength()/1000);
   		} 
    	catch(Exception ex) 
    	{
        	System.out.println("Error with playing sound.");
        	ex.printStackTrace();
   		}
	}

}
