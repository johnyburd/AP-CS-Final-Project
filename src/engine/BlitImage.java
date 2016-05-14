package src.engine;

import java.awt.image.BufferedImage;

public class BlitImage
{
    private BufferedImage buffImg;
    private int xPx, yPx;
    private int height, width;

    public BlitImage(BufferedImage bi, int x, int y)
    {
        buffImg = bi;
        xPx = x;
        yPx = y;
        height = buffImg.getHeight();
        width = buffImg.getWidth();

    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
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
