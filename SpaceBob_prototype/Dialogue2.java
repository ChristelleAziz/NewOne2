import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dialogue2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dialogue2 extends World
{

    /**
     * Constructor for objects of class Dialogue2.
     * 
     */
    public Dialogue2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(555,185, 1); 
    }
    public void act()
    {
        Greenfoot.delay(140);
        Greenfoot.setWorld(new Menu());
    }
}
