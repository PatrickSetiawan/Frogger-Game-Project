import org.newdawn.slick.SlickException;

/**
 * RideableSprite Class which inherits from MovingSprite. It allows the Player to "ride" it and protects the Player from
 * dying to WaterTiles as long as its bounding boxes and the Player's bounding boxes are still intersecting. It also
 * carries the Player by making the Player's speed and direction same as its speed and direction whenever they intersect
 * All the water vehicles Sprites inherit from this class
 * @author Patrick Setiawan
 */

public class RideableSprite extends MovingSprite {
    private static final float safeguard = 2f;

    public RideableSprite(String imageSrc, float x, float y, boolean movesRight) throws SlickException {
        super(imageSrc, x, y, movesRight);
    }

    /* Create a safeguard, used to prevent player from being dragged offscreen by the rideable object. Instead the
    player just barely stays at the edge of the screen */
    public static float getSafeguard() {
        return safeguard;
    }

    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);
        // Checks if player/extra life is not standing on a rideable object
        if ((this.getBoundingBox().intersects(other.getBoundingBox())) && (other instanceof Player || other instanceof
                ExtraLife)) {
            this.setIsCarrying(true);
        } else {
            this.setIsCarrying(false);
        }

        /* Because of the safeguard, if for example the player stands on the left side of the screen's safeguard, the
        the player will not move along with a log tha moves left. This is intended behaviour. However, if the player
        moves up to a log that moves right, the player should be dragged along with it since the log's direction has
        changed and the player should be able to move right. The code below is therefore created for this function */
        if (this.getBoundingBox().intersects(other.getBoundingBox())) {
            if (this.getMovesRight() && other.getBoundingBox().getRight() + this.getSafeguard() < App.SCREEN_WIDTH) {
                other.setXCoor(other.getXCoor() + this.getSpeed());
            } else if (other.getBoundingBox().getLeft() - this.getSafeguard() > App.SCREEN_X) {
                other.setXCoor(other.getXCoor() - this.getSpeed());
            }
        }
    }
}
