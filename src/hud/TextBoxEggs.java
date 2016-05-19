package src.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImageFilter;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;



public class TextBoxEggs
{
	public BufferedImage textboxEggs;
	public Graphics graphics;
	public Graphics2D please;
	public static int x = 0;
	public int y = 195;
	public ArrayList<String> list = new ArrayList<String>();
	public boolean isChanged = false;
	public int eggs = 0;
	public String eggsGot = ""
	//Max is 195 
	//Min is 15
	//Max x is 385
	
	public TextBoxEggs()
	{
		textbox = new BufferedImage(400, 200, BufferedImage.TYPE_INT_ARGB);
		graphics = textboxEggs.getGraphics();

		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, 400, 200);
		graphics.setColor(Color.BLACK);
    		please = (Graphics2D) graphics;
		please.drawImage(textboxEggs, null , 0,0 );
	}
	
	
	public void addEgg(String text)
	{
	  eggs++;
	  eggsGot = eggs + "";
		isChanged = true;
	}
  	
  	public BufferedImage getBufferedImage()
  	{
		if(isChanged)
  		{
  			graphics.setFont(new Font("Arial Black", Font.BOLD, 15));
				graphics.drawString(eggsGot, x, y);
			please.drawImage(textboxEggs, null , 0,0 );
			isChanged = false;
			int width = textboxEggs.getWidth();
        		int height = textboxEggs.getHeight();

        		for (int i = 0; i < width; i++)
            		    for (int j = 0; j < height; j++)
                		if (textboxEggs.getRGB(i, j) == Color.LIGHT_GRAY.getRGB())
                	           textboxEggs.setRGB(i, j, 00000000);
  						
  			        return textboxEggs;
  		}

        int width = textboxEggs.getWidth();
        int height = textboxEggs.getHeight();

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                if (textboxEggs.getRGB(i, j) == Color.LIGHT_GRAY.getRGB())
                    textboxEggs.setRGB(i, j, 00000000);
		
  		return textboxEggs;
  	}
  
}
