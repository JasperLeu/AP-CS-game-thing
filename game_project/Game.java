import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and Mouse
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
    
    public Game()
    {   
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        Greenfoot.setSpeed(60);
        prepare();
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
    public int[][] getWalls()
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
