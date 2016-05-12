package src.logic.player;

public class Player
{
  private int health;
  private final double reach = 1.5;
  
  public int getHealth()
  {
    return health;
  }
  
  public int getReach()
  {
    return reach;
  }
  
  public final setHealth(int h)
  {
    health = h;
  }
}
