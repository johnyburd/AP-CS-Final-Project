package src.logic.entity;

import src.logic.inventory.Equipment;
import src.logic.armor.*;
import src.logic.weapon.*;
import src.logic.hero.*;

import src.hud.TextBox;

public class Entity
{
  private double speed, hitAccuracy;
  private int health, hitDamage, x, y;
  private Equipment equippedItems;
  private TextBox textbox;

  private final String PATH = "res/sprites/baddie.png";
  private Sprite sprite;

  public Entity(double spd, double hA, int hlth, int hD, Equipment equip, int xPos, int yPos, TextBox t)
  {
    speed = spd;
    hitAccuracy = hA;
    health = hlth;
    hitDamage = hD;
    equippedItems = equip;
    x = xPos;
    y = yPos;
    textbox = t;
  }
  
  public void updateXCoord(int newX)
  {
    x = newX;
  }
  
  public void updateYCoord(int newY)
  {
    y = newY;
  }
  
  public int getXCoord()
  {
    return x;
  }
  
  public int getYCoord()
  {
    return y;
  }

  public int getTotalDamage()
  {
    int dmg = getHitDamage();
    Weapon weap = equippedItems.getWeapon();
    dmg += weap.getWeaponDamage();
    return dmg;
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
  
  public void changeEquippedWeapon(Weapon weap)
  {
    equippedItems.switchWeapon(weap);
  }
  
  public Armor getEquippedArmor()
  {
    return equippedItems.getArmor();
  }

  public void changeEquippedArmor(Armor arm)
  {
    equippedItems.switchArmor(arm);
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

  public void onMonsterAttack(Hero p)
  {
    if(monsterHitSuccessful())
      p.onPlayerHit(this);
  }
  
  // this is what happens when the monster gets hit
  public void onMonsterHit(Hero p)
  {
    //these two lines will shorten the code
    Armor arm = this.getEquippedArmor();
    Weapon weap = p.getEquippedWeapon();
    
    int pDmg = p.getTotalDamage();
    int mDef = this.getTotalArmorClass();
    
    int mDmg = 0;
    int armDmg = 0;
    
    if(this.getEquippedArmor() != null)
      armDmg = pDmg - mDef;
    pDmg -= armDmg;
    this.setHealth(this.getHealth() - pDmg);
    
    //damage the weapon appropriately
    if(mDef > 0)
      weap.setDurability(weap.getDurability() - mDef);
    else
      weap.setDurability(weap.getDurability() - 3);
    
    if(weap.doesWeaponBreak())
    {  
      p.changeEquippedWeapon(Weapon.weaponArray[0]);
      textbox.addMessage("Your sword broke! You begin bashing monsters with your bare hands!");
    }  
    if(this.getEquippedArmor() != null)  
      if(this.getEquippedArmor().getDurability() <= 0)
        this.changeEquippedArmor(null);
  }
}
