package unsw.dynamicBehaviour;

import unsw.dungeon.DungeonController;
import unsw.dungeon.Tile;
import unsw.dynamicEntity.DynamicEntity;
import unsw.dynamicEntity.Enemy;
import unsw.dynamicEntity.Player;

/**
 * The behaviour that causes an enemy to kill a player.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class KillPlayer implements DynamicInteraction {

	/**
	 * Method executes the interaction between two moving entities
	 * If the movingEntity is a enemy and they are moving into a player, the
	 * player loses the game and changes the current game state to its lose state
	 * @param movingEntity the entity moving onto a new tile
	 * @param x the x coordinate of the tile that is being moved into
	 * @param y the y coordinate of the tile that is being moved into
	 * @param entity the entity that is being moved into
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void interact(DynamicEntity movingEntity, Integer x, Integer y, DynamicEntity entity,
			DungeonController controller) {

		if (movingEntity instanceof Enemy && entity instanceof Player){
			
			Player player = (Player) entity;
			
			if (entity.getX() != movingEntity.getX() || entity.getY() != movingEntity.getY()) {
				movingEntity.executeMoveBehaviour(x, y, controller);
			}
			
			if(!player.isInvincible()) {
				controller.loseGame();
			}
		}
	}

}
