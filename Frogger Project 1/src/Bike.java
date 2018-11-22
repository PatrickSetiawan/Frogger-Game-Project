import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Bike Class which inherits from MovingSprite. Behaves as a hazard and makes the player lose a life upon collision. It
 * also reverses direction every time it reaches the x-coordinates 1000 and 24 by reversing its movesRight boolean
 * variable
 * @author Patrick Setiawan
 */

public class Bike extends MovingSprite {
    private float speed = 0.2f;
    private static final float REVERSEXCOOR1 = 1000;
    private static final float REVERSEXCOOR2 = 24;

    public Bike(String imageSrc, float x, float y, boolean movesRight) throws SlickException {
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

    // Reverses the bike direction every time it reaches the x coordinates when it is supposed to reverse
    @Override
    public void update(Input input, int delta, Sprite otherSprite) {
        super.update(input, delta, otherSprite);
        if (this.getMovesRight() && this.getXCoor() >= this.REVERSEXCOOR1) {
            this.setMovesRight(false);
        }
        else if (!this.getMovesRight() && this.getXCoor() <= this.REVERSEXCOOR2){
            this.setMovesRight(true);
        }
    }
}

