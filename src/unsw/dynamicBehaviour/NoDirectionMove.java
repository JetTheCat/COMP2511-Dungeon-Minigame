package unsw.dynamicBehaviour;

import unsw.dungeon.DungeonController;
import unsw.dungeon.Tile;
import unsw.dynamicEntity.DynamicEntity;

/**
 * The most common way an entity will move; without considering direction.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class NoDirectionMove implements DynamicMoveBehaviour {

	/**
	 * Moves the entity to a particular tile.
	 * @param entity the entity that is moving
	 * @param x the x coordinate that the player wants to move to
	 * @param y the y coordinate that the player wants to move to
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void move(DynamicEntity entity, Integer x, Integer y, DungeonController controller) {
		Tile[][] tiles = controller.getTiles();
		tiles[entity.getX()][entity.getY()].removeEntity(entity);
		tiles[x][y].addEntity(entity);
		entity.setX(x);
		entity.setY(y);
	}

}
