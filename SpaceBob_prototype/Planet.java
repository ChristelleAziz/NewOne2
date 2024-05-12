import greenfoot.*;

public class Planet extends Actor {
    
    /**
     * Act - Move the planet to a fixed position if it reaches the world boundaries.
     */
    public void act() {
        checkBoundaries();
    }
    
    /**
     * Check if the planet is at the world boundaries and move it to a fixed position if necessary.
     */
    private void checkBoundaries() {
        int x = getX();
        int worldWidth = getWorld().getWidth();
        
        // If the planet reaches the left or right boundary, move it to a fixed position
        if (x == 0 || x >= worldWidth - 1) {
            movePlanetToFixedPosition();
        }
    }
    
    /**
     * Move the planet to a fixed position.
     */
    private void movePlanetToFixedPosition() {
        setLocation(750, 750); // Fixed coordinates
    }
}
