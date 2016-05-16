// uses prim's algorithim
// https://en.wikipedia.org/wiki/Maze_generation_algorithm

package src.dungeon;

import src.dungeon.Wall;
import src.logic.inventory.*;
import src.logic.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class DungeonGenerator
{
    private ArrayList<Wall> walls;
    private ArrayList<Wall> removedWalls;

    private static int[][] dun; //geon

    public DungeonGenerator(int size)
    {
        dun = new int[size][size];

        walls = new ArrayList<Wall>();
        removedWalls = new ArrayList<Wall>();

    }
    public int[][] getNewDungeon()
    {
        this.mazeGen();
        for (int i = 0; i < dun.length; i++)
            for (int j = 0; j < dun[i].length; j++)
                if (dun[i][j] == 0)
                    if (i < dun.length/2)
                        dun[i][j] = 4;
                    else
                        dun[i][j] = 2;
                else
                    dun[i][j] = 0;
        return dun;
    }
    public static void main(String[] args)
    {
        DungeonGenerator dungeonMaster = new DungeonGenerator(20);

        dungeonMaster.mazeGen();
        int[][] dungeon = dungeonMaster.dun;

        for (int i = 0; i < dungeon.length; i++)
        {
            for (int j = 0; j < dungeon[i].length; j++)
            {
                System.out.print(dungeon[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void mazeGen()
    {
        Random rand = new Random();
        // at this point 1 will be air and 0 walls

        dun[9][9] = 1;
        walls.add(new Wall(10, 9, false));
        walls.add(new Wall(8, 9, false));
        walls.add(new Wall(9,8, true));
        walls.add(new Wall(9,10, true));

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
                if(a < 50 && numChests > 0 && ((dun[i][j-1] == 0 && dun[i+1][j] == 0 && dun[i][j-1] == 0) || (dun[i-1][j] == 0 && dun[i][j+1] == 0 && dun[i+1][j] == 0)))
                {
                    numChests--;
                    //spawn the chest at this point
                }
            }
    }
}
