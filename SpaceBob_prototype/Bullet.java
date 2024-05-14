import greenfoot.*;

public class Bullet extends Actor {
    private int speed = 20;
    public static int killedEnemies = 0;
    public static int level = 1;

    public Bullet() {
        getImage().scale(getImage().getWidth() / 5, getImage().getHeight() / 5);
    }

    public void act() {
        moveBullet();
        if (getWorld() != null) { // Check if the bullet is still in the world
        checkCollision();
        }
        //resetLevel();
        levelUp();
    }

    /**
     * Moves the bullet horizontally and removes it if it reaches the world edge.
     */
    private void moveBullet() {
        move(speed);

        // Remove the bullet if it reaches the world edge
        if (getX() >= getWorld().getWidth() - 1) {
            getWorld().removeObject(this);
            return;
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
            Minion m = (Minion)minion;
            m.handleCollision();
            getWorld().removeObject(this);
            killedEnemies++;
            return;
        }
    }
    //public void resetLevel() {
        //if (object instanceof Bo == null) {
         //   level = 1;
        //}
    //}
    public void levelUp() {
        if (killedEnemies == 1) {
            if (level == 1) {
                Greenfoot.setWorld(new Level_2());
            }
            if (level == 2) {
                Greenfoot.setWorld(new Level_3());
            }
            if (level == 3) {
                Greenfoot.setWorld(new Level_4());
            }
            killedEnemies = 0; //reinitialise counter
            level++;//increment level
        }
    }
    
}
