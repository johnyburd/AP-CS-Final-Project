package src.logic.entity;

import src.logic.inventory.Equipment.java;
import src.logic.armor.*;
import src.logic.weapon.*;

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
    enchanted = false;
  }
  
  //expressed as a percentage in the form xx.xx%
  public double getHitAccuracy()
  {
    return hitAccuracy;
  }
  
  public Equipment getEquippedWeapon()
  {
    return equippedItems.getWeapon();
  }
  
  public Equipment getEquippedArmor()
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
    return prob >= this.getHitAccuracy;
  }
  
  public void onMonsterAttack()
  {
    if(monsterHitSuccessful())
      onMonsterHit();
    for(double i = 10.0; i > this.getSpeed(); i -= .5)
    {
    }
  }
  
  public void setEquippedWeapon(Weapon weap)
  {
    equippedItems.switchWeapon(weap);
  }
  
  public void setEquippedArmor(Armor arm)
  {
    equippedItems.switchArmor(arm);
  }
  
  public void onMonsterHit(Player p)
  {
    int dmg = this.getHitDamage();
    if(getEquippedWeapon() != null)
      dmg += getEquippedWeapon().getWeaponDamage();
    playerDmg = dmg - p.getTotalArmorClass();
    dmg -= playerDmg;
    int armorDmg = dmg, shieldDmg = 0;
    if(p.getEquippedShield() != null && p.getEquippedShield().isRaised())
    {
      armorDmg = dmg - p.getEquippedShield().getArmorClass();
      shieldDmg = dmg - armorDmg;
      p.getEquippedShield().setDurability(p.getEquippedShield.getDurability() - shieldDmg);
    }
    if(p.getEquippedArmor != null)
    {
      p.getEquippedArmor().setDurability(p.getEquippedArmor().getDurability() - armorDmg);
      if(this.getEquippedWeapon() != false)
        this.getEquippedWeapon().setDurability(getEquippedWeapon().getDurability() - p.getTotalArmorClass());
    }
    else if(this.getEquippedWeapon() != false)
      if(this.getEquippedWeapon.getName() != "Fist")
      {  
        this.getEquippedWeapon().setDurability(getEquippedWeapon.getDurability() - 3);
        if(doesWeaponBreak == true)
          this.setEquippedWeapon(null);
      }
  }
}
