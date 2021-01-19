package unsw.dynamicEntity;

import unsw.dungeon.DungeonController;
import unsw.dungeon.Entity;
import unsw.dynamicBehaviour.DynamicInteraction;
import unsw.dynamicBehaviour.DynamicMoveBehaviour;
import unsw.dynamicBehaviour.Heaviness;
import unsw.staticEntity.StaticEntity;

/**
 * An entity that can move or be moved in the dungeon.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class DynamicEntity extends Entity {
	private Heaviness mass;
	private DynamicInteraction interactionBehaviour;
	private DynamicMoveBehaviour moveBehaviour;
	
	/**
	 * Constructs an entity that can move or be moved.
	 * @param x the x coordinate of the entity
	 * @param y the y coordinate of the entity
	 * @param ID the unique identifier of the dynamic entity
	 * @param mass the heaviness of the object (light/heavy)
	 * @param interactionBehaviour the way it will interact with other dynamic entities
	 * @param moveBehaviour the way the entity will move 
	 */
	public DynamicEntity(int x, int y, int ID, String view, Heaviness mass, DynamicInteraction interactionBehaviour, DynamicMoveBehaviour moveBehaviour) {
		super(x, y, ID, view);
		this.mass = mass;
		this.interactionBehaviour = interactionBehaviour;
		this.moveBehaviour = moveBehaviour;
	}
	
	/**
	 * Method determines whether the entity is heavy enough to trigger floor switches.
	 * @param entity the floor switch
	 * @param controller the controller for the dungeon
	 */
	public void executeCheckWeight(StaticEntity entity, DungeonController controller) {
		mass.checkWeight(entity, controller);
	}
	
	/**
	 * Method executes the interaction between two moving entities
	 * @param entity the entity that is being moved into
	 * @param x the x coordinate of the tile that is being moved into
	 * @param y the y coordinate of the tile that is being moved into
	 * @param controller the controller for the dungeon
	 */
	public void executeCheckInteraction(DynamicEntity entity, Integer x, Integer y, DungeonController controller) {
		interactionBehaviour.interact(this, x, y, entity, controller);
		
	}	
	
	/**
	 * Moves this entity to a particular tile.
	 * @param x the x coordinate that the entity wants to move to
	 * @param y the y coordinate that the entity wants to move to
	 * @param controller the controller for the dungeon
	 */
	public void executeMoveBehaviour(Integer x, Integer y, DungeonController controller) {
		moveBehaviour.move(this, x, y,controller);
	}
}
