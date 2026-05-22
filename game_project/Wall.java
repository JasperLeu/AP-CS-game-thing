import greenfoot.*;

/**
 * Write a description of class Wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall  
{
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private GreenfootImage texture;
    
    public Wall(double x1, double y1, double x2, double y2, GreenfootImage tex)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        texture = tex;
    }
    
    public Color[] sampleWallTexture(double percent)
    {
        Color[] colors = new Color[texture.getHeight()];
        for (int y = 0; y < texture.getWidth(); y++)
        {
            colors[y] = texture.getColorAt((int)(percent*texture.getWidth())%(texture.getWidth()), y);
        }
        return colors;
    }
    
    public boolean isVertical()
    {
        return x1 == x2;
    }
    
    public Vector getPt1()
    {
        return new Vector(x1, y1);
    }
    public Vector getPt2()
    {
        return new Vector(x2, y2);
    }
    
    public double getX1()
    {
        return x1;
    }
    public double getX2()
    {
        return x2;
    }
    public double getY1()
    {
        return y1;
    }
    public double getY2()
    {
        return y2;
    }
    
    public Vector getVector()
    {
        return new Vector(x2-x1, y2-y1);
    }
}
