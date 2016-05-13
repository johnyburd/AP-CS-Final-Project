package src.engine;

public class Player
{
    // position on map, facing direction vector, field of view vector`
    private double x, y, xFacing, yFacing, xView, yView;
    
    // movement speed and rotating speed
    private final double TURN_SPEED = 0.04, DEFAULT_SPEED = 0.15;
    private double SPEED = DEFAULT_SPEED;


    public Player(double xc, double yc, double xf, double yf, double xv, double yv)
    {
        x = xc;
        y = yc;
        xFacing = xf;
        yFacing = yf;
        xView = xv;
        yView = yv;
    }
    
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getXFacing()
    {
        return xFacing;
    }
    public double getYFacing()
    {
        return yFacing;
    }
    public double getXView()
    {
        return xView;
    }
    public double getYView()
    {
        return yView;
    }

    public void update(int[][] dungeon, Keyboard k)
    {
        if (k.qKeyDown())
            SPEED = DEFAULT_SPEED/2;
        else
            SPEED = DEFAULT_SPEED;

        if (k.upKeyDown())
        {
            double newX = x + xFacing * SPEED;
            double newY = y + yFacing * SPEED;

            if (dungeon[(int)newX][(int)y] == 0)  // These make sure the Player can't move into a wall
                x = newX;
            if (dungeon[(int)x][(int)newY] == 0)
                y = newY;
        }

        if (k.downKeyDown())
        {
            double newX = x - xFacing * SPEED;
            double newY = y - yFacing * SPEED;

            if (dungeon[(int)newX][(int)y] == 0)
                x = newX;
            if (dungeon[(int)x][(int)newY] == 0)
                y = newY;
        }

        // https://en.wikipedia.org/wiki/Rotation_matrix

        if (k.rightKeyDown())
        {
            double tempX = xFacing;
            double tempXView = xView;

            xFacing = xFacing * Math.cos(-TURN_SPEED) - yFacing * Math.sin(-TURN_SPEED);
            yFacing = tempX * Math.sin(-TURN_SPEED) + yFacing * Math.cos(-TURN_SPEED);

            xView=xView*Math.cos(-TURN_SPEED) - yView * Math.sin(-TURN_SPEED);
            yView=tempXView * Math.sin(-TURN_SPEED) + yView * Math.cos(-TURN_SPEED);
        }

        // exact same as right only opposite SPEED
        if (k.leftKeyDown())
        {
            double tempX = xFacing;
            double tempXView = xView;

            xFacing = xFacing * Math.cos(TURN_SPEED) - yFacing * Math.sin(TURN_SPEED);
            yFacing = tempX * Math.sin(TURN_SPEED) + yFacing * Math.cos(TURN_SPEED);

            xView=xView*Math.cos(TURN_SPEED) - yView * Math.sin(TURN_SPEED);
            yView=tempXView * Math.sin(TURN_SPEED) + yView * Math.cos(TURN_SPEED);
        }
    }
}
