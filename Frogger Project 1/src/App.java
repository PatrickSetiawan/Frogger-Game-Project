/**
 * Sample Project for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame {
    /** screen width, in pixels */
    public static final int SCREEN_WIDTH = 1024;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 768;
    /** tile dimensions in pixels */
    public static final int TILE_DIMENSIONS = 48;
    /** tile location offset from the object's centre when drawing the tile*/
    public static final int TILE_OFFSET_CENTRE_X = TILE_DIMENSIONS/2;
    public static final int TILE_OFFSET_CENTRE_Y = TILE_DIMENSIONS/2;
    /** starting coordinates (top-left)*/
    public static final int SCREEN_X = 0;
    public static final int SCREEN_Y = 0;
    /** Player's starting coordinates*/
    public static final float STARTINGPLAYERX = 512;
    public static final float STARTINGPLAYERY = 720;
    /** check whether the first level is complete*/
    public static boolean levelZero = true;

    private World world;
    private World world1;

    public App() {
        super("Shadow Leap");
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {
        world = new World(0);
        world1 = new World(1);
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        if (levelZero) {
            world.update(input, delta);
        }
        else {
            world1.update(input,delta);
        }
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
            throws SlickException {
        if (levelZero) {
            world.render(g);
        }
        else {
            world1.render(g);
        }
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}
