package src.engine;

import src.engine.Engine;

public class Ray
{
    private Player player;

    public int screenColumn;
    // x coord on the camera plane
    public double cameraX;

    int playerX;
    int playerY;
    
    // x and y component
    public double xComp;
    public double yComp;

    public double sideDistX;
    public double sideDistY;
    
    // length of ray from one side to next in map
    public double deltaDistX;
    public double deltaDistY;

    public double perpWallDist;
    
    // direction to go in x and y
    int stepX, stepY;

    public Ray(Player p, int col)
    {
        player = p;

        screenColumn = col;

        cameraX = 2 * screenColumn / (double)(Engine.SCREEN_WIDTH) - 1;

        xComp = player.getXFacing() + player.getXView() * cameraX;
        yComp = player.getYFacing() + player.getYView() * cameraX;

        playerX = (int)player.getX();
        playerY = (int)player.getY();

        deltaDistX = Math.sqrt(1 + (Math.pow(yComp,2) / Math.pow(xComp,2)));
        deltaDistY = Math.sqrt(1 + (Math.pow(xComp,2) / Math.pow(yComp,2)));
        
        // figure out the step direction and initial distance to a side
        if ( xComp < 0)
        {
            stepX = -1;
            sideDistX = (player.getX() - playerX) * deltaDistX;
        }

        else
        {
            stepX = 1;
            sideDistX = (playerX + 1.0 - player.getX()) * deltaDistX;
        }
        if (yComp < 0)
        {
            stepY = -1;
            sideDistY = (player.getY() - playerY) * deltaDistY;
        }
        else
        {
            stepY = 1;
            sideDistY = (playerY + 1.0 - player.getY()) * deltaDistY;
        }
    }

}
