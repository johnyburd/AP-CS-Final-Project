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
  
  public double getReach()
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
    Armor arm = equippedItems.getArmor();
    Shield shi = equippedItems.getShield();
    int ac = arm.getArmorClass();
    if(shi.isRaised() == true)
    {
      ac += shi.getArmorClass();
    }
    return ac;
  }
  
  public double getTotalReach()
  {
    return reach + equippedItems.getWeapon().getWeaponLength();
  }
}
