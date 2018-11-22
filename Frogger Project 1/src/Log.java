import org.newdawn.slick.SlickException;

/**
 * Log Class which inherits from RideableSprite. Protects the player from water
 * @author Patrick Setiawan
 */


public class Log extends RideableSprite {
    private float speed = 0.1f;

    public Log(String imageSrc, float x, float y, boolean movesRight) throws SlickException {
        super(imageSrc, x, y, movesRight);
        this.setSpeed(speed);
    }
}
