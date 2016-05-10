import java.awt.Color;
import java.awt.Graphics
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Engine extents Jframe implements Runnable
{

    private Thread thread;
    private boolean running;

    private Player player;
    private Screen screen;

    private ArrayList<Texture> textures;

    private BufferedImage buffImg;
    private int[] pixels;

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
        pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

        textures = new ArrayList<Texture>();

        //TODO add textures

        player = new Player(1.5,1.5,1,0,0,0.66);
        addKeyListener(player);

        screen = new Screen(dungeon, dungeonWidth, dungeonHeight; textures, 800, 600);

        this.setSize(800,600);
        this.setResizable(false);
        this.setTitle("Final Project Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.black);
       // setLocationRelativeTo(null);
        this.setVisable(true);
        this.start();
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
        catch(InterruptedExeption e)
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
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
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
                //TODO update player
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
