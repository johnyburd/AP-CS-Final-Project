import src.logic.weapon.Weapon.java;
import src.logic.armor.Armor.java;
import src.logic.armor.Shield.java;

public class Equipment
{
	public Weapon primaryWeapon = Weapon[0];
	public Armor theArmor;
	public Shield theShield;
	
	
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
