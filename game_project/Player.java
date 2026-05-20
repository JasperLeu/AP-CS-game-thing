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
    private double radius = .5;
    
    public Player()
    {
        super();
        moveSpeed = 3;
        turnSpeed = Math.PI/4;
    }
    protected void addedToWorld(World world)
    {
        ((Game)world).setPlayer(this);
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
    
    public double[] getCollision(double[][] walls)
    {
        for (int i = 1; i < walls.length; i++)
        {
            if (walls[i] == null || walls[i-1] == null)
                continue;
            double aSquared = Math.pow(walls[i-1][0]-x, 2)+Math.pow(walls[i-1][1]-y, 2);
            double b = Math.sqrt(Math.pow(walls[i][0]-x, 2)+Math.pow(walls[i][1]-y, 2));
            double c = Math.sqrt(Math.pow(walls[i-1][0]-walls[i][0], 2)+Math.pow(walls[i-1][1]-walls[i][1], 2));
            double dist = b * Math.sin(Math.acos((Math.pow(b,2)+Math.pow(c,2)+aSquared) / (2*b*c)));
            if (dist < radius)
            {
                double angle = Math.atan2(walls[i][1]-walls[i-1][1], walls[i][0]-walls[i-1][0]) - Math.PI/2;
            }
        }
        return null;
    }
}
