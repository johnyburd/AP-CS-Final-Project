package src.logic.armor;

public class Armor
{
  private int durability, armorClass;
  private String name;
  private boolean enchanted;
  
  public Armor(int dur, int aC, String str)
  {
    durability = dur;
    armorClass = aC;
    name = str;
    enchanted = false;
  }
  
  public void setEnchanted()
  {
    enchanted = true;
  }
  
  public int getDurability()
  {
    return durability;
  }
  
  public int getArmorClass()
  {
    return armorClass;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setDurability(int dur)
  {
    durability = dur;
  }
  
  public void setArmorClass(int aC)
  {
    armorClass = aC;
  }
  
  public void setName(String str)
  {
    name = str;
  }
  public Armor[] armorArray = {new Armor(20, 5, "Leather Armor"), new Armor(40, 10, "Chain Mail"), new Armor(80, 20, "Plate Armor")};
}
