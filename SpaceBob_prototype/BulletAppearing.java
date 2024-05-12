import greenfoot.*;

public class BulletAppearing extends Actor {
    
    public BulletAppearing() {
        getImage().scale(getImage().getWidth() / 3, getImage().getHeight() / 3);
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
