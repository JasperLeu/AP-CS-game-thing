import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Actor
{
    private Vector pos;
    private static GreenfootImage texture = null;
    private static final double size = 4;
    
    public Enemy(double x, double y)
    {
        super();
        pos = new Vector(x, y);
    }
    
    protected void addedToWorld(World world)
    {
        if (texture == null)
            texture = ((Game)world).setupTexture("guy.jpg", 256);
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
