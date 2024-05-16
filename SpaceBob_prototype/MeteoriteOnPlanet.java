import greenfoot.*;

public class MeteoriteOnPlanet extends BadGuys {
    public MeteoriteOnPlanet() {
        setImage("test40.png");
    }

    public void act() {
        // Obtient la position x du bord gauche de l'écran
        move();
        // Vérifie si la position x de la météorite dépasse le bord gauche de l'écran
    
    }
    private void move(){
        int x = getX();
        if (x <= 0) {
            getWorld().removeObject(this); // Supprime la météorite lorsque le bord gauche est atteint
        }
    }
}
