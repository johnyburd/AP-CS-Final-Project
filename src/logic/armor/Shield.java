package src.logic.armor;

import src.engine.Keyboard;

public class Shield extends Armor
{
  private boolean isRaised;
  
  public Shield(int dur, int AC, String str)
  {
    super(dur, AC, str);
  }
  
  public boolean isUp()
  {
    return isRaised;
  }
  
  public void setShieldUp()
  {
    isRaised = true;
  }
  
  public void setShieldDown()
  {
    isRaised = false;
  }
}
