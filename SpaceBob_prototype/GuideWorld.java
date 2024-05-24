import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SplashScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GuideWorld extends World
{
    /**
     * Constructor for objects of class SplashScreen.
     * 
     */
    public GuideWorld()
    {    
        // Create a new world with 1000x800 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1); 
    }
    public void act()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new Level_1());
        }
    }
}
