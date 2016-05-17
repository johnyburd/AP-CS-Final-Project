package src.hud;

import src.engine.Keyboard;
import src.engine.Engine;

import src.logic.hero.Hero;
import src.logic.weapon.Weapon;
import src.logic.armor.*;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Hud
{

    private static ArrayList<BufferedImage> hudTextures;
    private Keyboard keyboard;
    private Engine engine;

    private Hero hero;

    private final int PAD = 10;

    public Hud(Engine e, Keyboard k, Hero h)
    {

        engine = e;
        keyboard = k;
        hero = h;

        if (hudTextures == null)
            Hud.fillList();

    }
    public static void fillList()
    {
        // This is here because it doesn't need to be constructed more than once, but it can't go before the constructor because it has to be within a try block
        hudTextures = new ArrayList<BufferedImage>();
        try
        {
            hudTextures.add(ImageIO.read(new File("res/hud/equipmentHud.png"))); // 0
            hudTextures.add(ImageIO.read(new File("res/hud/Heart.png")));
            hudTextures.add(ImageIO.read(new File("res/hud/halfHeart.png")));
            hudTextures.add(ImageIO.read(new File("res/hud/armorTemplate.png")));
            hudTextures.add(ImageIO.read(new File("res/hud/leatherArmor.png")));
            hudTextures.add(ImageIO.read(new File("res/hud/chainArmor.png"))); //5
            hudTextures.add(ImageIO.read(new File("res/hud/plateArmor.png")));
            hudTextures.add(ImageIO.read(new File("res/hud/shield.png")));
            hudTextures.add(ImageIO.read(new File("res/hud/egg.png")));
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void updateHud()
    {
        updateShield();
        updateEquipment();
        updateHealth();
        updateEggs();
    }

    // updates the weapon and armor slots
    private void updateEquipment()
    {
        BufferedImage buffImg = hudTextures.get(0);

        int armorImg = 0;
        String type = "";

        if (hero.hasArmor())
        {
            Armor current = hero.getEquippedArmor();

            type = current.toString();
        }
        else
            type = "other";

        if (type.indexOf("Plate") != -1)
            armorImg = 6;
        else if (type.indexOf("Leather") != -1)
            armorImg = 4;
        else if (type.indexOf("Chain") != -1)
            armorImg = 5;
        else
            armorImg = 3;


        int width = buffImg.getWidth();
        int height = buffImg.getHeight();

        int drawWidth = PAD;
        int drawHeight = (Engine.SCREEN_HEIGHT - height) - PAD;
        engine.blitImage(buffImg, drawWidth, drawHeight);

        // draw armor
        engine.blitImage(hudTextures.get(armorImg), 19, Engine.SCREEN_HEIGHT - height + 1);
    }
    private void updateEggs()
    {
        BufferedImage buffImg = hudTextures.get(8);

        int height = buffImg.getHeight();
        engine.blitImage(buffImg, PAD, height/2 + PAD);
    }
    private void updateShield()
    {
        BufferedImage buffImg = hudTextures.get(7);

        int width = buffImg.getWidth();
        int height = buffImg.getHeight();

        if (keyboard.qKeyDown())
            engine.blitImage(buffImg, 0, Engine.SCREEN_HEIGHT-height);
    }

    // updates the health bar
    private void updateHealth()
    {

        // converts 100pt health system into 20pt
        double hearts = hero.getHealth() / 10.0;

        BufferedImage heart = hudTextures.get(1);
        BufferedImage halfHeart = hudTextures.get(2);

        int width = heart.getWidth();
        int height = heart.getHeight();

        int i;

        for (i = 0; i < (int)hearts; i++)
        {
            engine.blitImage(heart, Engine.SCREEN_WIDTH - width - 5 - i * width, height + 5);
        }
        if (hearts % 1 >= 0.5)
            engine.blitImage(halfHeart, Engine.SCREEN_WIDTH - width - 5 - i * width, height + 5);
    }


}
