package src.logic.hero;

import src.logic.inventory.Equipment;
import src.logic.weapon.Weapon;
import src.logic.armor.*;
import src.logic.entity.*;
import src.engine.Player;

public class Hero
{
  private int health;
  private final double reach = 1.5;
  private Equipment equippedItems;
 
 //for actual gameplay
 public Hero(Weapon weap)
 {
   health = 100;
   equippedItems = new Equipment(weap, null, null);
 }
 
 //test constructor
 public Hero(Weapon weapon, Armor armor, Shield shield)
 {
   health = 100;
   equippedItems = new Equipment(weapon, armor, shield);
 }
 
 public Equipment getEquippedItems()
 {
   return equippedItems;
 }
 
 public boolean hasArmor()
 {
   return equippedItems.getArmor() != null;
 }
 
 public boolean hasSword()
 {
   return equippedItems.getWeapon() != Weapon.weaponArray[0];
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
  
  public int getTotalDamage()
  {
    return this.getEquippedWeapon().getWeaponDamage();
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
  
  //this is called when the player gets hit
  public void onPlayerHit(Entity e)
  {
    int pac = this.getTotalArmorClass();
    int mac = e.getTotalDamage();
    int playerDmg = 0;
    int armorDmg = 0;
    int shieldDmg = 0;
    if(mac > pac)
    { 
      playerDmg = mac - pac;
      mac -= pac;
    }
    if(this.hasShield() && this.getEquippedShield().isRaised())
      {
        if(this.hasArmor())
          {
          armorDmg = mac - this.getEquippedShield().getArmorClass();
          if(armorDmg < 0)
            armorDmg = 0;
          }
        shieldDmg = mac - this.getEquippedShield().getArmorClass();
        if(shieldDmg < 0)
          shieldDmg = mac;
        mac -= this.getEquippedShield().getArmorClass();
      }
    else if(this.hasArmor())
    {
      armorDmg = mac;
    }
    this.setHealth(this.getHealth() - playerDmg);
    if(this.hasArmor())
      this.getEquippedArmor().setDurability(this.getEquippedArmor().getDurability() - armorDmg);
    if(this.hasShield())
      this.getEquippedShield().setDurability(this.getEquippedShield().getDurability() - shieldDmg);
    //this part here assigns damage to the weapon then sees if it breaks
    int armclass = this.getTotalArmorClass();
    Weapon weap = e.getEquippedWeapon();
    if(armclass > 0)
      weap.setDurability(weap.getDurability() - armclass);
    else
      weap.setDurability(weap.getDurability() - 3);
    if(weap.doesWeaponBreak())
      e.changeEquippedWeapon(null);
      
    //this section sees if the ARMOR breaks
    
    if(this.getEquippedArmor() != null && this.getEquippedArmor().getDurability() <= 0)
      this.changeEquippedArmor(null);
  }
  
  public void onPlayerAttack(Entity e)
  {
    //calc the distance from e and see if it's within reach
    e.onMonsterHit(this);
  }
}
