import org.newdawn.slick.SlickException;

/**
 * LongLog Class which inherits from RideableSprite. Protects the Player from water
 * @author Patrick Setiawan
 */


public class LongLog extends RideableSprite {
    private float speed = 0.07f;

    public LongLog(String imageSrc, float x, float y, boolean movesRight) throws SlickException {
        super(imageSrc, x, y, movesRight);
        this.setSpeed(speed);
    }
}