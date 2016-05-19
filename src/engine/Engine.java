package src.engine;

import src.hud.Hud;
import src.hud.TextBox;

import src.engine.Player;
import src.dungeon.DungeonGenerator;

import src.logic.hero.Hero;
import src.logic.weapon.Weapon;
import src.logic.armor.*;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Engine extends JFrame implements Runnable
{
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;

    private Thread thread;
    private boolean running;

    private TextBox textbox;

    private Player player;
    private Keyboard keyboard;
    private Raycaster raycaster;
    private Hud hud;

    private ArrayList<BlitImage> blitImageList;

    private BufferedImage buffImg;
    private int[] pixels;

    private Graphics g;


    private int dungeonHeight = 15;
    private int dungeonWidth = 15;
    private DungeonGenerator dungeonMaster;
    public static int[][] dungeon;
   /** public static int[][] dungeon =
        {
            {1,1,1,1,1,1,1,1,2,2,2,2,2,2,2},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
            {1,0,0,0,0,0,0,0,0,0,1,1,0,0,2},
            {1,0,0,0,0,0,0,0,0,0,1,1,0,0,2},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
            {1,0,0,0,0,0,2,2,0,0,0,0,0,0,4},
            {1,0,0,0,0,0,2,2,0,0,0,0,0,0,4},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
            {1,1,1,1,1,1,1,4,4,4,4,4,4,4,4}
        };**/
    // LOGIC STUFF

    private Hero hero;

    public Engine()
    {

        thread = new Thread(this);

        textbox = new TextBox();
        buffImg = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)buffImg.getRaster().getDataBuffer()).getData();

        keyboard = new Keyboard();
    
        player = new Player(2,7.5,1,0,0,-0.66, dungeon);
        addKeyListener(keyboard);

        // to store images qued
        blitImageList = new ArrayList<BlitImage>();

        dungeonMaster = new DungeonGenerator(15, player, textbox);
        dungeon = dungeonMaster.getNewDungeon();
        player.updateDungeon(dungeon); // player requires dungeon

        //textbox.addMessage("looks super good");
        //textbox.addMessage("testing");
        //textbox.addMessage("the only problem is that it wraps too early.  if that's hard to fix, don't bother");
        
        Sound.playPlayerHit();


        raycaster = new Raycaster(dungeon, dungeonHeight, dungeonWidth, this, player);


        hero = new Hero(Weapon.weaponArray[0]);
        hud = new Hud(this, keyboard, hero, textbox);


        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
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
        g = bs.getDrawGraphics();
        g.drawImage(buffImg, 0, 0, buffImg.getWidth(), buffImg.getHeight(), null);

        // runs the the list of images qued
        while (blitImageList.size() > 0)
        {
            BlitImage bi = blitImageList.get(0);

            g.drawImage(bi.getBuffImg(), bi.getX(), bi.getY(), bi.getWidth(), bi.getHeight(), null);

            blitImageList.remove(0);
        }


        bs.show();
    }

    // adds images to be displayed when blit() runs
    public void blitImage(BufferedImage b, int xPx, int yPx)
    {
        blitImageList.add(new BlitImage(b, xPx, yPx));
    }

    public void run()
    {
        double difference = 0;
        long last = System.nanoTime();
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

                raycaster.refresh(pixels);
                player.update(dungeon, keyboard);
                difference--;
            }
            blit();

            hud.updateHud();
        }
    }

    public static void main(String[] args)
    {
        Engine smiley = new Engine();
    }
}
