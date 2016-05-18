package src.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImageFilter;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;



public class TextBox
{
	public BufferedImage textbox;
	public Graphics graphics;
	public Graphics2D please;
	public static int x = 0;
	public int y = 195;
	public ArrayList<String> list = new ArrayList<String>();
	public boolean isChanged = false;
	//Max is 195 
	//Min is 15
	//Max x is 385
	
	public TextBox()
	{
		textbox = new BufferedImage(400, 200, BufferedImage.TYPE_INT_ARGB);
		graphics = textbox.getGraphics();

		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, 400, 200);
		graphics.setColor(Color.BLACK);
    		please = (Graphics2D) graphics;
		please.drawImage(textbox, null , 0,0 );
	}
	
	
	public void addMessage(String text)
	{
		if(text.length() > 45)
		{
			int z = 0;
			
			while(z < text.length())
			{
				
				if((z + 45) >= text.length())
				{
					list.add(text.substring(z,text.length()));
					z+=45;
				}
				else
				{
					int stop = text.lastIndexOf(" ", (z + 40));
					list.add(text.substring(z,stop +1));
					z+=(stop+1);
				}
			}
		}
		else
		{
			list.add(text);
		}
		
		while(list.size() > 13)
		{
			list.remove(0);
		}
		isChanged = true;
	}
  	
  	public BufferedImage getBufferedImage()
  	{
		if(isChanged)
  		{
  			graphics.setFont(new Font("Arial Black", Font.BOLD, 15));
		
			for(int n = list.size()-1; n >= 0; n--)
			{
				graphics.drawString(list.get(n), x, y);
				y-=15;
			}
			please.drawImage(textbox, null , 0,0 );
			isChanged = false;
			int width = textbox.getWidth();
        		int height = textbox.getHeight();

        		for (int i = 0; i < width; i++)
            		    for (int j = 0; j < height; j++)
                		if (textbox.getRGB(i, j) == Color.LIGHT_GRAY.getRGB())
                	           textbox.setRGB(i, j, 00000000);
  						
  			        return textbox;
  		}

        int width = textbox.getWidth();
        int height = textbox.getHeight();

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                if (textbox.getRGB(i, j) == Color.LIGHT_GRAY.getRGB())
                    textbox.setRGB(i, j, 00000000);
		
  		return textbox;
  	}
  
}
