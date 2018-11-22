import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Bus Class which inherits from MovingSprite. It behaves as a hazard and makes the Player lose a life upon collision
 * @author Patrick Setiawan
 */

public class Bus extends MovingSprite {
    private float speed = 0.15f;

    public Bus(String imageSrc, float x, float y, boolean movesRight) throws SlickException {
        super(imageSrc, x, y, movesRight);
        this.setSpeed(speed);
    }

    // Player loses a life upon collision
    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);
        if (this.getBoundingBox().intersects(other.getBoundingBox())) {
            other.setLifeCount(other.getLifeCount()-1);
            other.setXCoor(App.STARTINGPLAYERX);
            other.setYCoor(App.STARTINGPLAYERY);
        }
    }
}
