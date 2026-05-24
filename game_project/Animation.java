import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Animation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Animation extends Actor
{
    double frameRate;
    GreenfootImage[] animFrames;
    String[] frameNames;
    int currFrame = 0;
    double timer = 0;
    public Animation(String[] frames, double frameRate)
    {
        frameNames = frames;
        this.frameRate = frameRate;
    }
    protected void addedToWorld(World world)
    {
        animFrames = new GreenfootImage[frameNames.length];
        for (int i = 0; i < frameNames.length; i++)
        {
            animFrames[i] = ((Game)world).rescaleImage(new GreenfootImage(frameNames[i]), world.getWidth());
        }
    }
    public void act()
    {
        if (timer < animFrames.length)
            timer += ((Game)getWorld()).getDeltaTime() * frameRate;
        setImage(animFrames[(int)timer%animFrames.length]);
    }
    public boolean inAnimation()
    {
        return timer < animFrames.length;
    }
    public void play()
    {
        timer = 0;
    }
}
