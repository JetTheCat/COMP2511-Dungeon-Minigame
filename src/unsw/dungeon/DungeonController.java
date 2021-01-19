package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import unsw.dungeonComposite.*;
import unsw.dungeonState.DungeonState;
import unsw.dynamicEntity.*;
import unsw.staticEntity.*;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 * Modified by Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 */
public class DungeonController {
    @FXML
    private GridPane squares;
    @FXML
    private GridPane inventory;
    @FXML 
    private GridPane goal;
    @FXML
    private ImageView UIImage;
    private List<ImageView> initialEntities;
    private Player player;
    private Dungeon dungeon;
    private List<Entity> dungeonEntities;

	private SubMenuScreen subMenuScreen;
	private WinScreen winScreen;
	private LoseScreen loseScreen;
	
	// Image Views for Goal & Inventory Display UI
	private ImageView sword;
	private ImageView key;
	private ImageView potion;
	private ImageView gold;
	private ImageView enemy;
	private ImageView boulder;
	private ImageView exit;
	private ImageView playerView;
	private ImageView trap;

    /**
     * Constructs a dungeon controller. 
     * The dungeon controller processes any movements by the player and processes the 
     * dungeon/goals
     * @param dungeon the dungeon that is currently being monitored and controlled.
     * @param initialEntities a JavaFX list that holds the llist of initial entities.
     */
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.setEntities();
    }

    /**
     * Method initialises the dungeon images.
     */
    @FXML
    public void initialize() {
    	UIImage.setImage(new Image("/dungeon_brick.png"));
    	Image ground = new Image("/dirt_0_new.png");
 
    	// Add the ground first so it is below all other entities
    	for (int x = 0; x < dungeon.getWidth(); x++) {
    		for (int y = 0; y < dungeon.getHeight(); y++) {
    			squares.add(new ImageView(ground), x, y);
    		}
    	}

    	// Add to Goal Pane
    	ImageView goalHeading = new ImageView(new Image("/goals.png"));
    	gold = new ImageView(new Image("/gold_disable.png"));
    	enemy = new ImageView(new Image("/elf_disable.png"));
    	boulder = new ImageView(new Image("/boulder_disable.png"));
    	exit = new ImageView(new Image("/exit_disable.png"));
    	goal.add(goalHeading, 0, 0);
    	goal.add(gold, 1, 0);
    	goal.add(enemy, 3, 0);
    	goal.add(boulder, 5, 0);
    	goal.add(exit, 7, 0);
    	
    	// Add to Inventory Pane
    	ImageView bagHeading = new ImageView(new Image("bag.png"));
    	sword = new ImageView(new Image("/sword_disable.png"));
    	key = new ImageView(new Image("/key_disable.png"));
    	potion = new ImageView(new Image("/potion_disable.png"));
    	trap = new ImageView(new Image("/trap_disable.png"));
    	inventory.add(bagHeading, 0, 0);
    	inventory.add(sword, 1, 0);
    	inventory.add(new Label(), 2, 0);
    	inventory.add(key, 3, 0);
    	inventory.add(new Label(), 4, 0);
    	inventory.add(potion, 5, 0);
    	inventory.add(new Label(), 6, 0);
    	inventory.add(trap,7,0);
    	
        // Adds entity above the ground entity
        for (ImageView entity : initialEntities) {
        	if(entity.getId().equals("1337")) {
        		playerView = entity;
        	}
            squares.getChildren().add(entity);
        }
        
        addGoalsToUI();

    }
    
    
    /**
     * Method to display goal objects in the UI
     */
    public void addGoalsToUI() {
    	List<GoalObject> goals = dungeon.getGoalList();
    	for (GoalObject goal : goals) {
    		addGoalToUI(goal);
    	}
    }
    
    /**
     * Method adds a single goal to the UI
     * @param goal the goal that is being added
     */
    private void addGoalToUI(GoalObject goals) {
    	String goalString = goals.getGoal();
    	switch (goalString) {
    	case "treasure":
    		
    		gold.setImage(new Image("/gold_pile.png"));
    		goal.add(new Label("0/" + goals.getOriginalState()), 2, 0);
    		break;
    	case "enemies":
    		enemy.setImage(new Image("/deep_elf_master_archer.png"));
    		goal.add(new Label("0/" + goals.getOriginalState()), 4, 0);
    		break;
    	case "boulders":
    		boulder.setImage(new Image("/boulder.png"));
    		goal.add(new Label("0/" + goals.getOriginalState()), 6, 0);
    		break;
    	case "exit":
    		exit.setImage(new Image("/exit.png"));
    		goal.add(new Label("0/" + goals.getOriginalState()), 8, 0);
    		break;
    	}
    	
    }
    
    
    /**
     * Method to modify the state of the elements being displayed in the UI
     * @param obj - The name of the UI element in a String, E.g. "enemies", "treasure", etc.
     */
    public void modifyState(String obj) {
    	if (goal == null || inventory == null) {
    		return;
    	}
    	GoalObject goalObj = null;
		for (GoalObject g : dungeon.getGoalList()) {
			if (g.getGoal().equals(obj)) {
				goalObj = g;
				break;
			}	
		}	

    	switch (obj) {
    	case "treasure":
    		updateGoal(2, goalObj);
    		break;
    	case "enemies":
    		updateGoal(4, goalObj);
    		break;
    	case "boulders":
    		updateGoal(6, goalObj);
    		break;
    	case "exit":
    		updateGoal(8, goalObj);
    		break;
    	case "sword":
    		if (player.getDurability() == 0) {	// Change to grey and remove the text
        		sword.setImage(new Image("sword_disable.png"));
        		updateInventory(2, 0);
    		} else {
    	   		sword.setImage(new Image("greatsword_1_new.png"));
        		updateInventory(2, player.getDurability());
    		}

    		break;
    	case "potion":
    		if (!player.isInvincible()) {	// Change to grey and remove the text
        		potion.setImage(new Image("potion_disable.png"));
        		updateInventory(6, 0);
    		} else {
    	   		potion.setImage(new Image("brilliant_blue_new.png"));
    	   		updateInventory(6, player.getDuration());
    		}
    		break;
    	case "key":
    		if (!player.isHoldingKey()) {	// Change to grey and remove the text
        		key.setImage(new Image("key_disable.png"));
    		} else {
    	   		key.setImage(new Image("key.png"));

    		}
    		break;
    	case "trap":
    		if (!player.isHoldingTrap()) {
    			trap.setImage(new Image("trap_disable.png"));
    		} else {
    			trap.setImage(new Image("trap_open.png"));
    		}
    	}
    	
    }
    
    
    /**
     * Method to update the amount of uses left for a player item in the inventory UI
     * @param position - The position of the UI element in the gridPane
     * @param text - The amount of uses left for an item in 
     */
    private void updateInventory(int position, Integer text) {
		ObservableList<Node> goalNodes = inventory.getChildren();
    	for (Node n : goalNodes){
   			 if (n instanceof Label && GridPane.getColumnIndex(n) == position){
   	    	     Label label = (Label) n;
   				 if (text == 0) {
   					label.setText("");
   	    	     } else {
   	    	    	label.setText(text.toString());
   	    	     }
   				 break;
   	    	        
   			 }
   		}
	    
    }
    
    private void updateGoal(int position, GoalObject goalObj) {
		ObservableList<Node> goalNodes = goal.getChildren();
    	for (Node node : goalNodes){
   			 if (node instanceof Label && GridPane.getColumnIndex(node) == position){
   	    	        Label label= (Label) node; 
   	    	        int difference = goalObj.getOriginalState() - goalObj.getState();
   	    	        label.setText(difference + "/" + goalObj.getOriginalState());
   	    	        break;
   			 }
   		}
    }
    
    /*
     * Code referenced from:
     * https://stackoverflow.com/questions/45778386/in-javafx-how-to-remove-a-specific-node-from-a-gridpane-with-the-coordinate-of
     */
    public void removeFromTile(Entity obj) {
    	// For JUnit testing where the GridPane doesn't exist
    	if(squares == null) {
    		return;
    	}
    	
    	ObservableList<Node> children = squares.getChildren();
    	for (Node node : children){
    		 if(node.getId() != null) {

    			 if (node instanceof ImageView && GridPane.getRowIndex(node) == obj.getY() 
    	    			 && GridPane.getColumnIndex(node) == obj.getX() && 
    	    			 node.getId().equals(obj.getID().toString())){

    	    	        ImageView imageView= (ImageView) node; 
    	    	        squares.getChildren().remove(imageView);
    	    	        break;
    	    	 }
    		 }
    	}
    }
    
    /**
     * Method adds a trap image to the tile
     * @param obj
     * @param y 
     * @param x 
     */
    public void addToTile(Entity obj, int x, int y) {
    	if (squares == null) {
    		return;
    	}
    	ImageView trap = new ImageView (new Image ("/trap_open.png"));
    	trap.setId(obj.getID().toString());
    	squares.add(trap, x, y);
    }
    
    
    /**
     * Method to update the door object's image to an open image
     * @param obj - An object of type Entity & instance of Door
     */
    public void setDoorImage(Entity obj) {
    	// For JUnit testing where the GridPane doesn't exist
    	if(squares == null) {
    		return;
    	}
    	ObservableList<Node> children = squares.getChildren();
    	for (Node node : children){
    		 if(node.getId() != null) {
    			 if (node instanceof ImageView && GridPane.getRowIndex(node) == obj.getY() 
    	    			 && GridPane.getColumnIndex(node) == obj.getX() && 
    	    			 node.getId().equals(obj.getID().toString())){
    	    	        ImageView imageView= (ImageView) node; 
    	    	        imageView.setImage(new Image("/open_door.png"));
    	    	        break;
    	    	 }
    		 }
    	}
    }

    
    /**
     * Method to update the trap object's image to an open image
     * @param obj - An object of type Entity & instanceof Trap
     */
    public void setTrapImage(Entity obj) {
    	// For JUnit testing where the GridPane doesn't exist
    	if(squares == null) {
    		return;
    	}
    	ObservableList<Node> children = squares.getChildren();
    	for (Node node : children){
    		 if(node.getId() != null) {
    			 if (node instanceof ImageView && GridPane.getRowIndex(node) == obj.getY() 
    	    			 && GridPane.getColumnIndex(node) == obj.getX() && 
    	    			 node.getId().equals(obj.getID().toString())){
    	    	        ImageView imageView= (ImageView) node; 
    	    	        imageView.setImage(new Image("/trap_open.png"));
    	    	        break;
    	    	 }
    		 }
    	}
    }

    
    /**
     * Method handles inputs by the player and checks interactions when they try to move.
     * @param event the keyboard button that was pressed.
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case W:
        	player.setDirection(Direction.UP);
        	playerView.setImage(new Image("player_up.png"));
        	break;
        case A:
        	player.setDirection(Direction.LEFT);
        	playerView.setImage(new Image("player_left.png"));
        	break;
        case S:
        	player.setDirection(Direction.DOWN);
        	playerView.setImage(new Image("player_down.png"));
        	break;
        case D:
        	player.setDirection(Direction.RIGHT);
        	playerView.setImage(new Image("player_right.png"));
        	break;
        case UP:
        	player.setDirection(Direction.UP);
        	playerView.setImage(new Image("player_up.png"));
        	dungeon.checkInteraction(player.getX(), player.getY() - 1, this);
            break;
        case DOWN:
        	player.setDirection(Direction.DOWN);
        	playerView.setImage(new Image("player_down.png"));
        	dungeon.checkInteraction(player.getX(), player.getY() + 1, this);
            break;
        case LEFT:
        	player.setDirection(Direction.LEFT);
        	playerView.setImage(new Image("player_left.png"));
        	dungeon.checkInteraction(player.getX() - 1, player.getY(), this);
            break;
        case RIGHT:
        	player.setDirection(Direction.RIGHT);
        	playerView.setImage(new Image("player_right.png"));
        	dungeon.checkInteraction(player.getX() + 1, player.getY(), this);
            break;
        case SPACE:
        	if (player.isHoldingSword()) {
        		player.swingSword(this);
        		moveAllEnemies();
        	}
        	break;
        case ESCAPE:
        	subMenuScreen.start();
        	break;
        case F:
        	if(player.isHoldingTrap()) {
        		player.setTrap(this);	
        		if (player.isHoldingTrap())	
        			moveAllEnemies();
        	}
        	break;
        default:
            break;
        }
    }
    
    /**
     * Method moves all enemies in the dungeon.
     */
    public void moveAllEnemies() {
    	ArrayList<Entity> tmpList = new ArrayList<Entity>(getDungeonEntity());
    	int size = tmpList.size();
		for (int i = 0; i < size; i ++) {
			Entity entity = tmpList.get(i);
			if (entity instanceof Enemy) {
				Enemy enemy = (Enemy) entity;
				
				if(enemy.isAlive()) {
					enemy.moveEnemy(getPlayer(), this);
				}
			}
		}
	}

	public void setEntities(){
    	this.dungeonEntities = dungeon.getDungeonEntity();
    }
	
	public List<Entity> getDungeonEntity(){
		return this.dungeonEntities;
	}
    
    public void setPlayer() {
    	this.player = dungeon.getPlayer();
    }

	public Player getPlayer() {
		return player;
	}
	

    /**
     * Method checks if the dungeon's goals have been completed
     * @return true if completed, false otherwise
     */
    public boolean checkGoal() {
    	return dungeon.checkGoal();
    }
    
    public Tile[][] getTiles(){
    	return dungeon.getTiles();
    }
    
    public List<GoalObject> getGoalList(){
    	return dungeon.getGoalList();
    }

    /**
     * Method changes the state of the dungeon to its lose state, 
     * if the dungeon is in it's play state
     */
	public void loseGame() {
		dungeon.loseGame();
		if(loseScreen != null) {
			loseScreen.start(); // Change to lose screen when player dies
		}
	}
    
    /**
     * Method changes the state of the dungeon to its win state, 
     * if the dungeon is in it's play state
     */
	public void winGame() {
		dungeon.winGame();
		if (winScreen != null) {
			winScreen.start(); // Changes to win screen when completed dungeon
		}
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
		dungeon.checkBoulderInteraction(entity, x, y, controller);
	}

	public DungeonState getDungeonState() {
		// TODO Auto-generated method stub
		return dungeon.getCurrentState();
	}

	/**
	 * Method gets the other portal that corresponds to a portal's id.
	 * @param id the id of the portal being searched for
	 * @param portal1 the original portal
	 * @return the other corresponding portal, should not return null.
	 */
	public Portal getLinkedPortal(Integer id, Portal portal1) {
		Portal portal2 = null;
		for (Entity entity : dungeonEntities) {
			if (entity instanceof Portal) {
				Portal tempPortal = (Portal) entity;
				// .equals() over == because == only works for numbers between -128 and 127
				if (tempPortal.getID().equals(portal1.getID()) && 
					(tempPortal.getX() != portal1.getX() || tempPortal.getY() != portal1.getY())) {
					portal2 = tempPortal;
					break;
				}
			}
		}

		return portal2;
	}

	
    public void setSubMenuScreen(SubMenuScreen subMenuScreen) {
        this.subMenuScreen = subMenuScreen;
    }
    
    public SubMenuScreen getSubMenuScreen() {
    	return subMenuScreen;
    }
    
    public void setWinScreen(WinScreen obj) {
    	this.winScreen = obj;
    }
    
    public WinScreen getWinScreen() {
    	return winScreen;
    }
    
    public void setLoseScreen(LoseScreen obj) {
    	this.loseScreen = obj;
    }
    
    public LoseScreen getLoseScreen() {
    	return loseScreen;
    }

}

