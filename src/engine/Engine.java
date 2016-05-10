import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Engine extends JFrame implements Runnable
{

    private Thread thread;
    private boolean running;

    private Player player;
    private Keyboard keyboard;
    private Raster raster;

    private BufferedImage buffImg;
    private int[] pixels;

    private int dungeonHeight = 15;
    private int dungeonWidth = 15;
    public static int[][] dungeon =
        {
            {1,1,1,1,1,1,1,1,2,2,2,2,2,2,2},
            {1,0,0,0,0,2,0,0,2,0,0,0,0,0,2},
            {1,0,0,0,0,2,0,0,2,0,0,0,0,0,2},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
            {1,0,0,0,0,2,0,0,2,0,0,0,0,0,2},
            {1,4,4,4,4,2,0,0,2,0,0,0,0,0,2},
            {1,0,0,0,0,2,0,0,2,0,0,0,0,0,2},
            {1,0,0,0,0,0,0,0,2,3,3,3,3,3,2},
            {1,0,0,0,0,2,0,0,2,0,0,0,0,0,4},
            {1,0,0,0,0,2,0,0,2,0,0,0,0,0,4},
            {1,3,3,3,3,2,0,0,2,0,0,0,0,0,4},
            {1,0,0,0,0,2,0,0,0,0,0,0,0,0,4},
            {1,0,0,0,0,0,0,0,2,0,0,0,0,0,4},
            {1,0,0,0,0,2,0,0,2,0,0,0,0,0,4},
            {1,1,1,1,1,1,1,4,4,4,4,4,4,4,4}
        };

    public Engine()
    {
        thread = new Thread(this);
        buffImg = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)buffImg.getRaster().getDataBuffer()).getData();


        keyboard = new Keyboard();

        player = new Player(1.5,1.5,1,0,0,0.66);
        addKeyListener(keyboard);

        raster = new Raster(dungeon, dungeonWidth, dungeonHeight, 800, 600);

        setSize(800,600);
        setResizable(false);
        setTitle("Final Project Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.black);
       // setLocationRelativeTo(null);
        setVisible(true);
        start();
    }


    //  synchronized means only one thread can run it at a time
    private synchronized void start()
    {
        running = true;
        thread.start();
    }

    private synchronized void stop()
    {
        running = false;
        try
        {
            thread.join();

        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void blit()
    {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(buffImg, 0, 0, buffImg.getWidth(), buffImg.getHeight(), null);
        bs.show();
    }

    public void run()
    {
        long last = System.nanoTime();
        double difference = 0;
        final double ns = 1000000000.0 / 60.0;
        requestFocus();

        while(running)
        {
            long current = System.nanoTime();
            difference += ((current - last) / ns);
            last = current;

            while (difference >= 1)
            {
                //happens at most 60 times a second
                //TODO update screen

                raster.update(player, pixels);
                player.update(dungeon, keyboard);
                difference--;
            }
            blit();
        }
    }

    public static void main(String[] args)
    {
        Engine smiley = new Engine();
    }
}
