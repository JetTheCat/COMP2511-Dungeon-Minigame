package unsw.staticEntity;

import unsw.dynamicEntity.Player;
import unsw.staticBehaviour.EquipItem;

/**
 * A sword that will kill enemies.
 * @author Jet Young Lim and Andrew Han for COMP2511 (1/11/19)
 *
 */
public class Sword extends StaticEntity {
	private Integer uses;
	
	/**
	 * Constructs an entity that will remain still and cannot move from its tile
	 * A sword can be picked up by the player and can kill 5 enemies before breaking.
	 * @param x the x coordinate of the entity
	 * @param y the y coordinate of the entity
	 * @param ID the unique identifier of the static entity
	 */
	public Sword(int x, int y, int ID) {
		super(x,y,ID, "/greatsword_1_new.png", new EquipItem());
		this.uses = 5;
	}
	
	/**
	 * Method reduces the durability of the sword after killing an enemy.
	 * Destroys the sword if there are no more uses left.
	 * @param player the player that is holding this sword
	 */
	public void decreaseDurability(Player player) {
		if(uses > 0) {
			uses--;
		}
		if (uses == 0) {
			player.setHoldingSword(false);
			player.removeFromBackpack(this);
		}
	}
	
	public Integer getDurability() {
		return this.uses;
	}
	
	
}
