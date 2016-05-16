package src.logic.inventory;

import src.logic.player.Player;
import src.logic.weapon.Weapon;
import src.logic.inventory.Equipment;
import src.logic.armor.*;

public class Chest
{
  private Equipment containedItem;
  private boolean isOpened;
  
  public Chest()
  {
    containedItem = null;
    isOpened = false;
  }
  
  public Chest(Equipment equip)
  {
    containedItem = equip;
    isOpened = false;
  }
  
  public Chest(Player p)
  {
    containedItem = null;
    isOpened = false;
  }
  
  public void onGenRandomChest(Player p)
  {
    int i = (int)(Math.random() * 4);
    if(i == 0)
      containedItem = null;
    else if(i == 1)
    {
      int a = (int)(Math.random() * Weapon.weaponArray.length);
      containedItem = new Equipment(Weapon.weaponArray[a[);
    }
    else if(i == 2)
    {
      int a = (int)(Math.random() * Armor.armorArray.length)
      containedItem = new Equipment(Armor.armorArray[a]);
    }
    else if(i == 3)
    {
      int a = (int)(Math.random() * Shield.shieldArray.length)
      containedItem = new Equipment(Shield.shieldArray[a]);
    }
  }
  
  public void onOpenChest(Player p)
  {
    if(containedItem == null)
    { 
      isOpened = true;
      p.setHealth(100);
    }
    else
      //open gui and give choice to replace equipment
      //if choice is made, the following code is executed.
      /*
        * Equipment temp = containedItem;
        * containedItem = p.getEquippedItems();
        * p.setEquippedItems(temp);
      */
  }
}
