package unsw.staticBehaviour;

import java.util.List;

import unsw.dungeon.*;
import unsw.dynamicEntity.DynamicEntity;
import unsw.dynamicEntity.Player;
import unsw.staticEntity.Portal;
import unsw.staticEntity.StaticEntity;

/**
 * A behaviour that lets players teleport through portals.
 * @author Jet Young Lim and Andrew Han for COMP2511 (2/11/19)
 *
 */
public class Teleport implements InteractBehaviour {

	/**
	 * Method checks what happens when a player or other moving entity
	 * moves ontop of a tile with entities that don't move.
	 * A player will be teleported to the other corresponding portal.
	 * @param movingEntity the entity moving onto a new tile
	 * @param x the x coordinate of the tile that is being moved into
	 * @param y the y coordinate of the tile that is being moved into
	 * @param entity the entity that is being moved into
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void interact(DynamicEntity movingEntity, Integer x, Integer y, StaticEntity entity, DungeonController controller) {
		if (entity instanceof Portal && movingEntity instanceof Player) {
			Portal portal = (Portal) entity;
			portal.teleportEntity(movingEntity, controller);
		}

	}

}
