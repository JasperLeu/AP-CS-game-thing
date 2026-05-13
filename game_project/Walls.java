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
    private ArrayList<int[]> walls = new ArrayList();
    /**
     * Act - do whatever the Walls wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Walls()
    {
        super();
        int[] wallVerts = {-10, 10, 10, 10, 10, -10, -10, -10};
        for (int i = 1; i < wallVerts.length; i+=2)
        {
            walls.add(new int[]{wallVerts[i-1], wallVerts[i]});
        }
    }
    public void act()
    {
        // Add your action code here.
    }
    public ArrayList<int[]> getWalls()
    {
        return walls;
    }
}
