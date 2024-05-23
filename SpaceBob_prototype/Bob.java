import greenfoot.*;
import java.util.List;

public class Bob extends Actor {
    private int verticalSpeed = 0;
    private int acceleration = 1;
    private int jumpHeight = -20;
    private int livesCount = 5;
    private int armorsCount = 5;
    private int bulletsCount = 10;
    private boolean isTouchingSpike2 = false;
    private boolean collisionDetected = false;
    private int animationSpeed;
    private int frame = 1;
    private boolean doubleJumpAvailable = true;
    private boolean canLoseLife = true;
    private boolean canLoseArmor = true;
     private boolean isInvincible = false;
    private long lastAnimationTime = 0;
    private long lastCollisionCheckTime = 0;

    private GreenfootImage bobwalk1right = new GreenfootImage("bob_walk1right.png");
    private GreenfootImage bobwalk2right = new GreenfootImage("bob_walk2right.png");
    private GreenfootImage bobwalk3right = new GreenfootImage("bob_walk3right.png");
    private GreenfootImage bobwalk4right = new GreenfootImage("bob_walk4right.png");
    
    private GreenfootImage bobwalk1left = new GreenfootImage("bob_walk1left.png");
    private GreenfootImage bobwalk2left = new GreenfootImage("bob_walk2left.png");
    private GreenfootImage bobwalk3left = new GreenfootImage("bob_walk3left.png");
    private GreenfootImage bobwalk4left = new GreenfootImage("bob_walk4left.png");
    
    private GreenfootImage bobwalk1left_with_armor = new GreenfootImage("bob_walk1left_with_armor.png");
    private GreenfootImage bobwalk2left_with_armor = new GreenfootImage("bob_walk2left_with_armor.png");
    private GreenfootImage bobwalk3left_with_armor = new GreenfootImage("bob_walk3left_with_armor.png");
    private GreenfootImage bobwalk4left_with_armor = new GreenfootImage("bob_walk4left_with_armor.png");
    
    private GreenfootImage bobwalk1right_with_armor = new GreenfootImage("bob_walk1right_with_armor.png");
    private GreenfootImage bobwalk2right_with_armor = new GreenfootImage("bob_walk2right_with_armor.png");
    private GreenfootImage bobwalk3right_with_armor = new GreenfootImage("bob_walk3right_with_armor.png");
    private GreenfootImage bobwalk4right_with_armor = new GreenfootImage("bob_walk4right_with_armor.png");
    public void act() {
        handleMovement();
        checkFalling();
        collectItems();
        adjustWorldPosition();
        animateIfNecessary();
        checkCollisionIfNecessary();
    }

     private double simulationSpeed = 1.0; // Défaut: vitesse de simulation normale

    public void setSimulationSpeed(double speed) {
        this.simulationSpeed = speed;
    }

    public double getSimulationSpeed() {
        return this.simulationSpeed;
    }
    
    private void checkCollisionIfNecessary() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCollisionCheckTime >= 200) { // Vérifier la collision toutes les 200 millisecondes
            checkCollision();
            lastCollisionCheckTime = currentTime;
        }
    }

    private void animateIfNecessary() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAnimationTime >= 100) { // Changer d'image toutes les 100 millisecondes
            if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
                animateRight();
            } else if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
                animateLeft();
            }
            lastAnimationTime = currentTime;
        }
    }

    private void handleMovement() {
        animationSpeed = animationSpeed + 1;
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) {
            moveRight();
        }
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) {
            moveLeft();
        }
        if ((Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("w")) && (onPlatform() || onPlanet() || doubleJumpAvailable)) {
            jump();
            setImage(bobwalk3right);
        }
        if (bulletsCount > 0 && Greenfoot.mouseClicked(null)) {
            shootBullet();
        }
    }

    private void moveRight() {
        int delta = (int) (7 * getSimulationSpeed()); // 10 peut être ajusté selon la vitesse désirée
        move(delta);
        if (animationSpeed % 500 == 0)
            animateRight();
    }

    private void moveLeft() {
        int delta = (int) (-4 * getSimulationSpeed()); // -3 peut être ajusté selon la vitesse désirée
        move(delta);
        if (animationSpeed % 500 == 0)
            animateLeft();
    }

    private void jump() {
        if (onPlatform() || onPlanet()) {
            verticalSpeed = jumpHeight;
            fall();
            playSound("jump10.wav");
            doubleJumpAvailable = true;
        } else if (!onPlatform() && !onPlanet() && doubleJumpAvailable) {
            verticalSpeed = jumpHeight;
            fall();
            playSound("jump10.wav");
            doubleJumpAvailable = false;
        }
    }

