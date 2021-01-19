package unsw.dynamicBehaviour;

import unsw.dungeon.*;
import unsw.dungeonComposite.GoalObject;
import unsw.staticEntity.FloorSwitch;
import unsw.staticEntity.StaticEntity;

/**
 *The entity is too light and unable to trigger floor switches.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class LightMass implements Heaviness {

	/**
	 * Method determines whether the entity is heavy enough to trigger floor switches.
	 * Does not trigger the floor switch but makes sure it is untriggered.
	 * @param entity the floor switch
	 * @param controller the controller for the dungeon
	 */
	@Override
	public void checkWeight(StaticEntity entity, DungeonController controller) {
		// Does nothing to trigger the floor switch but makes sure it is in an untriggered state
		if (entity instanceof FloorSwitch) {
			FloorSwitch floorSwitch = (FloorSwitch) entity;
			if(floorSwitch.triggered) {
				floorSwitch.unTrigger();
				for(GoalObject curr : controller.getGoalList()) {
					if(curr.getGoal().equals("boulders")) {
						curr.increaseState();
						controller.modifyState("boulders");
					}
				}
			}
		}
	}

}
