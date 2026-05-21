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
    public Vector(double angle)
    {
        x = Math.cos(angle);
        y = Math.sin(angle);
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
    public Vector plus(Vector other)
    {
        return new Vector(x+other.getX(), y+other.getY());
    }
    
    public void subtract(Vector other)
    {
        x -= other.getX();
        y -= other.getY();
    }
    public Vector minus(Vector other)
    {
        return new Vector(x-other.getX(), y-other.getY());
    }
    
    public Vector times(double fac)
    {
        return new Vector(x * fac, y * fac);
    }
    
    public void normalize()
    {
        double mag = magnitude();
        x /= mag;
        y /= mag;
    }
    public Vector normalized()
    {
        double mag = magnitude();
        return new Vector(x / mag, y / mag);
    }
    
    public double getDist(Vector other)
    {
        return Math.sqrt(Math.pow(other.getX()-x, 2)+Math.pow(other.getY()-y, 2));
    }
    
    public double dot(Vector other)
    {
        return other.getX()*x + other.getY()*y;
    }
    
    public double getAngle(Vector other)
    {
        return Math.acos(dot(other) / (magnitude() * other.magnitude()));
    }
    
    public double getAngle()
    {
        return Math.atan2(y, x);
    }
    
    public double magnitude()
    {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    
    public double cross(Vector other)
    {
        return x*other.getY() - other.getX()*y;
    }
}
