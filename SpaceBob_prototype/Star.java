import greenfoot.*;

public class Star extends Actor {
    
    public Star() {
        getImage().scale(getImage().getWidth() / 60, getImage().getHeight() / 60);
    }
    
    /**
     * Act - Move the star to the opposite side if it reaches the world boundaries.
     */
    public void act() {
        moveIfAtWorldBoundaries();
    }
    
    /**
     * Move the star to the opposite side if it reaches the world boundaries.
     */
    private void moveIfAtWorldBoundaries() {
        int x = getX();
        int worldWidth = getWorld().getWidth();
        
        // If the star reaches the left or right boundary, move it to the opposite side
        if (x == 0 || x >= worldWidth - 1) {
            moveStarToOppositeSide();
        }
    }
    
    /**
     * Move the star to the opposite side.
     */
    private void moveStarToOppositeSide() {
        int worldWidth = getWorld().getWidth();
        int newY = Greenfoot.getRandomNumber(240) + 40;
        
        // Set the star's position based on the side it reached
        if (getX() == 0) {
            setLocation(worldWidth - 1, newY);
        } else {
            setLocation(0, newY);
        }
    }
}
