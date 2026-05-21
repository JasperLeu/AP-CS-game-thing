import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Map here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class Map extends Actor
{
    private ArrayList<Wall> walls;
    private GreenfootImage wallTexture;
    private int wallTexWidth;
    
    public Map()
    {
        super();
        walls = new ArrayList<Wall>();
        double[] pts = {-10, -10, -10, 7, 5, 7, 5, 0, 8, 0, 8, 20, 15, 20, 15, -10, -10, -10};
        // add all of the walls
        for (int i = 0; i < pts.length-3; i+=2)
        {
            walls.add(new Wall(pts[i], pts[i+1], pts[i+2], pts[i+3]));
        }
        
        wallTexWidth = 64;
        wallTexture = new GreenfootImage(wallTexWidth, wallTexWidth);
        GreenfootImage originalTex = new GreenfootImage("brick.jpg");
        double scaleFac = Math.min(originalTex.getWidth(), originalTex.getHeight())/(double)wallTexWidth;
        for (int x = 0; x < wallTexWidth; x++)
        {
            for (int y = 0; y < wallTexWidth; y++)
            {
                wallTexture.setColorAt(x, y, originalTex.getColorAt((int)(x*scaleFac), (int)(y*scaleFac))); 
            }
        }
    }
    protected void addedToWorld(World world)
    {
        ((Game)world).setMap(this);
    }
    
    public Color[] getTextureCol(double percent)
    {
        Color[] colors = new Color[wallTexWidth];
        for (int y = 0; y < wallTexWidth; y++)
        {
            colors[y] = wallTexture.getColorAt((int)(percent
        }
    }
    
    public ArrayList<Wall> getWalls()
    {
        return walls;
    }
}
