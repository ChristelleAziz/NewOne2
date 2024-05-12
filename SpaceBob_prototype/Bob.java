import greenfoot.*; 
import java.util.List;
import java.util.ArrayList;

public class Bob extends Actor {
    private int vSpeed = 0;
    private int acceleration = 1;
    private int jumpHeight = -10;
    private int collect = 0;
    private int livesCount = 5;
    private int bulletsCount = 10;
    private boolean isTouchingSpike = false;
    private boolean collisionDetected = false;
    private boolean hasJumped = false;
    public static int level = 1; // static: player must always remember level
    
    public int frame = 1;
    public int animationSpeed;
    
    GreenfootImage bob1 = new GreenfootImage("Bob.png");
    GreenfootImage bob2 = new GreenfootImage("Bob2.png");
    GreenfootImage bob3 = new GreenfootImage("RBob1.png");
    GreenfootImage bob4 = new GreenfootImage("RBob2.png");
    
    public void act() {
        moveAround();
        checkFalling();
        collect();
        levelUp();
        
    
        if (!isTouchingSpike) {
            hasJumped = false;
        }
        adjustWorldPosition();
        checkCollision(); 
    }
    
    private void adjustWorldPosition() {
        // Get all objects in the world
        List<Actor> objects = getWorld().getObjects(Actor.class);

        // Loop through each object
        for (Actor object : objects) {
            // If the object is not Bob, move it to the left
            if (object != this && !(object instanceof Live) && !(object instanceof BulletDisplayed) && !(object instanceof PlanetBackground) && !(object instanceof Castle) && !(object instanceof King) && !(object instanceof Mam) && !(object instanceof Castle) && !(object instanceof King)) {
                object.move(-3);
            }
        }
    }    

    private void fall() {
        setLocation(getX(), getY() + vSpeed);
        vSpeed = vSpeed + acceleration;
    }

