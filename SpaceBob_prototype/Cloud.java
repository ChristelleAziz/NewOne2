import greenfoot.*;

public class Cloud extends Actor {
    
    private GreenfootImage cloud2 = new GreenfootImage("cloud2.png");
    private GreenfootImage cloud1 = new GreenfootImage("cloud1.png");
    
    private boolean isMoving = false;
    public Cloud() {
    }
    
    /**
     * Act - Move the cloud horizontally and wrap around if it reaches the edge of the world.
     */
    public void act() {
        moveIfAtWorldBoundaries();
    }
    
    /**
     * Move the cloud horizontally and wrap around if it reaches the edge of the world.
     */
    private void moveIfAtWorldBoundaries() {
    int x = getX();
    int worldWidth = getWorld().getWidth();
    int cloudWidth = getImage().getWidth();
    
    // Calculate the threshold for when to switch the image and stop moving
    int threshold = worldWidth - (cloudWidth * 5 / 10);

    if (!isMoving) {
        if (x == 0 || x >= worldWidth - 1) {
            setImage(cloud2);
            moveCloudToOppositeSide();
            isMoving = true;
        }
    } else {
        // Check if the platform is 3/4 past the screen width
        if (getX() > 0 && getX() < threshold) {
            setImage(cloud1);
            // Move the platform 50 pixels to the right
            setLocation(getX() + 190, getY());
            isMoving = false;
        }
    }
}

private void moveCloudToOppositeSide() {
        int worldWidth = getWorld().getWidth();
        int newY = Greenfoot.getRandomNumber(160) + 40;
        
        // Set the platform's position based on the side it reached
        if (getX() == 0) {
            setLocation(worldWidth - 1, newY);
        } else {
            setLocation(0, newY);
        }
    }
}