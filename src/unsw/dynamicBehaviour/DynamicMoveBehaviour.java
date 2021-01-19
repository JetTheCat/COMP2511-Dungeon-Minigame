package unsw.dynamicBehaviour;

import unsw.dungeon.DungeonController;
import unsw.dynamicEntity.DynamicEntity;

/**
 * The way an entity moves in the dungeon.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public interface DynamicMoveBehaviour {
	/**
	 * Moves the entity to a particular tile.
	 * @param entity the entity that is moving
	 * @param x the x coordinate that the player wants to move to
	 * @param y the y coordinate that the player wants to move to
	 * @param controller the controller for the dungeon
	 */
	public void move (DynamicEntity entity, Integer x, Integer y, DungeonController controller);
}
