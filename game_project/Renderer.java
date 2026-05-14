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
    private int[][] walls;
    private int WIDTH;
    private int HEIGHT;
    
    protected void addedToWorld(World world)
    {
        // Render Constants
        WIDTH = world.getWidth();
        HEIGHT = world.getHeight();
        FOV = 60 * Math.PI / 180;
        WALL_COLOR = new Color(255, 240, 180);
        FLOOR_COLOR = new Color(150, 200, 130, 255);
        CEILING_COLOR = new Color(150, 200, 255);
        WALL_HEIGHT = 1800;
        // initialize references
        player =  world.getObjects(Player.class).get(0);
        walls = ((Walls)world.getObjects(Walls.class).get(0)).getWalls();
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
    
    public void drawWalls()
    {
        GreenfootImage image = getWorld().getBackground();
        for (int x = 0; x < WIDTH; x++)
        {
            double angle = player.getRot() - FOV/2 + (double)x / WIDTH * FOV;
            double closestDist = Integer.MAX_VALUE;
            for (int i = 1; i <= walls.length; i ++) // Loop through each wall and get a raycast to it.
            {
                double[] hitPt = castRay(player.getPos(), walls[i-1], walls[i%walls.length], angle);
                if (hitPt == null)
                    continue;
                double dist = Math.sqrt(Math.pow(player.getPos()[0]-hitPt[0], 2) + Math.pow(player.getPos()[1]-hitPt[1], 2));
                if (dist < closestDist)
                    closestDist = dist;
            }    
            // Draw the closest point from the raycast
            int v = -(int)(closestDist * 10);
            int r = Math.clamp(WALL_COLOR.getRed()+v, 0, 255);
            int g = Math.clamp(WALL_COLOR.getGreen()+v, 0, 255);
            int b = Math.clamp(WALL_COLOR.getBlue()+v, 0, 255);
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
    
    public double[] castRay(double[] pos, int[] pt1, int[] pt2, double angle) // null if doesnt hit anything
    {
        double angleInDeg = angle * 180 / Math.PI;
        boolean angleIsVertical = Math.abs(Math.round(angleInDeg)%360) == 90 || Math.abs(Math.round(angleInDeg)%360) == 270;
        double[] hitPt;
        // for when wall is vertical
        if (pt1[0] == pt2[0]){
            if (angleIsVertical || (pos[0] > pt1[0]) == (Math.abs(angleInDeg%360) < 90 || Math.abs(angleInDeg%360) > 270)) // null if both are vertical or if points away from wall
                return null;
            hitPt = new double[]{pt1[0], pos[1]+(pt1[0] - pos[0])*Math.tan(angle)};
        }
        // for when angle is vertical
        else if (angleIsVertical)
        {
            double m = ((double)pt2[1]-pt1[1])/(pt2[0]-pt1[0]);
            if ((m * pos[0] + pt1[1] - pt1[0] * m < pos[1]) == (Math.sin(angle) > m*Math.cos(angle)))
                return null;
            // check if angle points awaay from wall
            hitPt = new double[]{pos[0], m*pos[0]+pt1[1]-m*pt1[0]};
        }
        else // normal case (no infinite slope or anything)
        {
            double m = ((double)pt2[1]-pt1[1])/(pt2[0]-pt1[0]);
            if ((m * pos[0] + pt1[1] - pt1[0] * m < pos[1]) == (Math.sin(angle) > m*Math.cos(angle))) // check if angle points awaay from wall
                return null;
            // return coord of intersect
            double xIntersect = (pt1[1] - pt1[0] * m + pos[0] * Math.tan(angle) - pos[1])/(Math.tan(angle) - m);
            double yIntersect = (xIntersect - pos[0]) * Math.tan(angle) + pos[1];
            hitPt = new double[]{xIntersect, yIntersect};
        }
        // check if hit point is actually between the two specified points
        int minX = Math.min(pt1[0], pt2[0]);
        int maxX = Math.max(pt1[0], pt2[0]);
        int minY = Math.min(pt1[1], pt2[1]);
        int maxY = Math.max(pt1[1], pt2[1]);
        double roundX = Math.round(hitPt[0]*10000)/10000;
        double roundY = Math.round(hitPt[1]*10000)/10000;
        if (roundX <= maxX && roundX >= minX && roundY <= maxY && roundY >= minY)
            return hitPt;
        return null;
    }
}
