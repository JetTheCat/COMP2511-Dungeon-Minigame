package unsw.dungeonState;

import unsw.dungeon.Dungeon;

/**
 * A potential pause state for the dungeon
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class PauseDungeonState implements DungeonState {
	Dungeon dungeon;
	
	/**
	 * Constructs a pause state for the dungeon.
	 * @param dungeon the dungeon that has a dungeon state created for it
	 */
	public PauseDungeonState(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	/**
	 * Method tries to change the dungeon state to its play state
	 */
	@Override
	public void playGame() {
		dungeon.setDungeonState(dungeon.getPlayState());

	}

	/**
	 * Method tries to change the dungeon state to its pause state
	 */
	@Override
	public void pauseGame() {
		// Do nothing

	}

	/**
	 * Method tries to change the  dungeon state to its lose state
	 */
	@Override
	public void loseGame() {
		// Do nothing

	}
	
	/**
	 * Method tries to change the  dungeon state to its win state
	 */
	@Override
	public void winGame() {
		// Do nothing

	}

}
