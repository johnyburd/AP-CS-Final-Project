package src.logic.inventory;

import java.util.ArrayList;
import src.logic.hero.Hero;
import src.logic.weapon.Weapon;
import src.logic.inventory.Equipment;
import src.logic.armor.*;
import src.engine.Player;

import src.engine.Sprite;

public class Chest
{
  private Equipment containedItem;
  private boolean isOpened, isLocked = false;

  public static ArrayList<Chest> chests = new ArrayList<Chest>();

  private final String PATH = "res/sprites/longChest.png";
  private Sprite sprite;
  
  //choose this one if it is random or if it is going to pregened to contain health
  public Chest(int x, int y, Player player)
  {
    containedItem = null;
    isOpened = false;
    sprite = new Sprite(player, PATH, x, y, 0);
  }
  
  //call these three if it contains predefined equipment;
  public Chest(int x, int y, Weapon weap, Player player)
  {
    containedItem = new Equipment(weap);
    isOpened = false;
    sprite = new Sprite(player, PATH, x, y, 0);
  }
  
  public Chest(int x, int y, Armor arm, Player player)
  {
    containedItem = new Equipment(arm);
    isOpened = false;
    sprite = new Sprite(player, PATH, x, y, 0);
  }
  
  public Chest(int x, int y, Shield shi, Player player)
  {
    containedItem = new Equipment(shi);
    isOpened = false;
    sprite = new Sprite(player, PATH, x, y, 0);
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
  
  public void onOpenChest(Hero p)
  {
    if(isLocked != true)
      if(containedItem == null)
      { 
        isOpened = true;
        p.setHealth(100);
      }
      else
        ;
        //open gui and give choice to replace equipment
        //if choice is made, something resembling the following code but that actually works in this context is executed.
        /*
          * Equipment temp = containedItem;
          * containedItem = p.getEquippedItems();
          * p.setEquippedItems(temp);
        */
  }
  
  // gets the contents of the opened chest, and locks out the others. no other chest in the game will be locked
  // hope the player chooses wisely on these
  // honestly, pick the sword, every other weapon will shatter ridiculously early in the boss match
  // don't fight the boss with just your fist, it will take forever
  public void onOpenBossChest(Hero p, Chest[] others)
  {
    this.onOpenChest(p);
    Chest.setAllLocked(others);
  }
}
