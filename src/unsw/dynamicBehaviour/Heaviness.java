package unsw.dynamicBehaviour;

import unsw.dungeon.DungeonController;
import unsw.staticEntity.StaticEntity;

/**
 * How heavy an entity is.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public interface Heaviness {
	/**
	 * Method determines whether the entity is heavy enough to trigger floor switches.
	 * @param entity the floor switch
	 * @param controller the controller for the dungeon
	 */
	public void checkWeight(StaticEntity entity, DungeonController controller);
}
