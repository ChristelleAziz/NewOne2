import greenfoot.*; 

public class Meteorite2 extends BadGuys {
    private static final int DEFAULT_FALL_SPEED = 3;
    private static final int MIN_X = -100; 
    private static final int MAX_X = 800;
    private static final int MAX_Y = 620;
    private static final int FRAMES_PER_SECOND = 30;
    
    private int deltaX;
    private boolean shouldRemove = false;
    private int actCount = 0;
    
    private static final GreenfootImage meteorite7 = new GreenfootImage("Meteorite7.png");
    private static final GreenfootImage meteorite8 = new GreenfootImage("Meteorite8.png");
    private static final GreenfootImage meteorite9 = new GreenfootImage("Meteorite9.png");
    private static final GreenfootImage meteorite10 = new GreenfootImage("Meteorite10.png");

    public Meteorite2() {
        int direction = Greenfoot.getRandomNumber(2);
        deltaX = (direction == 0) ? -2 : 2;
        setImage(deltaX == -2 ? meteorite9 : meteorite7);
    }

    public void act() {
        if (shouldRemove) {
            getWorld().removeObject(this);
            return;
        }
        
        if (getWorld() == null) {
            return;
        }
        
        moveMeteorite();
        animate();

        if (isTouching(Planet.class)) {
            shouldRemove = true;
        }
        
        if (getX() <= 0 && !isTouching(Planet.class)) {
            getWorld().removeObject(this); 
        }
    }

    private void moveMeteorite() {
        setLocation(getX() + deltaX, getY() + DEFAULT_FALL_SPEED);
    }

    public boolean shouldRemove() {
        return isTouching(Planet.class) || getY() >= getWorld().getHeight();
    }

    private void animate() {
        if (actCount % FRAMES_PER_SECOND == 0) {
            if (deltaX == -2) { 
                setImage(getImage() == meteorite7 ? meteorite8 : meteorite7);
            } else if (deltaX == 2) { 
                setImage(getImage() == meteorite9 ? meteorite10 : meteorite9);
            }
        }
        actCount++;
    }
}
