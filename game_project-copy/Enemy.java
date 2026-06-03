import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class Enemy extends Actor
{
    private Vector pos;
    private GreenfootImage texture = null;
    private GreenfootImage defaultTexture = null;
    private double startSize = 5;
    private double size;
    
    private boolean cooldownActive = false;
    private double cooldownDuration = 1.0;
    
    private double[] hitAnimation;
    private GreenfootImage hitTexture = null;
    private Timer hitAnimTimer;
    
    //private GreenfootImage texture;
    private int health = 4;
    private double speed = 3;
    private int damage = 10;
    private double attackRange = 5;
    private double attackDelay = 2;
    
    private Timer attackTimer;
    private Game gameWorld;
    private Player player;
    private ArrayList<Wall> walls;
    
    public enum EnemyTypes {
        BASIC,
        TANK,
        SWIFT,
        RANGED,
        HEAL,
        BOSS,
    }
    
    public Enemy(double x, double y, EnemyTypes type) {
        super();
        
        pos = new Vector(x, y);
        initializeType(type);
        
        hitAnimation = new double[]{1, .9, .7, .75, 1};
        hitAnimTimer = new Timer(15, hitAnimation.length);
        attackTimer = new Timer(1, attackDelay);
    }
    
    public void initializeType(EnemyTypes type) {
        switch(type) {
            case EnemyTypes.BASIC:
                health = 2;
                startSize = 3;
                speed = 20;
                damage = 10;
                attackRange = 5;
                attackDelay = 1;
                defaultTexture = new GreenfootImage("enemy.png");
                break;
            case EnemyTypes.TANK:
                health = 5;
                startSize = 4;
                speed = 10;
                damage = 15;
                attackRange = 6;
                attackDelay = 2;
                defaultTexture = new GreenfootImage("tank.png");
                break;
            case EnemyTypes.SWIFT:
                health = 1;
                startSize = 3;
                speed = 40;
                damage = 5;
                attackRange = 2;
                attackDelay = 0.2;
                defaultTexture = new GreenfootImage("roach.png");
                break;
            case EnemyTypes.RANGED:
                health = 2;
                startSize = 3;
                speed = 5;
                damage = 10;
                attackRange = 50;
                attackDelay = 5;
                defaultTexture = new GreenfootImage("kitty.png");
                break;
            case EnemyTypes.BOSS:
                health = 30;
                startSize = 6;
                speed = 10;
                damage = 40;
                attackRange = 5;
                attackDelay = 3;
                defaultTexture = new GreenfootImage("guy.jpg");
                break;
        }
        size = startSize;
    }
    
    protected void addedToWorld(World world)
    {
        world.addObject(hitAnimTimer, 0, 0);
        world.addObject(attackTimer, 0, 0);
        
        if (defaultTexture == null)
            defaultTexture = ((Game)world).setupTexture("enemy.png", 256);
        if (hitTexture == null)
            hitTexture = ((Game)world).tintImage(defaultTexture, new Color(100, 0, 0));
        
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
        Vector toPlayer = player.getPos().minus(getPos());
        if (toPlayer.magnitude() < attackRange){
            if (attackTimer.getTime() >= attackDelay)
                attackPlayer();
            return;
        }
        Vector angleUnitVector = toPlayer.normalized();
        pos.add(angleUnitVector.times((speed)*((Game)getWorld()).getDeltaTime()));
    }
    
    public boolean seesPlayer() {
        if (gameWorld == null)
        {
            gameWorld = (Game)(getWorld());
            player = gameWorld.getPlayer();
            walls = gameWorld.getWalls();
        }
        Renderer render = gameWorld.getGraphics();
        
        double angleToPlayer = player.getPos().minus(getPos()).getAngle();
        for (Wall wall: walls) {
            Vector wallClosestPoint = render.castRay(getPos(), wall, angleToPlayer);
            if (wallClosestPoint != null && getPos().getDist(wallClosestPoint) < getPos().getDist(player.getPos())) 
                return false;
        }
        return true;
    }
    
    public void checkDeath()
    {
        if (health <= 0)
        {
            if (getWorld().getObjects(Enemy.class).size() <= 1) {
                gameWorld.waveComplete();
            }
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
        health --;
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
