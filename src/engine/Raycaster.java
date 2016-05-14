package src.engine;

import src.engine.Sprite;
import src.engine.Engine;
import src.engine.Player;
import src.engine.Keyboard;

import java.util.ArrayList;
import java.awt.Color;

public class Raycaster
{
    public int[][] map;
    public int dungeonWidth, dungeonHeight, width, height;

    private ArrayList<Sprite> sprites;
    private ArrayList<Integer> spritesAllowedX;
    private ArrayList<Integer> spritesAllowedY;

    public Raycaster(int[][] d, int dH, int dW, int w, int h, Engine engine, Player p)
    {
        dungeonHeight = dH;
        dungeonWidth = dW;
        map = d;
        width = w;
        height = h;

        sprites = new ArrayList<Sprite>();
        spritesAllowedX = new ArrayList<Integer>();
        spritesAllowedY = new ArrayList<Integer>();

        sprites.add(new Sprite(engine, p, "res/baddie.png", 10, 7, 1.0));
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

    public int[] refresh(int[] pixels, Player camera)
    {
        clearScreen(pixels);

       // for(int i = 2; i < pixels.length; i+=Engine.SCREEN_WIDTH)
         //   if (i/Engine.SCREEN_WIDTH > 30 && i/Engine.SCREEN_WIDTH < 400)
           //   pixels[i] = Color.black.getRGB();


       double xPos = 3;
       double yPos = 3;
       double xf = 1;
       double yf = 0;
       double xv = 0;
       double yv = -0.66;

       double test1 = 0;
       double test2 = 0;
       double testy = 0;

      // System.out.println(camera.getX());
      // System.out.println(camera.camera.getY());

        for(int x=0; x<width; x=x+1) {
            double cameraX = 2 * x / (double)(width) -1;
            double rayDirX = camera.getXFacing() + camera.getXView() * cameraX;
            double rayDirY = camera.getYFacing() + camera.getYView() * cameraX;
            //Map position
            int mapX = (int)camera.getX();
            int mapY = (int)camera.getY();
            //length of ray from current position to next x or y-side
            double sideDistX;
            double sideDistY;
            //Length of ray from one side to next in map
            double deltaDistX = Math.sqrt(1 + (rayDirY*rayDirY) / (rayDirX*rayDirX));
            double deltaDistY = Math.sqrt(1 + (rayDirX*rayDirX) / (rayDirY*rayDirY));
            double perpWallDist;
            //Direction to go in x and y
            int stepX, stepY;
            int hit = 0;
            int side=0;//was the wall vertical or horizontal
            //Figure out the step direction and initial distance to a side
            if (rayDirX < 0)
            {
                stepX = -1;
                sideDistX = (camera.getX() - mapX) * deltaDistX;
            }
            else
            {
                stepX = 1;
                sideDistX = (mapX + 1.0 - camera.getX()) * deltaDistX;
            }
            if (rayDirY < 0)
            {
                stepY = -1;
                sideDistY = (camera.getY() - mapY) * deltaDistY;
            }
            else
            {
                stepY = 1;
                sideDistY = (mapY + 1.0 - camera.getY()) * deltaDistY;
            }
            //Loop to find where the ray hits a wall
            while(hit == 0 || hit == 2) {

                if (hit == 2 && noDup(mapX, mapY))
                {
                    if (test1 == 0)
                        test1 = x;
                    spritesAllowedX.add(mapX);
                    spritesAllowedY.add(mapY);
                    test2 = x;
                    hit = 0;
                }
                //Jump to next square
                if (sideDistX < sideDistY)
                {
                    sideDistX += deltaDistX;
                    mapX += stepX;
                    side = 0;
                }
                else
                {
                
                    sideDistY += deltaDistY;
                    mapY += stepY;
                    side = 1;
                }
                //Check if ray has hit a wall
                //System.out.println(mapX + ", " + mapY + ", " + map[mapX][mapY]);
                if(map[mapX][mapY] > 0 && map[mapX][mapY] < 11) hit = 1;
                else if (map[mapX][mapY] > 10) hit = 2;
            }
            if (hit == 1)
            {
                //Calculate distance to the point of impact
                if(side==0)
                    perpWallDist = Math.abs((mapX - camera.getX() + (1 - stepX) / 2) / rayDirX);
                else
                    perpWallDist = Math.abs((mapY - camera.getY() + (1 - stepY) / 2) / rayDirY);  
                //Now calculate the height of the wall based on the distance from the camera
                int lineHeight;
                if(perpWallDist > 0) lineHeight = Math.abs((int)(height / perpWallDist));
                else lineHeight = height;
                //calculate lowest and highest pixel to fill in current stripe
                int drawStart = -lineHeight/2+ height/2;
                if(drawStart < 0)
                    drawStart = 0;
                int drawEnd = lineHeight/2 + height/2;
                if(drawEnd >= height) 
                    drawEnd = height - 1;
                //add a texture
                int texNum = map[mapX][mapY] - 1;
                double wallX;//Exact position of where wall was hit
                if(side==1) {//If its a y-axis wall
                    wallX = (camera.getX() + ((mapY - camera.getY() + (1 - stepY) / 2) / rayDirY) * rayDirX);
                } else {//X-axis wall
                    wallX = (camera.getY() + ((mapX - camera.getX() + (1 - stepX) / 2) / rayDirX) * rayDirY);
                }
                wallX-=Math.floor(wallX);
                //x coordinate on the texture
                int texX = (int)(wallX * (Texture.textures.get(texNum).getSize()));
                if(side == 0 && rayDirX > 0) texX = Texture.textures.get(texNum).getSize() - texX - 1;
                if(side == 1 && rayDirY < 0) texX = Texture.textures.get(texNum).getSize() - texX - 1;
                //calculate y coordinate on texture
                testy = (int)((drawStart + drawEnd)/2.0);
                for(int y=drawStart; y<drawEnd; y++) {
                    
                    int texY = (((y*2 - height + lineHeight) << 6) / lineHeight) / 2;
                    int color;
                    if(side==0) color = Texture.textures.get(texNum).pixels[texX + (texY * Texture.textures.get(texNum).getSize())];
                    else color = (Texture.textures.get(texNum).pixels[texX + (texY * Texture.textures.get(texNum).getSize())]>>1) & 8355711;//Make y sides darker
                    pixels[x + y*(width)] = color;
                }
            }
            else if (hit == 2)
            {
                //baddie.drawSprite(100,100);
            }
        }
        
        while (spritesAllowedX.size() > 0)
        {
            for (Sprite s : sprites)
                if ((int)s.getX() == spritesAllowedX.get(0))
                    if ((int)s.getY() == spritesAllowedY.get(0))
                    {
                        s.drawSprite((int)((test1+test2)/2), (int)testy);
                    }
            spritesAllowedX.remove(0);
            spritesAllowedY.remove(0);
        }


        return pixels;
    }

    private boolean noDup(int x, int y)
    {
        for (int i = 0; i < spritesAllowedX.size(); i++)
            if (spritesAllowedX.get(i) == x && spritesAllowedY.get(i) == y)
                return false;
        return true;
    }
}

