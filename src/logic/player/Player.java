package src.logic.player;

import src.logic.inventory.Equipment;
import src.logic.weapon.Weapon;
import src.logic.armor.*;

public class Player
{
  private int health;
  private final double reach = 1.5;
  private Equipment equippedItems;
 
 public Player(int hlt, Weapon weapon, Armor armor, Shield shield)
 {
   health = hlt;
   equippedItems = new Equipment(weapon, armor, shield);
 }
 
 public boolean hasShield()
 {
   return equippedItems.getShield() != null;
 }
 
 public Shield getEquippedShield()
 {
   return equippedItems.getShield();
 }
 
 public Armor getEquippedArmor()
 {
   return equippedItems.getArmor();
 }
 
 public Weapon getEquippedWeapon()
 {
   return equippedItems.getWeapon();
 }
  
  public int getHealth()
  {
    return health;
  }
  
  public int getReach()
  {
    return reach;
  }
  
  public void setHealth(int h)
  {
    health = h;
  }
  
  public void changeEquippedArmor(Armor arm)
  {
    equippedItems.switchArmor(arm);
  }
  
  public void changeEquippedWeapon(Weapon weap)
  {
    equippedItems.switchWeapon(weap);
  }
  
  public void changeEquippedShield(Shield shield)
  {
    equippedItems.switchShield(shield);
  }
  
  public int getTotalArmorClass()
  {
    int ac = equippedItems.getArmor();
    if(equippedItems.getShield().isRaised() == true)
    {
      ac += equippedItems.getShield().getArmorClass();
    }
    return ac;
  }
  
  public double getTotalReach()
  {
    return reach + equippedItems.getWeapon().getWeaponLength();
  }
}
