import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dialogue1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dialogue1 extends World
{

    /**
     * Constructor for objects of class Dialogue1.
     * 
     */
    public Dialogue1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(545,180, 1); 
    }
    public void act()
    {
        Greenfoot.delay(100);
        Greenfoot.setWorld(new Dialogue2());
    }
}
