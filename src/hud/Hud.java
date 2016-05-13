package src.hud;

import src.engine.Engine;
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

    public Hud()
    {
        if (hudTextures == null)
            Hud.fillList();

    }
    public static void fillList()
    {
        // This is here because it doesn't need to be constructed more than once, but it can't go before the constructor because it has to be within a try block
        hudTextures = new ArrayList<BufferedImage>();
        try
        {
            hudTextures.add(ImageIO.read(new File("res/hud/equipmentHud.png")));
            hudTextures.add(ImageIO.read(new File("res/hud/Heart.png")));
            hudTextures.add(ImageIO.read(new File("res/hud/armorTemplate.png")));
            hudTextures.add(ImageIO.read(new File("res/hud/leatherArmor.png")));
            hudTextures.add(ImageIO.read(new File("res/hud/chainArmor.png")));
            hudTextures.add(ImageIO.read(new File("res/hud/plateArmor.png")));
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void updateHud(Engine e)
    {
        updateEquipment(e);
        updateHealth(e);
    }

    // updates the weapon and armor slots
    private void updateEquipment(Engine e)
    {
        BufferedImage buffImg = hudTextures.get(0);

        int width = buffImg.getWidth();
        int height = buffImg.getHeight();

        int drawWidth = 10;
        int drawHeight = (Engine.SCREEN_HEIGHT - height) - 10;
        e.blitImage(buffImg, drawWidth, drawHeight);

        boolean noOtherArmor = true;
        if (noOtherArmor)
            e.blitImage(hudTextures.get(2), 19, Engine.SCREEN_HEIGHT - height + 1);
    }

    // updates the health bar
    private void updateHealth(Engine e)
    {
        // get health here
        int hearts = 10;

        BufferedImage heart = hudTextures.get(1);

        int width = heart.getWidth();
        int height = heart.getHeight();

        for (int i = 0; i < hearts; i++)
        {
            e.blitImage(heart, Engine.SCREEN_WIDTH - width - 5 - i * width, height + 5);
        }
    }


}
