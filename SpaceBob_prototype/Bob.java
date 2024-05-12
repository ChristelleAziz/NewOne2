import greenfoot.*;
import java.util.List;

public class Bob extends Actor {
    private int verticalSpeed = 0;
    private int acceleration = 1;
    private int jumpHeight = -10;
    private int coinsCollected = 0;
    private int livesCount = 5;
    private int bulletsCount = 10;
    private boolean isTouchingSpike = false;
    private boolean collisionDetected = false;
    private boolean hasJumped = false;
    public static int level = 1;

    public int frame = 1;
    public int animationSpeed;

    private GreenfootImage bob1 = new GreenfootImage("Bob.png");
    private GreenfootImage bob2 = new GreenfootImage("Bob2.png");
    private GreenfootImage bob3 = new GreenfootImage("RBob1.png");
    private GreenfootImage bob4 = new GreenfootImage("RBob2.png");

    public void act() {
        handleMovement();
        checkFalling();
        collectItems();
        levelUp();

        if (!isTouchingSpike) {
            hasJumped = false;
        }
        adjustWorldPosition();
        checkCollision();
    }

    private void handleMovement() {
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
        if ((Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("w")) && !hasJumped) {
            jump();
        }
        if (bulletsCount > 0 && Greenfoot.mouseClicked(null)) {
            shootBullet();
        }
    }

    private void moveRight() {
        move(3);
        animateRight();
    }

    private void moveLeft() {
        move(-4);
        animateLeft();
    }

    private void jump() {
        verticalSpeed = jumpHeight;
        fall();
        GreenfootSound jumpSound = new GreenfootSound("jump10.wav");
        adjustVolume(jumpSound, 70);
        jumpSound.play();
        hasJumped = true;
    }

    private void shootBullet() {
        getWorld().addObject(new Bullet(), getX(), getY());
        GreenfootSound shootSound = new GreenfootSound("shoot.wav");
        adjustVolume(shootSound, 70);
        shootSound.play();
        bulletsCount--;
        loseBulletDisplayed();
        Actor minion = getOneIntersectingObject(Minion.class);
        if (minion != null) {
            getWorld().removeObject(minion);
        }
    }

    private void adjustVolume(GreenfootSound sound, int volume) {
        sound.setVolume(volume);
    }

    private void adjustWorldPosition() {
        List<Actor> objects = getWorld().getObjects(Actor.class);
        for (Actor object : objects) {
            if (object != this && !(object instanceof Live) && !(object instanceof BulletDisplayed)
                    && !(object instanceof PlanetBackground) && !(object instanceof Castle)
                    && !(object instanceof King) && !(object instanceof Mam) && !(object instanceof Label)) {
                object.move(-3);
            }
        }
    }

    private void fall() {
        setLocation(getX(), getY() + verticalSpeed);
        verticalSpeed += acceleration;
    }

    private void checkFalling() {
        if (!onGround() && !onPlanet()) {
            fall();
        } else {
            verticalSpeed = 0;
        }
    }

    private boolean onGround() {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight() / 2, Platform.class);
        return under != null;
    }

    private boolean onPlanet() {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight() / 2, Planet.class);
        return under != null;
    }

    private void collectItems() {
        collectCoin();
        collectBullet();
    }

    private void collectCoin() {
        Actor coin = getOneIntersectingObject(Coin.class);
        if (coin != null) {
            getWorld().removeObject(coin);
            coinsCollected++;
            ((Background) getWorld()).changeCoinsCounter(1);
            GreenfootSound pickupCoinSound = new GreenfootSound("pickupCoin.wav");
            adjustVolume(pickupCoinSound, 70);
            pickupCoinSound.play();
        }
    }

    private void collectBullet() {
        Actor bulletAppearing = getOneIntersectingObject(BulletAppearing.class);
        if (bulletAppearing != null) {
            getWorld().removeObject(bulletAppearing);
            bulletsCount++;
            addBulletDisplayed();
            GreenfootSound pickupBulletSound = new GreenfootSound("pickupCoin.wav");
            adjustVolume(pickupBulletSound, 70);
            pickupBulletSound.play();
        }
    }

    private void checkCollision() {
        Actor badGuy = getOneIntersectingObject(BadGuys.class);
        if (badGuy != null && !collisionDetected) {
            if (badGuy instanceof Minion || badGuy instanceof Spike) {
                loseLife();
                collisionDetected = true;
                GreenfootSound hurtSound = new GreenfootSound("hurt.wav");
                adjustVolume(hurtSound, 70);
                hurtSound.play();
            }
        } else if (badGuy == null) {
            collisionDetected = false;
        }
    }

    private void loseLife() {
        livesCount--;
        removeLive();
        if (livesCount == 0) {
            Greenfoot.setWorld(new Background2());
        }
        temporaryInvincibility();
    }

    private void temporaryInvincibility() {
        GreenfootImage image = getImage();
        long startTime = System.currentTimeMillis();
        boolean visible = true;
        while (System.currentTimeMillis() - startTime < 3000) {
            if (visible) {
                image.setTransparency(0);
                visible = false;
            } else {
                image.setTransparency(255);
                visible = true;
            }
            Greenfoot.delay(5);
        }
        image.setTransparency(255);
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

    public void levelUp() {
        Actor portal = getOneIntersectingObject(Portal.class);
        if (portal != null) {
            if (level == 1) {
            level = 2;
            Greenfoot.setWorld(new Background2());
            } else if (level == 2) {
            level = 3;
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
