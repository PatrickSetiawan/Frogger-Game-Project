import org.newdawn.slick.SlickException;

/**
 * Goal Class which inherits from Sprite. Reaching the Goals serve as the main objective of the player. The Goals are
 * located in between the trees and are initially not rendered (hence invisible). When the player reaches a Goal, the
 * Goal is rendered with an image of the Player Sprite (the frog) and becomes a Solid Tile, which prevents the player
 * from moving into an already-reached Goal. When all the goals have been reached in a level, the Player moves into the
 * next level. When all the goals in the last level have been reached, the Player wins and the game exits
 * @author Patrick Setiawan
 */

public class Goal extends Sprite{
    private boolean goalReached = false;

    public boolean getGoalReached() {
        return goalReached;
    }

    public void setGoalReached(boolean goalReached) {
        this.goalReached = goalReached;
    }

    public Goal(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);
        if ((this.getBoundingBox().intersects(other.getBoundingBox())) && other instanceof Player) {
            // Returns player to starting position if goal is reached
            if (!(this.getGoalReached())) {
                this.setGoalReached(true);
                other.setXCoor(App.STARTINGPLAYERX);
                other.setYCoor(App.STARTINGPLAYERY);
            }
            // If goal is already reached before, it acts like a solid
            else {
                other.setYCoor(other.getYCoor() + App.TILE_DIMENSIONS);
                other.setPlayerIsPushed(true);
            }
        }
    }


}
