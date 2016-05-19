package src.logic.weapon;

public class Weapon
{
  private int weaponDamage, durability;
  private double length;
  private String name;
  private boolean enchanted;
  
  public Weapon(int wD, int dur, double len, String str)
  {
    weaponDamage = wD;
    durability = dur;
    length = len;
    name = str;
    enchanted = false;
  }
  
  public boolean isEnchanted()
  {
    return enchanted;
  }
  
  public void setEnchanted()
  {
    enchanted = true;
  }
  
  public String getName()
  {
    return name;
  }
  
  public double getWeaponLength()
  {
    return length;
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
    length = WL;
  }
  
  public void setName(String str)
  {
    name = str;
  }
  
  public boolean doesWeaponBreak()
  {
    return this.getDurability() <= 0 && name != "Fist";
  }
  
  public static Weapon[] weaponArray = {new Weapon(4, -1, 0, "Fist"), new Weapon(7, 15, 2, "Dull Sword"), new Weapon(10, 20, 2, "Decent Sword"), new Weapon(13, 25, 2, "Good Sword"), new Weapon(16, 30, 2, "Great Sword"), new Weapon(19, 25, 2.5, "Longsword"), new Weapon(25, 100, 2.1, "Master Sword")};
  
  public String toString()
  {
    return this.name;
  }
}
