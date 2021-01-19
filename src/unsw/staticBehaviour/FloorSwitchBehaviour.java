package unsw.staticBehaviour;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.DungeonController;
import unsw.dungeon.Entity;
import unsw.dungeon.Tile;
import unsw.dungeonComposite.GoalObject;
import unsw.dynamicEntity.Boulder;
import unsw.dynamicEntity.DynamicEntity;
import unsw.staticEntity.StaticEntity;

/**
 * A behaviour that determines whether a floor switch is triggered.
 * @author Jet Young Lim and Andrew Han for COMP2511 (2/11/19)
 *
 */
public class FloorSwitchBehaviour implements InteractBehaviour {

	/**
	 * Method checks what happens when a player or other moving entity
	 * moves ontop of a tile with entities that don't move.
	 * Moves the entity ontop of the switch and checks whether they are
	 * heavy enough to trigger it.
	 * @param movingEntity the entity moving onto a new tile
	 * @param x the x coordinate of the tile that is being moved into
	 * @param y the y coordinate of the tile that is being moved into
	 * @param entity the entity that is being moved into
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void interact(DynamicEntity movingEntity, Integer x, Integer y, StaticEntity entity, DungeonController controller){			
		movingEntity.executeMoveBehaviour(x, y, controller);
		movingEntity.executeCheckWeight(entity, controller);
	}

	
	
}
