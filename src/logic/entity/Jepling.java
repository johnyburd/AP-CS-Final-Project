package src.logic.entity;

import src.logic.inventory.Equipment;
import src.logic.weapon.*;
import src.logic.armor.*;

public class Jepling extends Entity
{
  Textbox textbo;
  public Jepling()
  {
    super(1.0, 88.88, 250, 25, new Equipment(), 25, 25, textbo);
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
