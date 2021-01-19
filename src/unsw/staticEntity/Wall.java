package unsw.staticEntity;

import unsw.staticBehaviour.BlockEntity;

/**
 * A barrier preventing movement through it.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class Wall extends StaticEntity {

	/**
	 * Constructs an entity that will remain still and cannot move from its tile
	 * A wall stops movement by any entity.
	 * @param x the x coordinate of the entity
	 * @param y the y coordinate of the entity
	 * @param ID the unique identifier of the static entity
	 */
    public Wall(int x, int y, int ID) {
        super(x, y, ID, "/brick_brown_0.png", new BlockEntity());
    }

}
