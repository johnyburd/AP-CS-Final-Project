package src.engine;

import java.awt.Color;
public class Raycaster
{
    public int[][] dungeon;
    public int dungeonWidth, dungeonHeight, width, height;

    public Raycaster(int[][] d, int dH, int dW, int w, int h)
    {
        dungeonHeight = dH;
        dungeonWidth = dW;
        dungeon = d;
        width = w;
        height = h;
    }

    private void clearScreen(int[] pixels)
    {
        // Creates the floor and ceiling by filling the top and bottom half of the screen
        // also clears the screen
        for(int n=0; n<pixels.length/2; n++)
            if(pixels[n] != Color.DARK_GRAY.getRGB())
                pixels[n] = Color.DARK_GRAY.getRGB();

        for(int i=pixels.length/2; i<pixels.length; i++)
            if(pixels[i] != Color.gray.getRGB())
                pixels[i] = Color.gray.getRGB();
    }

    public void refresh(int[] pixels, Player p)
    {

        clearScreen(pixels);


    }
}
