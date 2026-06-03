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
    private int wallTexWidth;
    private Color FLOOR_COLOR;
    private Color CEILING_COLOR;
    
    protected void addedToWorld(World world)
    {
        ((Game)world).setMap(this);
        
        FLOOR_COLOR = new Color(140, 140, 140, 255);
        CEILING_COLOR = new Color(90, 90, 90);        
        
        updateWallsOnWave(world, 1);
        updateEnemiesOnWave(world, 1);
    
    }
    public void updateWallsOnWave(World world, int wave) {
        ((Game)world).setMap(this);
        walls = new ArrayList<Wall>();
        
        //room 1
        walls.add(new Wall(world, -6.25, -26.25, -6.25, -6.25, "weave.jpg"));
        walls.add(new Wall(world, -6.25, -6.25, -26.25, -6.25, "weave.jpg"));
        walls.add(new Wall(world, -26.25, -6.25, -26.25, 23.75, "weave.jpg"));
        walls.add(new Wall(world, -26.25, 23.75, -21.25, 23.75, "weave.jpg"));
        walls.add(new Wall(world, -6.25, 23.75, 23.75, 23.75, "weave.jpg"));
        walls.add(new Wall(world, 23.75, 23.75, 23.75, 18.75, "weave.jpg"));
        walls.add(new Wall(world, 23.75, 3.75, 23.75, -26.25, "weave.jpg"));
        walls.add(new Wall(world, 23.75, -26.25, 18.75, -26.25, "weave.jpg"));
        walls.add(new Wall(world, 3.75, -26.25, -6.25, -26.25, "weave.jpg"));
        walls.add(new Wall(world, 3.75, -26.25, 3.75, -36.25, "weave.jpg"));
        walls.add(new Wall(world, 18.75, -26.25, 18.75, -36.25, "weave.jpg"));
        walls.add(new Wall(world, 23.75, 3.75, 33.75, 3.75, "weave.jpg"));
        walls.add(new Wall(world, 23.75, 18.75, 33.75, 18.75, "weave.jpg"));
        walls.add(new Wall(world, -6.25, 23.75, -6.25, 33.75, "weave.jpg"));
        walls.add(new Wall(world, -21.25, 23.75, -21.25, 33.75, "weave.jpg"));
        walls.add(new Wall(world, 33.75, 3.75, 33.75, -26.25, "rivets.jpg"));
        walls.add(new Wall(world, 33.75, -26.25, 38.75, -26.25, "rivets.jpg"));
        walls.add(new Wall(world, 53.75, -26.25, 78.75, -26.25, "rivets.jpg"));
        walls.add(new Wall(world, 78.75, -26.25, 78.75, 43.75, "rivets.jpg"));
        walls.add(new Wall(world, 33.75, 18.75, 33.75, 48.75, "rivets.jpg"));
        walls.add(new Wall(world, 33.75, 48.75, 38.75, 48.75, "rivets.jpg"));
        walls.add(new Wall(world, 53.75, 48.75, 78.75, 48.75, "rivets.jpg"));
        walls.add(new Wall(world, 78.75, 48.75, 78.75, 43.75, "rivets.jpg"));
        walls.add(new Wall(world, 53.75, -6.25, 58.75, -6.25, "rivets.jpg"));
        walls.add(new Wall(world, 58.75, -6.25, 58.75, 28.75, "rivets.jpg"));
        walls.add(new Wall(world, 58.75, 28.75, 53.75, 28.75, "rivets.jpg"));
        walls.add(new Wall(world, 53.75, 28.75, 53.75, -6.25, "rivets.jpg"));
        walls.add(new Wall(world, 38.75, -26.25, 38.75, -36.25, "rivets.jpg"));
        walls.add(new Wall(world, 53.75, -26.25, 53.75, -36.25, "rivets.jpg"));
        walls.add(new Wall(world, 38.75, 48.75, 38.75, 58.75, "rivets.jpg"));
        walls.add(new Wall(world, 53.75, 48.75, 53.75, 58.75, "rivets.jpg"));
        walls.add(new Wall(world, 18.75, -36.25, 38.75, -36.25, "brick.jpg"));
        walls.add(new Wall(world, 53.75, -36.25, 83.75, -36.25, "brick.jpg"));
        walls.add(new Wall(world, 83.75, -36.25, 83.75, -81.25, "brick.jpg"));
        walls.add(new Wall(world, 83.75, -81.25, -6.25, -81.25, "brick.jpg"));
        walls.add(new Wall(world, -6.25, -81.25, -6.25, -91.25, "brick.jpg"));
        walls.add(new Wall(world, -6.25, -91.25, -61.25, -91.25, "brick.jpg"));
        walls.add(new Wall(world, -61.25, -91.25, -61.25, -26.25, "brick.jpg"));
        walls.add(new Wall(world, 3.75, -36.25, -26.25, -36.25, "brick.jpg"));
        walls.add(new Wall(world, -26.25, -36.25, -26.25, -26.25, "brick.jpg"));
        walls.add(new Wall(world, -26.25, -26.25, -36.25, -26.25, "brick.jpg"));
        walls.add(new Wall(world, -61.25, -26.25, -51.25, -26.25, "brick.jpg"));
        walls.add(new Wall(world, -46.25, -76.25, -46.25, -41.25, "brick.jpg"));
        walls.add(new Wall(world, -46.25, -41.25, -41.25, -41.25, "brick.jpg"));
        walls.add(new Wall(world, -41.25, -41.25, -41.25, -76.25, "brick.jpg"));
        walls.add(new Wall(world, -41.25, -76.25, -46.25, -76.25, "brick.jpg"));
        walls.add(new Wall(world, -6.25, -61.25, -6.25, -56.25, "brick.jpg"));
        walls.add(new Wall(world, -6.25, -56.25, -1.25, -56.25, "brick.jpg"));
        walls.add(new Wall(world, -1.25, -56.25, -1.25, -61.25, "brick.jpg"));
        walls.add(new Wall(world, -1.25, -61.25, -6.25, -61.25, "brick.jpg"));
        walls.add(new Wall(world, 23.75, -61.25, 23.75, -56.25, "brick.jpg"));
        walls.add(new Wall(world, 23.75, -56.25, 28.75, -56.25, "brick.jpg"));
        walls.add(new Wall(world, 28.75, -56.25, 28.75, -61.25, "brick.jpg"));
        walls.add(new Wall(world, 28.75, -61.25, 23.75, -61.25, "brick.jpg"));
        walls.add(new Wall(world, 53.75, -61.25, 53.75, -56.25, "brick.jpg"));
        walls.add(new Wall(world, 53.75, -56.25, 58.75, -56.25, "brick.jpg"));
        walls.add(new Wall(world, 58.75, -56.25, 58.75, -61.25, "brick.jpg"));
        walls.add(new Wall(world, 58.75, -61.25, 53.75, -61.25, "brick.jpg"));
        walls.add(new Wall(world, -41.25, -76.25, -21.25, -76.25, "brick.jpg"));
        walls.add(new Wall(world, -21.25, -76.25, -21.25, -71.25, "brick.jpg"));
        walls.add(new Wall(world, -21.25, -71.25, -41.25, -71.25, "brick.jpg"));
        walls.add(new Wall(world, -51.25, -26.25, -51.25, 38.75, "brick.jpg"));
        walls.add(new Wall(world, -36.25, -26.25, -36.25, 38.75, "brick.jpg"));
        walls.add(new Wall(world, -36.25, 38.75, -26.25, 38.75, "brick.jpg"));
        walls.add(new Wall(world, -26.25, 53.75, -36.25, 53.75, "brick.jpg"));
        walls.add(new Wall(world, -51.25, 38.75, -51.25, 93.75, "brick.jpg"));
        walls.add(new Wall(world, -51.25, 93.75, -26.25, 93.75, "brick.jpg"));
        walls.add(new Wall(world, -26.25, 78.75, -36.25, 78.75, "brick.jpg"));
        walls.add(new Wall(world, -36.25, 78.75, -36.25, 53.75, "brick.jpg"));
        walls.add(new Wall(world, 38.75, 58.75, 28.75, 58.75, "brick.jpg"));
        walls.add(new Wall(world, 28.75, 73.75, 38.75, 73.75, "brick.jpg"));
        walls.add(new Wall(world, 53.75, 58.75, 53.75, 118.75, "brick.jpg"));
        walls.add(new Wall(world, 53.75, 118.75, 28.75, 118.75, "brick.jpg"));
        walls.add(new Wall(world, 28.75, 103.75, 38.75, 103.75, "brick.jpg"));
        walls.add(new Wall(world, 38.75, 103.75, 38.75, 73.75, "brick.jpg"));
        walls.add(new Wall(world, -26.25, 38.75, -26.25, 33.75, "paper.jpg"));
        walls.add(new Wall(world, -26.25, 33.75, -21.25, 33.75, "paper.jpg"));
        walls.add(new Wall(world, -6.25, 33.75, -1.25, 33.75, "paper.jpg"));
        walls.add(new Wall(world, -1.25, 33.75, -1.25, 68.75, "paper.jpg"));
        walls.add(new Wall(world, -1.25, 68.75, -26.25, 68.75, "paper.jpg"));
        walls.add(new Wall(world, -26.25, 68.75, -26.25, 53.75, "paper.jpg"));
        walls.add(new Wall(world, -26.25, 78.75, -26.25, 73.75, "paper.jpg"));
        walls.add(new Wall(world, -26.25, 73.75, 3.75, 73.75, "paper.jpg"));
        walls.add(new Wall(world, 3.75, 73.75, 3.75, 33.75, "paper.jpg"));
        walls.add(new Wall(world, 3.75, 33.75, 28.75, 33.75, "paper.jpg"));
        walls.add(new Wall(world, 28.75, 33.75, 28.75, 58.75, "paper.jpg"));
        walls.add(new Wall(world, 28.75, 73.75, 28.75, 103.75, "paper.jpg"));
        walls.add(new Wall(world, 28.75, 118.75, 28.75, 143.75, "paper.jpg"));
        walls.add(new Wall(world, 28.75, 143.75, 3.75, 143.75, "paper.jpg"));
        walls.add(new Wall(world, 3.75, 143.75, 3.75, 98.75, "paper.jpg"));
        walls.add(new Wall(world, 3.75, 98.75, -26.25, 98.75, "paper.jpg"));
        walls.add(new Wall(world, -26.25, 98.75, -26.25, 93.75, "paper.jpg"));
        
        
        if (wave <= 6) {
            walls.add(new Wall(world, -51.25, -16.25, -36.25, -16.25, "space.jpg"));
            walls.add(new Wall(world, -36.25, 38.75, -36.25, 53.75, "space.jpg"));
            walls.add(new Wall(world, -36.25, 78.75, -36.25, 93.75, "space.jpg"));
            walls.add(new Wall(world, 38.75, 58.75, 53.75, 58.75, "space.jpg"));
            
        }
        if (wave <= 4) {
            walls.add(new Wall(world, 3.75, -36.25, 18.75, -36.25, "space.jpg"));
            walls.add(new Wall(world, 38.75, -36.25, 53.75, -36.25, "space.jpg"));
        }
        if (wave <= 2) {
            walls.add(new Wall(world, 33.75, 3.75, 33.75, 18.75, "space.jpg"));
            walls.add(new Wall(world, -6.25, 33.75, -21.25, 33.75, "space.jpg"));
        }
        
        if (((Game)world).getGraphics() != null)
            ((Game)world).getGraphics().drawWalls();
    }
    
    public void updateEnemiesOnWave(World world, int wave) {
        switch(wave) {
            case 1:
                world.addObject(new Enemy(28.75, 13.75, Enemy.EnemyTypes.BASIC), 0, 0);
                break;
            case 2:
                world.addObject(new Enemy(28.75, 13.75, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(-16.25, 28.75, Enemy.EnemyTypes.BASIC), 0, 0);
                break;
            case 3:
                world.addObject(new Enemy(73.4375, -20.3125, Enemy.EnemyTypes.BASIC), 0, 0);
                world.addObject(new Enemy(44.375, 52.5, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(-5.625, 63.75, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(68.75, 13.4375, Enemy.EnemyTypes.SWIFT), 0, 0);
                break;
            case 4:
                world.addObject(new Enemy(-31.5625, 44.0625, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(58.4375, 38.4375, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(39.375, -15.3125, Enemy.EnemyTypes.SWIFT), 0, 0);
                world.addObject(new Enemy(73.125, -5.9375, Enemy.EnemyTypes.SWIFT), 0, 0);
                world.addObject(new Enemy(38.4375, 19.375, Enemy.EnemyTypes.RANGED), 0, 0);
                break;
            case 5:
                world.addObject(new Enemy(43.75, -46.25, Enemy.EnemyTypes.BASIC), 0, 0);
                world.addObject(new Enemy(13.75, -76.25, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(-16.25, -46.25, Enemy.EnemyTypes.SWIFT), 0, 0);
                world.addObject(new Enemy(-41.25, -86.25, Enemy.EnemyTypes.RANGED), 0, 0);
                world.addObject(new Enemy(-16.25, 8.75, Enemy.EnemyTypes.BASIC), 0, 0);
                world.addObject(new Enemy(8.75, -16.25, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(43.75, 3.75, Enemy.EnemyTypes.SWIFT), 0, 0);
                world.addObject(new Enemy(68.75, 28.75, Enemy.EnemyTypes.RANGED), 0, 0);
                break;
            case 6:
                world.addObject(new Enemy(38.4375, 19.375, Enemy.EnemyTypes.BASIC), 0, 0);
                world.addObject(new Enemy(78.75, -46.25, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(48.75, -71.25, Enemy.EnemyTypes.RANGED), 0, 0);
                world.addObject(new Enemy(-21.25, -86.25, Enemy.EnemyTypes.SWIFT), 0, 0);
                world.addObject(new Enemy(-56.25, -46.25, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(-16.25, 48.75, Enemy.EnemyTypes.BASIC), 0, 0);
                world.addObject(new Enemy(68.75, 38.75, Enemy.EnemyTypes.SWIFT), 0, 0);
                world.addObject(new Enemy(53.75, -16.25, Enemy.EnemyTypes.RANGED), 0, 0);       
                break;
            case 7:
                world.addObject(new Enemy(-51.25, -81.25, Enemy.EnemyTypes.BASIC), 0, 0);
                world.addObject(new Enemy(8.75, -71.25, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(68.75, -46.25, Enemy.EnemyTypes.RANGED), 0, 0);
                world.addObject(new Enemy(68.75, -16.25, Enemy.EnemyTypes.SWIFT), 0, 0);
                world.addObject(new Enemy(63.75, 38.75, Enemy.EnemyTypes.BASIC), 0, 0);
                world.addObject(new Enemy(43.75, 113.75, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(-41.25, 88.75, Enemy.EnemyTypes.RANGED), 0, 0);
                world.addObject(new Enemy(-41.25, 3.75, Enemy.EnemyTypes.SWIFT), 0, 0);
                world.addObject(new Enemy(13.75, 68.75, Enemy.EnemyTypes.RANGED), 0, 0);
                world.addObject(new Enemy(3.75, 8.75, Enemy.EnemyTypes.SWIFT), 0, 0);
                break;
            case 8:
                world.addObject(new Enemy(-54.375, -85.0, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(-30.9375, -61.25, Enemy.EnemyTypes.RANGED), 0, 0);
                world.addObject(new Enemy(-13.4375, 1.875, Enemy.EnemyTypes.SWIFT), 0, 0);
                world.addObject(new Enemy(44.0625, -17.5, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(69.0625, 35.0, Enemy.EnemyTypes.RANGED), 0, 0);
                world.addObject(new Enemy(17.1875, 47.8125, Enemy.EnemyTypes.SWIFT), 0, 0);
                world.addObject(new Enemy(-12.5, 85.0, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(12.8125, 128.75, Enemy.EnemyTypes.RANGED), 0, 0);
                world.addObject(new Enemy(-13.4375, 50.0, Enemy.EnemyTypes.SWIFT), 0, 0);
                world.addObject(new Enemy(76.875, -52.1875, Enemy.EnemyTypes.TANK), 0, 0);
                world.addObject(new Enemy(17.1875, -72.8125, Enemy.EnemyTypes.RANGED), 0, 0);
                world.addObject(new Enemy(10.3125, 9.0625, Enemy.EnemyTypes.BOSS), 0, 0);
                break;
        }
    }
    public Color getFloorColor()
    {
        return FLOOR_COLOR;
    }
    public Color getCeilingColor()
    {
        return CEILING_COLOR;
    }
    public ArrayList<Wall> getWalls()
    {
        return walls;
    }
}
