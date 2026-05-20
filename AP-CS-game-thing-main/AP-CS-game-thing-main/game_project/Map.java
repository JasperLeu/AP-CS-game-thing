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
    
    public Map()
    {
        super();
        walls = new ArrayList<Wall>();
        walls.add(new Wall(-10, -10, -10, 20));
    }
    protected void addedToWorld(World world)
    {
        ((Game)world).setMap(this);
    }
    public ArrayList<Wall> getWalls()
    {
        return walls;
    }
}
