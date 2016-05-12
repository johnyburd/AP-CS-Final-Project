package src.logic.weapon;

public class Weapon
{
  private int weaponDamage, durability;
  
  public int getWeaponDamage()
  {
    return weaponDamage;
  }
  
  public int getDurability()
  {
    return durability;
  }
  
  public void setWeaponDamage(int wD)
  {
    weaponDamage = wD;
  }
  
  public void setDurability(int dur)
  {
    durability = dur;
  }
  
  //to be filled
  public Weapon[] weaponArray = {};
}
