import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and Mouse
import java.util.ArrayList;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends World
{
    private Renderer graphics;
    private Map map;
    private Player player;
    private double deltaTime;
    private double lastTime;
    
    public Game()
    {   
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        lastTime = System.currentTimeMillis();
        Greenfoot.setSpeed(100);
        prepare();
        addObject(new Enemy(-5, -5), 550, 200);
        addObject(new Enemy(2, 2), 550, 200);
    }
    
    public void act()
    {
        deltaTime = (System.currentTimeMillis()-lastTime)/1000.0;
        lastTime = System.currentTimeMillis();
    }
    
    public double getDeltaTime()
    {
        return deltaTime;
    }
    
    public GreenfootImage setupTexture(String imgName, int resolution)
    {
        GreenfootImage newTexture = new GreenfootImage(resolution, resolution);
        GreenfootImage originalTex = new GreenfootImage(imgName);
        double scaleFac = Math.min(originalTex.getWidth(), originalTex.getHeight())/(double)resolution;
        for (int x = 0; x < resolution; x++)
        {
            for (int y = 0; y < resolution; y++)
            {
                newTexture.setColorAt(x, y, originalTex.getColorAt((int)(x*scaleFac), (int)(y*scaleFac))); 
            }
        }
        return newTexture;
    }
    public Color[] sampleTexture(GreenfootImage texture, double percent)
    {
        Color[] colors = new Color[texture.getHeight()];
        for (int y = 0; y < texture.getWidth(); y++)
        {
            colors[y] = texture.getColorAt((int)(percent*texture.getWidth())%(texture.getWidth()), y);
        }
        return colors;
    }
    
    public void setGraphics(Renderer r)
    {
        graphics = r;
    }
    public Renderer getGraphics()
    {
        return graphics;
    }
    
    public void setMap(Map m)
    {
        map = m;
    }
    public Map getMap()
    {
        return map;
    }
    public ArrayList<Wall> getWalls()
    {
        return map.getWalls();
    }
    
    public void setPlayer(Player p)
    {
        player = p;
    }
    public Player getPlayer()
    {
        return player;
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Player player = new Player();
        addObject(player,550,50);
        Map map = new Map();
        addObject(map,580,380);
        Renderer renderer = new Renderer();
        addObject(renderer,550,350);
    }
}
