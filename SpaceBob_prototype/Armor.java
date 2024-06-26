import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Armor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Armor extends Actor
{
    /**
     * Act - do whatever the Armor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Armor()
        {
            getImage().scale(getImage().getWidth()/2,getImage().getHeight()/2);
        }
    public void act() {
        removeIfAtLeftEdge();
    }
    
    /**
     * Remove the coin if it reaches the left edge of the world.
     */
    private void removeIfAtLeftEdge() {
        if (getX() == 0) {
            getWorld().removeObject(this);
        }
    }
}
