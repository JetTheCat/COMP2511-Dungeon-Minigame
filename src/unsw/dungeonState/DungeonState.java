package unsw.dungeonState;

/**
 * The current state of the dungeon.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public interface DungeonState {
	/**
	 * Method tries to change the dungeon state to its play state
	 */
	public void playGame();
	
	/**
	 * Method tries to change the dungeon state to its pause state
	 */
	public void pauseGame();
	
	/**
	 * Method tries to change the  dungeon state to its lose state
	 */
	public void loseGame();
	
	/**
	 * Method tries to change the  dungeon state to its win state
	 */
	public void winGame();
}
