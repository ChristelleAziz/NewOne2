import greenfoot.*;

public class Spike extends BadGuys {

    public void act() {
        moveAcrossWorld();
    }

    /**
     * Moves the spike across the world horizontally and wraps around if it reaches the edge.
     */
    private void moveAcrossWorld() {
        int x = getX();
        int worldWidth = getWorld().getWidth();

        if (x == 0) {
            setLocation(worldWidth - 1, getRandomYPosition());
        } else if (x >= worldWidth - 1) {
            setLocation(0, getRandomYPosition());
        }
    }

    /**
     * Gets a random Y position within a specified range.
     * 
     * @return The random Y position.
     */
    private int getRandomYPosition() {
        return Greenfoot.getRandomNumber(1) + 623; // Adjust the Y position range as needed
    }
}
