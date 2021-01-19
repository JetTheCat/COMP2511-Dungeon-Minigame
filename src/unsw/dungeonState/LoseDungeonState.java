package unsw.dungeonState;

import unsw.dungeon.Dungeon;

/**
 * A potential lose state for the dungeon
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class LoseDungeonState implements DungeonState {
	private Dungeon dungeon;
	
	/**
	 * Constructs a lose state for the dungeon.
	 * @param dungeon the dungeon that has a lose state created for it
	 */
	public LoseDungeonState(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	/**
	 * Method tries to change the dungeon state to its play state
	 */
	@Override
	public void playGame() {
		// Do nothing because you lost

	}

	/**
	 * Method tries to change the dungeon state to its pause state
	 */
	@Override
	public void pauseGame() {
		// Do nothing because you lost

	}

	/**
	 * Method tries to change the  dungeon state to its lose state
	 */
	@Override
	public void loseGame() {
		// Do nothing because you lost

	}

	/**
	 * Method tries to change the  dungeon state to its win state
	 */
	@Override
	public void winGame() {
		// Do nothing because you lost

	}

}
