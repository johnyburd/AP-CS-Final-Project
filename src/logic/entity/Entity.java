
public abstract class Entity
{
  private double speed, hitAccuracy, 
  private int health, hitDamage, 
  
  //expressed as a percentage in the form xx.xx%
  public double getHitAccuracy()
  {
    return hitAccuracy;
  }
  
  public int hitDamage()
  {
    return hitDamage;
  }
  
  public double getSpeed()
  {
    return speed;
  }
  
  public int getHealth()
  {
    return health;
  }
}
