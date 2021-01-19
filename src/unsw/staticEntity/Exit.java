package unsw.staticEntity;

import java.util.ArrayList;
import java.util.List;

import unsw.staticBehaviour.ExitBehaviour;

/**
 * A way out from the dungeon.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class Exit extends StaticEntity{
	private boolean opened;
	
	/**
	 * Constructs an entity that will remain still and cannot move from its tile.
	 * An exit will be closed and block passage until all goals are complete.
	 * It will then allow players to escape the current dungeon and complete the level.
	 * @param x the x coordinate of the entity
	 * @param y the y coordinate of the entity
	 * @param ID the unique identifier of the static entity
	 */
	public Exit(int x, int y, int ID) {
		super(x,y,ID, "/exit.png", new ExitBehaviour());
		this.opened = false;
	}
	
	public boolean isOpened() {
		return opened;
	}
	
	/**
	 * Method opens the exit.
	 */
	public void openExit() {
		opened = true;
	}
	

}
