import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Crosshair here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Crosshair extends Actor
{
    
    public void act()
    {
        // Add your action code here.
    }
    protected void addedToWorld(World world)
    {
        setImage(((Game)world).rescaleImage(new GreenfootImage("crosshair.png"), world.getWidth()/20));
        setLocation(world.getWidth()/2, world.getHeight()/2);
    }
}
