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

    public void update(int[][] dungeon, Keyboard k)
    {
        if (k.upKeyDown())
        {
            double newX = x + xFacing * SPEED;
            double newY = y + yFacing * SPEED;

            if (dungeon[(int)newX][y] == 0)  // These make sure the Player can't move into a wall
                x = newX;
            if (dungeon[x][newY] == 0)
                y = newY;
        }

        if (k.downKeyDown())
        {
            double newX = x - xFacing * SPEED;
            double newY = y - yFacing * SPEED;

            if (dungeon[(int)newX][y] == 0)
                x = newX;
            if (dungeon[x][newY] == 0)
                y = newY;
        }

        // https://en.wikipedia.org/wiki/Rotation_matrix

        if (k.rightKeyDown())
        {
            double tempX = xFacing;
            double tempXView = xView;

            xFacing = xFacing * Math.cos(-ROTATION_SPEED) - yFacing * Math.sin(-ROTATION_SPEED);
            yFacing = tempX * Math.sin(-ROTATION_SPEED) + yFacing * Math.cos(-ROTATION_SPEED);

            xView=xView*Math.cos(-ROTATION_SPEED) - yView * Math.sin(-ROTATION_SPEED);
            yView=tempXView * Math.sin(-ROTATION_SPEED) + yView * Math.cos(-ROTATION_SPEED);
        }

        // exact same as right only opposite ROTATION_SPEED
        if (k.leftKeyDown())
        {
            double tempX = xFacing;
            double tempXView = xView;

            xFacing = xFacing * Math.cos(ROTATION_SPEED) - yFacing * Math.sin(ROTATION_SPEED);
            yFacing = tempX * Math.sin(ROTATION_SPEED) + yFacing * Math.cos(ROTATION_SPEED);

            xView=xView*Math.cos(ROTATION_SPEED) - yView * Math.sin(ROTATION_SPEED);
            yView=tempXView * Math.sin(ROTATION_SPEED) + yView * Math.cos(ROTATION_SPEED);
        }
    }
}
