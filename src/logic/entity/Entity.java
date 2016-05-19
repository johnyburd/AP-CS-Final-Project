package src.logic.entity;

import src.logic.inventory.Equipment;
import src.logic.armor.*;
import src.logic.weapon.*;
import src.logic.hero.*;

import src.engine.Sprite;
import src.engine.Player;
import src.engine.Engine;
import src.sound.Sound;

import src.sound.Sound;

import src.hud.TextBox;

import java.util.ArrayList;

public class Entity
{
  public double speed, hitAccuracy, x, y;
  private int health, hitDamage;
  private Equipment equippedItems;
  private TextBox textbox;

  public static ArrayList<Entity> entities = new ArrayList<Entity>();

  private final String PATH = "res/sprites/baddie.png";
  private Sprite spriteObj;
  private Player player;

  public Entity(double spd, double hA, int hlth, int hD, Equipment equip, double xPos, double yPos, TextBox t, Player p)
  {
    speed = spd;
    hitAccuracy = hA;
    health = hlth;
    hitDamage = hD;
    equippedItems = equip;
    x = xPos;
    y = yPos;
    textbox = t;
    spriteObj = new Sprite(p, PATH, x, y, 0);
    Sprite.sprites.add(spriteObj);
    player = p;
  }

  public Sprite getSprite()
  {
    return spriteObj;
  }
  public void updateEntity()
  {
    // movenment
    if (dist(x - speed, y) < dist(x,y) && Engine.dungeon[(int)(x-speed)][(int)y] == 0)
        updateXCoord(x - speed);

    else if (dist(x + speed, y) < dist(x,y) && Engine.dungeon[(int)(x+speed)][(int)y] == 0)
        updateXCoord(x + speed);
    if (dist(x, y - speed) < dist(x,y) && Engine.dungeon[(int)x][(int)(y-speed)] == 0)
        updateYCoord(y-speed);
    else if (dist(x, y+ speed) < dist(x,y) && Engine.dungeon[(int)x][(int)(y+speed)] == 0)
        updateYCoord(y+ speed);

     //hit
    if (Math.sqrt(dist(x,y)) < 2 && monsterHitSuccessful())
        Engine.staticHero.onPlayerHit(this);


  }
  public double dist(double x, double y)
  {
    return Math.pow(x-player.getX(),2) + Math.pow(y - player.getY(),2);
    }
  
  public void updateXCoord(double newX)
  {
    x = newX;
    spriteObj.x = newX;
  }
  
  public void updateYCoord(double newY)
  {
    y = newY;
    spriteObj.y = newY;
  }
  
  public double getXCoord()
  {
    return x;
  }
  
  public double getYCoord()
  {
    return y;
  }

  public int getTotalDamage()
  {
    int dmg = getHitDamage();
    Weapon weap = equippedItems.getWeapon();
    if(weap != null)
      dmg += weap.getWeaponDamage();
    return dmg;
  }

  public int getTotalArmorClass()
  {
    Armor arm = equippedItems.getArmor();
    if(arm != null)
      return arm.getArmorClass();
    return 0;
  }

  //expressed as a percentage in the form xx.xx%
  public double getHitAccuracy()
  {
    return hitAccuracy;
  }

  public Weapon getEquippedWeapon()
  {
    Weapon w = equippedItems.getWeapon();
    return w;
  }
  
  public void changeEquippedWeapon(Weapon weap)
  {
    equippedItems.switchWeapon(weap);
  }
  
  public Armor getEquippedArmor()
  {
    return equippedItems.getArmor();
  }

  public void changeEquippedArmor(Armor arm)
  {
    equippedItems.switchArmor(arm);
  }
  
  public int getHitDamage()
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

  public void setSpeed(double s)
  {
    speed = s;
  }

  //express in the form xx.xx
  public void setHitAccuracy(double hA)
  {
    hitAccuracy = hA;
  }

  public void setHealth(int h)
  {
    health = h;
  }

  public void sethitDamage(int hD)
  {
    hitDamage = hD;
  }

  public boolean monsterHitSuccessful()
  {
    double prob = Math.random() * 100;
    return prob >= this.getHitAccuracy();
  }

  public void setEquippedWeapon(Weapon weap)
  {
    equippedItems.switchWeapon(weap);
  }

  public void setEquippedArmor(Armor arm)
  {
    equippedItems.switchArmor(arm);
  }

  public void onMonsterAttack(Hero p)
  {
    if(monsterHitSuccessful())
      p.onPlayerHit(this);
  }
  
  // this is what happens when the monster gets hit
  public void onMonsterHit(Hero p)
  {
    Sound.playEnemyGrunt();
    //these two lines will shorten the code
    Armor arm = this.getEquippedArmor();
    Weapon weap = p.getEquippedWeapon();
    
    int pDmg = p.getTotalDamage();
    int mDef = this.getTotalArmorClass();
    
    int mDmg = 0;
    int armDmg = 0;
    
    if(arm != null)
      armDmg = pDmg - mDef;
    pDmg -= armDmg;
    this.setHealth(this.getHealth() - pDmg);
    
    //damage the weapon appropriately
    if(mDef > 0)
      weap.setDurability(weap.getDurability() - mDef);
    else
      weap.setDurability(weap.getDurability() - 3);
    
    if(weap.doesWeaponBreak())
    {  
      p.changeEquippedWeapon(Weapon.weaponArray[0]);
      textbox.addMessage("Your sword broke! You begin bashing monsters with your bare hands!");
    }  
    if(this.getEquippedArmor() != null)  
      if(this.getEquippedArmor().getDurability() <= 0)
        this.changeEquippedArmor(null);
  }
}
