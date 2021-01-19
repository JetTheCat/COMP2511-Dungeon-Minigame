package unsw.staticBehaviour;

import java.util.List;

import unsw.dynamicEntity.DynamicEntity;
import unsw.dynamicEntity.Player;
import unsw.staticEntity.StaticEntity;
import unsw.staticEntity.Sword;
import unsw.dungeon.*;

/**
 * A behaviour that lets players equip weapons
 * @author Jet Young Lim and Andrew Han for COMP2511 (2/11/19)
 *
 */
public class EquipItem implements InteractBehaviour {

	/**
	 * Method checks what happens when a player or other moving entity
	 * moves ontop of a tile with entities that don't move.
	 * Causes the player to pickup and equip a sword.
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
		movingEntity.executeMoveBehaviour(x, y, controller);
		Player player = (Player) movingEntity;
    	if (entity instanceof Sword) {
    		if (!player.isHoldingSword()) {
    			player.addToBackpack(entity);
    			player.setHoldingSword(true);
    			tiles[x][y].removeEntity(entity);
    			controller.removeFromTile(entity);
				controller.modifyState("sword");
    		}
    	}

	}

}
