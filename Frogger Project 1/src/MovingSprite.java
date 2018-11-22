import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * MovingSprite which inherits from Sprite. Objects from this class are generally Sprites that can move and collide with
 * player. It differs from Rideable Sprite in that it does not allow the Player to move into it. All the land vehicle
 * Sprites inherit from this class
 * @author Patrick Setiawan
 */

public class MovingSprite extends Sprite {
    private boolean movesRight;
    private float speed;

    // Adds a movesright boolean to moving sprites. Returns true if moving sprite moves right
    public MovingSprite(String imageSrc, float x, float y, boolean movesRight) throws SlickException {
        super(imageSrc, x, y);
        this.movesRight = movesRight;
    }

    public boolean getMovesRight() {
        return movesRight;
    }

    public void setMovesRight(boolean movesRight) {
        this.movesRight = movesRight;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    // Moves the sprites in their directions and returns them to the opposite side if it reaches the screen's edge
    @Override
    public void update(Input input, int delta, Sprite otherSprite) {
        super.update(input, delta, otherSprite);
        if (this.movesRight) {
            this.setXCoor(this.getXCoor() + speed);
            if (this.getBoundingBox().getLeft() >= App.SCREEN_WIDTH) {
                this.setXCoor(-(this.getBoundingBox().getWidth()/2));
            }
        }
        else {
            this.setXCoor(this.getXCoor() - speed);
            if (this.getBoundingBox().getRight() <= App.SCREEN_X) {
                this.setXCoor(App.SCREEN_WIDTH + (this.getBoundingBox().getWidth()/2));
            }
        }
    }
}
