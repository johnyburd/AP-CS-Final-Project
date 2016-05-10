import java.util.ArrayList;
import java.awt.Color;
public class Raster
{
    public int[][] dungeon;
    public int dungeonWidth, dungeonHeight, width, height;
    public ArrayList textures;

    public Raster(int[][] d, int dH, int dW, int w, int h)
    {
        dungeonHeight = dH;
        dungeonWidth = dW;
        dungeon = d;
        width = w;
        height = h;
    }

    public int[] update(Player player, int[] pixels)
    {
        for(int n=0; n<pixels.length/2; n++) {
            if(pixels[n] != Color.DARK_GRAY.getRGB()) pixels[n] = Color.DARK_GRAY.getRGB();
        }
        for(int i=pixels.length/2; i<pixels.length; i++) {
            if(pixels[i] != Color.gray.getRGB()) pixels[i] = Color.gray.getRGB();
        }

        return pixels;
    }
}
