package unsw.staticEntity;

import unsw.staticBehaviour.FloorSwitchBehaviour;

/**
 * A switch on the floor that can be triggered by heavy objects.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class FloorSwitch extends StaticEntity {
	public boolean triggered;
	
	/**
	 * Constructs an entity that will remain still and cannot move from its tile
	 * A floor switch is a part of a goal and needs to be triggered by boulders.
	 * @param x the x coordinate of the entity
	 * @param y the y coordinate of the entity
	 * @param ID the unique identifier of the static entity
	 */
	public FloorSwitch(int x, int y, int ID) {
		super(x, y, ID, "/pressure_plate.png", new FloorSwitchBehaviour());
		triggered = false;
	}
	
	public boolean isTriggered() {
		return triggered;
	}
	
	/**
	 * Sets the floor switch to be triggered.
	 */
	public void trigger() {
		triggered = true;
	}
	
	/**
	 * Untriggers the floor switch.
	 */
	public void unTrigger() {
		triggered = false;
	}
	
}
