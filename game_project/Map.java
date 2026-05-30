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
        
	walls.add(new Wall(world, -11.25, -6.25, -11.25, -11.25));
	walls.add(new Wall(world, -11.25, -11.25, 8.75, -11.25));
	walls.add(new Wall(world, 8.75, -11.25, 8.75, 8.75));
	walls.add(new Wall(world, 8.75, 8.75, -11.25, 8.75));
	walls.add(new Wall(world, -11.25, 8.75, -11.25, -1.25));
	walls.add(new Wall(world, -11.25, -1.25, -16.25, -1.25));
	walls.add(new Wall(world, -16.25, -1.25, -16.25, 28.75));
	walls.add(new Wall(world, -16.25, 28.75, -11.25, 28.75));
	walls.add(new Wall(world, -11.25, 28.75, -11.25, 8.75));
	walls.add(new Wall(world, -11.25, -6.25, -36.25, -6.25));
	walls.add(new Wall(world, -36.25, -6.25, -36.25, -11.25));
	walls.add(new Wall(world, -36.25, -11.25, -11.25, -11.25));
	walls.add(new Wall(world, -46.25, -6.25, -41.25, -6.25));
	walls.add(new Wall(world, -41.25, -6.25, -41.25, -11.25));
	walls.add(new Wall(world, -41.25, -11.25, -46.25, -11.25));
	walls.add(new Wall(world, -46.25, -41.25, -46.25, 33.75));
	walls.add(new Wall(world, -46.25, 33.75, 28.75, 33.75));
	walls.add(new Wall(world, 28.75, 33.75, 28.75, -76.25));
	walls.add(new Wall(world, 28.75, -76.25, -16.25, -76.25));
	walls.add(new Wall(world, -16.25, -76.25, -16.25, -46.25));
	walls.add(new Wall(world, -16.25, -46.25, 18.75, -46.25));
	walls.add(new Wall(world, 18.75, -46.25, 18.75, -41.25));
	walls.add(new Wall(world, 18.75, -41.25, -46.25, -41.25));
	walls.add(new Wall(world, 28.75, -41.25, 23.75, -41.25));
	walls.add(new Wall(world, 23.75, -41.25, 23.75, -46.25));
	walls.add(new Wall(world, 23.75, -46.25, 28.75, -46.25));
	walls.add(new Wall(world, 8.75, -11.25, 8.75, -36.25));
	walls.add(new Wall(world, 8.75, -36.25, 13.75, -36.25));
	walls.add(new Wall(world, 13.75, -36.25, 13.75, 8.75));
	walls.add(new Wall(world, 13.75, 8.75, 8.75, 8.75));
	walls.add(new Wall(world, -21.25, -41.25, -21.25, -16.25));
	walls.add(new Wall(world, -21.25, -16.25, -16.25, -16.25));
	walls.add(new Wall(world, -16.25, -16.25, -16.25, -41.25));
	world.addObject(new Enemy(-30.9375, -35.625, 4), 0, 0);
	world.addObject(new Enemy(-9.6875, -30.9375, 4), 0, 0);
	world.addObject(new Enemy(-8.75, -54.0625, 4), 0, 0);
	world.addObject(new Enemy(-3.125, -68.75, 4), 0, 0);
	world.addObject(new Enemy(21.25, -19.6875, 4), 0, 0);
	world.addObject(new Enemy(-1.875, 14.6875, 4), 0, 0);
	world.addObject(new Enemy(-23.125, 14.6875, 4), 0, 0);
	world.addObject(new Enemy(-42.1875, 11.25, 4), 0, 0);
	world.addObject(new Enemy(21.875, 25.9375, 4), 0, 0);
	
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
