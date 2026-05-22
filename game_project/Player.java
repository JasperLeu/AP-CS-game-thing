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
        moveSpeed = 10;
        turnSpeed = Math.PI;
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
        Game game = (Game)getWorld();
        getCollision(game.getWalls());
        double tSpd = turnSpeed * game.getDeltaTime();
        double mSpd = moveSpeed * game.getDeltaTime();
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
        double dir = 0;
        for (int i = 0; i < walls.size(); i++)
        {
            Vector wallVec = walls.get(i).getVector();
            double angle = wallVec.getAngle(pos.minus(walls.get(i).getPt1()));
            double distToPt1 = pos.getDist(walls.get(i).getPt1());
            double distToPt2 = pos.getDist(walls.get(i).getPt2());
            double wallLength = walls.get(i).getPt1().getDist(walls.get(i).getPt2());
            double dist = distToPt1 * Math.sin(angle);
            double posAlongWall = distToPt1 * Math.cos(angle);
            if (posAlongWall > wallLength || posAlongWall < 0){
                dist = Math.min(distToPt1, distToPt2);
                if (dist < minDist)
                {
                    if (dist == distToPt1)
                        dir = pos.minus(walls.get(i).getPt1()).getAngle();
                    else
                        dir = pos.minus(walls.get(i).getPt2()).getAngle();
                }
            }
            else if (dist < minDist){
                minDist = dist;
                dir = wallVec.getAngle() + Math.PI/2;
                if (wallVec.cross(pos.minus(walls.get(i).getPt1())) < 0)
                    dir -= Math.PI;
            }
        }
        if (minDist < radius)
            pos.add((new Vector(dir)).times(radius-minDist));
        return null;
    }
}
