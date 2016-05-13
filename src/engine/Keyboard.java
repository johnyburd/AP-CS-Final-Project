package src.engine;

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
        keysStatus = new boolean[8];
        keycodes = new int[8];
        keycodes[0] = KeyEvent.VK_LEFT;
        keycodes[1] = KeyEvent.VK_RIGHT;
        keycodes[2] = KeyEvent.VK_UP;
        keycodes[3] = KeyEvent.VK_DOWN;
        keycodes[4] = KeyEvent.VK_Q;
        keycodes[5] = KeyEvent.VK_A;
        keycodes[6] = KeyEvent.VK_D;
        keycodes[7] = KeyEvent.VK_E;
       // keycodes = {KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN};
    }

    // keyPressed keyReleased and keyTyped are all required to implement KeyListener
    public void keyPressed(KeyEvent e)
    {
        for (int i = 0; i < keycodes.length; i++)
            if (e.getKeyCode() == keycodes[i])
            {
                keysStatus[i] = true;
            }
    }

    public void keyReleased(KeyEvent e)
    {
        for (int i = 0; i < keycodes.length; i++)
            if (e.getKeyCode() == keycodes[i])
            {
                keysStatus[i] = false;
            }
    }

    public boolean qKeyDown()
    { // block
        return keysStatus[4];
    }
    public boolean aKeyDown()
    { // strafe left
        return keysStatus[5];
    }
    public boolean dKeyDown()
    { // strafe right
        return keysStatus[6];
    }
    public boolean eKeyDown()
    { // pick up
        return keysStatus[7];
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