private void shootBullet() {
    // Get the mouse coordinates
    int mouseX = Greenfoot.getMouseInfo().getX();
    int mouseY = Greenfoot.getMouseInfo().getY();

    // Calculate the angle between Bob's position and the mouse click position
    double angle = Math.atan2(mouseY - getY(), mouseX - getX());

    // Calculate the horizontal and vertical components of bullet velocity
    int speed = 5; // Adjust bullet speed as needed
    int dx = (int) (speed * Math.cos(angle));
    int dy = (int) (speed * Math.sin(angle));

    // Add the bullet with the computed velocity components
    getWorld().addObject(new Bullet(dx, dy), getX(), getY());

    bulletsCount--; // Réduire le nombre de balles ici
    removeBulletDisplayed(); // Retirer une balle affichée

    playSound("shoot.wav");
}



    private void playSound(String filename) {
        GreenfootSound sound = new GreenfootSound(filename);
        sound.setVolume(70);
        sound.play();
    }

    private void adjustWorldPosition() {
        for (Actor object : getWorld().getObjects(Actor.class)) {
            if (!(object instanceof Live) && !(object instanceof BulletDisplayed)
                    && !(object instanceof PlanetBackground) && !(object instanceof Castle)
                    && !(object instanceof King) && !(object instanceof Mam) && !(object instanceof Label) && !(object instanceof Star) && !(object instanceof decorPlanets) && !(object instanceof Armor)) {
                object.move(-3);
            }
        }
    }

    private void fall() {
        setLocation(getX(), getY() + verticalSpeed);
        verticalSpeed += acceleration;
        if (onPlatform() || onPlanet()) {
            doubleJumpAvailable = true;
        }
    }

    private void checkFalling() {
        if (!onPlatform() && !onPlanet()) {
            fall();
        } else {
            verticalSpeed = 0;
            doubleJumpAvailable = true;
        }
    }

    private boolean onPlatform() {
        Platform platform = (Platform)getOneObjectAtOffset(0, getImage().getHeight() / 2, Platform.class);
        return platform != null && getX() >= platform.getX() - platform.getImage().getWidth() / 2 &&
                getX() <= platform.getX();
    }

    private boolean onPlanet() {
        return getOneObjectAtOffset(0, getImage().getHeight() / 2, Planet.class) != null;
    }

    private void collectItems() {
        collectCoin();
        collectBullet();
    }

     private void collectCoin() {
        Actor coin = getOneIntersectingObject(Coin.class);
        if (coin != null) {
            getWorld().removeObject(coin);
            if (getWorld() instanceof Level_1) {
                ((Level_1) getWorld()).changeCoinsCounter(1);
            } else if (getWorld() instanceof Level_2) {
                ((Level_2) getWorld()).changeCoinsCounter(1);
            } else if (getWorld() instanceof Level_3) {
                ((Level_3) getWorld()).changeCoinsCounter(1);
            } else if (getWorld() instanceof Level_4) {
                ((Level_4) getWorld()).changeCoinsCounter(1);
            } else if (getWorld() instanceof Level_5) {
                ((Level_5) getWorld()).changeCoinsCounter(1);
            }
            playSound("pickupCoin.wav");
        }
    }
private void collectBullet() {
    Actor bulletAppearing = getOneIntersectingObject(BulletAppearing.class);
    if (bulletAppearing != null) {
        getWorld().removeObject(bulletAppearing);
        bulletsCount++;
        addBulletDisplayed();
        loseBulletDisplayed(); // Appelé uniquement lorsque le joueur collecte une balle
        playSound("pickupCoin.wav");
    }
}

private void checkCollision() {
    Actor badGuy = getOneIntersectingObject(BadGuys.class);
    if (badGuy != null && !collisionDetected) {
        if (badGuy instanceof Minion || badGuy instanceof Spike || badGuy instanceof Meteorite2) {
            if (isTouchingMinion() || isTouchingSpike() || isTouchingMeteorite()) {
                playSound("hurt.wav");
                if (armorsCount > 0) {
                    loseArmor(); // Bob loses an armor if he has any remaining
                } else {
                    loseLife(); // Bob loses a life if he doesn't have any armor left
                }
                collisionDetected = true;
            }
        }
    } else if (badGuy == null) {
        collisionDetected = false;
    }
}



private void decreaseBulletsCount() {
    bulletsCount--;
}

 private boolean isTouchingMinion() {
        Minion minion = (Minion) getOneIntersectingObject(Minion.class);
        return minion != null && getX() < minion.getX();
    }
    
    private boolean isTouchingSpike() {
        return !getObjectsInRange(50, Spike.class).isEmpty();
    }

    private boolean isTouchingMeteorite() {
        return !getObjectsInRange(100, Meteorite2.class).isEmpty();
        
    }
    
