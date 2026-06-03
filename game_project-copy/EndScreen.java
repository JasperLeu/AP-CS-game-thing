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
    String grade;
    public EndScreen(double score, double waves) {
        super();
        this.score = score;
        this.waves = waves;
        if (score < 50000) grade = "D";
        else if (score < 150000) grade = "C";
        else if (score < 250000) grade = "B";
        else if (score < 350000) grade = "A";
        else if (score < 450000) grade = "S";
        else if (score < 570000) grade = "S+";
        else grade = "S++";
    }
    public EndScreen(double score, double waves, boolean won) {
        super();
        this.score = score;
        this.waves = waves;
        if (score < 50000) grade = "D";
        else if (score < 150000) grade = "C";
        else if (score < 250000) grade = "B";
        else if (score < 350000) grade = "A";
        else if (score < 450000) grade = "S";
        else if (score < 570000) grade = "S+";
        else grade = "S++";
        if (won) {
            setImage(new GreenfootImage("bg-won.png"));
        }
    }
    protected void addedToWorld(World world)
    {
        Counter scoreCounter = new Counter("", score, Color.WHITE);
        world.addObject(scoreCounter, 734, 136);
        Counter waveCounter = new Counter("", waves, Color.WHITE);
        world.addObject(waveCounter, 746, 280);
        Color gradeColor = new Color(95, 58, 135);
        if (score < 50000) gradeColor = new Color(95, 58, 135);
        else if (score < 150000) gradeColor = new Color(99, 124, 201);
        else if (score < 250000) gradeColor = new Color(62, 184, 79);
        else if (score < 350000) gradeColor = new Color(247, 82, 45);
        else if (score < 450000) gradeColor = new Color(247, 156, 45);
        else if (score < 570000) gradeColor = new Color(247, 210, 45);
        else gradeColor = new Color(168, 236, 255);
        GreenfootImage gradeImage = new GreenfootImage(grade, 300, gradeColor, new Color(0,0,0,0));
        getImage().drawImage(gradeImage, 732, 300);
    }
}
