import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class Enemy extends Actor
{
    private Vector pos;
    private GreenfootImage texture = null;
    private static GreenfootImage defaultTexture = null;
    private double startSize;
    private double size;
    private double speed = 3;
    
    private double[] hitAnimation;
    private static GreenfootImage hitTexture = null;
    private Timer hitAnimTimer;
    
    private int health;
    private int damage = 10;
    private double attackRange = 5;
    private double attackDelay = 2;
    private Timer attackTimer;
    
    private Game gameWorld;
    
    private ArrayList<Wall> walls;
    
    public Enemy(double x, double y, int h)
    {
        super();
        
        pos = new Vector(x, y);
        health = h;
        startSize = h;
        size = startSize;
        
        hitAnimation = new double[]{1, .9, .7, .75, 1};
        hitAnimTimer = new Timer(15, hitAnimation.length);
        attackTimer = new Timer(1, attackDelay);
    }
    
    protected void addedToWorld(World world)
    {
        world.addObject(hitAnimTimer, 0, 0);
        world.addObject(attackTimer, 0, 0);
        
        if (texture == null)
            defaultTexture = ((Game)world).setupTexture("enemy.png", 256);
        if (hitTexture == null)
            hitTexture = ((Game)world).tintImage(defaultTexture, new Color(100, 0, 0));
            
        texture = defaultTexture;
    }
    
    public void act()
    {
        updateAnimation();
        if (seesPlayer())
            moveToPlayer();
        checkDeath();
    }

    public void attackPlayer()
    {
        ((Game)getWorld()).getPlayer().damage(damage);
        attackTimer.reset();
    }
    
    public void moveToPlayer() {
        Player player = ((Game)getWorld()).getPlayer();
        Vector toPlayer = player.getPos().minus(getPos());
        if (toPlayer.magnitude() < attackRange){
            if (attackTimer.getTimer() >= attackDelay)
                attackPlayer();
            return;
        }
        Vector angleUnitVector = toPlayer.normalized();
        pos.add(angleUnitVector.times((speed+(double)health/2)*((Game)getWorld()).getDeltaTime()));
    }
    
    public boolean seesPlayer() {
        if (getWorld() == null) {
            return false;
        }
        
        gameWorld = (Game)(getWorld());
        render = gameWorld.getGraphics();
        player = gameWorld.getPlayer();
        walls = gameWorld.getWalls();
        
        Vector playerDirectionVector = player.getPos().minus(getPos());
        for (Wall wall: walls) {
            Vector wallClosestPoint = render.castRay(getPos(), wall, playerDirectionVector.getAngle());
            if (wallClosestPoint != null && getPos().getDist(wallClosestPoint) < getPos().getDist(player.getPos())) return false;
        }
        return true;
    }
    
    public void checkDeath()
    {
        if (health <= 0)
        {
            getWorld().removeObject(hitAnimTimer);
            getWorld().removeObject(this);
        }
    }
    
    public void updateAnimation()
    {
        size = startSize * hitAnimation[(int)(hitAnimTimer.getTime())%hitAnimation.length];
        if (size != startSize){
            texture = hitTexture;
        }
        else
            texture = defaultTexture;
    }
    
    public boolean hit()
    {
        hitAnimTimer.reset();
        health--;
        if (health <= 0)
            return true;
        return false;
    }
    
    public GreenfootImage getTexture()
    {
        return texture;
    }
    
    public Vector getPos()
    {
        return pos;
    }
    
    public double getSize()
    {
        return size;
    }
    
    
    
}
