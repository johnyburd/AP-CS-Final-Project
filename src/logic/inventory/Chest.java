package src.logic.inventory;

import java.util.ArrayList;
import src.logic.hero.Hero;
import src.logic.weapon.Weapon;
import src.logic.inventory.Equipment;
import src.logic.armor.*;

import src.engine.Player;
import src.engine.Sprite;
import src.engine.Engine;
import src.engine.Keyboard;

import src.hud.TextBox;

import java.util.Scanner;

public class Chest
{
  private double xCoord;
  private double yCoord
  private Equipment containedItem;
  private boolean isOpened, isLocked = false;
  private TextBox text;
  private Keyboard keyboard = Engine.staticKeyboard;

  public static ArrayList<Chest> chests = new ArrayList<Chest>();

  private final String PATH = "res/sprites/longChest.png";
  private Sprite spriteChest;
  
  //choose this one if it is random or if it is going to pregened to contain health
  public Chest(double x, double y, Player player, TextBox textbox)
  {
    containedItem = null;
    isOpened = false;
    spriteChest = new Sprite(player, PATH, x, y, 0);
    Sprite.sprites.add(spriteChest);
    text = textbox;
    xCoord = x;
    yCoord = y;
  }
  
  //call these three if it contains predefined equipment;
  public Chest(int x, int y, Weapon weap, Player player, TextBox textbox)
  {
    containedItem = new Equipment(weap);
    isOpened = false;
    spriteChest = new Sprite(player, PATH, x, y, 0);
    Sprite.sprites.add(spriteChest);
    text = textbox;
    xCoord = x;
    yCoord = y;
  }
  
  public Chest(int x, int y, Armor arm, Player player, TextBox textbox)
  {
    containedItem = new Equipment(arm);
    isOpened = false;
    spriteChest = new Sprite(player, PATH, x, y, 0);
    Sprite.sprites.add(spriteChest);
    text = textbox;
    xCoord = x;
    yCoord = y;
  }
  
  public Chest(int x, int y, Shield shi, Player player, TextBox textbox)
  {
    containedItem = new Equipment(shi);
    isOpened = false;
    spriteChest = new Sprite(player, PATH, x, y, 0);
    Sprite.sprites.add(spriteChest);
    text = textbox;
    xCoord = x;
    yCoord = y;
  }
  
  public Sprite getSprite()
  {
    return spriteChest;
  }
  
  public void setIsOpened()
  {
    isOpened = true;
  }
  
  public void setIsLocked()
  {
    isLocked = true;
  }
  
  public static void setAllLocked(Chest[] chests)
  {
    for(Chest che: chests)
      che.setIsLocked();
  }
  
  public static void setAllLocked(ArrayList<Chest> chests)
  {
    for(Chest che: chests)
      che.setIsLocked();
  }
  
  // if it returns true, then life has already been taken from the chest. should be used to check if the chest needs to be removed
  // from the game
  public boolean getIsOpened()
  {
    return isOpened;
  }
  
  // if this returns true, the chest can't be opened. for use in boss battle for the player's choice in god-tier buff
  public boolean getIsLocked()
  {
    return isLocked;
  }

  public bolean equals(Chest other)
  {
    return (spriteChest.xCoord - other.xCoord == 0) && (spriteChest.yCoord - other.yCoord == 0);
  }
  
  public void randomizeChest()
  {
    int i = (int)(Math.random() * 4);
    if(i == 0)
      containedItem = null;
    else if(i == 1)
    {
      int a = (int)(Math.random() * Weapon.weaponArray.length);
      containedItem = new Equipment(Weapon.weaponArray[a]);
    }
    else if(i == 2)
    {
      int a = (int)(Math.random() * Armor.armorArray.length);
      containedItem = new Equipment(Armor.armorArray[a]);
    }
    else if(i == 3)
    {
      int a = (int)(Math.random() * Shield.shieldArray.length);
      containedItem = new Equipment(Shield.shieldArray[a]);
    }
  }
  
  public void onOpenChest(Hero p, Player play)
  {
    if(isLocked != true)
      if(containedItem == null)
      { 
        isOpened = true;
        p.setHealth(100);
      }
      else
      {
        //Scanner kb = new Scanner(System.in);
        Equipment temp = new Equipment(containedItem);
        text.addMessage("You found " + containedItem.toString());
        text.addMessage("Do you wish to exchange your current item for this?");
        text.addMessage("Enter (y/n)");
        String st = containedItem.toString();
        if(st.indexOf("Sword") != -1 || st.toString().indexOf("sword") != -1)
        {
          while(!keyboard.yKeyDown() && !keyboard.nKeyDown());
          if(keyboard.yKeyDown())
          {
            Equipment equip = new Equipment(p.getEquippedWeapon());
            containedItem = equip;
            p.changeEquippedWeapon(temp.getWeapon());
          }
        }
        
        else if(st.indexOf("armor") != -1 || st.indexOf("mail") != -1)
        {
          while(!keyboard.yKeyDown() && !keyboard.nKeyDown());
          if(keyboard.yKeyDown())
          {
            Equipment equip = new Equipment(p.getEquippedArmor());
            containedItem = equip;
            p.changeEquippedArmor(temp.getArmor());
          }
        }
        
        else if(st.indexOf("Shield") != -1)
        {
          while(!keyboard.yKeyDown() && !keyboard.nKeyDown());
          if(keyboard.yKeyDown())
          {
            Equipment equip = new Equipment(p.getEquippedShield());
            containedItem = equip;
            p.changeEquippedShield(temp.getShield());
          }
        }
      }
  }
  
  // gets the contents of the opened chest, and locks out the others. no other chest in the game will be locked
  // hope the player chooses wisely on these
  // honestly, pick the sword, every other weapon will shatter ridiculously early in the boss match
  // don't fight the boss with just your fist, it will take forever
  public void onOpenBossChest(Hero p, Player play, ArrayList<Chest> others)
  {
    this.onOpenChest(p, play);
    Chest.setAllLocked(others);
  }
}
