import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Actor
{
    private double FPS;
    private double maxTime;
    private double timer;
    public Timer(double fps, double max)
    {
        maxTime = max;
        FPS = fps;
        timer = 0;
    }
    public void act()
    {
        if (timer < maxTime)
            timer += ((Game)getWorld()).getDeltaTime()*FPS;
        else
            timer = maxTime;
    }
    public double getTime()
    {
        return timer;
    }
    public void reset()
    {
        timer = 0;
    }
}
