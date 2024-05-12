import greenfoot.*;

public class Minion extends BadGuys {
    private boolean addedToWorld = false;
    
    public Minion() {
        getImage().scale(getImage().getWidth()/4, getImage().getHeight()/4);
    }
    
    public void act() {
        if (!addedToWorld) {
            return; // Exit if not yet added to the world
        }
        
        int x = getX();
        int worldWidth = getWorld().getWidth();
        if (x <= 0 || x >= worldWidth - 1) {
            // Remove the minion from the world
            getWorld().removeObject(this);
            
            // Spawn a new minion to replace the removed one
            spawnReplacementMinion();
        }    
    }
    
    @Override
    protected void addedToWorld(World world) {
        addedToWorld = true; // Update flag when added to the world
    }
    
    public void handleCollision() {
        getWorld().removeObject(this); // Remove the minion from the world
    }
    
    private void spawnReplacementMinion() {
        // Check if added to the world
        if (!addedToWorld) {
            return;
        }
        
        // Get a reference to the world
        World world = getWorld();
        
    }
}
