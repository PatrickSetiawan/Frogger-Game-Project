import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The World Class contains the World object whose job is to update and render everything in the game. It's primary
 * function is to construct, update and render the objects from all classes. It does so through it's update and render
 * function, which when called allows it to call the update and render function from all the classes themselves.
 * @author Patrick Setiawan
 */

public class World {

    private int level;
    private int timeElapsedMs = 0;
    private int turtleStartSinkTime = 7000;
    private int turtleStartRiseTime = 9000;
    private final int NEXTSINKTIME = 7000;
	private GrassBackground grassBackground;
	private Player player;
	private ExtraLife extraLife;
    private ArrayList<Life> lifeList;
	private ArrayList<Bus> busList;
	private ArrayList<RaceCar> raceCarList;
    private ArrayList<Bike> bikeList;
	private ArrayList<Bulldozer> bulldozerList;
    private ArrayList<Tree> treeList;
    private ArrayList<Log> logList;
    private ArrayList<LongLog> longLogList;
    private ArrayList<Turtle> turtleList;
	private ArrayList<WaterTile> waterTileList;
    private ArrayList<Goal> goalList;
	private ArrayList<Integer> offSetList;
	private ArrayList<Integer> separationDistList;

	public World(int level) throws SlickException {
        // Perform initialisation logic
        grassBackground = new GrassBackground("assets/grass.png");
        player = new Player("assets/frog.png", App.STARTINGPLAYERX, App.STARTINGPLAYERY);
        lifeList = new ArrayList<>();
        busList = new ArrayList<>();
        raceCarList = new ArrayList<>();
        bikeList = new ArrayList<>();
        bulldozerList = new ArrayList<>();
        logList = new ArrayList<>();
        longLogList = new ArrayList<>();
        turtleList = new ArrayList<>();
        treeList = new ArrayList<>();
        waterTileList = new ArrayList<>();
        goalList = new ArrayList<>();
        offSetList = new ArrayList<>();
        separationDistList = new ArrayList<>();

        // World constructor
        this.level = level;

        // Create the lives counter
        float lifeX = 24;
        float lifeY = 744;
        float lifeDistance = 32;

        // Construct the lives objects
        for (int i = 0; i < player.getLifeCount(); i++) {
            lifeList.add((new Life("assets/lives.png", lifeX, lifeY)));
            lifeX += lifeDistance;
        }

        // Create the goals
        // Store the coordinates of the goals
        ArrayList<Integer> GoalXCoor = new ArrayList<>(Arrays.asList(120, 312, 504, 696, 888));

        // Construct the goal objects
        for (int i = 0; i < GoalXCoor.size(); i++) {
            goalList.add((new Goal("assets/frog.png", GoalXCoor.get(i), App.TILE_DIMENSIONS)));
        }

        // Construct the water tiles and store their bounding boxes, starting from the top-left coordinate
        int waterRows = 6;
        int startingWaterX = 24;
        int startingWaterY = 96;

        // Construct the water tiles using loops
        for (int i = 0; i < waterRows; i++) {
            if (i != 0) {
                startingWaterY += App.TILE_DIMENSIONS;
            }
            for (int j = 0; j < App.SCREEN_WIDTH; j += App.TILE_DIMENSIONS) {
                waterTileList.add(new WaterTile("assets/water.png", startingWaterX, startingWaterY));
                startingWaterX += App.TILE_DIMENSIONS;
            }
            startingWaterX = 0;
        }

        // Construct the trees and store their bounding boxes
        int treeRows = 2;
        int startingTreeX = 0;
        int startingTreeY = 0;

        // Store the coordinates of the tree holes
        ArrayList<Integer> treeHoleXCoor = new ArrayList<>(Arrays.asList(96, 144, 288, 336, 480, 528, 672, 720, 864,
                912));

        // Construct the trees using loops
        for (int i = 0; i < treeRows; i++) {
            if (i != 0) {
                startingTreeY += App.TILE_DIMENSIONS;
            }
            for (int j = 0; j < App.SCREEN_WIDTH; j += App.TILE_DIMENSIONS) {
                // Create gaps in the trees for the "goal"
                if (!(treeHoleXCoor.contains(startingTreeX) && i == 1)) {
                    treeList.add(new Tree("assets/tree.png", startingTreeX, startingTreeY));
                }
                startingTreeX += App.TILE_DIMENSIONS;
            }
            startingTreeX = 0;
        }

        extraLife = new ExtraLife("assets/extralife.png", App.STARTINGPLAYERX + 5*App.TILE_DIMENSIONS,
                App.STARTINGPLAYERY - 7*App.TILE_DIMENSIONS);

        // Level 0: Construct the objects and their bounding boxes using loops
        if (this.level == 0) {
            int waterObjectRows = 6;

            /* Add offset and separation distance values to ArrayList in order from top to bottom of the water object
             row */
            offSetList.add(100);
            offSetList.add(0);
            offSetList.add(200);
            offSetList.add(100);
            offSetList.add(150);
            offSetList.add(0);

            separationDistList.add(350);
            separationDistList.add(400);
            separationDistList.add(500);
            separationDistList.add(280);
            separationDistList.add(600);
            separationDistList.add(400);

            int StartingObjectY = 96;

            /* Since unlike the vehicle rows the water objects are constructed at coordinates and directions that do not
            always follow a fixed pattern, exceptions have to be included */

            /* ArrayList to store rows of long or short logs and their directions (1 = Long, 0 = Short, 1 = Right,
            0 = Left) */
            ArrayList<Integer> LogLengthList = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 1, 0));
            ArrayList<Boolean> DirectionList = new ArrayList<>(Arrays.asList(true, false, true, false, true, true));

