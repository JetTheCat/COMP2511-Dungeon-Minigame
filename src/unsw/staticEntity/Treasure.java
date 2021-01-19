package unsw.staticEntity;

import unsw.staticBehaviour.StoreItem;

/**
 * A pile of gold.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class Treasure extends StaticEntity {
	
	/**
	 * Constructs an entity that will remain still and cannot move from its tile
	 * Treasure can be collected by the player.
	 * @param x the x coordinate of the entity
	 * @param y the y coordinate of the entity
	 * @param ID the unique identifier of the static entity
	 */
	public Treasure(int x, int y, int ID) {
		super(x,y,ID, "/gold_pile.png",new StoreItem());
	}
}
