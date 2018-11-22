import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * Player Class which inherits from Sprite. This is the class that allows users to control the playable character. Its
 * Sprite has a Frog image. The user is able to control the Player using the 4 directional keys on the keyboard to move
 * it to its corresponding direction. The Player has 3 starting lives and loses a life when colliding with hazards. The
 * game ends when Player runs out of life or the Player completes the final level
 * @author Patrick Setiawan
 */

public class Player extends Sprite {
    private int startingLives = 3;

    public Player(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
        this.setLifeCount(startingLives);
        this.setPlayerIsPushed(false);
    }

    // List of interactions when player collides with different types of objects
    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);
    }

    // Allow player to move and revert player position if player attempts to move off-screen
    @Override
    public void update(Input input, int delta, Sprite otherSprite) {

        super.update(input, delta, otherSprite);

        contactSprite(otherSprite);

        if (input.isKeyPressed(Input.KEY_UP) && (this.getBoundingBox().getTop() - App.TILE_DIMENSIONS > App.SCREEN_Y)) {
            this.setYCoor(this.getYCoor() - App.TILE_DIMENSIONS);
        }
        else if (input.isKeyPressed(Input.KEY_DOWN) && (this.getBoundingBox().getBottom() + App.TILE_DIMENSIONS <
                App.SCREEN_HEIGHT)) {
            this.setYCoor(this.getYCoor() + App.TILE_DIMENSIONS);
        }
        else if (input.isKeyPressed(Input.KEY_LEFT) && (this.getBoundingBox().getLeft() - App.TILE_DIMENSIONS >
                App.SCREEN_X)) {
            this.setXCoor(this.getXCoor() - App.TILE_DIMENSIONS);
        }
        else if (input.isKeyPressed(Input.KEY_RIGHT) && (this.getBoundingBox().getRight() + App.TILE_DIMENSIONS <
                App.SCREEN_WIDTH)) {
            this.setXCoor(this.getXCoor() + App.TILE_DIMENSIONS);
        }

        /* Player loses a life when pushed off screen by the bulldozer (specifically, player loses a life when any part
        of the player touches the horizontal edges of the screen) and returned to original position */
        if (this.getXCoor() + App.TILE_OFFSET_CENTRE_X >= App.SCREEN_WIDTH || this.getXCoor() - App.TILE_OFFSET_CENTRE_X
                <= App.SCREEN_X) {
            this.setLifeCount(this.getLifeCount()-1);
            this.setXCoor(App.STARTINGPLAYERX);
            this.setYCoor(App.STARTINGPLAYERY);
        }
    }

    @Override
    public void render() {
        super.render();
    }
}