import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private double x;
    private double y;
    private double rot;
    private double moveSpeed;
    private double turnSpeed;
    
    public Player()
    {
        super();
        moveSpeed = 5;
        turnSpeed = Math.PI/2;
    }
    
    public void act()
    {
        move();
        //setLocation((int)(x*10+200), (int)(-y*10+200));
        setRotation((int)(-rot * 180 / Math.PI)+90);
        // Add your action code here.
    }
    
    public double[] getPos()
    {
        return new double[]{x, y};
    }
    public double getRot()
    {
        return rot;
    }
    
    public void move()
    {
        double tSpd = turnSpeed / 60;
        double mSpd = moveSpeed / 60;
        if (Greenfoot.isKeyDown("left"))
            rot += tSpd;
        if (Greenfoot.isKeyDown("right"))
            rot -= tSpd;
        if (Greenfoot.isKeyDown("w")){
            x += mSpd * Math.cos(rot);
            y += mSpd * Math.sin(rot);
        }
        if (Greenfoot.isKeyDown("s")){
            x -= mSpd * Math.cos(rot);
            y -= mSpd * Math.sin(rot);
        }
        if (Greenfoot.isKeyDown("a")){
            x += mSpd * Math.cos(rot+Math.PI/2);
            y += mSpd * Math.sin(rot+Math.PI/2);
        }
        if (Greenfoot.isKeyDown("d")){
            x += mSpd * Math.cos(rot-Math.PI/2);
            y += mSpd * Math.sin(rot-Math.PI/2);
        }
    }
}
