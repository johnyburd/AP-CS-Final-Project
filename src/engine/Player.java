public class Player
{
    // position on map, facing direction vector, field of view vector`
    private double x, y, xFacing, yFacing, xView, yView;
    
    // movement speed and rotating speed
    private final double SPEED = 0.75, TURN_SPEED = 0.05;

    public Player(double xc, double yc, double xf, double yf, double xv, double yv)
    {
        x = x;
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

            xFacing = xFacing * Math.cos(-SPEED) - yFacing * Math.sin(-SPEED);
            yFacing = tempX * Math.sin(-SPEED) + yFacing * Math.cos(-SPEED);

            xView=xView*Math.cos(-SPEED) - yView * Math.sin(-SPEED);
            yView=tempXView * Math.sin(-SPEED) + yView * Math.cos(-SPEED);
        }

        // exact same as right only opposite SPEED
        if (k.leftKeyDown())
        {
            double tempX = xFacing;
            double tempXView = xView;

            xFacing = xFacing * Math.cos(SPEED) - yFacing * Math.sin(SPEED);
            yFacing = tempX * Math.sin(SPEED) + yFacing * Math.cos(SPEED);

            xView=xView*Math.cos(SPEED) - yView * Math.sin(SPEED);
            yView=tempXView * Math.sin(SPEED) + yView * Math.cos(SPEED);
        }
    }
}
