package src.logic.inventory;

import src.logic.player.Player;
import src.logic.weapon.Weapon;
import src.logic.armor.*;

public class Chest
{
  public void onOpenChest(Player p)
  {
    int i = (int)(Math.random() * 4);
    if(i == 0)
      p.setHealth(100);
    else if(i == 1)
    {
      int a = (int)(Math.random * Weapon.weaponArray.length);
      //p.changeEquippedWeapon(Weapon.weaponArray[a]);
    }
    else if(i == 2)
    {
      
    }
    else if(i == 3)
    {
      
    }
  }
}
