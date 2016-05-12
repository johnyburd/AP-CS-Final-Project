package src.logic.weapon;

public class Weapon
{
  private int weaponDamage, durability;
  private String name;
  
  public Weapon(int wD, int dur, String str)
  {
    weaponDamage = wD;
    durability = dur;
    name = str;
  }
  
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
  public Weapon[] weaponArray = {new Weapon(4, -1, "Fist")};
}
