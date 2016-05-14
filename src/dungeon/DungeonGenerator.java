// uses prim's algorithim
// https://en.wikipedia.org/wiki/Maze_generation_algorithm

package src.dungeon;

import src.dungeon.Wall;

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
    public static void main(String[] args)
    {
        DungeonGenerator dungeonMaster = new DungeonGenerator(35);

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
        walls.add(new Wall(9,8,true));
        walls.add(new Wall(9,10,true));

        while (walls.size() > 0)
        {
//            for (Wall wa : walls)
                //System.out.println(wa);
            //System.out.println();
            int randWall = rand.nextInt(walls.size());
            Wall w = walls.get(randWall);

            boolean before = false, after = false;
            if (w.getHorz())
            {
                after = (w.getCol() + 1 == 1);

                before = (w.getCol() -1 == 1);
            }
            else
            {
                after = (w.getRow() + 1 == 1);

                before = (w.getRow() -1 == 1);
            }

            if (before && after)
                removedWalls.add(walls.remove(randWall));
            else
            {
                if (w.getHorz())
                {
                    if (before)
                    {
                        removedWalls.add(walls.remove(randWall));
                        dun[w.getRow()][w.getCol()] = 1;
                        dun[w.getRow()][w.getCol() + 1] = 1;
                        addApplicableWalls(new Wall(w.getRow(), w.getCol() + 2, true));
                    }
                    else
                    {
                        removedWalls.add(walls.remove(randWall));
                        dun[w.getRow()][w.getCol()] = 1;
                        dun[w.getRow()][w.getCol() - 1] = 1;
                        addApplicableWalls(new Wall(w.getRow(), w.getCol() - 2, true));
                    }
                }
                else
                {
                    if (before)
                    {
                        removedWalls.add(walls.remove(randWall));
                        dun[w.getRow()][w.getCol()] = 1;
                        dun[w.getRow() + 1][w.getCol()] = 1;
                        addApplicableWalls(new Wall(w.getRow()+2, w.getCol(), false));
                    }
                    else
                    {
                        removedWalls.add(walls.remove(randWall));
                        dun[w.getRow()][w.getCol()] = 1;
                        dun[w.getRow() - 1][w.getCol()] = 1;
                        addApplicableWalls(new Wall(w.getRow()-2, w.getCol(), false));
                    }
                }
            }
        }
    }

    private void addApplicableWalls(Wall w)
    {
        if (w.getRow() < dun.length && noDup(w)) // -3
            walls.add(new Wall(w.getRow() + 1, w.getCol(), false));
        if (w.getRow() > 0 && noDup(w)) // 2
            walls.add(new Wall(w.getRow() - 1, w.getCol(), false));

        if (w.getCol() < dun[0].length && noDup(w)) // -3
            walls.add(new Wall(w.getRow(), w.getCol() + 1, true));
        if (w.getCol() > 0 && noDup(w)) // 2
            walls.add(new Wall(w.getRow(), w.getCol() -1, true));
    }
    private boolean noDup(Wall w)
    {
        boolean result = true;
        for (int i = 0; i < walls.size(); i++)
            if (walls.get(i).equals(w))
                result = false;
        for (int i = 0; i < removedWalls.size(); i++)
            if (removedWalls.get(i).equals(w));
                result = false;
        return result;
    }
}


