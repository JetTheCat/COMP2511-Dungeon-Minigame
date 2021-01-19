package unsw.staticBehaviour;

import java.util.List;

import unsw.dungeonComposite.GoalObject;
import unsw.dynamicEntity.DynamicEntity;
import unsw.dynamicEntity.Player;
import unsw.staticEntity.StaticEntity;
import unsw.staticEntity.Treasure;
import unsw.dungeon.*;

/**
 * A behaviour that lets a player store an item in their inventory.
 * @author Jet Young Lim and Andrew Han for COMP2511 (2/11/19)
 *
 */
public class StoreItem implements InteractBehaviour {

	/**
	 * Method checks what happens when a player or other moving entity
	 * moves ontop of a tile with entities that don't move.
	 * Checks and only allows one sword and one key to be held by the player,
	 * but has no limit for picking up treasure.
	 * @param movingEntity the entity moving onto a new tile
	 * @param x the x coordinate of the tile that is being moved into
	 * @param y the y coordinate of the tile that is being moved into
	 * @param entity the entity that is being moved into
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void interact(DynamicEntity movingEntity, Integer x, Integer y, StaticEntity entity, DungeonController controller) {
		Tile[][] tiles = controller.getTiles();
		if(!(movingEntity instanceof Player)) {
			return;
		}

		Player player = (Player) movingEntity;
		movingEntity.executeMoveBehaviour(x,y, controller);
		
		if (entity instanceof Treasure) {
			for(GoalObject curr : controller.getGoalList()) {
				if(curr.getGoal().equals("treasure")) {
					curr.decreaseState();
					controller.modifyState("treasure");
					break;
				}
			}
			player.addToBackpack(entity);
			tiles[x][y].removeEntity(entity);
			controller.removeFromTile(entity);
			if(controller.checkGoal()) {
				controller.winGame();
			}
		} else if (!player.isHoldingKey()) {
			player.addToBackpack(entity);
			player.setHoldingKey(true);
			tiles[x][y].removeEntity(entity);
			controller.removeFromTile(entity);
			controller.modifyState("key");
		} 
		
	}

}
