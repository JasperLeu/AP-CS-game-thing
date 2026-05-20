/**
 * Write a description of class Vector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vector  
{
    // instance variables - replace the example below with your own
    private double x;
    private double y;

    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    
    public void add(Vector other)
    {
        x += other.getX();
        y += other.getY();
    }
    
    public double cross(Vector other)
    {
        return other.getX()*x + other.getY()*y;
    }
    
    public double getAngle(Vector other)
    {
        return Math.acos(cross(other) / (magnitude() * other.magnitude()));
    }
    
    public double magnitude()
    {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
