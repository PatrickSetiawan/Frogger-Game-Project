import org.newdawn.slick.SlickException;

/**
 * Turtle Class which inherits from RideableSprite. Protects the player from water. However, every 7 seconds, the turtle
 * sinks in the water and is neither visible nor able to protect the Player from water. It uses the boolean variable
 * isFloating do this, and only protects the Player from water if isFloating is currently True
 * @author Patrick Setiawan
 */

public class Turtle extends RideableSprite{
    private float speed = 0.085f;
    private boolean isFloating = true;

    public Turtle(String imageSrc, float x, float y, boolean movesRight) throws SlickException {
        super(imageSrc, x, y, movesRight);
        this.setSpeed(speed);
    }

    public boolean getIsFloating() {
        return isFloating;
    }

    public void setIsFloating(boolean isFloating) {
        this.isFloating = isFloating;
    }

    // Only "carries" the player if the turtle is currently floating
    @Override
    public void contactSprite(Sprite other) {
        if (this.isFloating) {
            super.contactSprite(other);
        } else {
            this.setIsCarrying(false);
        }
    }
}
