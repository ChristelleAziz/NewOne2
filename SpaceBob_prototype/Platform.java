import greenfoot.*;

public class Platform extends Actor {
    private GreenfootImage platform2 = new GreenfootImage("platform2.png");
    private GreenfootImage platform1 = new GreenfootImage("platform1.png");
    
    private boolean isMoving = false;
    public Platform() {
    }
    
    /**
     * Act - Move the platform to the opposite side if it reaches the world boundaries.
     */
    public void act() {
        moveIfAtWorldBoundaries();
    }
    
private void moveIfAtWorldBoundaries() {
    int x = getX();
    int worldWidth = getWorld().getWidth();
    int platformWidth = getImage().getWidth();
    
    // Calculate the threshold for when to switch the image and stop moving
    int threshold = worldWidth - (platformWidth * 5 / 10);

    if (!isMoving) {
        if (x == 0 || x >= worldWidth - 1) {
            setImage(platform2);
            movePlatformToOppositeSide();
            isMoving = true;
        }
    } else {
        // Check if the platform is 3/4 past the screen width
        if (getX() > 0 && getX() < threshold) {
            setImage(platform1);
            // Move the platform 50 pixels to the right
            setLocation(getX() + 190, getY());
            isMoving = false;
        }
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