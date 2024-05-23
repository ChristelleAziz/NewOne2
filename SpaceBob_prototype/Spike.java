import greenfoot.*;

public class Spike extends BadGuys {

    private GreenfootImage spike2 = new GreenfootImage("spike2.png");
    private GreenfootImage spike1 = new GreenfootImage("spike1.png");
    
    private boolean isMoving = false;
    
    /**
     * Act - Move the cloud horizontally and wrap around if it reaches the edge of the world.
     */
    public void act() {
        moveIfAtWorldBoundaries();
    }

    private void moveIfAtWorldBoundaries() {
    int x = getX();
    int worldWidth = getWorld().getWidth();
    int spikeWidth = getImage().getWidth();
    
    // Calculate the threshold for when to switch the image and stop moving
    int threshold = worldWidth - (spikeWidth * 5 / 10);

    if (!isMoving) {
        if (x == 0 || x >= worldWidth - 1) {
            setImage(spike2);
            moveSpikeToOppositeSide();
            isMoving = true;
        }
    } else {
        // Check if the platform is 3/4 past the screen width
        if (getX() > 0 && getX() < threshold) {
            setImage(spike1);
            // Move the platform 50 pixels to the right
            setLocation(getX() + 190, getY());
            isMoving = false;
        }
    }
}

private void moveSpikeToOppositeSide() {
        int worldWidth = getWorld().getWidth();
        int newY = Greenfoot.getRandomNumber(1) + 630;
        
        // Set the platform's position based on the side it reached
        if (getX() == 0) {
            setLocation(worldWidth - 1, newY);
        } else {
            setLocation(0, newY);
        }
    }
}