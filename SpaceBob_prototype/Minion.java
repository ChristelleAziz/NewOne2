import greenfoot.World;
import greenfoot.*;
import java.util.List;

public class Minion extends BadGuys {
    private boolean addedToWorld = false;
    //public static int killedEnemies = 0;
    //public static int level = 1;
    public Minion() {
        getImage().scale(getImage().getWidth()/4, getImage().getHeight()/4);
    }
    
    public void act() {
        if (!addedToWorld) {
            return;
        }
        
        int x = getX();
        int worldWidth = getWorld().getWidth();
        if (x <= 0 || x >= worldWidth - 1) {
            getWorld().removeObject(this);
            spawnReplacementMinion();
        }    
        //levelUp();
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
}
