import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{
    // keep track left, right, forward, back in that order
    private boolean[] keysStatus = {false, false, false false};

    private int[] keycodes = {keyEvent.VK_LEFT, keyEvent.VK_RIGHT, keyEvent.VK_UP, keyEvent.VK_DOWN};
d

    public Keyboard()
    {
        // nothin
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

    public void keyTyped(KeyEvent e)
    {
    }
}
