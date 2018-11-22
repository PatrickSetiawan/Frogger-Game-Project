import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Bulldozer Class that inherits from MovingSprite. It does NOT behave like a hazard, instead, it pushed players
 * in its direction and at its speed.
 * @author Patrick Setiawan
 */

public class Bulldozer extends MovingSprite {
    private float speed = 0.05f;

    // Sets all bulldozers to be solid
    public Bulldozer(String imageSrc, float x, float y, boolean movesRight) throws SlickException {
        super(imageSrc, x, y, movesRight);
        this.setIsSolid(true);
        this.setSpeed(speed);
    }

    // Moves the player when bulldozer is in contact with the player, instead of making the player lose a life
    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);
        if (this.getBoundingBox().intersects(other.getBoundingBox()) && other instanceof Player) {
            if (other.getPrevDirection() == NONE) {
                if (this.getMovesRight()) {
                    other.setXCoor(other.getXCoor() + speed);
                }
                else {
                    other.setXCoor(other.getXCoor() - speed);
                }
            }
        }
    }
}
