import org.newdawn.slick.SlickException;

/**
 * ExtraLife Class which inherits from Sprite. It gives the Player an extra life and disappears upon collision with the
 * Player, by increasing the Player's lifecount by 1. Only one ExtraLife object is present in each level
 * @author Patrick Setiawan
 */

public class ExtraLife extends Sprite {
    private boolean lifeTaken = false;

    public ExtraLife(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    // Player gains a life upon collision
    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);
        // Extra life is taken if it intersects with Player
        if (this.getBoundingBox().intersects(other.getBoundingBox()) && !this.lifeTaken) {
            other.setLifeCount(other.getLifeCount()+1);
            this.lifeTaken = true;
        }
    }

    // If the extra life is already taken, it disappears
    @Override
    public void render() {
        if (!this.lifeTaken) {
            super.render();
        }
    }
}
