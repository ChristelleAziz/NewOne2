import greenfoot.*;

public class MyWorld extends World {
    public MyWorld() {
        super(800, 600, 1); // Crée un monde de taille 800x600 pixels avec un facteur de cellule de 1x1.
        
        prepare(); // Appelle la méthode prepare() pour ajouter les instances du joueur et des plateformes.
    }
    
    private void prepare() {
        Player player = new Player(); // Crée une nouvelle instance du joueur.
        addObject(player, 100, 500); // Ajoute le joueur aux coordonnées (100, 500) dans le monde.
        
        Platform platform1 = new Platform(); // Crée une nouvelle instance de plateforme.
        addObject(platform1, 400, 550); // Ajoute la première plateforme.
        
        Platform platform2 = new Platform(); // Crée une autre instance de plateforme.
        addObject(platform2, 700, 400); // Ajoute la deuxième plateforme.
    }
}
