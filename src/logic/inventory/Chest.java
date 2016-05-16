package src.logic.inventory;

import src.logic.player.Player;
import src.logic.weapon.Weapon;
import src.logic.inventory.Equipment;
import src.logic.armor.*;

public class Chest
{
  private Equipment containedItem;
  
  public Chest()
  {
    containedItem = null;
  }
  
  public Chest(Equipment equip)
  {
    containedItem = equip;
  }
  
  public Chest(Player p)
  {
    p.setHealth(100);
  }
  
  public void onOpenRandomChest(Player p)
  {
    int i = (int)(Math.random() * 4);
    if(i == 0)
      p.setHealth(100);
    else if(i == 1)
    {
      int a = (int)(Math.random * Weapon.weaponArray.length);
      containedItem = new Equipment(a);
    }
    else if(i == 2)
    {
      int a = 0;
    }
    else if(i == 3)
    {
      
    }
  }
}
