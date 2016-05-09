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

    private Camera camera;
    private Screen screen;

    private ArrayList<Texture> textures;

    private BufferedImage buffImg;
    private int[] pixels;

    public static int[][] map =
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

        camera = new Camera(1.5,1.5,1,0,0,0.66);
        screen = new Screen(map, mapWidth, mapHeight; textures, 800, 600);
        addKeyListener(camera);
        setSize(800,600);
        setResizable(false);
        this.setTitle("Dank Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.black);
       // setLocationRelativeTo(null);
        setVisable(true);
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
        catch(InterruptedExeption e)
        {
            e.printStackTrace();
        }
    }

    public void render()
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
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        requestFocus();

        while(running)
        {
            long now = System.nanoTime();
            delta += ((now - last) / ns);
            last = now;

            while (delta >= 1)
            {
                //happens at most 60 times a second
                //TODO update screen
                //TODO update camera
                delta--;
            }
            render();
        }
    }

    public static void main(String[] args)
    {
        Engine game = new Engine();
    }
}
