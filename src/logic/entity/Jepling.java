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
  
  public int getTotalArmorClass()
  {
    return 10;
  }
}
