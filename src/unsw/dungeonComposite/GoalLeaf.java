package unsw.dungeonComposite;

import java.util.List;

import unsw.dungeon.*;

/**
 * Composite pattern for the goal.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 */
public class GoalLeaf implements GoalComponent {
	String goal;
	
	/**
	 * Constructs a goal leaf, which is a bottom level goal of either
	 * "treasure", "exit", "boulders", or "treasures"
	 * @param goal the bottom level goal that is being created
	 */
	public GoalLeaf(String goal) {
		super();
		this.goal = goal;
	}
	
	/**
	 * Method checks if the goal has been completed for the dungeon
	 * @param goalList the list of goals that must be completed
	 * @return true if goals have been completed, false otherwise
	 */
	@Override
	public boolean checkGoal(List<GoalObject> goalList) {
		for (GoalObject obj  : goalList) {
			
			if(obj.getGoal().equals(goal)) {
				//System.out.println("Goal: " + obj.getGoal() + " state(number): " + obj.getState());
				return obj.getState() == 0;
			}
		}
		return false;
	}

}
