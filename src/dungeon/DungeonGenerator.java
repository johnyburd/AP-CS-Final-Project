// uses prim's algorithim
// https://en.wikipedia.org/wiki/Maze_generation_algorithm

package src.dungeon;

import src.dungeon.Wall;
import src.logic.inventory.*;
import src.engine.Player;
import src.engine.Sprite;
import src.logic.entity.Entity;

import java.util.ArrayList;
import java.util.Random;

import src.hud.TextBox;
import src.engine.Engine;

public class DungeonGenerator
{
    private ArrayList<Wall> walls;
    private ArrayList<Wall> removedWalls;
    private ArrayList<Chest> chests;
    
    private static int[][] dun; //geon

    private Player player;
    private TextBox textbox;

    public DungeonGenerator(int size, Player p, TextBox t)
    {
        dun = new int[size][size];

        walls = new ArrayList<Wall>();
        removedWalls = new ArrayList<Wall>();

        player = p;

        textbox = t;
    }
    public int[][] getNewDungeon(int level)
    {
        int[] texs = new int[3];
        this.mazeGen();
        // convert maze into usable format of the engine
        for (int i = 0; i < dun.length; i++)
            for (int j = 0; j < dun[i].length; j++)
                if (dun[i][j] == 0)
                {
                    switch (level)
                    {
                        case 1:
                        {
                            texs[0] = 1;
                            texs[1] = 2;
                            //texs[2] = 3;
                            break;
                        }
                        case 2:
                        {
                            texs[0] = 3;
                            texs[1] = 4;
                            break;
                        }
                        case 3:
                        {
                            texs[0] = 5;
                            texs[1] = 6;
                            break;
                        }
                    }
                    int rand =(int)(Math.random()*3);
                    if (i < dun.length/2)
                        dun[i][j] = texs[0];
                    else
                        dun[i][j] = texs[1];
                }
                else
                    dun[i][j] = 0;
        setRandomChest(4);
        spawnBaddies(6, level);
        return dun;
    }

    private void mazeGen()
    {
        Random rand = new Random();
        // at this point 1 will be air and 0 walls

        dun[1][dun.length/2] = 1;
        //walls.add(new Wall(10, 9, false));
        //walls.add(new Wall(8, 9, false));
        //walls.add(new Wall(9,8, true));
        //walls.add(new Wall(9,10, true));
        walls.add(new Wall(2,dun.length/2, false));

        while (walls.size() > 0)
        {
//            for (Wall wa : walls)
                //System.out.println(wa);
            //System.out.println();
            int randWall = rand.nextInt(walls.size());
            Wall w = walls.get(randWall);

            boolean before = false, after = false, above = false, below = false;
            if (w.getHorz())
            {
                after = (dun[w.getRow()][w.getCol() + 1] == 1);

                before = (dun[w.getRow()][w.getCol() - 1] == 1);
            }
            else
            {
                above = (dun[w.getRow() + 1][w.getCol()] == 1);

                below = (dun[w.getRow() - 1][w.getCol()] == 1);
            }

            if (before && after)
                removedWalls.add(walls.remove(randWall));
            else if (above && below)
                removedWalls.add(walls.remove(randWall));
            else
            {
                if (w.getHorz())
                {
                    if (before)
                    {
                        //removedWalls.add(walls.remove(randWall));
                        dun[w.getRow()][w.getCol()] = 1;
                        dun[w.getRow()][w.getCol() + 1] = 1;
                        addApplicableWalls(new Wall(w.getRow(), w.getCol() + 1, true));
                    }
                    else
                    {
                        removedWalls.add(walls.remove(randWall));
                        dun[w.getRow()][w.getCol()] = 1;
                        dun[w.getRow()][w.getCol() - 1] = 1;
                        addApplicableWalls(new Wall(w.getRow(), w.getCol() - 1, true));
                    }
                }
                else
                {
                    if (below)
                    {
                        removedWalls.add(walls.remove(randWall));
                        dun[w.getRow()][w.getCol()] = 1;
                        dun[w.getRow() + 1][w.getCol()] = 1;
                        addApplicableWalls(new Wall(w.getRow()+1, w.getCol(), false));
                    }
                    else
                    {
                        removedWalls.add(walls.remove(randWall));
                        dun[w.getRow()][w.getCol()] = 1;
                        dun[w.getRow() - 1][w.getCol()] = 1;
                        addApplicableWalls(new Wall(w.getRow()-1, w.getCol(), false));
                    }
                }
            }
        }
    }

    private void addApplicableWalls(Wall w)
    {
        if (w.getRow() < dun.length -2 && noDup(w)) // -2
            walls.add(new Wall(w.getRow() + 1, w.getCol(), false));
        if (w.getRow() > 2 && noDup(w)) // 2
            walls.add(new Wall(w.getRow() - 1, w.getCol(), false));

        if (w.getCol() < dun[0].length - 2 && noDup(w)) // -2
            walls.add(new Wall(w.getRow(), w.getCol() + 1, true));
        if (w.getCol() > 2 && noDup(w)) // 2
            walls.add(new Wall(w.getRow(), w.getCol() -1, true));
    }
    private boolean noDup(Wall w)
    {
        boolean result = true;
        for (int i = 0; i < walls.size(); i++)
            if (walls.get(i).equals(w))
                result = false;
       // for (int i = 0; i < removedWalls.size(); i++)
         //   if (removedWalls.get(i).equals(w));
           //     result = false;
        return result;
    }
    
    private void setRandomChest(int numChests)
    {
        for(int i = 0; i < dun.length; i++)
            for(int j = 0; j < dun[i].length; j++)
            {
                int a = (int)(Math.random() * 100);
                if((a < 500 && numChests > 0 && dun[i][j] == 0 ) && ((dun[i][j-1] > 0 && dun[i+1][j] > 0 && dun[i][j-1] > 0) || (dun[i-1][j] > 0 && dun[i][j+1] > 0 && dun[i+1][j] > 0)))
                {
                    numChests--;
                   // dun[i][j] = 15;
                    Chest chest = new Chest(((double)i + 0.5), j + 0.5, player, textbox);
                    chest.randomizeChest();
                    if (chest == null)
                        System.out.println("NULL");
                    //chests.add(chest);
                    System.out.println("chest added");
                }
            }
    }
    
    private void spawnBaddies(int numBaddies, int dungeonLevel)
    {
        for(int i = 0; i < dun.length; i++)
            for(int j = 0; j < dun[i].length; j++)
            {
                int a = (int)(Math.random() * 100);
                if((a < 50 && numBaddies > 0 && dun[i][j] == 0 ))
                {
                    numBaddies--;
                   // dun[i][j] = 15
                    Entity monster = new Entity(0.045, 50.0, 10*dungeonLevel, 7 + dungeonLevel, Equipment.randomMonsterEquipment(dungeonLevel), (double)(i+0.5), (double)(j+0.5), textbox, player);
                    if (monster == null)
                        System.out.println("NULL");
                    System.out.println("monster added");
                }
            }
    }
}
