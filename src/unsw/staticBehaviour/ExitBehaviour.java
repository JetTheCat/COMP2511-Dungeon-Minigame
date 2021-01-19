package unsw.staticBehaviour;

import java.util.List;

import unsw.dungeonComposite.GoalObject;
import unsw.dynamicEntity.DynamicEntity;
import unsw.dynamicEntity.Player;
import unsw.staticEntity.StaticEntity;
import unsw.dungeon.*;

/**
 * A behaviour that blocks or allows passage through the exit.
 * @author Jet Young Lim and Andrew Han for COMP2511 (2/11/19)
 *
 */
public class ExitBehaviour implements InteractBehaviour {

	/**
	 * Method checks what happens when a player or other moving entity
	 * moves ontop of a tile with entities that don't move.
	 * If all goals have been completed, then the exit is opened and the player can pass through.
	 * Otherwise, it acts as a wall.
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
	
		GoalObject obj = null;
		for(GoalObject curr : controller.getGoalList()) {
			if(curr.getGoal().equals("exit")) {
				curr.decreaseState();
				controller.modifyState("exit");
				obj = curr;
				break;
			}
		}
		if(controller.checkGoal()) {
			movingEntity.executeMoveBehaviour(x, y, controller);
			controller.winGame();
		} else {
			obj.increaseState();
			controller.modifyState("exit");
		}
		
		

	}

}
