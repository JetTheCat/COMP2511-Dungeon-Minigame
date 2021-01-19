package unsw.staticEntity;

import sun.tools.tree.ThisExpression;
import unsw.dynamicEntity.Player;
import unsw.staticBehaviour.DoorBehaviour;

/**
 * A door that can block or allow passage through it.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class Door extends StaticEntity {
	private boolean opened;
	
	/**
	 * Constructs an entity that will remain still and cannot move from its tile
	 * A door will be closed and block passage through it.
	 * Can be opened using a corresponding key.
	 * @param x the x coordinate of the entity
	 * @param y the y coordinate of the entity
	 * @param ID the unique identifier of the static entity
	 */
	public Door(int x, int y, int ID) {
		super(x, y, ID, "/closed_door.png", new DoorBehaviour());
		this.opened = false;
	}
		
	
	public boolean isOpened() {
		return opened;
	}
	
	public void openDoor() {
		opened = true;
	}


}
