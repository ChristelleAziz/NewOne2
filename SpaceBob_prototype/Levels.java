import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Levels here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Levels extends World
{
    public static Label coinsCounter;
    /**
     * Constructor for objects of class Levels.
     * 
     */
    public static int coinsAmount = 0;
    public static int enemiesLeft = 2;
    public Levels()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1); 
    }
}