            // Construct the logs and long logs using loops
            for (int i = 0; i < waterObjectRows; i++) {
                if (i != 0) {
                  StartingObjectY += App.TILE_DIMENSIONS;
                }
                for (int j = offSetList.get(i); j <= App.SCREEN_WIDTH; j += separationDistList.get(i)) {
                    // Alternate between bus and bulldozers for each row
                    if (LogLengthList.get(i) == 0) {
                        logList.add(new Log("assets/log.png", j, StartingObjectY, DirectionList.get(i)));
                    } else {
                        longLogList.add(new LongLog("assets/longlog.png", j, StartingObjectY,
                                DirectionList.get(i)));
                    }
                }
            }

            // Reset the values in the list to be used for the next constructor
            offSetList.removeAll(offSetList);
            separationDistList.removeAll(separationDistList);

            int vehicleRows = 5;

            /* Add offset and separation distance values to ArrayList in order from top to bottom of the vehicle rows */
            offSetList.add(48);
            offSetList.add(0);
            offSetList.add(128);
            offSetList.add(64);
            offSetList.add(250);

            separationDistList.add(312);
            separationDistList.add(240);
            separationDistList.add(240);
            separationDistList.add(576);
            separationDistList.add(312);

            boolean movesRight = true;
            boolean createBus = false;

            StartingObjectY = 432;

