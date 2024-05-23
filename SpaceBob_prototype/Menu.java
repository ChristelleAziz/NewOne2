import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{

    /**
     * Constructor for objects of class Menu.
     * 
     */
    public Menu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        WholeMenu wholeMenu = new WholeMenu();
        addObject(wholeMenu,306,188);
        ExitButton exitButton = new ExitButton();
        addObject(exitButton,308,312);
        GuideButton guideButton = new GuideButton();
        addObject(guideButton,309,255);
        PlayButton playButton = new PlayButton();
        addObject(playButton,305,197);
    }
}
