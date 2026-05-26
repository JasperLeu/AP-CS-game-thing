import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class Enemy extends Actor
{
    private Vector pos;
    private GreenfootImage texture = null;
    private static GreenfootImage defaultTexture = null;
    private double startSize;
    private double size;
    private double range = 2;
    private double speed = 0.2;
    
    private double[] hitAnimation;
    private static GreenfootImage hitTexture = null;
    private Timer hitAnimTimer;
    
    private int health;
    
    private Game gameWorld;
    private Renderer render;
    private Player player;
    
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
    }
    
    protected void addedToWorld(World world)
    {
        world.addObject(hitAnimTimer, 0, 0);
        
        if (texture == null)
            defaultTexture = ((Game)world).setupTexture("guy.jpg", 256);
        if (hitTexture == null)
            hitTexture = ((Game)world).tintImage(defaultTexture, new Color(100, 0, 0));
            
        texture = defaultTexture;
    }
    
    public void act()
    {
        updateAnimation();
        checkDeath();
        if (seesPlayer()) {
            moveToPlayer();
        }
    }
    
    public void moveToPlayer() {
        if (getWorld() == null) {
            return;
        }
        Player player = getWorld().getObjects(Player.class).get(0);
        Vector playerDirectionVector = player.getPos().minus(getPos());
        Vector angleUnitVector = new Vector(playerDirectionVector.getAngle());
        pos.add(angleUnitVector.times(speed));
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