    public void moveAround() {
        animationSpeed = animationSpeed + 1;
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            move(3);
            if(animationSpeed % 5 == 0)
            {
                animateRight();
            }
        }
        
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            move(-4);
            if(animationSpeed % 5 == 0)
            {
                animateLeft();
            }
        }
        
        if (Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("w")) {
            if (!hasJumped) {
                vSpeed = jumpHeight;
                fall();
                GreenfootSound jumpSound = new GreenfootSound("jump10.wav");
                adjustVolume(jumpSound, 70);
                jumpSound.play();
                hasJumped = true;
                
            }
        }
        
        if (bulletsCount == 0) {
            
        } else {
                if (Greenfoot.mouseClicked(null)) {
                getWorld().addObject(new Bullet(), getX(), getY());
                GreenfootSound shootSound = new GreenfootSound("shoot.wav");
                adjustVolume(shootSound, 70);
                shootSound.play();
                loseBulletDisplayed();
            
                // Check for collision with Minion when the bullet is added
                Actor minion = getOneIntersectingObject(Minion.class);
                if (minion != null) {
                    // If the bullet intersects with a Minion, remove the Minion
                    getWorld().removeObject(minion); // Remove the minion from the world
                }
            }
        }
    }

    private void adjustVolume(GreenfootSound sound, int volume) {
        sound.setVolume(volume);
    }

    boolean onGround() {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight() / 2, Platform.class);
        boolean isOnGround = under != null;
        return isOnGround;
    }
    
    boolean onPlanet() {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight() / 2, Planet.class);
        return under != null;
    }

    public void checkFalling() {
        if (!onGround() && !onPlanet()) {
            fall();
        } else {
            vSpeed = 0;
        }
    }
    
    public void collect() {
        Actor coin = getOneIntersectingObject(Coin.class);
        if (coin != null) {
            getWorld().removeObject(coin);
            collect++;
            GreenfootSound pickupCoinSound = new GreenfootSound("pickupCoin.wav");
            adjustVolume(pickupCoinSound, 70);
            pickupCoinSound.play();
        }
        
        Actor bulletAppearing = getOneIntersectingObject(BulletAppearing.class);
        if (bulletAppearing != null) {
            getWorld().removeObject(bulletAppearing);
            bulletsCount++;
            
            addBulletDisplayed();
            
            GreenfootSound pickupCoinSound = new GreenfootSound("pickupCoin.wav");
            adjustVolume(pickupCoinSound, 70);
            pickupCoinSound.play();
        }
    }
    private void checkCollision() {
        Actor badGuy = getOneIntersectingObject(BadGuys.class); // Détecter la collision avec un ennemi
        if (badGuy != null && !collisionDetected) { // Vérifier si une collision a été détectée et si Bob n'est pas déjà en collision
            if (badGuy instanceof Minion || badGuy instanceof Spike) {
                loseLife(); // Gérer la perte de vie
                collisionDetected = true; // Marquer la collision comme détectée
                GreenfootSound jumpSound = new GreenfootSound("hurt.wav");
                adjustVolume(jumpSound, 70);
                jumpSound.play();
            }
        } else if (badGuy == null) {
            collisionDetected = false; // Réinitialiser le détecteur de collision si Bob n'est plus en collision avec un ennemi
        }
    }

    private void loseLife() {
        livesCount--;
        removeLive(); // Remove one live heart
        if (livesCount == 0) {
            Greenfoot.setWorld(new Background2());
        }
        temporaryShield();
    }
    // a temporary 'shield' after enemy collision
    private void temporaryShield(){
        GreenfootImage image = getImage(); // Get current image
        long startTime = System.currentTimeMillis();
        boolean visible = true;
        while (System.currentTimeMillis() - startTime < 3000) { // Loop for 3 seconds
            if (visible) {
                image.setTransparency(0); // Make Bob invisible
                visible = false;
            } else {
                image.setTransparency(255); // Make Bob visible
                visible = true;
            }
            Greenfoot.delay(5); // Short delay to toggle visibility
        }
        image.setTransparency(255); // Ensure Bob is visible after the effect
    }
    
    private void removeLive() {
        List<Live> hearts = getWorld().getObjects(Live.class);
        if (!hearts.isEmpty()) {
            getWorld().removeObject(hearts.get(hearts.size() - 1)); // Remove the last live heart
        }
    }
    
    private void loseBulletDisplayed() {
        bulletsCount--;
        removeBulletDisplayed();
    }

    private void removeBulletDisplayed() {
        List<BulletDisplayed> bulletsDisplayed = getWorld().getObjects(BulletDisplayed.class);
        if (!bulletsDisplayed.isEmpty()) {
            getWorld().removeObject(bulletsDisplayed.get(bulletsDisplayed.size() - 1));
        }
    }
    
private void addBulletDisplayed() {
    // Get all bullets displayed in the row
    List<BulletDisplayed> bulletsDisplayed = getWorld().getObjects(BulletDisplayed.class);
    
    // If there are fewer than 10 bullets displayed, add a new bullet to maintain the row
    if (bulletsDisplayed.size() < 10) {
        // Calculate the x-coordinate for the new bullet based on the number of bullets already displayed
        int newX = 40 + bulletsDisplayed.size() * 20;
        
        // Add the new bullet to the world
        BulletDisplayed newBullet = new BulletDisplayed();
        getWorld().addObject(newBullet, newX, 90);
    }
}

    
    public void levelUp() {
        Actor portal = getOneIntersectingObject(Portal.class);
        if (portal != null) { //Bob touching portal
            if (level == 1){
                level = 2;
                //Greenfoot.setWorld(new Level2World());
                Greenfoot.setWorld(new Background2());
            }
        else if (level == 2) {
            level = 3;
            //l3 wrld
        }
        }
    }
    public void animateRight()
    {
        if(frame == 1)
        {
            setImage(bob1);
            frame = 2;
        }
        else
        {
            setImage(bob2);
            frame = 1;
        }
    }
    public void animateLeft()
    {
        if(frame == 1)
        {
            setImage(bob3);
            frame = 2;
        }
        else
        {
            setImage(bob4);
            frame = 1;
        }
    }
}