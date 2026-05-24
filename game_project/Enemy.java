import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{
    private Vector pos;
    private GreenfootImage texture = null;
    private static GreenfootImage defaultTexture = null;
    private double startSize;
    private double size;
    private double range = 2;
    
    private double[] hitAnimation;
    private static GreenfootImage hitTexture = null;
    private Timer hitAnimTimer;
    
    public Enemy(double x, double y)
    {
        super();
        pos = new Vector(x, y);
        startSize = 2;
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
    }
    
    public void updateAnimation()
    {
        size = startSize * hitAnimation[(int)(hitAnimTimer.getTime())%hitAnimation.length];
        if (size != startSize)
            texture = hitTexture;
        else
            texture = defaultTexture;
    }
    
    public void playHitAnimation()
    {
        hitAnimTimer.reset();
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
