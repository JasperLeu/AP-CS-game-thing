import greenfoot.*;  // (Wimporld, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class Renderer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Renderer extends Actor
{   
    private double FOV;
    private int PIX_WIDTH;
    
    private Player player;
    private ArrayList<Wall> walls;
    private int WIDTH;
    private int HEIGHT;
    
    private Object thingLookedAt = null;
    
    protected void addedToWorld(World world)
    {
        setImage(new GreenfootImage(world.getWidth(), world.getHeight()));
        // Render Constants
        WIDTH = world.getWidth();
        HEIGHT = world.getHeight();
        FOV = 60 * Math.PI / 180;
        PIX_WIDTH = 3;
        // initialize references
        ((Game)getWorld()).setGraphics(this);
        player =  ((Game)getWorld()).getPlayer();
        walls = ((Game)getWorld()).getWalls();
    }
    /**
     * Act - do whatever the Renderer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        drawBackground();
        drawWalls();
    }
    
    public void drawBackground()
    {
        GreenfootImage image = getImage();
        image.setColor(new Color(255, 255, 255));
        image.fill();
        image.setColor(((Game)getWorld()).getMap().getFloorColor());
        image.fillRect(0, HEIGHT/2, WIDTH, HEIGHT/2);
        image.setColor(((Game)getWorld()).getMap().getCeilingColor());
        image.fillRect(0, 0, WIDTH, HEIGHT/2);
    }
    
    public double clamp(double val, double min, double max)
    {
        if (val < min)
            return min;
        if (val > max)
            return max;
        return val;
    }
    
    public void drawWalls()
    {
        GreenfootImage screen = getImage();
        List<Enemy> allEnemies = getWorld().getObjects(Enemy.class);
        for (int x = 0; x < WIDTH; x+=PIX_WIDTH)
        {
            double angle = player.getRot() - FOV/2 + (double)x / WIDTH * FOV;
            double closestWallDist = Integer.MAX_VALUE;
            Vector closestHitPt = null;
            Wall closestWall = null;
            for (int i = 0; i < walls.size(); i ++) // Loop through each wall and get a raycast to it.
            {
                Vector hitPt = castRay(player.getPos(), walls.get(i), angle);
                if (hitPt == null)
                    continue;

                double actualDist = player.getPos().getDist(hitPt);
                double dist = Math.cos(((double)x / WIDTH - 0.5) * FOV) * actualDist;
                //double dist = actualDist;
                if (dist < closestWallDist){
                    closestHitPt = hitPt;
                    closestWallDist = dist;
                    closestWall = walls.get(i);
                }
            }
            int maxHeight = 0;
            // Loop through enemies and find all at this pixel
            ArrayList<Enemy> enemies = new ArrayList();
            ArrayList<Double> enemyPts = new ArrayList();
            ArrayList<Double> enemyDists = new ArrayList();
            ArrayList<Integer> enemyHeights = new ArrayList();
            
            for (Enemy enemy : allEnemies)
            {
                Vector toEnemy = enemy.getPos().minus(player.getPos());
                double distToEnemy = toEnemy.magnitude();
                double angleToEnemy = normalizeAngle(toEnemy.getAngle() - angle);
                if(Math.abs((angleToEnemy+Math.PI)%(Math.PI*2)-Math.PI) > Math.PI/2)
                    continue;
                double ptOnEnemy = distToEnemy * Math.sin(angleToEnemy);
                if (Math.abs(ptOnEnemy) > enemy.getSize()/2)
                    continue;
                enemies.add(enemy);
                enemyPts.add((ptOnEnemy + enemy.getSize()/2) / enemy.getSize());
                enemyDists.add(distToEnemy);
                int enemyH = (int)(WIDTH / Math.tan(FOV/2)/2 * enemy.getSize() / distToEnemy);
                enemyHeights.add(enemyH);
                if (enemyH > maxHeight)
                    maxHeight = enemyH;
            }
            Color[] wallColors = new Color[0];
            int wallH = 0;
            if (closestWall != null){
                wallH = (int)(WIDTH / Math.tan(FOV/2)/2 * closestWall.getHeight() / closestWallDist);
                double percent = closestWall.getPt1().getDist(closestHitPt) / (closestWall.getHeight());
                wallColors = ((Game)getWorld()).sampleTexture(closestWall.getTexture(), percent);
                if (wallH > maxHeight)
                    maxHeight = wallH;
            }
            Color[][] enemyColors = new Color[enemies.size()][];
            for (int i = 0; i < enemyColors.length; i++){
                enemyColors[i] = ((Game)getWorld()).sampleTexture(enemies.get(i).getTexture(), enemyPts.get(i));
            }
            // Draw the closest point from the raycast
            for (int y = -maxHeight/2; y < maxHeight/2; y+=PIX_WIDTH)
            {
                // Sooo, all this was supposed to check for transparent pixels but it doesnt work when enemies overlap :(
                Color currColor = null;
                double closestDist = Double.MAX_VALUE;
                
                if (closestWall != null && Math.abs(y) < wallH/2){
                    Color c = wallColors[(int)((y+wallH/2)/(double)wallH*wallColors.length)];
                    if (c.getAlpha() == 0)
                        continue;
                    closestDist = closestWallDist;
                    currColor = c;
                    if (x == (WIDTH/PIX_WIDTH)/2*PIX_WIDTH && Math.abs(y) < PIX_WIDTH)
                        thingLookedAt = closestWall;
                }
                for (int i = 0; i < enemies.size(); i++)
                {
                    if (Math.abs(y) < enemyHeights.get(i)/2 && enemyDists.get(i) < closestDist)
                    {
                        Color c = enemyColors[i][(int)((y+enemyHeights.get(i)/2)/(double)enemyHeights.get(i)*enemyColors[i].length)];
                        if (c.getAlpha() == 0)
                            continue;
                        closestDist = enemyDists.get(i);
                        currColor = c;
                        if (x == (WIDTH/PIX_WIDTH)/2*PIX_WIDTH && Math.abs(y) < PIX_WIDTH)
                            thingLookedAt = enemies.get(i);
                    }
                }
                
                if (currColor != null)
                {
                    closestDist *= -2;
                    int r = (int)clamp(currColor.getRed()+closestDist, 0, 255);
                    int g = (int)clamp(currColor.getGreen()+closestDist, 0, 255);
                    int b = (int)clamp(currColor.getBlue()+closestDist, 0, 255);
                    screen.setColor(new Color(r, g, b));
                    screen.fillRect(WIDTH-x-PIX_WIDTH, HEIGHT/2+y, PIX_WIDTH, PIX_WIDTH);
                }
                else if (x == (WIDTH/PIX_WIDTH)/2*PIX_WIDTH && Math.abs(y) < PIX_WIDTH)
                {
                    thingLookedAt = null;
                }
            }
        }   
    }
    
    public Vector castRay(Vector pos, Wall wall, double angle) // null if doesnt hit anything
    {
        double angleInDeg = angle * 180 / Math.PI;
        boolean angleIsVertical = Math.abs(Math.round(angleInDeg)%360) == 90 || Math.abs(Math.round(angleInDeg)%360) == 270;
        Vector hitPt;
        // for when wall is vertical
        if (wall.isVertical()){
            if (angleIsVertical || (pos.getX() > wall.getX1()) == (Math.abs(angleInDeg%360) < 90 || Math.abs(angleInDeg%360) > 270)) // null if both are vertical or if points away from wall
                return null;
            hitPt = new Vector(wall.getX1(), pos.getY()+(wall.getX1() - pos.getX())*Math.tan(angle));
        }
        // for when angle is vertical
        else if (angleIsVertical)
        {
            double m = (wall.getY2()-wall.getY1())/(wall.getX2()-wall.getX1());
            if ((m * pos.getX() + wall.getY1() - wall.getX1() * m < pos.getY()) == (Math.sin(angle) > m*Math.cos(angle)))
                return null;
            // check if angle points awaay from wall
            hitPt = new Vector(pos.getX(), m*pos.getX()+wall.getY1()-m*wall.getX1());
        }
        else // normal case (no infinite slope or anything)
        {
            double m = (wall.getY2()-wall.getY1())/(wall.getX2()-wall.getX1());
            if ((m * pos.getX() + wall.getY1() - wall.getX1() * m < pos.getY()) == (Math.sin(angle) > m*Math.cos(angle)))
                return null;
            // return coord of intersect
            double xIntersect = (wall.getY1() - wall.getX1() * m + pos.getX() * Math.tan(angle) - pos.getY()) / (Math.tan(angle)-m);
            double yIntersect = (xIntersect - pos.getX()) * Math.tan(angle) + pos.getY();
            hitPt = new Vector(xIntersect, yIntersect);
        }
        // check if hit point is actually between the two specified points
        double minX = Math.round(Math.min(wall.getX1(), wall.getX2())*10000)/10000.0;
        double maxX = Math.round(Math.max(wall.getX1(), wall.getX2())*10000)/10000.0;
        double minY = Math.round(Math.min(wall.getY1(), wall.getY2())*10000)/10000.0;
        double maxY = Math.round(Math.max(wall.getY1(), wall.getY2())*10000)/10000.0;
        double roundX = (double)Math.round(hitPt.getX()*10000)/10000.0;
        double roundY = (double)Math.round(hitPt.getY()*10000)/10000.0;
        if (roundX <= maxX && roundX >= minX && roundY <= maxY && roundY >= minY)
            return hitPt;
        return null;
    }
    
    public double normalizeAngle(double angle)
    {
        if (angle < 0)
            angle += ((int)(angle/-Math.PI/2)+1) * Math.PI*2;
        return angle % 360;
    }
    
    public Object getLookedAt()
    {
        return thingLookedAt;
    }
}
