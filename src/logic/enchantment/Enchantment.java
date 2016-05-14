package src.logic.Enchantment;
import src.logic.weapon.*;
import src.logic.armor.*;

public class Enchantment
{
  public static boolean isEnchanted(Weapon weap)
  {
    return weap.isEnchanted();
  }
  
  public static boolean isEnchanted(Armor arm)
  {
    return arm.isEnchanted();
  }
  
  public static boolean isEnchanted(Shield shi)
  {
    return shi.isEnchanted();
  }
  
  public static void enchantWeapon(Weapon weap)
  {
    int i = (int)(Math.random() * 2);
    int amt = (int)(Math.random() * 4) + 1;
    if(i == 0)
      weap.setDurability(weap.getDurability() + amt);
    else if(i == 1)
      weap.setWeaponDamage(weap.getWeaponDamage() + amt);
    weap.setName(weap.getName() + " + " + amt);
    weap.setEnchanted();
  }
  
  public static void enchantArmor(Armor arm)
  {
    int i = (int)(Math.random() * 2);
    int amt = (int)(Math.random() * 4) + 1;
    if(i == 0)
      arm.setDurability(arm.getDurability + amt);
    else if(i == 1)
      arm.setArmorClass(arm.getArmorClass() + amt);
    arm.setName(arm.getName() + " + " + amt);
    arm.setEnchanted();
  }
  
  public static void enchantShield(Shield shi)
  {
    enchantArmor(shi);
  }
}
