package unsw.staticEntity;

import unsw.staticBehaviour.StoreItem;

/**
 * An item that can open doors.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class Key extends StaticEntity {
	
	/**
	 * Constructs an entity that will remain still and cannot move from its tile
	 * A key will open a corresponding door.
	 * @param x the x coordinate of the entity
	 * @param y the y coordinate of the entity
	 * @param ID the unique identifier of the static entity
	 */
	public Key(int x, int y, int ID) {
		super(x,y,ID, "/key.png", new StoreItem());

	}

}
