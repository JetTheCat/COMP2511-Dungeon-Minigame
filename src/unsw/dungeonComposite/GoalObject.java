package unsw.dungeonComposite;

/**
 * A goal object that stores the number of entities that must be killed/collected/triggered.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 */
public class GoalObject {
	public Integer state;
	public Integer originalState;
	public String goal;
	
	/**
	 * Constructs a goal object, which stores the number of entities that must be
	 * killed/collected/triggered
	 * @param state the amount of entities that must be killed/collected/triggered
	 * @param goal the type of goal that is being tracked (treasure/exit/floor switch/enemy)
	 */
	public GoalObject(Integer state, String goal) {
		super();
		this.state = state;
		this.originalState = state;
		this.goal = goal;
	}

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public Integer getOriginalState() {
		return originalState;
	}
	
	/**
	 * Deincrements the number of entities that must be killed/collected/triggered
	 */
	public void decreaseState() {
		this.state--;
	}

	/**
	 * Increments the number of entities that must be killed/collected/triggered
	 */
	public void increaseState() {
		// TODO Auto-generated method stub
		this.state++;
	}
	

}
