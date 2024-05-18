import greenfoot.*;
import java.util.List;

public class Minion extends BadGuys {
    private boolean addedToWorld = false;
    private int speed = 2; // Vitesse de déplacement
    
    private GreenfootImage minionwalk1 = new GreenfootImage("minion1.png");
    private GreenfootImage minionwalk2 = new GreenfootImage("minion2.png");
    private GreenfootImage minionwalk3 = new GreenfootImage("minion3.png");
    private GreenfootImage minionwalk4 = new GreenfootImage("minion4.png");
    
    private int animationFrame = 0;
    private int animationDelay = 5;
    private int animationTimer = 0; 
    
    public Minion() {
        getImage().scale(getImage().getWidth() / 4, getImage().getHeight() / 4);
        setImage(minionwalk1);
    }
    
    public void act() {
        if (!addedToWorld) {
            return;
        }
        
        moveLeft(); // Appel de la méthode pour déplacer le minion vers la gauche
        animate();
        int x = getX();
        int worldWidth = getWorld().getWidth();
        if (x <= 0 || x >= worldWidth - 1) {
            getWorld().removeObject(this);
            spawnReplacementMinion();
        }    
    }
    
    // Méthode pour déplacer le minion vers la gauche
    private void moveLeft() {
        setLocation(getX() - speed, getY()); // Déplacer le minion vers la gauche
    }
    
    @Override
    protected void addedToWorld(World world) {
        addedToWorld = true;
    }
    
    public void handleCollision() {
        //killedEnemies++;
        //getWorld().removeObject(this); // Remove the minion from the world
    }
    
    private void spawnReplacementMinion() {
        // Check if added to the world
        if (!addedToWorld) {
            return;
        }
        
        // Get a reference to the world
        World world = getWorld();
        
        // Check if the world is null
        if (world == null) {
            return;
        }
        
        // Get the height of the world
        int worldHeight = world.getHeight();
        
        // Spawn a new minion at the right corner
        Minion replacement = new Minion();
        world.addObject(replacement, world.getWidth() - 1, worldHeight - 1); // Spawn at the bottom-right corner
    }
    
    private void animate() {
         animationTimer++;
         if (animationTimer >= animationDelay) {
        animationTimer = 0; // Reset timer
        
        // Change to the next frame
        if (animationFrame == 0) {
            setImage(minionwalk1);
        } else if (animationFrame == 1) {
            setImage(minionwalk2);
        } else if (animationFrame == 2) {
            setImage(minionwalk3);
        } else if (animationFrame == 3) {
            setImage(minionwalk4);
        }
        
        // Move to the next frame
        animationFrame = (animationFrame + 1) % 4;
    }
}
}
