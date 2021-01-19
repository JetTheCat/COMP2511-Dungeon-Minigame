package unsw.dynamicEntity;

import unsw.dungeon.DungeonController;
import unsw.dynamicBehaviour.HeavyMass;
import unsw.dynamicBehaviour.NoDirectionMove;
import unsw.dynamicBehaviour.Push;
import unsw.staticBehaviour.*;

/**
 * A boulder in the dungeon, used for triggering switches and blocking.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class Boulder extends DynamicEntity{
	
	/**
	 * Constructs a boulder, which can be pushed onto floor switches and acts as a wall
	 * to other entities other than the player.
     * @param x the x coordinate of the entity
     * @param y the y coordinate of the entity
     * @param id the unique identifier for the entity
	 */
	public Boulder(int x, int y, int ID) {
		super(x,y,ID, "/boulder.png", new HeavyMass(), new Push(), new NoDirectionMove());
	}

	/**
	 * Method checks if the boulder has been pushed into a new position.
	 * @param entity the boulder that is being pushed.
	 * @param x the x coordinate of the position that the boulder is trying to be pushed onto
	 * @param y the y coordinate of the position that the boulder is trying to be pushed onto
	 * @param controller the controller for the dungeon
	 * @return true if the boulder has been pushed into a new position, false otherwise
	 */
	public boolean pushed(DynamicEntity entity, Integer x, Integer y, DungeonController controller) {
		controller.checkBoulderInteraction(entity, x, y, controller);
		return entity.getX() == x && entity.getY() == y;
	}
}
