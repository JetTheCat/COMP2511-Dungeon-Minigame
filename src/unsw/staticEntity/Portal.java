package unsw.staticEntity;

import unsw.dungeon.DungeonController;
import unsw.dungeon.Tile;
import unsw.dynamicEntity.DynamicEntity;
import unsw.staticBehaviour.Teleport;

/**
 * An opening that teleports players to another position.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class Portal extends StaticEntity{
	
	/**
	 * Constructs an entity that will remain still and cannot move from its tile
	 * A portal teleports players to its other corresponding portal.
	 * @param x the x coordinate of the entity
	 * @param y the y coordinate of the entity
	 * @param ID the unique identifier of the static entity
	 */
	public Portal(int x, int y, int ID) {
		super(x, y, ID, "/portal.png", new Teleport());
	}
	
	/**
	 * Method teleports the player to the other portal
	 * @param entity the entity that is being teleported
	 * @param controller the controller for the dungeon
	 */
	public void teleportEntity(DynamicEntity entity, DungeonController controller) {
		Portal linkedPortal = controller.getLinkedPortal(this.getID(), this);
		Integer portalX = linkedPortal.getX();
		Integer portalY = linkedPortal.getY();
		entity.executeMoveBehaviour(portalX, portalY, controller);
	}
}
