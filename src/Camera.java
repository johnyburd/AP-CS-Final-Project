import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera implements KeyListener
{
    // position on map, facing direction vector, field of view vector`
    private double xPos, yPos, xDir, yDir, xView, yView;
    // keep track left, right, forward, back in that order
    private boolean[] = {false, false, false false};

    private int[] keycodes = {keyEvent.VK_LEFT, keyEvent.VK_RIGHT, keyEvent.VK_UP, keyEvent.VK_DOWN};

    private final double SPEED = 0.75, TURN_SPEED = 0.05;

    public Camera(double x, double y, double xd, double yd, double xp, double yp)
    {
        xPos = x;
        yPos = y;
        xDir = xd;
        yDir = yd;
        xPlane = xp;
        yPlane = yp;
    }

    public void keyPressed(KeyEvent key)
    {
        if ((key.getKeyCode() == KeyEvent.VK_LEFT))
            left = true;
        if ((key.getKeyCode() == KeyEvent.VK_RIGHT))

    }

    public void keyReleased(KeyEvent key)
    {
        if ((key.getKeyCode() == KeyEvent.VK_LEFT))
            left = false;
        if ((key.getKeyCode() == KeyEvent.VK_RIGHT())
            right = false;
        if ((key.getKeyCode() == KeyEvent.VK_UP))
            forward = true;
        if ((key.getKeyCode() == KeyEvent.VK_DOWN))
            back = true;
    }

    public void keyTyped(KeyEvent key)
    {
    }
}
