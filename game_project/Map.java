import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Map extends Actor
{
    private int[][] wallPts;
    
    public Map()
    {
        super();
        wallPts = new int[][]{{-10, 30}, {20, 30}, {20, 18}, {0, 18}, {0, 10}, {10, 10}, {10, -10}, {-10, -10}};
    }
    protected void addedToWorld(World world)
    {
        ((Game)world).setMap(this);
    }
    public int[][] getWalls()
    {
        return wallPts;
    }
}
