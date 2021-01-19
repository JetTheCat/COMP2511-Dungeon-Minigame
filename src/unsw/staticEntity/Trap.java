package unsw.staticEntity;
import unsw.dynamicEntity.Player;
import unsw.staticBehaviour.StoreItem;
import unsw.staticBehaviour.TrapBehaviour;

public class Trap extends StaticEntity{
	private boolean set;
	
	public Trap(int x, int y, int ID) {
		super(x,y,ID, "/trap_close.png", new TrapBehaviour());
		set = false;
	}
	
	public void setTrap() {
		set = true;
		
	}

	public boolean isSet() {
		return set;
	}
}
