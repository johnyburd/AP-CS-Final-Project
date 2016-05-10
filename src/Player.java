import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener
{
    // position on map, facing direction vector, field of view vector`
    private double xPos, yPos, xDir, yDir, xView, yView;
    // keep track left, right, forward, back in that order
    private boolean[] keysStatus = {false, false, false false};

    private int[] keycodes = {keyEvent.VK_LEFT, keyEvent.VK_RIGHT, keyEvent.VK_UP, keyEvent.VK_DOWN};

    // movement speed and rotating speed
    private final double SPEED = 0.75, TURN_SPEED = 0.05;

    public Player(double x, double y, double xd, double yd, double xp, double yp)
    {
        xPos = x;
        yPos = y;
        xDir = xd;
        yDir = yd;
        xPlane = xp;
        yPlane = yp;
    }

    // keyPressed keyReleased and keyTyped are all required to implement KeyListener
    public void keyPressed(KeyEvent e)
    {
        for (int i = 0; i < keycodes.length; i++)
            if (e.getKeyCode() == keycodes[i])
                keysStatus[i] = true;


    }

    public void keyReleased(KeyEvent e)
    {
        for (int i = 0; i < keycodes.length; i++)
            if (e.getKeyCode() == keycodes.length; i++)
                keysStatus[i] = false;
    }



    public boolean leftKeyDown()
    {
        return keysStatus[0];
    }
    public boolean rightKeyDown()
    {
        return keysStatus[1];
    }
    public boolean upKeyDown()
    {
        return keysStatus[2];
    }
    public boolean downKeyDown()
    {
        return keysStatus[3];
    }

    public void keyTyped(KeyEvent key)
    {
    }
}
