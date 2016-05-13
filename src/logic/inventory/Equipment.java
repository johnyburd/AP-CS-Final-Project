import src.logic.weapon.Weapon.java;
import src.logic.armor.Armor;
import src.logic.armor.Shield;

public class Equipment
{
	public Weapon primaryWeapon = weaponArray[0];
	public Armor theArmor;
	public Shield theShield;
	
	public Equipment(Weapon weap, Armor arm, Shield shi)
	{
		primaryWeapon = weap;
		theArmor = arm;
		theShield = shi;
	}
	
	//accessors
	public Weapon getWeapon()
	{
		return primaryWeapon;
	}
	
	public Armor getArmor()
	{
		return theArmor;
	}
	
	public Shield getShield()
	{
		return theShield;
	}
	
	
	//mutators
	public void switchWeapon(Weapon other)
	{
		primaryWeapon = other;
	}
	
	public void switchArmor(Armor other)
	{
		theArmor = other;
	}
	
	public void switchShield(Shield other)
	{
		theShield = other;
	}
}
