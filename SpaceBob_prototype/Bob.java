import greenfoot.*;
import java.util.List;

public class Bob extends Actor {
    private int verticalSpeed = 0;
    private int acceleration = 1;
    private int jumpHeight = -20;
    private int livesCount = 5;
    private int bulletsCount = 10;
    private boolean isTouchingSpike2 = false;
    private boolean collisionDetected = false;
    private int animationSpeed;
    private int frame = 1;
    private boolean doubleJumpAvailable = true;
    private boolean canLoseLife = true;
    
    private GreenfootImage bobwalk1right = new GreenfootImage("bob_walk1right.png");
    private GreenfootImage bobwalk2right = new GreenfootImage("bob_walk2right.png");
    private GreenfootImage bobwalk3right = new GreenfootImage("bob_walk3right.png");
    private GreenfootImage bobwalk4right = new GreenfootImage("bob_walk4right.png");
    
    private GreenfootImage bobwalk1left = new GreenfootImage("bob_walk1left.png");
    private GreenfootImage bobwalk2left = new GreenfootImage("bob_walk2left.png");
    private GreenfootImage bobwalk3left = new GreenfootImage("bob_walk3left.png");
    private GreenfootImage bobwalk4left = new GreenfootImage("bob_walk4left.png");
    public void act() {
        handleMovement();
        checkFalling();
        collectItems();
        adjustWorldPosition();
        checkCollision();
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
        move(6);
        if(animationSpeed % 5 == 0)
        animateRight();
    }

    private void moveLeft() {
        move(-3);
        if(animationSpeed % 5 == 0)
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
        getWorld().addObject(new Bullet(), getX(), getY());
        playSound("shoot.wav");
        bulletsCount--;
        loseBulletDisplayed();

        Minion minion = (Minion) getOneIntersectingObject(Minion.class);
        if (minion != null) {
            getWorld().removeObject(minion);
        }
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
                    && !(object instanceof King) && !(object instanceof Mam) && !(object instanceof Label) && !(object instanceof Star)) {
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
    boolean onPlatform = platform != null && getX() >= platform.getX() - platform.getImage().getWidth() / 2 &&
    getX() <= platform.getX();
    return onPlatform;
}

private boolean onPlanet() {
    boolean onPlanet = getOneObjectAtOffset(0, getImage().getHeight() / 2, Planet.class) != null;
    return onPlanet;
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
            playSound("pickupCoin.wav");
        }
    }

    private void checkCollision() {
        Actor badGuy = getOneIntersectingObject(BadGuys.class);
        if (badGuy != null && !collisionDetected) {
            if (badGuy instanceof Minion || badGuy instanceof Spike || badGuy instanceof Meteorite2) {
                if (isTouchingMinion() || isTouchingSpike() || isTouchingMeteorite()) {
                    playSound("hurt.wav");
                    loseLife();
                    collisionDetected = true;
                }
            }
        } else if (badGuy == null) {
            collisionDetected = false;
        }
    }

    private void loseLife() {
        if (canLoseLife == true) {
            livesCount--;
            removeLive();
            if (livesCount == 0) {
                getWorld().removeObject(this);
                Greenfoot.setWorld(new Background2());
                Greenfoot.delay(5);
            }
            temporaryInvincibility(); 
        }
    }

    private void temporaryInvincibility() {
        GreenfootImage image = getImage();
        long startTime = System.currentTimeMillis();
        boolean visible = true;
        while (System.currentTimeMillis() - startTime < 1800) {
            image.setTransparency(visible ? 0 : 255);
            visible = !visible;
            Greenfoot.delay(5);
            canLoseLife = false;
        }
        image.setTransparency(255);
        //if (System.currentTimeMillis() - startTime == 5000){ //Put a timer here to deactivate shield
            canLoseLife = true;
        //}
    }

    private void removeLive() {
        List<Live> hearts = getWorld().getObjects(Live.class);
        if (!hearts.isEmpty()) {
            getWorld().removeObject(hearts.get(hearts.size() - 1));
        }
    }

    private void loseBulletDisplayed() {
        removeBulletDisplayed();
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
    
    public void animateRight()
    {
        if(frame == 1) {
            setImage(bobwalk1right);
            frame = 2;
        }
        else if(frame == 2) {
            setImage(bobwalk2right);
            frame = 3;
        }
        else if(frame == 3) {
            setImage(bobwalk3right);
            frame = 4;
        }
        else {
            setImage(bobwalk4right);
            frame = 1;
        }
    }
    
    public void animateLeft()
    {
        if(frame == 1) {
            setImage(bobwalk1left);
            frame = 2;
        }
        else if(frame == 2) {
            setImage(bobwalk2left);
            frame = 3;
        }
        else if(frame == 3) {
            setImage(bobwalk3left);
            frame = 4;
        }
        else {
            setImage(bobwalk4left);
            frame = 1;
        }
    }
}