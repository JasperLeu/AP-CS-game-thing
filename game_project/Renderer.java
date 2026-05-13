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
    private Player player;
    private ArrayList<int[]> walls;
    private double[] distances;
    private double FOV;
    
    protected void addedToWorld(World world)
    {
        // initialize references n stuff
        player =  world.getObjects(Player.class).get(0);
        walls = ((Walls)world.getObjects(Walls.class).get(0)).getWalls();
        FOV = 60 * Math.PI / 180;
    }
    /**
     * Act - do whatever the Renderer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Loop through each x pixel and find the distance of the ray cast at that point
        GreenfootImage image = getWorld().getBackground();
        image.setColor(new Color(255, 255, 255));
        image.fill();
        for (int x = 0; x < getWorld().getWidth(); x++)
        {
            double angle = player.getRot() - FOV/2 + (double)x / getWorld().getWidth() * FOV;
            double closestDist = Integer.MAX_VALUE;
            for (int i = 1; i < walls.size(); i ++)
            {
                double[] hitPt = castRay(player.getPos(), walls.get(i-1), walls.get(i), angle);
                if (hitPt == null)
                    continue;
                double dist = Math.sqrt(Math.pow(player.getPos()[0]-hitPt[0], 2) + Math.pow(player.getPos()[1]-hitPt[1], 2));
                if (hitPt[0] <= Math.max(walls.get(i)[0], walls.get(i-1)[0]) && hitPt[0] >= Math.min(walls.get(i)[0], walls.get(i-1)[0]) && dist < closestDist)
                {
                    closestDist = dist;
                    System.out.println(hitPt[0] + ", " + hitPt[1]);
                }
            }    
            int v = 255 - (int)(closestDist * 10);
            v = Math.clamp(v, 0, 255);
            image.setColor(new Color(v, v, v));
            int height = getWorld().getHeight();
            if (closestDist > 0.01)
                height = (int)(getWorld().getHeight() / closestDist);
            if (closestDist < Integer.MAX_VALUE)
                image.drawLine(player.getX(), player.getY(), 200+(int)(closestDist*10*Math.cos(angle)), 200-(int)(closestDist*10*Math.sin(angle)));
        }   
    }
    
    public double[] castRay(double[] pos, int[] pt1, int[] pt2, double angle)
    {
        boolean angleIsVertical = angle == Math.PI/2 || angle == Math.PI*3/2;
        if (pt1[0] == pt2[0]){
            if (angleIsVertical || (pos[0] > pt1[0]) == ((270-angle) % 360 - 180 > 0))
                return null;
            return new double[]{pt1[0], pos[0]+(pt1[0] - pos[0])*Math.tan(angle)};
        }
        else if (angleIsVertical)
        {
            if (pt1[0] == pt2[0])
                return null;
            double m = ((double)pt2[1]-pt1[1])/(pt2[0]-pt1[0]);
            if ((m * pos[0] + pt1[1] - pt1[0] * m < pos[1]) == (Math.tan(angle) > m))
                return null;
            return new double[]{pos[0], m*pos[0]+pt1[1]-m*pt1[0]};
        }
            
        if (Math.abs(Math.tan(angle) - m) < 0.0001)
            return null;
        if ((m * pos[0] + pt1[1] - pt1[0] * m < pos[1]) == (Math.tan(angle) > m))
            return null;
        double xIntersect = (pt1[1] - pt1[0] * m + pos[0] * Math.tan(angle) - pos[1])/(Math.tan(angle) - m);
        if (pt2[0] - pt1[0] == 0)
            xIntersect = pt1[0];
        double yIntersect = (xIntersect - pos[0]) * Math.tan(angle) + pos[1];
        if (angle == Math.PI / 2 || angle == Math.PI / 2 * 3)
            yIntersect = m * xIntersect + pt1[1] - pt1[0] * m;
        return new double[]{xIntersect, yIntersect};
    }
}
