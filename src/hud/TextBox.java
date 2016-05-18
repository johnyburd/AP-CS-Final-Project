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
	public int y = 15;
	public ArrayList<String> list = new ArrayList<String>();
	//Max is 195 
	//Min is 15
	//Max x is 385
	
	public TextBox()
	{
		textbox = new BufferedImage(400, 200, BufferedImage.TYPE_INT_RGB);
		graphics = textbox.getGraphics();

		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, 400, 200);
		graphics.setColor(Color.BLACK);
    		please = (Graphics2D) graphics;
		please.drawImage(textbox, null , 0,0 );
	}
	
	
	public void addMessage(String text)
	{
		if(text.length() > 30)
		{
			int z = 0;
			for(int i = 0; i < text.length()/30.0; i++)
			{
				if((z + 30) > text.length())
				{
					list.add(text.substring(z,text.length()));
				}
				else
				{
					list.add(text.substring(z,z+30));
					z+=30;
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
	}
  	
  	public BufferedImage getBufferedImage()
  	{
  		graphics.setFont(new Font("Arial Black", Font.BOLD, 15));
		
		for(int n = 0; n < list.size(); n++)
		{
			graphics.drawString(list.get(n), x, y);
			y+=15;
		}
		please.drawImage(textbox, null , 0,0 );
		
  		return textbox;
  	}
  
}
