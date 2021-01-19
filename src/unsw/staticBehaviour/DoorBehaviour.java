package unsw.staticBehaviour;

import java.util.List;

import unsw.dungeon.DungeonController;
import unsw.dungeon.Tile;
import unsw.dungeonComposite.GoalObject;
import unsw.dynamicEntity.DynamicEntity;
import unsw.dynamicEntity.Player;
import unsw.staticEntity.Door;
import unsw.staticEntity.StaticEntity;

/**
 * A behaviour that blocks or allows passage through doors.
 * @author Jet Young Lim and Andrew Han for COMP2511 (2/11/19)
 *
 */
public class DoorBehaviour implements InteractBehaviour {

	/**
	 * Method checks what happens when a player or other moving entity
	 * moves ontop of a tile with entities that don't move.
	 * Checks if the door can be opened by the movingEntity and moves
	 * them through the door if it is opened.
	 * @param movingEntity the entity moving onto a new tile
	 * @param x the x coordinate of the tile that is being moved into
	 * @param y the y coordinate of the tile that is being moved into
	 * @param entity the entity that is being moved into
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void interact(DynamicEntity movingEntity, Integer x, Integer y, StaticEntity entity, DungeonController controller) {
		
		Tile[][] tiles = controller.getTiles();
		if (entity instanceof Door) {
			Door door = (Door) entity;
			if (movingEntity instanceof Player ) {
				Player player = (Player) movingEntity;
				if (!door.isOpened()) {
					if (player.tryToOpen(door)) {
						player.removeKey(door.getID());
						player.setHoldingKey(false);
						controller.modifyState("key");
						player.executeMoveBehaviour(x, y, controller);
						controller.setDoorImage(entity);
					}
				} else {
					player.executeMoveBehaviour(x, y, controller);
				}
			} else if (door.isOpened()) {
				movingEntity.executeMoveBehaviour(x, y, controller);
			}
		} 
	}
}
