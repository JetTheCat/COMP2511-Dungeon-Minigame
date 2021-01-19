package unsw.dungeonComposite;

import java.util.List;

/**
 * Composite pattern for the goal.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 */
public interface GoalComponent {
	/**
	 * Method checks if the goal has been completed for the dungeon
	 * @param goalList the list of goals that must be completed
	 * @return true if goals have been completed, false otherwise
	 */
	public boolean checkGoal(List<GoalObject> goalList);
}
