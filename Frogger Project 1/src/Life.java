import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The Life Class is used to render the Player's lives at the bottom left of the screen, based on the Player's current
 * life count. For each life the player has, a small image of the Player Sprite can be seen in the screen, indicating
 * the number of lives the Player currently has. If all the Player's lives are gone, the game exits
 * @author Patrick Setiawan
 */

public class Life extends Sprite {
    public Life(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }
}