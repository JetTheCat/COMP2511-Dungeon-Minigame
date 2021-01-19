package unsw.staticBehaviour;

import java.util.List;

import unsw.dungeon.DungeonController;
import unsw.dungeon.Tile;
import unsw.dungeonComposite.GoalObject;
import unsw.dynamicEntity.DynamicEntity;
import unsw.staticEntity.StaticEntity;

/**
 * A behaviour that blocks movement into the tile that the entity occupies.
 * @author Jet Young Lim and Andrew Han for COMP2511 (2/11/19)
 *
 */
public class BlockEntity implements InteractBehaviour {

	/**
	 * Method checks what happens when a player or other moving entity
	 * moves ontop of a tile with entities that don't move.
	 * As this entity blocks movement, there will be no change in the system.
	 * @param movingEntity the entity moving onto a new tile
	 * @param x the x coordinate of the tile that is being moved into
	 * @param y the y coordinate of the tile that is being moved into
	 * @param entity the entity that is being moved into
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void interact(DynamicEntity movingEntity, Integer x, Integer y, StaticEntity entity, DungeonController controller) {
	}

}
