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
    private int lastMousePos;
    private double lastMouseInput;
    private double radius = 1;
    
    private Animation armAnim;
    private boolean isAttacking = false;
    
    public Player()
    {
        super();
        moveSpeed = 15;
        turnSpeed = 2;
        pos = new Vector(0, 0);
    }
    protected void addedToWorld(World world)
    {
        ((Game)world).setPlayer(this);
        armAnim = new Animation(new String[]{
            "frame1.png", 
            "frame2.png", 
            "frame3.png", 
            "frame4.png"}, 15);
        world.addObject(armAnim, world.getWidth()/2, world.getHeight()*4/7);
    }
    
    public void act()
    {
        move();
        turn();
        checkAttack();
        // Add your action code here.
    }
    
    public void checkAttack()
    {
        MouseInfo input = Greenfoot.getMouseInfo();
        if (!isAttacking && !armAnim.inAnimation() && input != null)
        {
            if (Greenfoot.getMouseInfo().getButton() == 1)
            {
                armAnim.play();
                isAttacking = true;
            }
        }
        if (Greenfoot.mouseClicked(null))
            isAttacking = false;
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
        double mSpd = moveSpeed * game.getDeltaTime();
        Vector movementVec = new Vector(0, 0);
        if (getCollision(game.getWalls()) && mSpd > radius)
            mSpd = radius;
        if (Greenfoot.isKeyDown("w")){
            movementVec.add(new Vector(mSpd * Math.cos(rot), mSpd * Math.sin(rot)));
        }
        if (Greenfoot.isKeyDown("s")){
            movementVec.add(new Vector(-mSpd * Math.cos(rot), -mSpd * Math.sin(rot)));
        }
        if (Greenfoot.isKeyDown("a")){
            movementVec.add(new Vector(mSpd * Math.cos(rot+Math.PI/2), mSpd * Math.sin(rot+Math.PI/2)));
        }
        if (Greenfoot.isKeyDown("d")){
            movementVec.add(new Vector(-mSpd * Math.cos(rot+Math.PI/2), -mSpd * Math.sin(rot+Math.PI/2)));
        }
        applyMovement(movementVec);
    }
    public void applyMovement(Vector moveVector)
    {
        Renderer r = ((Game)getWorld()).getGraphics();
        Vector closestHit = null;
        double closestDist = Double.MAX_VALUE;
        double angle = moveVector.getAngle();
        for (Wall w : ((Game)getWorld()).getWalls())
        {
            Vector hit = r.castRay(pos, w, angle);
            if (hit == null)
                continue;
            double dist = hit.getDist(pos);
            if (dist < closestDist){
                closestDist = dist;
                closestHit = hit;
            }
        }
        if (closestHit != null && closestDist <= moveVector.magnitude()){
            moveVector.normalize();
            moveVector.multiply(closestDist - radius);
        }
        pos.add(moveVector);
        getCollision(((Game)getWorld()).getWalls());
    }

    
    public void turn()
    {
        MouseInfo input = Greenfoot.getMouseInfo();
        if (input == null)
            return;
        rot += (input.getX()-lastMousePos)*-turnSpeed/(double)100;
        lastMousePos = input.getX();
    }
    
    
    public boolean getCollision(ArrayList<Wall> walls)
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
        if (minDist < radius){
            pos.add((new Vector(dir)).times(radius-minDist));
            return true;
        }
        return false;
    }
}
