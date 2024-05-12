import greenfoot.*;

public class Cloud extends Actor {
    
    public Cloud() {
        getImage().scale(getImage().getWidth() / 3, getImage().getHeight() / 3);
    }
    
    /**
     * Act - Move the cloud horizontally and wrap around if it reaches the edge of the world.
     */
    public void act() {
        moveCloud();
    }
    
    /**
     * Move the cloud horizontally and wrap around if it reaches the edge of the world.
     */
    private void moveCloud() {
        int x = getX();
        int worldWidth = getWorld().getWidth();

        if (x == 0) {
            setLocation(worldWidth - 1, getRandomYPosition());
        } else if (x >= worldWidth - 1) {
            setLocation(0, getRandomYPosition());
        }
    }

    /**
     * Get a random Y position within a specified range.
     * 
     * @return The random Y position.
     */
    private int getRandomYPosition() {
        return Greenfoot.getRandomNumber(160) + 40; // Adjust the Y position range as needed
    }
}