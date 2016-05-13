// uses prim's algorithim
// https://en.wikipedia.org/wiki/Maze_generation_algorithm

package src.dungeon;

import java.util.ArrayList;
import java.util.Random;

public class DungeonGenerator
{
    private ArrayList<Wall> horzWalls;
    private ArrayList<Wall> vertWalls;
    private ArrayList<Wall> maze;

    private static int[][] dun; //geon

    public DungeonGenerator(int size)
    {
        dun = new int[size][size];

        walls = new ArrayList<Wall>();
        maze = new arrayList<Wall>();

    }
000000
000000
000000
    private int[][] mazeGen()
    {
        Random rand = new Random();
        // at this point 1 will be air and 0 walls

        dun[1][1] = 1;
        maze.add(new Wall(1, 1));
        walls.add(new Wall(2, 1));
        walls.add(new Wall(1,2));

        while (walls.size() > 0)
        {
            int randWall = rand.nextInt(walls.size());



