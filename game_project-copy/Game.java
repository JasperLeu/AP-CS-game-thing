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
        super(1000, 600, 1);
        lastTime = System.currentTimeMillis();
        Greenfoot.setSpeed(100);
        prepare();
        setPaintOrder(Counter.class, EndScreen.class, Overlay.class, Crosshair.class, Animation.class, Renderer.class);
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
        for (int y = 0; y < texture.getHeight(); y++)
        {
            colors[y] = texture.getColorAt((int)(percent*texture.getWidth())%(texture.getWidth()), y);
        }
        return colors;
    }
    
    public void waveComplete() {
        player.incrementWave();
        player.addScore(10000 + 100*player.getHealth());
        map.updateWallsOnWave(this, player.getWave());
        map.updateEnemiesOnWave(this, player.getWave());
        if (player.getWave() > 8) {
            EndScreen deathScreen = new EndScreen(player.getScore(), player.getWave(), true);
            addObject(deathScreen, 500, 300);
        }
    }
    
    public GreenfootImage rescaleImage(GreenfootImage img, int newWidth)
    {
        double factor = (double)newWidth / img.getWidth();
        GreenfootImage newImage = new GreenfootImage(newWidth, (int)(img.getHeight()*factor));
        for (int x = 0; x < newImage.getWidth(); x++)
        {
            for (int y = 0; y < newImage.getHeight(); y++)
            {
                newImage.setColorAt(x, y, img.getColorAt((int)(x/factor), (int)(y/factor)));
            }
        }
        return newImage;
    }
    public GreenfootImage tintImage(GreenfootImage img, Color tint)
    {
        GreenfootImage newImage = new GreenfootImage(img.getWidth(), img.getHeight());
        for (int x = 0; x < newImage.getWidth(); x++)
        {
            for (int y = 0; y < newImage.getHeight(); y++)
            {
                Color c = img.getColorAt(x, y);
                int r = c.getRed() + tint.getRed();
                if (r > 255)
                    r = 255;
                int g = c.getGreen() + tint.getGreen();
                if (g > 255)
                    g = 255;
                int b = c.getBlue() + tint.getBlue();
                if (b > 255)
                    b = 255;
                newImage.setColorAt(x, y, new Color(r,g,b, c.getAlpha()));
            }
        }
        return newImage;
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
        addObject(new Player(),550,50);
        addObject(new Map(),580,380);
        addObject(new Crosshair(), getWidth()/2,getHeight()/2);
        Renderer renderer = new Renderer();
        addObject(renderer,getWidth()/2,getHeight()/2);
    }
}
