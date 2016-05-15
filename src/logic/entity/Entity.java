package src.logic.entity;

import src.logic.inventory.Equipment;
import src.logic.armor.*;
import src.logic.weapon.*;
import src.logic.player.*;

public abstract class Entity
{
  private double speed, hitAccuracy; 
  private int health, hitDamage;
  private Equipment equippedItems;
 
  public Entity(double spd, double hA, int hlth, int hD, Equipment equip)
  {
    speed = spd;
    hitAccuracy = hA;
    health = hlth;
    hitDamage = hD;
    equippedItems = equip;
  }
  
  public int getTotalArmorClass()
  {
    Armor arm = equippedItems.getArmor();
    if(arm != null)
      return arm.getArmorClass();
    return 0;
  }
  
  //expressed as a percentage in the form xx.xx%
  public double getHitAccuracy()
  {
    return hitAccuracy;
  }
  
  public Weapon getEquippedWeapon()
  {
    return equippedItems.getWeapon();
  }
  
  public Armor getEquippedArmor()
  {
    return equippedItems.getArmor();
  }
  
  public int getHitDamage()
  {
    return hitDamage;
  }
  
  public double getSpeed()
  {
    return speed;
  }
  
  public int getHealth()
  {
    return health;
  }
  
  public void setSpeed(double s)
  {
    speed = s;
  }
  
  //express in the form xx.xx
  public void setHitAccuracy(double hA)
  {
    hitAccuracy = hA;
  }
  
  public void setHealth(int h)
  {
    health = h;
  }
  
  public void sethitDamage(int hD)
  {
    hitDamage = hD;
  }
  
  public boolean monsterHitSuccessful()
  {
    double prob = Math.random() * 100;
    return prob >= this.getHitAccuracy();
  }
  
  public void setEquippedWeapon(Weapon weap)
  {
    equippedItems.switchWeapon(weap);
  }
  
  public void setEquippedArmor(Armor arm)
  {
    equippedItems.switchArmor(arm);
  }
}
