package model;

// Represents the buttons that the user needs to press the key
public class Buttons {

    private static final String[] FIXED_BUTTONS = { "A", "S", "D", "F", "J", "K", "L", ";" };
    private String nextKeyPress;

    // EFFECTS: construct an object with 8 fixed buttons, no points, and no next key
    // press.
    public Buttons() {
        nextKeyPress = null;
    }

    // EFFECTS: return the keys/buttons that the user needs to press
    public String[] getFixedButtons() {
        return FIXED_BUTTONS;
    }

    // EFFECTS: return the next key/button that the user have to press
    public String getNextFallingButton() {
        return nextKeyPress;
    }

    // MODIFIES: this
    // EFFECTS: Checks if the user's key press matches a valid falling button.
    // If the key press is correct, updates points and returns true.
    // Otherwise, returns false. 100 points are rewarded for each correct key press
    public boolean checkKeyPress(String userKeyPress) {
        if (userKeyPress.equals(nextKeyPress)) {
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: setting up the next key press
    public void setNextKeyPress(String nextKeyPress) {
        this.nextKeyPress = nextKeyPress;
    }
}
