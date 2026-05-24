import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{
    private Vector pos;
    private static GreenfootImage texture = null;
    private double size;
    private double range = 2;
    
    public Enemy(double x, double y)
    {
        super();
        pos = new Vector(x, y);
        size = 4;
    }
    
    protected void addedToWorld(World world)
    {
        if (texture == null)
            texture = ((Game)world).setupTexture("guy.jpg", 256);
    }
    
    public void act()
    {
        //if (pos.getDist(((Game)getWorld()).getPlayer().getPos()) < range)
        //    getWorld().removeObject(this);
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
