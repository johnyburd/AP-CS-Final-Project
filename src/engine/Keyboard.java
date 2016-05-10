import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{
    // keep track left, right, forward, back in that order
    private boolean[] keysStatus;
    private int[] keycodes;

    public Keyboard()
    {
        // these should default to false
        keysStatus = new boolean[4];
        keycodes = new int[4];
        keycodes[0] = KeyEvent.VK_LEFT;
        keycodes[1] = KeyEvent.VK_RIGHT;
        keycodes[2] = KeyEvent.VK_UP;
        keycodes[3] = KeyEvent.VK_DOWN;
       // keycodes = {KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN};
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
            if (e.getKeyCode() == keycodes.length)
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

    public void keyTyped(KeyEvent e)
    {
    }
}
