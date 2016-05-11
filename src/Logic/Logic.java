
public class Logic 
{
  public void onExitLevel()
  {
    //win or go to next level;
  }
  
  public boolean isDead(Player e)
  {
    if(e.getHealth() == 0)
    {
      //load the death animmation and screen overlay
    }
  }
  
  public void onFight()
  {
    
  }
  
  public boolean successfulMonsterAttack(Entity e)
  {
    double x = Math.random()*100;
    if(x <= e.getHitAccuracy)
      return true;
    return false;
  }
}
