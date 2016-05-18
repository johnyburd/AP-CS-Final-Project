package src.engine;

public class Player
{
    // position on map, Dir direction vector, field of CameraPlane vector`
    private double x, y, xDir, yDir, xCameraPlane, yCameraPlane;

    private int[][] dungeon;

    // movement speed and rotating speed
    private final double DEFAULT_TURN_SPEED = 0.04;
    private double MAX_TURN_SPEED = DEFAULT_TURN_SPEED;
    private double turnSpeed = 0;

    private final double DEFAULT_SPEED = 0.06;
    private double MAX_SPEED = DEFAULT_SPEED;
    private double ACCELERATION = 1/70.0;
    private double speed = 0;

    // distance from the wall
    private final double MAX_DIST = 0.1;


    public Player(double xc, double yc, double xf, double yf, double xv, double yv, int[][] d)
    {
        x = xc;
        y = yc;
        xDir = xf;
        yDir = yf;
        xCameraPlane = xv;
        yCameraPlane = yv;
        dungeon = d;
    }


    public void update(int[][] dungeon, Keyboard k)
    {

        if (k.qKeyDown())
            MAX_SPEED = DEFAULT_SPEED/2;
        else
            MAX_SPEED = DEFAULT_SPEED;

        if (k.aKeyDown())
        { // strafe left
            updateSpeed();
            if (k.upKeyDown() || k.downKeyDown())
                MAX_SPEED = DEFAULT_SPEED/2;
            else if (!k.qKeyDown())
                MAX_SPEED = DEFAULT_SPEED;

            double newX = x + -yDir * speed;
            double newY = y + xDir * speed;

            updatePos(newX, newY);
        }
        if (k.dKeyDown())
        { // strafe right
            updateSpeed();
            if (k.upKeyDown() || k.downKeyDown())
                MAX_SPEED = DEFAULT_SPEED/2;
            else if (!k.qKeyDown())
                MAX_SPEED = DEFAULT_SPEED;

            double newX = x + yDir * speed; // rotation matrix
            double newY = y + -xDir * speed;

            updatePos(newX, newY);
        }
        if (k.upKeyDown())
        {
            updateSpeed();

            double newX = x + xDir * speed;
            double newY = y + yDir * speed;

            updatePos(newX, newY);
        }

        if (k.downKeyDown())
        {
            updateSpeed();

            double newX = x - xDir * speed;
            double newY = y - yDir * speed;

            updatePos(newX, newY);
        }

        // https://en.wikipedia.org/wiki/Rotation_matrix

        if (k.rightKeyDown())
        {
            updateTurnSpeed();

            double tempX = xDir;
            double tempXCameraPlane = xCameraPlane;

            xDir = xDir * Math.cos(-turnSpeed) - yDir * Math.sin(-turnSpeed);
            yDir = tempX * Math.sin(-turnSpeed) + yDir * Math.cos(-turnSpeed);

            xCameraPlane=xCameraPlane*Math.cos(-turnSpeed) - yCameraPlane * Math.sin(-turnSpeed);
            yCameraPlane=tempXCameraPlane * Math.sin(-turnSpeed) + yCameraPlane * Math.cos(-turnSpeed);
        }

        // exact same as right only opposite SPEED
        if (k.leftKeyDown())
        {
            updateTurnSpeed();

            double tempX = xDir;
            double tempXCameraPlane = xCameraPlane;

            xDir = xDir * Math.cos(turnSpeed) - yDir * Math.sin(turnSpeed);
            yDir = tempX * Math.sin(turnSpeed) + yDir * Math.cos(turnSpeed);

            xCameraPlane=xCameraPlane*Math.cos(turnSpeed) - yCameraPlane * Math.sin(turnSpeed);
            yCameraPlane=tempXCameraPlane * Math.sin(turnSpeed) + yCameraPlane * Math.cos(turnSpeed);
        }

        if (!k.upKeyDown() && !k.downKeyDown() && !k.aKeyDown() && !k.dKeyDown())
            speed = 0;
    }

    private void updatePos(double newX, double newY)
    {
        if (newX > x)
        {
            if (dungeon[(int)(newX + MAX_DIST)][(int)y] == 0 && dungeon[(int)newX][(int)y] == 0)
                x = newX;
        }
        else
        {
            if (dungeon[(int)(newX - MAX_DIST)][(int)y] == 0 && dungeon[(int)newX][(int)y] == 0)
                x = newX;
        }
        if (newY > y)
        {
            if (dungeon[(int)x][(int)(newY + MAX_DIST)] == 0 && dungeon[(int)x][(int)newY] == 0)
                y = newY;
        }
        else
        {
            if (dungeon[(int)x][(int)(newY - MAX_DIST)] == 0 && dungeon[(int)x][(int)newY] == 0)
                y = newY;
        }
    }



    private void updateTurnSpeed()
    {
        if (turnSpeed < MAX_TURN_SPEED)
            turnSpeed += ACCELERATION;
        else
            turnSpeed = MAX_TURN_SPEED;
    }

    private void updateSpeed()
    {
        if (speed < MAX_SPEED)
            speed += ACCELERATION;
        else
            speed = MAX_SPEED;
    }


    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getXDir()
    {
        return xDir;
    }
    public double getYDir()
    {
        return yDir;
    }
    public double getXCameraPlane()
    {
        return xCameraPlane;
    }
    public double getYCameraPlane()
    {
        return yCameraPlane;
    }
    public void updateDungeon(int[][] d)
    {
        dungeon = d;
    }
}
