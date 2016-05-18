package src.logic.entity;

import src.logic.inventory.Equipment;
import src.logic.weapon.*;
import src.logic.armor.*;

public class Jepling extends Entity
{
  public Jepling()
  {
    super(1, 88.88, 250, 25, new Equipment());
  }
  
  public int getTotalDamage()
  {
    int a = (int)(Math.random() * 10);
    a += 15;
    return a;
  }
  
  public int getTotalArmorClass()
  {
    return 10;
  }
}
