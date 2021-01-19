package unsw.dynamicBehaviour;

import unsw.dungeon.*;
import unsw.dungeonComposite.*;
import unsw.staticEntity.FloorSwitch;
import unsw.staticEntity.StaticEntity;

/**
 *The entity is heavy enough to trigger floor switches.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class HeavyMass implements Heaviness {

	/**
	 * Method determines whether the entity is heavy enough to trigger floor switches.
	 * Triggers the floor switch on the tile.
	 * @param entity the floor switch
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void checkWeight(StaticEntity entity, DungeonController controller) {
		if (entity instanceof FloorSwitch) {
			FloorSwitch floorSwitch = (FloorSwitch) entity;
			floorSwitch.trigger();
			for(GoalObject curr : controller.getGoalList()) {
				if(curr.getGoal().equals("boulders")) {
					curr.decreaseState();
					controller.modifyState("boulders");
				}
			}
			if(controller.checkGoal()) {
				controller.winGame();
			}
		}
	}

}
