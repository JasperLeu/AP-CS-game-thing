import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Actor
{
    String counterLabel;
    double value;
    Color textColor;
    
    public Counter(String label, double startValue, Color color)
    {
        super();
        value = startValue;
        textColor = color;
        counterLabel = label;
    }
    
    protected void addedToWorld(World world)
    {
        updateText();
    }
    
    public double getValue()
    {
        return value;
    }
    
    public void setValue(double v)
    {
        value = v;
        updateText();
    }
    
    public void add(double change)
    {
        value += change;
        updateText();
    }
    
    public void updateText()
    {
        String val = ""+value;
        if (value == (int)value)
            val = ""+(int)value;
        setImage(new GreenfootImage(counterLabel + ": " + value, getWorld().getHeight()/15, textColor, new Color(0, 0, 0, 0)));
    }
}