private void loseLife() {
    if (canLoseLife && !isInvincible) { // Check if Bob is not currently invincible
        livesCount--;
        removeLive();
        if (livesCount == 0) {
            getWorld().removeObject(this);
            Greenfoot.setWorld(new Background2());
            Greenfoot.delay(5);
        }
    }
}

private void loseArmor() {
    if (canLoseArmor) {
        armorsCount--;
        removeArmor();
        temporaryInvincibility(); // Trigger temporary invincibility every time Bob loses an armor
        if (armorsCount == 0) { // If Bob has lost all armors, disable invincibility
            isInvincible = false;
        }
    }
}


private void temporaryInvincibility() {
     if (!isInvincible) { // Check if Bob is not currently invincible
        isInvincible = true; // Set Bob as invincible
        GreenfootImage originalImage = getImage();
        GreenfootImage armorImage = getArmorImage(originalImage); // Get the corresponding image with armor
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 1800) {
            setImage(armorImage); // Set the image with armor
            Greenfoot.delay(5);
            setImage(originalImage); // Reset image back to the original after a delay
            Greenfoot.delay(5);
        }
        isInvincible = false; // Reset invincibility after the duration
    }
}

private GreenfootImage getArmorImage(GreenfootImage originalImage) {
    // Determine which armor image corresponds to the original image of Bob
    if (originalImage == bobwalk1right) {
        return bobwalk1right_with_armor;
    } else if (originalImage == bobwalk2right) {
        return bobwalk2right_with_armor;
    } else if (originalImage == bobwalk3right) {
        return bobwalk3right_with_armor;
    } else if (originalImage == bobwalk4right) {
        return bobwalk4right_with_armor;
    } else if (originalImage == bobwalk1left) {
        return bobwalk1left_with_armor;
    } else if (originalImage == bobwalk2left) {
        return bobwalk2left_with_armor;
    } else if (originalImage == bobwalk3left) {
        return bobwalk3left_with_armor;
    } else { // originalImage == bobwalk4left
        return bobwalk4left_with_armor;
    }
}

private void removeLive() {
    List<Live> hearts = getWorld().getObjects(Live.class);
    if (!hearts.isEmpty()) {
        getWorld().removeObject(hearts.get(hearts.size() - 1));
    }
}

private void removeArmor() {
    List<Armor> armors = getWorld().getObjects(Armor.class);
    if (!armors.isEmpty()) {
        getWorld().removeObject(armors.get(armors.size() - 1));
    }
}

private void loseBulletDisplayed() {
    removeBulletDisplayed();
    // Decrease the displayed bullets count
    List<BulletDisplayed> bulletsDisplayed = getWorld().getObjects(BulletDisplayed.class);
    if (!bulletsDisplayed.isEmpty()) {
        // Remove the rightmost displayed bullet
        getWorld().removeObject(bulletsDisplayed.get(bulletsDisplayed.size() - 1));
    }
}


private void removeBulletDisplayed() {
    List<BulletDisplayed> bulletsDisplayed = getWorld().getObjects(BulletDisplayed.class);
    if (!bulletsDisplayed.isEmpty()) {
        getWorld().removeObject(bulletsDisplayed.get(bulletsDisplayed.size() - 1));
    }
}

private void addBulletDisplayed() {
    List<BulletDisplayed> bulletsDisplayed = getWorld().getObjects(BulletDisplayed.class);
    if (bulletsDisplayed.size() < 10) {
        int newX = 40 + bulletsDisplayed.size() * 20;
        BulletDisplayed newBullet = new BulletDisplayed();
        getWorld().addObject(newBullet, newX, 90);
    }
}

public void animateRight() {
    if (frame == 1) {
        setImage(bobwalk1right);
        frame = 2;
    } else if (frame == 2) {
        setImage(bobwalk2right);
        frame = 3;
    } else if (frame == 3) {
        setImage(bobwalk3right);
        frame = 4;
    } else {
        setImage(bobwalk4right);
        frame = 1;
    }
}

public void animateLeft() {
    if (frame == 1) {
        setImage(bobwalk1left);
        frame = 2;
    } else if (frame == 2) {
        setImage(bobwalk2left);
        frame = 3;
    } else if (frame == 3) {
        setImage(bobwalk3left);
        frame = 4;
    } else {
        setImage(bobwalk4left);
        frame = 1;
    }
}
}