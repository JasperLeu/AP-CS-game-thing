import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class Enemy extends Actor
{
    private Vector pos;
    private GreenfootImage texture = null;
    private static GreenfootImage defaultTexture = null;
    private double startSize;
    private double size;
    
    // Enemy stats
    private int health;
    private double attackRange = 2;
    private double speed = 3;
    private int damageAmount = 10;
    
    private boolean cooldownActive = false;
    private double cooldownDuration = 1.0;
    
    private double[] hitAnimation;
    private static GreenfootImage hitTexture = null;
    private Timer hitAnimTimer;
    
    private Game gameWorld;
    private Renderer render;
    private Player player;
    private Timer cooldownTimer;
    
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
        cooldownTimer = new Timer(cooldownDuration);
    }
    
    protected void addedToWorld(World world)
    {
        world.addObject(hitAnimTimer, 0, 0);
        world.addObject(cooldownTimer, 0, 0);
        
        if (texture == null)
            defaultTexture = ((Game)world).setupTexture("enemy.png", 256);
        if (hitTexture == null)
            hitTexture = ((Game)world).tintImage(defaultTexture, new Color(100, 0, 0));
            
        texture = defaultTexture;
    }
    
    public void act()
    {
        updateAnimation();
        checkDeath();
        if (seesPlayer())
            moveToPlayer();
    }
    
    public void attackPlayer() {
        Player player = gameWorld.getPlayer();
        player.takeDamage(damageAmount);
    }
    
    public void moveToPlayer() {
        Player player = getWorld().getObjects(Player.class).get(0);
        Vector toPlayer = player.getPos().minus(getPos());
        if (toPlayer.magnitude() <= attackRange) {
            if (cooldownTimer.getTime() >= cooldownDuration) {
                attackPlayer();
                cooldownTimer.reset();
            }
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
        player = getWorld().getObjects(Player.class).get(0);
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
    
    public void hit()
    {
        hitAnimTimer.reset();
        health--;
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
