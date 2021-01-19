package unsw.dungeonComposite;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import unsw.dungeon.Dungeon;

/**
 * A Component of the goal.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 */
public class GoalComposite implements GoalComponent {
	String operator;
	ArrayList<GoalComponent> goals = new ArrayList<>();
	
	/**
	 * Constructs a component of the goal.
	 * It can hold more subgoals that are a part of the operator.
	 * For example, if the operator for this class is "AND" and the 
	 * subgoals are "treasure" and "exit", all treasures must be picked up
	 * and the exit must be reached.
	 * @param operator a logic operator (AND or OR)
	 */
	public GoalComposite(String operator) {
		super();
		this.operator = operator;
	}

	/**
	 * Method checks if the goal has been completed for the dungeon
	 * @param goalList the list of goals that must be completed
	 * @return true if goals have been completed, false otherwise
	 */
	@Override
	public boolean checkGoal(List<GoalObject> goalList) {
		boolean result = false;
		switch(operator){
		case("OR"):
			for (GoalComponent goal : goals) {
				result = result || goal.checkGoal(goalList);
			}
			break;
		case("AND"):
			result = true;
			for (GoalComponent goal : goals) {
				result = result && goal.checkGoal(goalList);
			}
			break;
		}
		//System.out.println("Operator: " + operator + " result: " + result);
		return result;
	}
	
	/**
	 * Adds a goal to the list of goals
	 * Adds a GoalComposite, which can consist of smaller subgoals.
	 * @param goal the GoalComposite that is being added
	 */
	public void addGoal(GoalComposite goal) {
		goals.add(goal);
	}
	
	/**
	 * Adds a goal to the list of goals. 
	 * Added using a string of either "exit", "enemies", "boulders", "treasure"
	 * @param string the goal that needs to be added
	 */
	public void addGoal(String string) {
		goals.add(new GoalLeaf(string));
	}

	/**
	 * Method processes the goal and potential subgoals that may need to be added.
	 * @param subgoals the subgoals that are being added
	 * @param dungeon the dungeon that the goals are associated with
	 */
	public void addGoal(JSONArray subgoals, Dungeon dungeon) {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < subgoals.length(); i++) {
			JSONObject json = subgoals.getJSONObject(i);
			String goal = json.getString("goal");
	    	switch (goal) {	
	        case "AND":
	        case "OR":
	        	JSONArray subSubGoals = json.getJSONArray("subgoals");
	        	if (subSubGoals != null) {
	        		GoalComposite operator = new GoalComposite(goal);
	        		operator.addGoal(subSubGoals, dungeon);
	        		goals.add(operator);
	        	}
	        	
	        	break;
	    	
	    	default:
	    		dungeon.createGoalObject(goal);
	    		goals.add(new GoalLeaf(goal));
	    	}
		}
	}

}