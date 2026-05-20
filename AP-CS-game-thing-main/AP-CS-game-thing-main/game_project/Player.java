import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
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
    private Vector pos;
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
        pos = new Vector(0, 0);
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
    
    public Vector getPos()
    {
        return pos;
    }
    public double getRot()
    {
        return rot;
    }
    
    public void move()
    {
        //getCollision(((Game)getWorld()).getWalls());
        double tSpd = turnSpeed / 60;
        double mSpd = moveSpeed / 60;
        if (Greenfoot.isKeyDown("left"))
            rot += tSpd;
        if (Greenfoot.isKeyDown("right"))
            rot -= tSpd;
        if (Greenfoot.isKeyDown("w")){
            pos.add(new Vector(mSpd * Math.cos(rot), mSpd * Math.sin(rot)));
        }
        if (Greenfoot.isKeyDown("s")){
            pos.add(new Vector(-mSpd * Math.cos(rot), -mSpd * Math.sin(rot)));
        }
        if (Greenfoot.isKeyDown("a")){
            pos.add(new Vector(mSpd * Math.cos(rot+Math.PI/2), mSpd * Math.sin(rot+Math.PI/2)));
        }
        if (Greenfoot.isKeyDown("d")){
            pos.add(new Vector(-mSpd * Math.cos(rot+Math.PI/2), -mSpd * Math.sin(rot+Math.PI/2)));
        }
    }
    
    public Vector getCollision(ArrayList<Wall> walls)
    {
        double minDist = Double.MAX_VALUE;
        for (int i = 1; i < walls.size(); i++)
        {
            double angle = walls.get(i).getVector().getAngle(pos.minus(walls.get(i).getPt1()));
            double distToPt1 = pos.getDist(walls.get(i).getPt1());
            double wallLength = walls.get(i).getPt1().getDist(walls.get(i).getPt2());
            double dist = distToPt1 * Math.sin(angle);
            if (distToPt1 * Math.cos(angle) > wallLength || distToPt1 * Math.cos(angle) < 0)
                dist = Math.min(distToPt1, pos.getDist(walls.get(i).getPt2()));
            if (dist < minDist)
                minDist = dist;
        }
        return null;
    }
}
