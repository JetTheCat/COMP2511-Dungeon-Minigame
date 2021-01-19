package unsw.dynamicBehaviour;

import unsw.dungeon.DungeonController;
import unsw.dungeon.Tile;
import unsw.dungeonState.LoseDungeonState;
import unsw.dynamicEntity.Direction;
import unsw.dynamicEntity.DynamicEntity;
import unsw.dynamicEntity.Player;

/**
 * The way a player will move; by considering direction.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class PlayerMove implements DynamicMoveBehaviour {

	/**
	 * Moves the player to a particular tile.
	 * Also causes the duration of the potion to be decreased
	 * and moves all enemies closer or further away from the player
	 * @param entity the entity that is moving
	 * @param x the x coordinate that the player wants to move to
	 * @param y the y coordinate that the player wants to move to
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void move(DynamicEntity entity, Integer x, Integer y, DungeonController controller) {
		if (!(controller.getDungeonState() instanceof LoseDungeonState)) {
			Tile[][] tiles = controller.getTiles();
			Player player = (Player) entity;
			tiles[entity.getX()][entity.getY()].removeEntity(entity);
			tiles[x][y].addEntity(entity);
			entity.setX(x);
			entity.setY(y);
			if (player.isInvincible()) {
				player.reducePotionDuration();
				controller.modifyState("potion");
			}
			controller.moveAllEnemies();
		}
	}
	
}
