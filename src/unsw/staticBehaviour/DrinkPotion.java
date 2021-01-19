package unsw.staticBehaviour;

import java.util.List;

import unsw.dungeon.DungeonController;
import unsw.dungeon.Tile;
import unsw.dungeonComposite.GoalObject;
import unsw.dynamicEntity.DynamicEntity;
import unsw.dynamicEntity.Player;
import unsw.staticEntity.StaticEntity;

/**
 * A behaviour that causes picked up potions to be drunk.
 * @author Jet Young Lim and Andrew Han for COMP2511 (2/11/19)
 *
 */
public class DrinkPotion implements InteractBehaviour {

	/**
	 * Method checks what happens when a player or other moving entity
	 * moves ontop of a tile with entities that don't move.
	 * The entity is picked up by the player and gives them invincibility
	 * @param movingEntity the entity moving onto a new tile
	 * @param x the x coordinate of the tile that is being moved into
	 * @param y the y coordinate of the tile that is being moved into
	 * @param entity the entity that is being moved into
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void interact(DynamicEntity movingEntity, Integer x, Integer y, StaticEntity entity, DungeonController controller) {
		
		if(!(movingEntity instanceof Player)) {
			return;
		}
		movingEntity.executeMoveBehaviour(x,y, controller);
		// Updates player invincibility and add current potion to backpack 
		Player player = (Player) movingEntity;
		player.addInvincible();
		player.addToBackpack(entity);
		controller.modifyState("potion");
		
		// Removes entity at new tile as it has been used / pick up by player
		Tile[][] tiles = controller.getTiles();
		tiles[x][y].removeEntity(entity);
		controller.removeFromTile(entity);

	}

}
