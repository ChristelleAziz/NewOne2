import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ExitButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExitButton extends Actor
{
    /**
     * Act - do whatever the ExitButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseMoved(this)) {
            setImage("Menu_Exit_Highlighted.png");
        }

        if (Greenfoot.mouseMoved(getWorld())) {
            setImage("Menu_Exit.png");
        }

        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.delay(20);
            Greenfoot.setWorld(new SplashScreen());
        }
    }
}
