package unsw.staticEntity;

import java.util.List;

import unsw.dungeon.DungeonController;
import unsw.dungeon.Entity;
import unsw.dungeonComposite.GoalObject;
import unsw.dynamicEntity.DynamicEntity;
import unsw.staticBehaviour.InteractBehaviour;

/**
 * An entity that will not move from its tile position.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class StaticEntity extends Entity {
	private InteractBehaviour behaviour;
	
	/**
	 * Constructs an entity that will remain still and cannot move from its tile
	 * @param x the x coordinate of the entity
	 * @param y the y coordinate of the entity
	 * @param ID the unique identifier of the static entity
	 * @param interactionBehaviour the way it will interact with dynamic entities
	 */
	public StaticEntity(int x, int y, int ID, String view, InteractBehaviour behaviour) {
		super(x, y, ID, view);
		this.behaviour = behaviour;
	}
	
	/**
	 * Checks the interaction between entities that are moving ontop of this entity.
	 * @param entity the entity trying to move ontop of this static entity
	 * @param x the x coordinate of the entity
	 * @param y the y coordinate of the entity
	 * @param controller the controller for the dungeon.
	 */
	public void executeInteract(DynamicEntity entity, Integer x, Integer y, DungeonController controller) {
		behaviour.interact(entity, x, y, this, controller);
	}
	
}
