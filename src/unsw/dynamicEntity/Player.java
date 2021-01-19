package unsw.dynamicEntity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonController;
import unsw.dungeon.Entity;
import unsw.dungeon.Tile;
import unsw.dungeonComposite.GoalObject;
import unsw.dynamicBehaviour.LightMass;
import unsw.dynamicBehaviour.PlayerInteract;
import unsw.dynamicBehaviour.PlayerMove;
import unsw.staticBehaviour.*;
import unsw.staticEntity.Door;
import unsw.staticEntity.InvincibilityPotion;
import unsw.staticEntity.Key;
import unsw.staticEntity.StaticEntity;
import unsw.staticEntity.Sword;
import unsw.staticEntity.Trap;

/**
 * The player entity
 * @author Robert Clifton-Everest
 * Modified by Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 * 
 */
public class Player extends DynamicEntity {
	
    private Dungeon dungeon;
    private Direction direction;
    private boolean isInvincible;
    private boolean holdingSword;
    private boolean holdingKey;
    private boolean holdingTrap;
    private List<StaticEntity> backpack;

	/**
	 * Constructs a player, an avatar for the user to control.
	 * Has a backpack that can hold a list of item, can drink potions and equip weapons.
	 * @param dungeon the dungeon associated with the player
     * @param x the x coordinate of the entity
     * @param y the y coordinate of the entity
     * @param id the unique identifier for the entity
	 */
    public Player(Dungeon dungeon, int x, int y, int ID) {
        super(x, y, ID, "/player_down.png", new LightMass(), new PlayerInteract(), new PlayerMove()); 
        this.isInvincible = false;
        this.holdingKey = false;
        this.holdingSword = false;
        this.holdingTrap = false;
        this.dungeon = dungeon;
        this.backpack = new ArrayList<>();
        this.direction = Direction.DOWN;
    }

