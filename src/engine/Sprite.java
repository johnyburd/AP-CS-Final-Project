package src.engine;

import src.engine.Player;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics2D;

public class Sprite
{
    private BufferedImage image;
    private int width, height;

    private double x, y;

    private double speed;

    private double size;

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

        height = image.getHeight();
        width = image.getWidth();
    }

    public void drawSprite(int xPx, int yPx)
    {
        size = calcSize();

        BufferedImage scaledBufferedImage = resizeImage(image, (int)(width * size), (int)(height * size));

        engine.blitImage(scaledBufferedImage, xPx, yPx);
    }

    public  BufferedImage resizeImage(BufferedImage img, int w, int h) {
        int type=0;
        type = img.getType() == 0? BufferedImage.TYPE_INT_ARGB : img.getType();
        BufferedImage resizedImage = new BufferedImage(width, height,type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(img, 0, 0, w, h, null);
        g.dispose();
        return resizedImage;
     }

     private double calcSize()
     {
        double distance = Math.sqrt(Math.pow(x - player.getX(), 2) + Math.pow(y - player.getY(), 2));

        return 1 / distance;
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
