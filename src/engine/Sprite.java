package src.engine;

import src.engine.Player;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics2D;

public class Sprite implements Comparable<Sprite>
{
    private BufferedImage image;
    private int width, height;

    private double x, y;

    private double distance;

    private double speed;


    private Engine engine;

    private Player player;

    public Sprite(Engine e, Player p, String path, double xPos, double yPos, double s)
    {
        engine = e;
        player = p;

        speed = s;

        x = xPos;
        y = yPos;

        try
        {
            image = ImageIO.read(new File(path));
            width = image.getWidth();
            height = image.getHeight();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

      //  height = image.getHeight();
      //  width = image.getWidth();

        calcSize();
    }


     private void calcSize()
     {
        distance = Math.sqrt(Math.pow(x - player.getX(), 2) + Math.pow(y - player.getY(), 2));

        height *= (int) 1/ distance;
        width *= (int) 1/distance;
    }
    public double getDistance()
    {
        return distance;
    }
    public int compareTo(Sprite s)
    {
        if(this.getDistance() > s.getDistance())
            return -1;
        else if(this.getDistance() == s.getDistance())
            return 0;
        else
            return 1;
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
}
