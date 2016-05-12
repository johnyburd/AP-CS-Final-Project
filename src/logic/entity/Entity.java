package src.logic.entity;

public abstract class Entity
{
  private double speed, hitAccuracy; 
  private int health, hitDamage, armorClass;
  private Armor equippedArmor;
  private Weapon equippedWeapon;
  
  public double getArmorClass()
  {
    return armorClass;
  }
  
  //expressed as a percentage in the form xx.xx%
  public double getHitAccuracy()
  {
    return hitAccuracy;
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
  
  public void setArmorClass(int AC)
  {
    armorClass = AC;
  }
  
  public boolean monsterHitSuccessful()
  {
    double prob = Math.random() * 100;
    if(prob >= this.getHitAccuracy)
      return false;
    return true;
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
    playerDmg = dmg - p.getArmorClass();
    p.setHealth(p.getHealth() - playerDmg);
    // damage the armor and/or sheild appropriately, as well as the monster's weapon
  }
}