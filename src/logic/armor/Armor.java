package src.logic.armor;

public class Armor
{
  private int durability, armorClass;
  private String name;
  
  public int getDurability()
  {
    return durability;
  }
  
  public int getArmorClass()
  {
    return armorClass;
  }
  
  public Armor(int dur, int aC, String str)
  {
    durability = dur;
    armorClass = aC;
    name = str;
  }
}
