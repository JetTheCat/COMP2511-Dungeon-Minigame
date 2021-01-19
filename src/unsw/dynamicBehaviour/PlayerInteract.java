package unsw.dynamicBehaviour;

import java.awt.Point;

import unsw.dungeon.*;
import unsw.dungeonComposite.GoalObject;
import unsw.dynamicEntity.Boulder;
import unsw.dynamicEntity.Direction;
import unsw.dynamicEntity.DynamicEntity;
import unsw.dynamicEntity.Enemy;
import unsw.dynamicEntity.Player;

/**
 * The behaviour that causes a player to interact with other moving objects.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class PlayerInteract implements DynamicInteraction {

	/**
	 * Method executes the interaction between two moving entities
	 * If the movingEntity is a player and they are moving onto a boulder, it tries
	 * to see if the boulder can be moved.
	 * If they are moving onto a enemy, the game state changes to a lose state as the 
	 * player has died.
	 * @param movingEntity the entity moving onto a new tile
	 * @param x the x coordinate of the tile that is being moved into
	 * @param y the y coordinate of the tile that is being moved into
	 * @param entity the entity that is being moved into
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void interact(DynamicEntity movingEntity, Integer x, Integer y, DynamicEntity entity,
			DungeonController controller) {
		Player player = (Player) movingEntity;
		Tile[][] tiles = controller.getTiles();
		if (entity instanceof Enemy ) {
			player.executeMoveBehaviour(x, y, controller);
			if (player.isInvincible()) {
				tiles[x][y].killEnemies(controller);
				for(GoalObject curr : controller.getGoalList()) {
					if(curr.getGoal().equals("enemies")) {
						curr.decreaseState();
						controller.modifyState("enemies");
						break;
					}
				}
				if(controller.checkGoal()) {
					controller.winGame();
				}
			}else {
				controller.loseGame();
			}
		} else if (entity instanceof Boulder) {
			Boulder boulder = (Boulder) entity;
			Direction direction = player.getDirection();
			Point boulderPoint = new Point(boulder.getX(), boulder.getY());
			Point newPoint = direction.getPosition(boulderPoint);
			if (boulder.pushed(entity, newPoint.x, newPoint.y, controller)) {
				player.executeMoveBehaviour(x, y, controller);
			}
		}
		

	}

}
