package src.engine;

import src.engine.Sprite;
import src.engine.Engine;
import src.engine.Player;
import src.engine.Ray;
import src.engine.Keyboard;

import java.util.ArrayList;
import java.awt.Color;

public class Raycaster
{
    public int[][] map;
    public int dungeonWidth, dungeonHeight;

    Player player;


    public Raycaster(int[][] d, int dH, int dW, Engine engine, Player p)
    {
        dungeonHeight = dH;
        dungeonWidth = dW;
        map = d;
        player = p;
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

    public void refresh(int[] pixels)
    {
        clearScreen(pixels);

       // for(int i = 2; i < pixels.length; i+=Engine.SCREEN_WIDTH)
         //   if (i/Engine.SCREEN_WIDTH > 30 && i/Engine.SCREEN_WIDTH < 400)
           //   pixels[i] = Color.black.getRGB();
        for(int x=0; x<Engine.SCREEN_WIDTH; x=x+1) {
            boolean hit = false;
            int side=0;//was the wall vertical or horizontal

            Ray ray = new Ray(player, x);
            //Loop to find where the ray hits a wall
            while(!hit) {

                //Jump to next square
                if (ray.sideDistX < ray.sideDistY)
                {
                    ray.sideDistX += ray.deltaDistX;
                    ray.playerX += ray.stepX;
                    side = 0;
                }
                else
                {
                    ray.sideDistY += ray.deltaDistY;
                    ray.playerY += ray.stepY;
                    side = 1;
                }
                //Check if ray has hit a wall
                //System.out.println(mapX + ", " + mapY + ", " + map[mapX][mapY]);
                if(map[ray.playerX][ray.playerY] > 0)
                    hit = true;
            }{
                //Calculate distance to the point of impact
            if(side==0)
                ray.perpWallDist = Math.abs((ray.playerX - player.getX() + (1 - ray.stepX) / 2) / ray.xComp);
            else
                ray.perpWallDist = Math.abs((ray.playerY - player.getY() + (1 - ray.stepY) / 2) / ray.yComp);
            //Now calculate the height of the wall based on the distance from the camera
            int lineHeight;
            if(ray.perpWallDist > 0) lineHeight = Math.abs((int)(Engine.SCREEN_HEIGHT / ray.perpWallDist));
            else lineHeight = Engine.SCREEN_HEIGHT;
            //calculate lowest and highest pixel to fill in current stripe
            int drawStart = -lineHeight/2+ Engine.SCREEN_HEIGHT/2;
            if(drawStart < 0)
                drawStart = 0;
            int drawEnd = lineHeight/2 + Engine.SCREEN_HEIGHT/2;
            if(drawEnd >= Engine.SCREEN_HEIGHT)
                drawEnd = Engine.SCREEN_HEIGHT - 1;
            //add a texture
            int texNum = map[ray.playerX][ray.playerY] - 1;
            double wallX;//Exact position of where wall was hit
            if(side==1) {//If its a y-axis wall
                wallX = (player.getX() + ((ray.playerY - player.getY() + (1 - ray.stepY) / 2) / ray.yComp) * ray.xComp);
            } else {//X-axis wall
                wallX = (player.getY() + ((ray.playerX - player.getX() + (1 - ray.stepX) / 2) / ray.xComp) * ray.yComp);
            }
            wallX-=Math.floor(wallX);
            //x coordinate on the texture
            int texX = (int)(wallX * (Texture.textures.get(texNum).getSize()));
            if(side == 0 && ray.xComp > 0) texX = Texture.textures.get(texNum).getSize() - texX - 1;
            if(side == 1 && ray.yComp < 0) texX = Texture.textures.get(texNum).getSize() - texX - 1;
            //calculate y coordinate on texture
            for(int y=drawStart; y<drawEnd; y++) {
                    
                int texY = (((y*2 - Engine.SCREEN_HEIGHT + lineHeight) << 6) / lineHeight) / 2;
                int color;
                if(side==0) color = Texture.textures.get(texNum).pixels[texX + (texY * Texture.textures.get(texNum).getSize())];
                else color = (Texture.textures.get(texNum).pixels[texX + (texY * Texture.textures.get(texNum).getSize())]>>1) & 8355711;//Make y sides darker
                pixels[x + y*(Engine.SCREEN_WIDTH)] = color;
            }
           }
        }


    }
}

