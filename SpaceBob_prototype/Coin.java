import greenfoot.*;

public class Coin extends Actor {
    
    public Coin() {
        getImage().scale(getImage().getWidth() / 20, getImage().getHeight() / 20);
    }
    
    /**
     * Act - Remove the coin if it reaches the left edge of the world.
     */
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
