import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GuideButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GuideButton extends Actor
{
    /**
     * Act - do whatever the GuideButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseMoved(this)) {
            setImage("Menu_Guide_Highlighted.png");
        }

        if (Greenfoot.mouseMoved(getWorld())) {
            setImage("Menu_Guide.png");
        }

        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.delay(20);
            Greenfoot.setWorld(new GuideWorld());
        }
    }
}
