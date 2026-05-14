import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Walls here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Walls extends Actor
{
    private int[][] wallPts;
    /**
     * Act - do whatever the Walls wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Walls()
    {
        super();
        wallPts = new int[][]{{-10, 30}, {20, 30}, {20, 18}, {0, 18}, {0, 10}, {10, 10}, {10, -10}, {-10, -10}};
    }
    public int[][] getWalls()
    {
        return wallPts;
    }
}
