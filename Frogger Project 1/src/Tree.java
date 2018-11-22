import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * Tree Class which inherits from Sprite. It acts like a "solid" which does not allow the Player to move into it and is
 * NOT a hazard. It prevents the Player from moving into it by pushing the Player back in the direction it came from, if
 * the Player attempts to move into it
 * @author Patrick Setiawan
 */

public class Tree extends Sprite {

    // Sets all trees to be solids
    public Tree(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
        this.setIsSolid(true);
    }

    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);
        // "Pushes" the player back one tile if player attempts to move into tree
        if (this.getBoundingBox().intersects(other.getBoundingBox()) && other instanceof Player) {
            other.setYCoor(other.getYCoor() + App.TILE_DIMENSIONS);
            other.setPlayerIsPushed(true);
        }
    }
}