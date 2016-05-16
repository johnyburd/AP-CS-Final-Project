package src.logic.armor;

import src.engine.Keyboard;

public class Shield extends Armor
{
  private boolean isUp;
  
  public Shield(int dur, int AC, String str)
  {
    super(dur, AC, str);
  }
  
  public boolean isRaised()
  {
    return isUp;
  }
  
  public void setShieldRaised()
  {
    isUp = true;
  }
  
  public void setShieldDown()
  {
    isUp = false;
  }
  
  public static Shield[] shieldArray = {new Shield(15, 5, "Basic Shield"), new Shield(15, 10, "Medium Shield"), new Shield(20, 15, "Great Shield") ,new Shield(40, 30, "God Shield")};
}
