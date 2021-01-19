package unsw.dynamicBehaviour;

import unsw.dungeon.DungeonController;
import unsw.dynamicEntity.DynamicEntity;
import unsw.staticEntity.StaticEntity;
/**
 * The interaction between a dynamic entity and another dynamic entity.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public interface DynamicInteraction {
	/**
	 * Method executes the interaction between two moving entities
	 * @param movingEntity the entity moving onto a new tile
	 * @param x the x coordinate of the tile that is being moved into
	 * @param y the y coordinate of the tile that is being moved into
	 * @param entity the entity that is being moved into
	 * @param controller the controller for the dungeon
	 */
	public void interact(DynamicEntity movingEntity, Integer x, Integer y, DynamicEntity entity, DungeonController controller);
}
