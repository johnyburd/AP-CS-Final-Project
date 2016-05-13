package src.logic.weapon;

public class Weapon
{
  private int weaponDamage, durability;
  private double length;
  private String name;
  
  public Weapon(int wD, int dur, double len, String str)
  {
    weaponDamage = wD;
    durability = dur;
    length = len;
    name = str;
  }
  
  public double getWeaponLength()
  {
    return weaponLength;
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
  
  public void setWeaponLength(double WL)
  {
    weaponLength = WL;
  }
  
  public boolean doesWeaponBreak()
  {
    return this.getDurability <= 0;
  }
  
  //to be filled further with some medium level weapons. We can mix up damage and durability and reach for balanced weapons
  //basic weapon, fist, legendary weapon, a sorta ranged weapon. You get the gist.
  public Weapon[] weaponArray = {new Weapon(4, -1, 0, "Fist"), new Weapon(7, 15, 2, "Dull Sword"), new Weapon(25, 150, 2.1, "Legendary Sword of Awesome"), new Weapon(6, 20, 4, "Spear")};
}
