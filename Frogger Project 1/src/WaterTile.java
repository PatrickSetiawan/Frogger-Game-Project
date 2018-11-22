import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * WaterTile class which inherits from Sprite. It is a static hazard and causes the Player to lose a life if it collides
 * with the Player. The only exception is if the Player is riding on a Rideable Sprite or a floating turtle
 * @author Patrick Setiawan
 */

public class WaterTile extends Sprite {

    // Creates an array of images to be used in rendering the tiles
    public WaterTile(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);
        if (this.getBoundingBox().intersects(other.getBoundingBox()) && (!other.getIsRiding())) {

        }

        // Player loses a life when colliding with a water tile
        if (this.getBoundingBox().intersects(other.getBoundingBox()) && (!other.getIsRiding())) {
            other.setLifeCount(other.getLifeCount()-1);
            other.setXCoor(App.STARTINGPLAYERX);
            other.setYCoor(App.STARTINGPLAYERY);
        }
    }
}