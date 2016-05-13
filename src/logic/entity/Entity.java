package src.logic.entity;
import src.logic.inventory.Equipment.java;

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
  
  //expressed as a percentage in the form xx.xx%
  public double getHitAccuracy()
  {
    return hitAccuracy;
  }
  
  public Equipment getEquippedItems()
  {
    return equippedItems;
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
  
  public void sethitDamage(hD)
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
    // signal player's turn
  }
  
  public void onMonsterHit(Player p)
  {
    int dmg = this.getHitDamage();
    playerDmg = dmg - p.getTotalArmorClass();
    
    // damage the armor and/or sheild appropriately, as well as the monster's weapon
  }
}
