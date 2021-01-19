package unsw.staticBehaviour;

import java.util.List;

import unsw.dungeon.DungeonController;
import unsw.dungeon.Tile;
import unsw.dungeonComposite.GoalObject;
import unsw.dynamicEntity.DynamicEntity;
import unsw.staticEntity.StaticEntity;

/**
 * The behaviour when moving entities try to move over static entities.
 * @author Jet Young Lim and Andrew Han for COMP2511 (2/11/19)
 *
 */
public interface InteractBehaviour {
	/**
	 * Method checks what happens when a player or other moving entity
	 * moves ontop of a tile with entities that don't move.
	 * @param movingEntity the entity moving onto a new tile
	 * @param x the x coordinate of the tile that is being moved into
	 * @param y the y coordinate of the tile that is being moved into
	 * @param entity the entity that is being moved into
	 * @param controller the controller for the dungeon
	 */
	public void interact(DynamicEntity movingEntity, Integer x, Integer y, StaticEntity entity, DungeonController controller);

}
