import greenfoot.*;

public class Bullet extends Actor {
    private int dx;
    private int dy;
    
     public static int enemiesLeft = 2;
    public static int level = 1;
    
    public Bullet(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
        getImage().scale(getImage().getWidth() / 5, getImage().getHeight() / 5);
        updateRotation();
    }

    public void act() {
        moveBullet();
        if (getWorld() != null) { // Check if the bullet is still in the world
        checkCollision();
        }
        //resetLevel();
        levelUp();
    }

    private void moveBullet() {
        setLocation(getX() + (dx * 5), getY() + (dy * 5));
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    /**
     * Checks for collision with platforms, minions, or the world edge and removes the bullet accordingly.
     */
    private void checkCollision() {
        Actor wall = getOneIntersectingObject(Platform.class);
        if (wall != null) {
            getWorld().removeObject(this);
            return;
        }
    
        Actor minion = getOneIntersectingObject(Minion.class);
        if (minion != null) {
            Minion m = (Minion) minion;
            m.handleCollision();
            //getWorld().removeObject(this);
            enemiesLeft--;
            return;
        }
    
        Actor bulletAppearing = getOneIntersectingObject(BulletAppearing.class);
        if (bulletAppearing != null) {
            getWorld().removeObject(bulletAppearing);
            // Update the bullet count
            return;
        }
    }

    public void levelUp() {
        if (enemiesLeft == 0) {
            if (level == 1) {
                Greenfoot.setWorld(new Level_2());
                enemiesLeft = 0;
                level++;
            } else if (level == 2) {
                Greenfoot.setWorld(new Level_3());
                enemiesLeft = 0;
                level++;
            }
        if (enemiesLeft == 6) {
            if (level == 3) {
                Greenfoot.setWorld(new Level_4());
                enemiesLeft = 0;
                level++;
            }
        }
        if (enemiesLeft == 8) {
            if (level == 4) {
                Greenfoot.setWorld(new Level_5());
                enemiesLeft = 0;
                level++;
            }
        }
    }}

private void updateRotation() {
    // Calculate the rotation angle using trigonometry
    double angle = Math.atan2(dy, dx) * 180 / Math.PI;

    // Adjust the rotation angle to make it consistent with the coordinate system
    double rotation = angle;

    // Set the rotation of the bullet image
    setRotation((int) rotation);
}

}
