public class EnemiesToKillCounter extends Label {
    private int enemiesLeft;

    public EnemiesToKillCounter() {
        super("Enemies: 2");
        enemiesLeft = 0;
        updateLabel();
    }

    public void act() {
        
    }

    public void changeScore(int amount) {
        enemiesLeft -= amount;
        updateLabel();
    }

    private void updateLabel() {
        setText("Enemies Left: " + enemiesLeft);
    }
}
