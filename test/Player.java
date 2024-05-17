import greenfoot.*;

public class Player extends Actor {
    private boolean canJump = true;
    private boolean hasDoubleJumped = false;
    private int jumpSpeed = 15; // Hauteur du saut
    private int fallSpeed = 5; // Vitesse de descente
    private int doubleJumpSpeed = 12; // Hauteur du double saut
    private int maxHeight = 200; // Hauteur maximale autorisée

    public void act() {
        checkKeyPress();
        checkJump();
        fall();
    }

    private void checkKeyPress() {
        if (Greenfoot.isKeyDown("space")) {
            if (canJump) {
                jump();
            } else if (!hasDoubleJumped) {
                doubleJump();
            }
        }
    }

    private void jump() {
        if (getY() > maxHeight) { // Vérifie si le joueur a atteint la hauteur maximale
            return;
        }
        
        setLocation(getX(), getY() - jumpSpeed);
        canJump = false;
    }

    private void doubleJump() {
        setLocation(getX(), getY() - doubleJumpSpeed);
        hasDoubleJumped = true;
    }

    private void checkJump() {
        if (isOnGround()) {
            canJump = true;
            hasDoubleJumped = false; // Réinitialise le double saut lorsque le joueur touche le sol
        }
    }

    private void fall() {
        if (!canJump && !isOnGround()) {
            setLocation(getX(), getY() + fallSpeed);
        }
    }

    private boolean isOnGround() {
        int groundHeight = getImage().getHeight() / 2;
        Actor ground = getOneObjectAtOffset(0, groundHeight, Platform.class);
        return ground != null;
    }
}
