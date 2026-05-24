import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class Map extends Actor
{
    private ArrayList<Wall> walls;
    private int wallTexWidth;
    private Color FLOOR_COLOR;
    private Color CEILING_COLOR;
    
    protected void addedToWorld(World world)
    {
        ((Game)world).setMap(this);
        walls = new ArrayList<Wall>();
        
        FLOOR_COLOR = new Color(140, 140, 140, 255);
        CEILING_COLOR = new Color(90, 90, 90);
        
        double[] pts = {-10, -10, -10, 7, 5, 7, 5, 0, 8, 0, 8, 20, 15, 20, 15, -10, -10, -10};
        // add all of the walls
        for (int i = 0; i < pts.length-3; i+=2)
        {
            walls.add(new Wall(world, pts[i], pts[i+1], pts[i+2], pts[i+3]));
        }
    }
    
    public Color getFloorColor()
    {
        return FLOOR_COLOR;
    }
    public Color getCeilingColor()
    {
        return CEILING_COLOR;
    }
    public ArrayList<Wall> getWalls()
    {
        return walls;
    }
}
