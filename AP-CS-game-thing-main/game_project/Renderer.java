import greenfoot.*;  // (Wimporld, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Renderer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Renderer extends Actor
{   
    private double FOV;
    private int WALL_HEIGHT;
    private Color WALL_COLOR;
    private Color FLOOR_COLOR;
    private Color CEILING_COLOR;
    
    private Player player;
    private ArrayList<Wall> walls;
    private int WIDTH;
    private int HEIGHT;
    
    protected void addedToWorld(World world)
    {
        // Render Constants
        WIDTH = world.getWidth();
        HEIGHT = world.getHeight();
        FOV = 40 * Math.PI / 180;
        WALL_COLOR = new Color(255, 240, 180);
        FLOOR_COLOR = new Color(150, 200, 130, 255);
        CEILING_COLOR = new Color(150, 200, 255);
        WALL_HEIGHT = 2000;
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
        GreenfootImage image = getWorld().getBackground();
        image.setColor(new Color(255, 255, 255));
        image.fill();
        image.setColor(FLOOR_COLOR);
        image.fillRect(0, HEIGHT/2, WIDTH, HEIGHT/2);
        image.setColor(CEILING_COLOR);
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
        GreenfootImage image = getWorld().getBackground();
        for (int x = 0; x < WIDTH; x++)
        {
            double angle = player.getRot() - FOV/2 + (double)x / WIDTH * FOV;
            double closestDist = Integer.MAX_VALUE;
            for (int i = 0; i < walls.size(); i ++) // Loop through each wall and get a raycast to it.
            {
                Vector hitPt = castRay(player.getPos(), walls.get(i), angle);
                if (hitPt == null)
                    continue;

                double actualDist = player.getPos().getDist(hitPt);
                double dist = Math.cos((double)x / WIDTH * FOV) * actualDist;
                if (dist < closestDist)
                    closestDist = dist;
            }    
            // Draw the closest point from the raycast
            int v = -(int)(closestDist * 3);
            int r = (int)clamp(WALL_COLOR.getRed()+v, 0, 255);
            int g = (int)clamp(WALL_COLOR.getGreen()+v, 0, 255);
            int b = (int)clamp(WALL_COLOR.getBlue()+v, 0, 255);
            image.setColor(new Color(r, g, b));
            int h = HEIGHT;
            if (closestDist > 0.01)
                h = (int)(WALL_HEIGHT / closestDist);
            if (closestDist < Integer.MAX_VALUE){
                image.drawLine(WIDTH-x, HEIGHT/2-h/2, WIDTH-x, HEIGHT/2+h/2);
                //image.drawLine(player.getX(), player.getY(), player.getX()+(int)(closestDist*10*Math.cos(angle)), player.getY()-(int)(closestDist*10*Math.sin(angle)));
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
            double m = ((double)wall.getY2()-wall.getY1())/(wall.getX2()-wall.getX1());
            if ((m * pos.getX() + wall.getY1() - wall.getX1() * m < pos.getY()) == (Math.sin(angle) > m*Math.cos(angle)))
                return null;
            // check if angle points awaay from wall
            hitPt = new Vector(pos.getX(), m*pos.getX()+wall.getY1()-m*wall.getX1());
        }
        else // normal case (no infinite slope or anything)
        {
            double m = ((double)wall.getY2()-wall.getY1())/(wall.getX2()-wall.getX1());
            if ((m * pos.getX() + wall.getY1() - wall.getX1() * m < pos.getY()) == (Math.sin(angle) > m*Math.cos(angle)))
                return null;
            // return coord of intersect
            double xIntersect = (wall.getY1() - wall.getX1() * m + pos.getX() * Math.tan(angle) - pos.getY()) / (Math.tan(angle)-m);
            double yIntersect = (xIntersect - pos.getX()) * Math.tan(angle) + pos.getY();
            hitPt = new Vector(xIntersect, yIntersect);
        }
        // check if hit point is actually between the two specified points
        int minX = (int)Math.min(wall.getX1(), wall.getX2());
        int maxX = (int)Math.max(wall.getX1(), wall.getX2());
        int minY = (int)Math.min(wall.getY1(), wall.getY2());
        int maxY = (int)Math.max(wall.getY1(), wall.getY2());
        double roundX = (double)Math.round(hitPt.getX()*10000)/10000;
        double roundY = (double)Math.round(hitPt.getY()*10000)/10000;
        if (roundX <= maxX && roundX >= minX && roundY <= maxY && roundY >= minY)
            return hitPt;
        return null;
    }
}
