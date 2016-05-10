import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Texture
{
    private int[] pixels;
    private String path;
    public final int SIZE;

    public Texture(String pa, int s)
    {
        path = pa;
        SIZE = s;
        pixels = new int[SIZE * SIZE];
        readImg();
    }

    private void readImg()
    {
        try
        {
            BufferedImage buffImg = ImageIO.read(new File(path));
            int width = image.getWidth();
            int height = image.getHeight();
            buffImg.getRGB(0, 0, width, height, pixels, 0, width);
        }
        catch (IOException e) // happens when the file cannot be found
        {
            e.printStackTrace();
        }
    }
}

