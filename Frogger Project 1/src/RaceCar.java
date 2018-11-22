import org.newdawn.slick.SlickException;

/**
 * RaceCar Class which inherits from MovingSprite. It behaves as a hazard and makes the Player lose a life upon
 * collision. It's speed is higher than the other MovingSprites
 * @author Patrick Setiawan
 */


public class RaceCar extends MovingSprite {
    private float speed = 0.5f;

    public RaceCar(String imageSrc, float x, float y, boolean movesRight) throws SlickException {
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