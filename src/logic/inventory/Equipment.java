package src.logic.inventory;

import src.logic.weapon.Weapon;
import src.logic.armor.*;

public class Equipment
{
	public Weapon primaryWeapon;
	public Armor theArmor;
	public Shield theShield;
	
	// This default constructor is for monsters only, allows basic opponents with no equipment
	public Equipment()
	{
		primaryWeapon = null;
		theArmor = null;
		theShield = null;
	}
	
	//copy constructor
	public Equipment(Equipment other)
	{
		primaryWeapon = other.primaryWeapon;
		theArmor = other.theArmor;
		theShield = other.theShield;
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
	
	//these remaining constructors are for the chests
	public Equipment(Weapon weap)
	{
		primaryWeapon = weap;
		theArmor = null;
		theShield = null;
	}
	
	public Equipment(Armor arm)
	{
		primaryWeapon = null;
		theArmor = arm;
		theShield = null;
	}
	
	public Equipment(Shield shi)
	{
		primaryWeapon = null;
		theArmor = null;
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
	
	public String toString()
	{
		String str = "";
		if(primaryWeapon != null)
			str = str + primaryWeapon.toString() + "\n";
		if(theArmor != null)
			str = str + theArmor.toString() + "\n";
		if(theShield != null)
			str = str + theShield.toString() + "\n";
		if(str.equals(""))
			str += "There is nothing equipped.";
        return str;
	}
	
	public static Equipment randomMonsterSword(int level)
	{
		int b = 3 - level;
		int a = (int)(Math.random() * (Weapon.weaponArray.length - b));
		Weapon weap = Weapon.weaponArray[a];
		return new Equipment(weap);
	}
	
	public static Equipment randomMonsterArmor(int level)
	{
		int b = 3 - level;
		int a = (int)(Math.random() * (Armor.armorArray.length - b));
		Armor arm = Armor.armorArray[a];
		return new Equipment(arm);
	}
	
	public static Equipment randomMonsterEquipment(int level)
	{
		int d = (int)(Math.random() * 2);
		int c = (int)(Math.random() * 101);
		if((c * level) >= 90)
		{
			if(d == 0)
				return randomMonsterArmor(level);
			else if(d == 1)
				return randomMonsterSword(level);
		}
		return null;
	}
}
