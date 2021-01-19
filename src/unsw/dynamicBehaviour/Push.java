package unsw.dynamicBehaviour;

import unsw.dungeon.DungeonController;
import unsw.dynamicEntity.DynamicEntity;

/**
 * The behaviour that occurs when a boulder is pushed.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class Push implements DynamicInteraction {

	/**
	 * Method executes the interaction between two moving entities
	 * This behaviour is irrelevant for boulders 
	 * as boulder movement is covered by Grid's playerInteraction method.
	 * It is only needed to set up a Boulder entity.
	 * @param movingEntity the entity moving onto a new tile
	 * @param x the x coordinate of the tile that is being moved into
	 * @param y the y coordinate of the tile that is being moved into
	 * @param entity the entity that is being moved into
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void interact(DynamicEntity movingEntity, Integer x, Integer y, DynamicEntity entity,
			DungeonController controller) {
		// Not needed as it is executed by  Grid playerInteraction

	}


}
