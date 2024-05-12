import greenfoot.*;

/**
 * A customizable label for displaying text.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Label extends Actor {
    private String text;
    private int fontSize;
    private Color textColor;
    private Color backgroundColor;

    /**
     * Create a new label with the specified text.
     * 
     * @param text The text to display on the label.
     */
    public Label(String text) {
        super();
        this.text = text;
        this.fontSize = 24;
        this.textColor = Color.WHITE;
        this.backgroundColor = new Color(0, 0, 0, 0);

        updateImage();
    }

    /**
     * Update the label's image with the current settings.
     */
    private void updateImage() {
        GreenfootImage img = new GreenfootImage(text, fontSize, textColor, backgroundColor);
        setImage(img);
    }

    public String getLabel() {
        return text;
    }

    public void setLabel(String text) {
        this.text = text;
        updateImage();
    }
    
    /**
     * Get the current text of the label.
     * 
     * @return The text of the label.
     */
    public String getText() {
        return text;
    }

    /**
     * Set the text of the label.
     * 
     * @param text The new text to display on the label.
     */
    public void setText(String text) {
        this.text = text;
        updateImage();
    }

    /**
     * Set the font size of the label.
     * 
     * @param fontSize The new font size.
     */
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        updateImage();
    }

    /**
     * Set the text color of the label.
     * 
     * @param textColor The new text color.
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        updateImage();
    }

    /**
     * Set the background color of the label.
     * 
     * @param backgroundColor The new background color.
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        updateImage();
    }

    /**
     * Act method (required by Greenfoot).
     */
    public void act() {
        // Add any necessary action code here
    }
}
