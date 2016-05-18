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
    public int[] pixels;

    private double x, y;

    private double distance;

    private double speed;

    public static ArrayList<Sprite> sprites = new ArrayList<Sprite>();



    private Player player;

    public Sprite(Player p, String path, double xPos, double yPos, double s)
    {
        player = p;

        speed = s;

        x = xPos;
        y = yPos;

        try
        {
            image = ImageIO.read(new File(path));
            width = image.getWidth();
            height = image.getHeight();

            pixels = new int[width * height];

            // copies the texture to an array of rgb values
            image.getRGB(0, 0, width, height, pixels, 0, width);
            width /= 2;
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }


     private void calcDist()
     {
        // no need for sqrt because this is just relative
        distance = Math.pow(x - player.getX(), 2) + Math.pow(y - player.getY(), 2);
    }
    public double getDistance()
    {
        return distance;
    }
    public int compareTo(Sprite s)
    {
        // update distances in the likely event the player has moved
        calcDist();
        s.calcDist();
        if(this.getDistance() > s.getDistance())
            return -1;
        else if(this.getDistance() == s.getDistance())
            return 0;
        else
            return 1;
    }
    public BufferedImage getTex()
    {
        return image;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
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
