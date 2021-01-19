/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeonComposite.*;
import unsw.dungeonState.DungeonState;
import unsw.dungeonState.LoseDungeonState;
import unsw.dungeonState.PauseDungeonState;
import unsw.dungeonState.PlayDungeonState;
import unsw.dungeonState.WinDungeonState;
import unsw.dynamicEntity.DynamicEntity;
import unsw.dynamicEntity.Enemy;
import unsw.dynamicEntity.Player;
import unsw.staticEntity.Exit;
import unsw.staticEntity.FloorSwitch;
import unsw.staticEntity.Treasure;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 * Modified by Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class Dungeon {
	private int id;
    private int width, height;
    private List<Entity> entities;
    private DungeonGrid grid;
    private Player player;
    private List<GoalObject> goalList;
    private GoalComposite goals;
    private DungeonState currentState;
    private DungeonState loseState;
    private DungeonState winState;
    private DungeonState pauseState;
    private DungeonState playState;

    /**
     * Constructs a dungeon. A dungeon is a labyrinthine environment filled with entities.
     * It has a state which can change based on movements from the player. 
     * It has a list of goals that the player must complete.
     * It has a grid which the entities will occupy.
     * @param id the unique identifier for the dungeon.
     * @param width the width of the dungeon
     * @param height the height of the dungeon
     */
    public Dungeon(int id, int width, int height) {
    	this.id = id; 
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.grid = new DungeonGrid(width, height, id);
        this.currentState = new PlayDungeonState(this);
        this.playState = this.currentState;
        this.winState = new WinDungeonState(this);
        this.loseState = new LoseDungeonState(this);
        this.pauseState = new PauseDungeonState(this);
        this.goalList = new ArrayList<GoalObject>();
        this.goals = new GoalComposite("AND");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
	public void setDungeonState(DungeonState state) {
		currentState = state;
	}

	/**
	 * Method checks the interaction between a player and the entities in a specific tile.
	 * Occurs when a player wants to move to the tile corresponding to the x and y coordinates
	 * @param x the x coordinate the player wants to move to
	 * @param y the y coordinate the player wants to move to
	 * @param dungeonController the controller for the dungeon
	 */
	public void checkInteraction(Integer x, Integer y, DungeonController dungeonController) {
		grid.playerInteraction(player, x, y, dungeonController);
	} 
	
	/**
	 * Adds a goal to the list of goals. 
	 * Added using a string of either "exit", "enemies", "boulders", "treasure"
	 * @param string the goal that needs to be added
	 */
	public void addGoal(String string) {
		goals.addGoal(string);
	}

	/**
	 * Adds a goal to the list of goals
	 * Adds a GoalComposite, which can consist of smaller subgoals.
	 * @param goal the GoalComposite that is being added
	 */
	public void addGoal(GoalComposite goal) {
		goals.addGoal(goal);
	}

	/**
	 * Checks if the goals have been completed.
	 * @return true if the goals have been completed, otherwise, false.
	 */
	public boolean checkGoal() {
		return goals.checkGoal(goalList);
	}
	
	/**
	 * Method counts the number of entities of a specific type within the dungeon.
	 * Checks using "treasure", "boulders", "enemies", "exit"
	 * @param entType the type of entity that is being searched for
	 * @return the number of entities that is being searched for
	 */
	public int getEntityCount(String entType) {
		int count = 0;
		switch(entType) {
		case("treasure"):
			for(Entity curr : entities) {
				if (curr instanceof Treasure) {
					count ++;
				}
			}
			break;
		case("boulders"):
			for(Entity curr : entities) {
				if (curr instanceof FloorSwitch) {
					count++;
				}
			}
			break;
		case("enemies"):
			for(Entity curr : entities) {
				if (curr instanceof Enemy) {
					count++;
				}
			}
			break;
		case("exit"):
			for(Entity curr : entities) {
				if (curr instanceof Exit) {
					count++;
				}
			}
			break;
			
		}
		
		return count;
	}
	
	/**
	 * Method creates a new GoalObject and adds it to the list of GoalObjects
	 * The string can be either "treasure", "boulders", "enemies", "exit"
	 * @param goal a string containing the goal that is being added
	 */
	public void createGoalObject(String goal) {
		goalList.add(new GoalObject(getEntityCount(goal), goal));
	}
	
    public Tile[][] getTiles(){
    	return grid.getTiles();
    }
    
    public List<GoalObject> getGoalList(){
    	return goalList;
    }

    /** 
     * Method changes the current state of the dungeon to it's win state,
     * if the dungeon is in a play state
     */
	public void winGame() {
		currentState.winGame();
	}
	
    /** 
     * Method changes the current state of the dungeon to it's lose state,
     * if the dungeon is in a play state
     */
	public void loseGame() {
		currentState.loseGame();
		
	}

	/**
	 * Method checks the interaction between a boulder and the entities in a specific tile.
	 * Occurs when a player is trying to push a boulder.
	 * @param entity the boulder that is being pushed
	 * @param x the x coordinate the boulder wants to move to
	 * @param y the y coordinate the boulder wants to move to
	 * @param controller the controller for the dungeon
	 */
	public void checkBoulderInteraction(DynamicEntity entity, Integer x, Integer y, DungeonController controller) {
		grid.playerInteraction(entity, x, y, controller);
	}
	
	/** 
	 * Adds an entity to a specific tile
	 * @param obj the entity that is being added to the tile
	 * @param x the x coordinate of the tile the entity is being added to
	 * @param y the y coordinate of the tile the entity is being added to
	 */
	public void addTileEntity(Entity obj, int x, int y) {
		grid.addEntity(obj, x, y);
	}
	
	/** 
	 * Removes an entity from a specific tile
	 * @param obj the entity that is being removed from the tile
	 * @param x the x coordinate of the tile the entity is being removed from
	 * @param y the y coordinate of the tile the entity is being removed from
	 */
	public void removeTileEntity(Entity obj, int x, int y) {
		grid.addEntity(obj, x, y);
	}

	public DungeonState getPauseState() {
		return pauseState;
	}

	public DungeonState getLoseState() {
		return loseState;
	}

	public DungeonState getWinState() {
		return winState;
	}

	public DungeonState getPlayState() {
		return playState;
	}
	
	/**
	 * Function to initialize the goal object
	 * @param operator - The operator in string format, E.g. "AND" or "OR" only
	 */
	public void setGoalComposite(GoalComposite obj) {
		this.goals = obj;
	}
	
	
	/**
	 * Function to return list of dungeon entities
	 * @return List of type Entity containing Entity objects
	 */
	public List<Entity> getDungeonEntity(){
		return this.entities;
	}
	
	/**
	 * Method gets the list of goals for the dungeon.
	 * @return the list of GoalObjects associated with the dungeon
	 */
	public List<GoalObject> showGoalList(){
		return this.goalList;
	}
	
	public DungeonState getCurrentState() {
		return this.currentState;
	}
	
	/**
	 * Method gets the enemy ontop of the tile
	 * @return an Enemy object if there exists an enemy on the tile, otherwise null
	 */
	public Enemy getEnemy() {
		for(Entity tmp : entities) {
			if(tmp instanceof Enemy) {
				Enemy enemy = (Enemy) tmp;
				return enemy;
			}
		}
		
		return null;
	}
	
    /**
     * Method processes the goals that defines what must be achieved by the player for the dungeon to be considered complete.
     * More complex goals can be built by logically composing basic goals. 
     * @param json the JSONObject holding the goals
     */
    public void processJSONGoals(JSONObject json) {
    	String goal = json.getString("goal");
    	switch (goal) {	
        case "AND":
        case "OR":

        	JSONArray subgoals = json.getJSONArray("subgoals");
        	if (subgoals != null) {
        		GoalComposite operator = new GoalComposite(goal);
        		operator.addGoal(subgoals, this);
        		addGoal(operator);
        	}

        	break;
    	
    	default:
    		addGoal(goal);
    		createGoalObject(goal);
    	}

    }
}
