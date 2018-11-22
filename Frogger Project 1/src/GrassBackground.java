import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * GrassBackground Class serves only as a background hence it does not need to inherit from Sprite, since its bounding
 * boxes has no purpose. The job of this class is to simply render the grass tiles as background
 * @author Patrick Setiawan
 */


public class GrassBackground {
    private int numberOfHorizontalTiles = App.SCREEN_WIDTH/App.TILE_DIMENSIONS + 1;
    private static final int TILE_LINES_NUMBER = 2;
    private static final int[] Y_LOCATIONS = new int[]{672, 384};
    private Image[][] tileBackgrounds;

    // Creates an array of images to be used in rendering the tiles
    public GrassBackground(String imagePath) throws SlickException {
        tileBackgrounds = new Image[TILE_LINES_NUMBER][numberOfHorizontalTiles];

        for (int i = 0; i < TILE_LINES_NUMBER; i++) {
            for (int j = 0; j < numberOfHorizontalTiles; j++) {
                tileBackgrounds[i][j] = new Image(imagePath);
            }
        }
    }

    // Renders the tiles based on the required positions
    public void render() {
        for (int i = 0; i < TILE_LINES_NUMBER; i++) {
            int xLocation = 0;
            for (int j = 0; j < numberOfHorizontalTiles; j++) {
                tileBackgrounds[i][j].draw(xLocation - App.TILE_OFFSET_CENTRE_X, Y_LOCATIONS[i] -
                        App.TILE_OFFSET_CENTRE_Y);
                xLocation += App.TILE_DIMENSIONS;
            }
        }
    }
}