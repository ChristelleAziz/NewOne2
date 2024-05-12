public class CoinsCounter extends Label {
    private int score;

    public CoinsCounter() {
        super("Coins: 0");
        score = 0;
        updateLabel();
    }

    public void act() {
        // Add any necessary action code here
    }

    public void changeScore(int amount) {
        score += amount;
        updateLabel();
    }

    private void updateLabel() {
        setText("Coins: " + score);
    }
}
