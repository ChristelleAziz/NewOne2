import greenfoot.*;

public class Platform extends Actor {
    
    public Platform() {
        getImage().scale(getImage().getWidth() * 2, getImage().getHeight());
    }
    
    /**
     * Act - Move the platform to the opposite side if it reaches the world boundaries.
     */
    public void act() {
        moveIfAtWorldBoundaries();
    }
    
    /**
     * Move the platform to the opposite side if it reaches the world boundaries.
     */
    private void moveIfAtWorldBoundaries() {
        int x = getX();
        int worldWidth = getWorld().getWidth();
        
        // If the platform reaches the left or right boundary, move it to the opposite side
        if (x == 0 || x >= worldWidth - 1) {
            movePlatformToOppositeSide();
        }
    }
    
    /**
     * Move the platform to the opposite side.
     */
    private void movePlatformToOppositeSide() {
        int worldWidth = getWorld().getWidth();
        int newY = Greenfoot.getRandomNumber(260) + 300;
        
        // Set the platform's position based on the side it reached
        if (getX() == 0) {
            setLocation(worldWidth - 1, newY);
        } else {
            setLocation(0, newY);
        }
    }
}
