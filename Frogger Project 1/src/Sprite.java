import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * The Sprite Class contains all objects in the game that have a picture and a bounding boxes. This includes most
 * objects in the game with the exception of backgrounds/tiles which do not require bounding boxes to be implemented.
 * Created Sprites have their own x and y coordinates, and their own bounding boxes.
 * @author Patrick Setiawan
 */


public class Sprite {
	private BoundingBox boundingBox;
	private Image spriteImage;
	private BoundingBox boundingBoxDirection;
    private int lifeCount;
    private boolean playerIsPushed;
    public static final int NONE = 0;
    private int prevDirection = NONE;
	private boolean isSolid = false;
    private boolean isRiding = false;
    private boolean isCarrying = false;
	private float xCoor;
	private float yCoor;

    // Getters and Setters for all the instance variables
    public boolean getPlayerIsPushed() {
        return playerIsPushed;
    }

    public void setPlayerIsPushed(boolean playerIsPushed) {
        this.playerIsPushed = playerIsPushed;
    }

	public BoundingBox getBoundingBoxDirection() { return boundingBoxDirection; }

	public void setBoundingBoxDirection(BoundingBox boundingBoxDirection) { this.boundingBoxDirection =
			boundingBoxDirection; }

	public boolean getIsCarrying() { return isCarrying; }

    public void setIsCarrying(boolean isCarrying) { this.isCarrying = isCarrying; }

    public boolean getIsRiding() { return isRiding; }

    public void setIsRiding(boolean isRiding) { this.isRiding = isRiding; }

    public int getLifeCount() { return lifeCount; }

    public void setLifeCount(int lifeCount) { this.lifeCount = lifeCount; }

    public boolean getIsSolid() {
		return isSolid;
	}

	public void setIsSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

    public int getPrevDirection() { return prevDirection; }

    public void setPrevDirection(int prevDirection) { this.prevDirection = prevDirection; }

	public Image getSpriteImage() { return spriteImage; }

	public void setSpriteImage(Image spriteImage) {
		this.spriteImage = spriteImage;
	}

	public float getXCoor() {
		return xCoor;
	}

	public void setXCoor(float xCoor) {
		this.xCoor = xCoor;
	}

	public float getYCoor() {
		return yCoor;
	}

	public void setYCoor(float yCoor) {
		this.yCoor = yCoor;
	}

	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}

	// Create a Sprite constructor
	public Sprite(String imageSrc, float x, float y) throws SlickException {
		this.spriteImage = new Image(imageSrc);
		this.xCoor = x;
		this.yCoor = y;
		this.boundingBox = new BoundingBox(this.spriteImage, x, y);
	}

	// Update the sprite
	public void update(Input input, int delta, Sprite otherSprite) {
		boundingBox.setX(this.getXCoor());
		boundingBox.setY(this.getYCoor());
		this.contactSprite(otherSprite);
	}


	public void test(Sprite other){
		this.getSpriteImage();
	}

	// Render the sprite
	public void render() {
		// Render the sprite such that the coordinates is always in the center of the sprite, regardless of its size
		getSpriteImage().draw(xCoor - getSpriteImage().getWidth()/2, yCoor - getSpriteImage().getHeight()/2);
	}

	// Should be called when one sprite makes contact with another
	public void contactSprite(Sprite other) {
	}
}