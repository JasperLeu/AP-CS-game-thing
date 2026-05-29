import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndScreen extends Actor
{
    double score;
    double waves;
    public EndScreen(double score, double waves) {
        super();
        this.score =score;
        this.waves = waves;
    }
    protected void addedToWorld(World world)
    {
        Counter scoreCounter = new Counter("Score", score, Color.WHITE);
        world.addObject(scoreCounter, 734, 136);
        Counter waveCounter = new Counter("Wave", waves, Color.WHITE);
        world.addObject(waveCounter, 746, 280);
    }
}
