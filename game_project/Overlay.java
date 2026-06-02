import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Overlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Overlay extends Actor
{
    GreenfootImage image;
    Color color;
    double startOpacity;
    Timer activationTimer;
    double fadeDuration;
    public Overlay(Color c, double duration)
    {
        startOpacity = c.getAlpha();
        fadeDuration = duration;
        activationTimer = new Timer(1, fadeDuration);
        activationTimer.finishTime();
        color = c;
    }
    
    protected void addedToWorld(World world)
    {
        world.addObject(activationTimer, 0, 0);
        image = new GreenfootImage(world.getWidth(), world.getHeight());
        image.setColor(color);
        image.fill();
        setImage(image);
    }
    public void act()
    {
        double progress = activationTimer.getTime() / fadeDuration;
        System.out.println(activationTimer.getTime());
        image.setTransparency((int)(startOpacity * (1-progress)));
    }
    public void activate()
    {
        activationTimer.reset();
    }
}
