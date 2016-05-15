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
    isRaised = false;
  }
}
