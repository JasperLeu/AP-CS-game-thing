import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and Mouse
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {   
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        Greenfoot.setSpeed(60);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Player player = new Player();
        addObject(player,410,101);
        player.setLocation(494,75);
        Walls walls = new Walls();
        addObject(walls,524,85);
        walls.setLocation(422,94);
        player.setLocation(474,119);
        Renderer renderer = new Renderer();
        addObject(renderer,472,192);
        renderer.setLocation(432,200);
        renderer.setLocation(417,156);
        walls.setLocation(440,119);
        walls.setLocation(329,137);
        renderer.setLocation(440,242);
        player.setLocation(485,153);
    }
}
