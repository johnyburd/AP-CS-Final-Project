package src.engine;

import java.awt.image.BufferedImage;

public class BlitImage
{
    private BufferedImage buffImg;
    private int xPx, yPx;

    public BlitImage(BufferedImage bi, int x, int y)
    {
        buffImg = bi;
        xPx = x;
        yPx = y;

    }

    public int getHeight()
    {
        return buffImg.getHeight();
    }

    public int getWidth()
    {
        return buffImg.getWidth();
    }

    public int getX()
    {
        return xPx;
    }
    public int getY()
    {
        return yPx;
    }

    public BufferedImage getBuffImg()
    {
        return buffImg;
    }
}
