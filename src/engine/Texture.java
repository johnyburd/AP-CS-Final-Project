import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Texture
{
    private int[] pixels;
    private String path;
    public final int SIZE;

    public static ArrayList<Texture> textures;
    textures.add(new Texture("../../res/wood.jpg", 64));
    textures.add(new Texture("../../res/redbrick.jpg", 64));
    textures.add(new Texture("../../res/bluestone.jpg", 64));
    textures.add(new Texture("../../res/greystone.jpg", 64));

    public Texture(String pa, int s)
    {
        SIZE = s;
        pixels = new int[SIZE * SIZE];
        path = pa;
        readImg();
    }

    private void readImg()
    {
        try
        {
            BufferedImage buffImg = ImageIO.read(new File(path));
            int width = buffImg.getWidth();
            int height = buffImg.getHeight();
            buffImg.getRGB(0, 0, width, height, pixels, 0, width);
        }
        catch (IOException e) // happens when the file cannot be found
        {
            e.printStackTrace();
        }
    }
}