    public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0)
             x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
        	
            x().set(getX() + 1);
    }
    
    /**
     * Sets the invincibility state of the player to true
     * Enemies will run away from the player and be killed if touched by the player
     */
    public void addInvincible() {
    	this.isInvincible = true;
    }
    
    /**
     * Removes invincibility from the player
     */
    public void removeInvincible() {
    	this.isInvincible = false;
    }
    
    /**
     * Adds an entity to the backpack of the player
     * @param entity the entity being added
     */
    public void addToBackpack(StaticEntity entity) {
    	this.backpack.add(entity);
    }
    
    /**
     * Removes an entity from the backpack of the player
     * @param entity the entity being removed
     */
    public void removeFromBackpack(StaticEntity entity) {
    	this.backpack.remove(entity);
    }
    
    /**
     * Checks if the player is holding a specific entity within their backpack
     * @param entity the entity that is being checked for
     * @return true if the player is holding the entity, false otherwise
     */
    public boolean isHolding(StaticEntity entity) {
    	return backpack.contains(entity);

    }

	public boolean isInvincible() {
		return isInvincible;
	}

	public Direction getDirection() {
		return direction;
	}

	public boolean isHoldingSword() {
		return holdingSword;
	}

	public void setHoldingSword(boolean holdingSword) {
		this.holdingSword = holdingSword;
	}

	public boolean isHoldingKey() {
		return holdingKey;
	}

	public void setHoldingKey(boolean holdingKey) {
		this.holdingKey = holdingKey;
	}

	/**
	 * Method causes the player to swing a sword in front of the player
	 * Kills any enemies in front of the player and reduces the durability by one
	 * if any enemies have been killed.
	 * @param dungeonController the controller for the dungeon
	 */
	public void swingSword(DungeonController dungeonController) {
		if (holdingSword == true) {
			Direction direction = getDirection();
			Point playerPoint = new Point(getX(), getY());
			Point newPoint = direction.getPosition(playerPoint);
			Tile[][] tiles = dungeonController.getTiles();
			if (tiles[newPoint.x][newPoint.y].killEnemies(dungeonController)) {
				
				for(GoalObject curr : dungeonController.getGoalList()) {
					if(curr.getGoal().equals("enemies")) {
						curr.decreaseState();
						dungeonController.modifyState("enemies");
						break;
					}
				}
				Sword sword = getSword();
				sword.decreaseDurability(this);
				dungeonController.modifyState("sword");
				if(dungeonController.checkGoal()) {
					dungeonController.winGame();
				}
			}
		}
		
	}

	/**
	 * Method gets the sword being held by the player.
	 * @return the sword held by the player, else null
	 */
	private Sword getSword() {
		Sword sword = null;
		for (StaticEntity entity : backpack) {
			if (entity instanceof Sword) {
				return (Sword) entity;
			}
		}
		
		return sword;
	}

	/**
	 * Method causes the player to try an open the door.
	 * Checks if the door's id matches the id of the key of the player,
	 * if they are holding one.
	 * @param door the door the player is trying to open
	 * @return true if the key and door id match, false otherwise
	 */
	public boolean tryToOpen(Door door) {
		if(getKey() != null) {
			if (door.getID().equals(getKey().getID())) {
				door.openDoor();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method gets the key being held by the player.
	 * @return the key held by the player, else null
	 */
	private Key getKey() {
		Key key = null;
		for (StaticEntity entity : backpack) {
			if (entity instanceof Key) {
				return (Key) entity;
			}
		}
		
		return key;
	}
	
	/**
	 * Method gets the potion being used by the player.
	 * @return the potion used by the player, else null
	 */
	private InvincibilityPotion getPotion() {
		InvincibilityPotion potion = null;
		for (StaticEntity entity : backpack) {
			if (entity instanceof InvincibilityPotion ) {
				return (InvincibilityPotion) entity;
			}
		}
		
		return potion;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
		
	}
	
	
	public List<StaticEntity> getBackpack(){
		return this.backpack;
	}
	
	
	/**
	 * Method to reduce the potion effect duration on the player
	 */
	public void reducePotionDuration() {
		
		int max = this.backpack.size();
		
		for (int i = 0; i < max; i++) {
			Entity entity = (Entity) backpack.get(i);
			if (entity instanceof InvincibilityPotion) {
				InvincibilityPotion potion = (InvincibilityPotion) entity;
				potion.decreaseDuration(this);
			}
		}
	}

	
	/**
	 * Remove the key being held by a player, if it exists.
	 * @param keyId the id of the key
	 */
	public void removeKey(Integer keyId) {
		Key key = null;
		for (StaticEntity entity : backpack) {
			if (entity instanceof Key) {
				if (entity.getID().equals(keyId)) {
					key = (Key) entity;
					removeFromBackpack(key);
					break;
				}
			}
		}
		
	}
	
	
	/**
	 * Method to retrieve the durability of a sword object
	 * in the player's backpack
	 * @return The number of uses left for the sword object
	 */
	public Integer getDurability() {
		Sword sword = getSword();
		if (sword != null) {
			return sword.getDurability();
		}
		else {
			return 0;
		}
	}
	
	public Integer getDuration() {
		InvincibilityPotion potion = getPotion();
		return potion.getRemainingDuration();
	}

	public boolean isHoldingTrap() {
		return holdingTrap;
	}

	public void setHoldingTrap(boolean holdingTrap) {
		this.holdingTrap = holdingTrap;
	}

	/**
	 * Method to allow player to place down a trap
	 * in a dungeon tile
	 * @param controller - The  DungeonController object controlling the current dungeon
	 */
	public void setTrap(DungeonController controller) {
		if (holdingTrap == true) {
			Tile[][] tiles = controller.getTiles();
			if (tiles[getX()][getY()].getEntities().size() == 1) {	// Only the player standing here
				removeTrap();	// From backpack
				setHoldingTrap(false);
				Trap trap = getTrap();
				trap.setX(getX());
				trap.setY(getY());
				controller.addToTile(trap, getX(), getY());
				tiles[getX()][getY()].addEntity(trap);
				trap.setTrap();
				controller.setTrapImage(trap);
				controller.modifyState("trap");
			}
		}
		
	}
	
	/**
	 * Method gets the trap being held by the player.
	 * @return the trap used by the player, else null
	 */
	private Trap getTrap() {
		Trap trap = null;
		for (StaticEntity entity : backpack) {
			if (entity instanceof Trap) {
				return (Trap) entity;
			}
		}
		
		return trap;
	}
	
	
	/**
	 * Method to remove a Trap object from 
	 * the player's backpack
	 */
	private void removeTrap() {
		Trap trap = null;
		for (StaticEntity entity : backpack) {
			if (entity instanceof Trap) {
				removeFromBackpack(trap);
				break;
			}
		}
	}

}