            // Construct the bus and bulldozers using loops
            for (int i = 0; i < vehicleRows; i++) {
                if (i != 0) {
                    StartingObjectY += App.TILE_DIMENSIONS;
                }
                movesRight = !movesRight;
                createBus = !createBus;
                for (int j = offSetList.get(i); j <= App.SCREEN_WIDTH; j += separationDistList.get(i)) {
                    // Alternate between bus and bulldozers for each row
                    if (createBus) {
                        busList.add(new Bus("assets/bus.png", j, StartingObjectY, movesRight));
                    } else {
                        bulldozerList.add(new Bulldozer("assets/bulldozer.png", j, StartingObjectY,
                                movesRight));
                    }
                }
            }
        }

        /* Level 1: Construct the objects and their bounding boxes using loops. When the new level begins, use this
        constructor. The player's lives are reset when entering a new level. */
        else if (this.level == 1) {
            int waterObjectRows = 6;

            /* Add offset and separation distance values to ArrayList in order from top to bottom of the water object
            row */
            offSetList.add(100);
            offSetList.add(0);
            offSetList.add(200);
            offSetList.add(100);
            offSetList.add(150);
            offSetList.add(0);

            separationDistList.add(350);
            separationDistList.add(400);
            separationDistList.add(500);
            separationDistList.add(280);
            separationDistList.add(600);
            separationDistList.add(400);

            /* ArrayList to store rows of long logs, turtles and short logs (Long Log = 0, Turtle = 1, Log = 2) */
            ArrayList<Integer> waterObjectList = new ArrayList<>(Arrays.asList(0, 1, 2, 1, 0, 1));

            boolean movesRight = false;
            int StartingObjectY = 96;

            // Construct the logs and long logs using loops
            for (int i = 0; i < waterObjectRows; i++) {
                if (i != 0) {
                    StartingObjectY += App.TILE_DIMENSIONS;
                }
                movesRight = !movesRight;
                for (int j = offSetList.get(i); j <= App.SCREEN_WIDTH; j += separationDistList.get(i)) {
                    // Alternate between bus and bulldozers for each row
                    if (waterObjectList.get(i) == 0) {
                        longLogList.add(new LongLog("assets/longlog.png", j, StartingObjectY, movesRight));
                    } else if (waterObjectList.get(i) == 1) {
                        turtleList.add(new Turtle("assets/turtles.png", j, StartingObjectY, movesRight));
                    } else if (waterObjectList.get(i) == 2) {
                        logList.add(new Log("assets/log.png", j, StartingObjectY, movesRight));
                    }
                }
            }

            // Reset the values in the list to be used for the next constructor
            offSetList.removeAll(offSetList);
            separationDistList.removeAll(separationDistList);

            int vehicleRows = 5;

            /* Add offset and separation distance values to ArrayList in order from top to bottom of the vehicle rows */
            offSetList.add(360);
            offSetList.add(0);
            offSetList.add(128);
            offSetList.add(64);
            offSetList.add(250);

            separationDistList.add(312);
            separationDistList.add(240);
            separationDistList.add(240);
            separationDistList.add(576);
            separationDistList.add(250);

            /* Store the types of vehicles to construct from the top row to bottom, with (0,1,2,3) corresponding to
            (bike, bulldozer, bus, racecar) respectively */
            ArrayList<Integer> vehicleList = new ArrayList<>(Arrays.asList(0,1,2,3,0));

            movesRight = true;
            StartingObjectY = 432;

            // Construct the bus and bulldozers using loops
            for (int i = 0; i < vehicleRows; i++) {
                if (i != 0) {
                    StartingObjectY += App.TILE_DIMENSIONS;
                }
                movesRight = !movesRight;
                for (int j = offSetList.get(i); j <= App.SCREEN_WIDTH; j += separationDistList.get(i)) {
                    // Alternate between bus and bulldozers for each row
                    if (vehicleList.get(i) == 0) {
                        bikeList.add(new Bike("assets/bike.png", j, StartingObjectY, movesRight));
                    } else if (vehicleList.get(i) == 1) {
                        bulldozerList.add(new Bulldozer("assets/bulldozer.png", j, StartingObjectY,
                                movesRight));
                    } else if (vehicleList.get(i) == 2) {
                        busList.add(new Bus("assets/bus.png", j, StartingObjectY, movesRight));
                    } else if (vehicleList.get(i) == 3) {
                        raceCarList.add(new RaceCar("assets/racecar.png", j, StartingObjectY, movesRight));
                    }
                }
            }
        }
	}

	public void update(Input input, int delta) {

	    // Accumulate the time elapsed since the game has started
	    timeElapsedMs += delta;

		// Update all of the sprites in the game
        int currLifeCount = player.getLifeCount();

        player.update(input, delta, player);

        extraLife.update(input, delta, player);

        for (Log i : logList) {
            i.update(input, delta, player);
            // Checks if a log is already carrying a player to prevent other logs from updating the player
            if (i.getIsCarrying()) {
                player.setIsRiding(true);
            }
        }
        for (LongLog i : longLogList) {
            i.update(input, delta, player);
            // Checks if a long log is already carrying a player
            if (i.getIsCarrying()) {
                player.setIsRiding(true);
            }
        }
        for (Turtle i : turtleList) {
            // Every 7 seconds, turtle no longer allows the player to ride on it
            if (timeElapsedMs >= turtleStartSinkTime && timeElapsedMs <= turtleStartRiseTime) {
                i.setIsFloating(false);
            }
            else if (timeElapsedMs > turtleStartRiseTime) {
                turtleStartSinkTime += NEXTSINKTIME;
                turtleStartRiseTime += NEXTSINKTIME;
            }
            else {
                i.setIsFloating(true);
            }
            i.update(input, delta, player);
            // Checks if a turtle is already carrying a player
            if (i.getIsCarrying()) {
                player.setIsRiding(true);
            }
        }

        // If player is riding a log/turtle, player does not lose a life
        for (WaterTile i : waterTileList) {
            i.update(input, delta, player);
        }

        for (Bus i : busList) {
            i.update(input, delta, player);
        }

        for (RaceCar i : raceCarList) {
            i.update(input, delta, player);
        }
        for (Bike i : bikeList) {
            i.update(input, delta, player);
        }

		for (Bulldozer i : bulldozerList) {
		    i.update(input, delta, player);
            if (player.getPlayerIsPushed()) {
                // Reset push status and break the loop
                player.setPlayerIsPushed(false);
                break;
            }
        }

        for (Tree i : treeList) {
            i.update(input, delta, player);
            /* If player is already pushed by a tree, skip the rest so that player won't be pushed twice (2 tiles) when
            simultaneously intersecting two trees */
            if (player.getPlayerIsPushed()) {
                // Break the loop
                break;
            }
        }

        for (Goal i : goalList) {
            i.update(input, delta, player);
            /* Similarly, since the goal is adjacent to the tree (both solid) skip the rest so that player won't be
            pushed twice (2 tiles) when simultaneously intersecting with a goal and a tree */
            if (player.getPlayerIsPushed()) {
                // Reset push status and break the loop
                player.setPlayerIsPushed(false);
                break;
            }
        }

        for (Life i: lifeList) {
            i.update(input, delta, player);
        }

        float xIncrement = 32;
        float lastLifeX = (lifeList.get(lifeList.size() - 1)).getXCoor() + xIncrement;
        float lastLifeY = (lifeList.get(lifeList.size() - 1)).getYCoor();

        // Life counter changes (life removed/added) based on player's lives
        if (player.getLifeCount() < currLifeCount) {
            // Game over if player has zero lives (game exits automatically if player reaches zero lives)
            lifeList.remove(lifeList.size()-1);
        }
        else if (player.getLifeCount() > currLifeCount){
            try {
                lifeList.add((new Life("assets/lives.png", lastLifeX, lastLifeY)));
            }
            catch (SlickException ex){
            }
        }

        // Resets player riding status
        player.setIsRiding(false);

        boolean levelComplete = true;

        // Checks if all goal has been reached
        for (Goal i: goalList) {
            if (!i.getGoalReached()) {
                levelComplete = false;
            }
        }

        // Checks if level one is also completed
        if (levelComplete && !App.levelZero) {
            //App.levelOne = false;
            System.exit(0);
        }

        // If level Zero completed, move to level One
        if (levelComplete) {
            App.levelZero = false;
        }
	}

	public void render(Graphics g) {
		// Draw all of the sprites in the game
		grassBackground.render();

        for (WaterTile i : waterTileList) {
            i.render();
        }
        for (Log i: logList) {
            i.render();
        }
        for (LongLog i: longLogList) {
            i.render();
        }
        for (Turtle i : turtleList) {
            // Every 7 seconds, don't render the turtle
            if (timeElapsedMs >= turtleStartSinkTime && timeElapsedMs <= turtleStartRiseTime) {
                break;
            }
            else if (timeElapsedMs > turtleStartRiseTime) {
                turtleStartSinkTime += NEXTSINKTIME;
                turtleStartRiseTime += NEXTSINKTIME;
            }
            i.render();
        }

        extraLife.render();
        player.render();

		for (Bus i : busList) {
			i.render();
		}
		for (RaceCar i : raceCarList) {
		    i.render();
        }
        for (Bike i : bikeList) {
            i.render();
        }
		for (Bulldozer i : bulldozerList) {
		    i.render();
        }
        for (Tree i : treeList) {
            i.render();
        }
        for (Goal i : goalList) {
            // Only render the frog in the goal if it has been reached
            if (i.getGoalReached()) {
                i.render();
            }
        }
        for (Life i: lifeList) {
            i.render();
        }
	}
}