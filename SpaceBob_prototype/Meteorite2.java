import greenfoot.World;
import greenfoot.*;

public class Meteorite2 extends BadGuys {
    private int deltaX;
    private boolean shouldRemove = false;
    
    GreenfootImage meteorite2 = new GreenfootImage("Meteorite2.png");
    GreenfootImage meteorite3 = new GreenfootImage("Meteorite3.png");
    
    public Meteorite2() {
        int direction = Greenfoot.getRandomNumber(2);
        deltaX = (direction == 0) ? -2 : 4;
    }

    public void act() {
        if (shouldRemove) {
            getWorld().removeObject(this);
            getWorld().addObject(new MeteoriteOnPlanet(), getX(), 620);
            return;
        }

        if (getWorld() == null) {
            return;
        }

        int fallSpeed = 2;
        setLocation(getX() + deltaX, getY() + fallSpeed);
        
        animate();

        if (isTouching(Planet.class)) {
            shouldRemove = true;
        }
        if (getX() <= 0 && !isTouching(Planet.class)) {
            getWorld().removeObject(this); // Supprime la météorite
        }
    }
    
     public boolean shouldRemove() {
        // Define the conditions under which the meteorite should be removed.
        // For example, if it's touching the planet or if it's out of the world bounds.
        return isTouching(Planet.class) || getY() >= getWorld().getHeight();
    }
    
    public void animate()
    {
       if (deltaX == -2) {
        setImage(meteorite2);
    } else if (deltaX == 4) {
        setImage(meteorite3);
    }
    }
}


