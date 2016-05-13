package src.logic.inventory;

import src.logic.weapon.Weapon;
import src.logic.armor.Armor;
import src.logic.armor.Shield;

public class Equipment
{
	public Weapon primaryWeapon = weaponArray[0];
	public Armor theArmor;
	public Shield theShield;
	
	// This default constructor is for monsters only, allows basic opponents with no equipment
	public Equipment()
	{
		primaryWeapon = null;
		theArmor = null;
		theShield = null;
	}
	
	// This constructor is for monsters only, as they cannot weild shields ever
	public Equipment(Weapon weap, Armor arm)
	{
		primaryWeapon = weap;
		theArmor = arm;
		theShield = null;
	}
	
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
