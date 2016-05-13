package src.dungeon;

public class Wall
{
    private int row;
    private int col;

    private boolean horizontal;
    

    public Wall(int r, int c, boolean h)
    {
        row = r;
        col = c;
        horizontal = h;
    }
    public Wall(int r, int c)
    {
        row = r;
        col = c;
        horizontal = false;
    }

    public int getRow()
    {
        return row;
    }
    public int getCol()
    {
        return col;
    }
    public boolean getHorz()
    {
        return horizontal;
    }
    public String toString()
    {
        return row + " " + col;
    }
    public boolean equals(Wall w)
    {
        if (row == w.row && w.col == col)
            return true;
        return false;
    }
}
