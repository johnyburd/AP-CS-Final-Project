package src.engine;

import src.engine.Sprite;
import src.engine.Engine;
import src.engine.Player;
import src.engine.Ray;
import src.engine.Keyboard;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Color;

public class Raycaster
{
    public int[][] map;
    public int dungeonWidth, dungeonHeight;

    Player player;

    private double[] distances;

    private Engine engine;

    public Raycaster(int[][] d, int dH, int dW, Engine eng, Player p)
    {
        dungeonHeight = dH;
        dungeonWidth = dW;
        map = d;
        player = p;

        engine = eng;

        distances = new double[Engine.SCREEN_WIDTH];


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
        for(int x = 0; x < Engine.SCREEN_WIDTH; x++)
        {
            boolean hitWall = false;
            // vertical or horz wall
            int side=0;

            Ray ray = new Ray(player, x);

            // check for wall at every grid border
            while(!hitWall)
            {
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

                if(map[ray.playerX][ray.playerY] > 0 && map[ray.playerX][ray.playerY] < 15)
                    hitWall = true;
            }{
                // calculate distance
            if(side==0)
                ray.perpWallDist = Math.abs((ray.playerX - player.getX() + (1 - ray.stepX) / 2) / ray.xComp);
            else
                ray.perpWallDist = Math.abs((ray.playerY - player.getY() + (1 - ray.stepY) / 2) / ray.yComp);

            // calc height based on distance
            int lineHeight;
            if(ray.perpWallDist > 0)
                lineHeight = Math.abs((int)(Engine.SCREEN_HEIGHT / ray.perpWallDist));
            else
                lineHeight = Engine.SCREEN_HEIGHT;

            // record distances to compare to sprites below
            distances[x] = ray.perpWallDist;


            int drawStart = -lineHeight/2+ Engine.SCREEN_HEIGHT/2;
            if(drawStart < 0)
                drawStart = 0;
            int drawEnd = lineHeight/2 + Engine.SCREEN_HEIGHT/2;
            if(drawEnd >= Engine.SCREEN_HEIGHT)
                drawEnd = Engine.SCREEN_HEIGHT - 1;

            // calc textures
            int texNum = map[ray.playerX][ray.playerY] - 1;

            // position on the wall where the ray hit
            double wallX;

            if(side==1) {  // y-axis
                wallX = (player.getX() + ((ray.playerY - player.getY() + (1 - ray.stepY) / 2) / ray.yComp) * ray.xComp);
            } else {    // x-axis
                wallX = (player.getY() + ((ray.playerX - player.getX() + (1 - ray.stepX) / 2) / ray.xComp) * ray.yComp);
            }
            wallX-=Math.floor(wallX);

            int texX = (int)(wallX * (Texture.textures.get(texNum).getSize()));
            if(side == 0 && ray.xComp > 0)
                texX = Texture.textures.get(texNum).getSize() - texX - 1;
            if(side == 1 && ray.yComp < 0)
                texX = Texture.textures.get(texNum).getSize() - texX - 1;

            for(int y = drawStart; y < drawEnd; y++)
            {

                int texY = (((y * 2 - Engine.SCREEN_HEIGHT + lineHeight) << 6) / lineHeight) / 2;
                int color;
                if(side==0)
                    color = Texture.textures.get(texNum).pixels[texX + (texY * Texture.textures.get(texNum).getSize())];
                else
                    color = (Texture.textures.get(texNum).pixels[texX + (texY * Texture.textures.get(texNum).getSize())]>>1) & 8355711;//Make y sides darker
                pixels[x + y*(Engine.SCREEN_WIDTH)] = color;
            }
        }

        }

        // puts them in order furthest to closest so they render in the right order
        Collections.sort(Sprite.sprites);

        for (Sprite s : Sprite.sprites) // loop through sorted sprites
        {

            double spriteX = s.getX() - player.getX();
            double spriteY = s.getY() - player.getY();

            double invDet = 1.0 / (player.getXCameraPlane() * player.getYDir() - player.getXDir() * player.getYCameraPlane()); //required for correct matrix multiplication
            double transformX = invDet * (player.getYDir() * spriteX - player.getXDir() * spriteY);
            double transformY = invDet * (-player.getYCameraPlane() * spriteX + player.getXCameraPlane() * spriteY);

            int spriteScreenX = (int)((Engine.SCREEN_WIDTH / 2) * (1 + transformX / transformY));

            int spriteHeight = Math.abs((int)(Engine.SCREEN_HEIGHT / (transformY)));
            //using "transformY" instead of the real distance prevents fisheye

            int drawStartY = -spriteHeight / 2 + Engine.SCREEN_HEIGHT / 2;
            if(drawStartY < 0)
                drawStartY = 0;
            int drawEndY = spriteHeight / 2 + Engine.SCREEN_HEIGHT / 2;
            if(drawEndY >= Engine.SCREEN_HEIGHT)
                drawEndY = Engine.SCREEN_HEIGHT - 1;

            //calculate width of the sprite
            int spriteWidth = Math.abs((int)(Engine.SCREEN_HEIGHT / (transformY)));
            int drawStartX = -spriteWidth / 2 + spriteScreenX;
            if(drawStartX < 0)
                drawStartX = 0;
            int drawEndX = spriteWidth / 2 + spriteScreenX;
            if(drawEndX >= Engine.SCREEN_WIDTH)
                drawEndX = Engine.SCREEN_WIDTH - 1;

                  //loop through every vertical stripe of the sprite on screen
            for(int x = drawStartX; x < drawEndX; x++)
            {
                //int texX = (int)(x * (Texture.textures.get(3).getSize()));
                int texX = (int)( (x - (-spriteWidth / 2 + spriteScreenX)) * s.getWidth() / spriteWidth);
                //the conditions in the if are:
                //1) it's in front of camera plane so you don't see things behind you
                //2) it's on the screen (left)
                //3) it's on the screen (right)
                //4) ZBuffer, with perpendicular distance
                if(transformY > 0 && x > 0 && x < Engine.SCREEN_WIDTH && transformY < distances[x])
                for(int y = drawStartY; y < drawEndY; y++) //for every pixel of the current column
                {
                    int texY = (((y * 2 - Engine.SCREEN_HEIGHT + spriteHeight) * s.getHeight()) / spriteHeight) / 2;
                    //int d = (y) * 256 - Engine.SCREEN_HEIGHT * 128 + spriteHeight * 128; //256 and 128 factors to avoid floats
                    //int texY = ((d * s.getHeight()) / spriteHeight) / 256;
                   // Uint32 color = texture[sprite[spriteOrder[i]].texture][texWidth * texY + texX]; //get current color from the texture
                   // if((color & 0x00FFFFFF) != 0) buffer[y][stripe] = color; //paint pixel if it isn't black, black is the invisible color
                    int color = s.pixels[texX + (texX + (texY * s.getHeight()))];
                   //int color = Color.RED.getRGB();
                    if (color != Color.BLACK.getRGB())
                        pixels[x + y * Engine.SCREEN_WIDTH] = color;//Color.RED.getRGB();
                }
            }
        }
    }
}
